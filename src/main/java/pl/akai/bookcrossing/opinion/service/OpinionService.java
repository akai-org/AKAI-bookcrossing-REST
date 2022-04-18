package pl.akai.bookcrossing.opinion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akai.bookcrossing.common.database.ResourceEntity;
import pl.akai.bookcrossing.common.database.ResourceRepository;
import pl.akai.bookcrossing.common.service.IllegalUserException;
import pl.akai.bookcrossing.opinion.database.OpinionEntity;
import pl.akai.bookcrossing.opinion.database.OpinionRepository;
import pl.akai.bookcrossing.opinion.dto.OpinionDto;
import pl.akai.bookcrossing.opinion.dto.OpinionRequestDto;
import pl.akai.bookcrossing.opinion.mapper.OpinionEntityToOpinionDtoMapper;
import pl.akai.bookcrossing.opinion.mapper.OpinionRequestDtoToOpinionEntityMapper;
import pl.akai.bookcrossing.security.CurrentUserService;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final ResourceRepository resourceRepository;
    private final CurrentUserService currentUserService;
    private final OpinionEntityToOpinionDtoMapper opinionEntityToOpinionDtoMapper;
    private final OpinionRequestDtoToOpinionEntityMapper opinionRequestDtoToOpinionEntityMapper;


    public Page<OpinionDto> getOpinionsByResourceId(int resourceId, Pageable pageable) {
        if (!resourceRepository.existsById(resourceId)) {
            throw new EntityNotFoundException("Resource");
        }
        return opinionRepository.findAllByResource_Id(resourceId, pageable)
                                .map(opinionEntityToOpinionDtoMapper::from);
    }

    public OpinionDto addOpinion(OpinionRequestDto opinionRequestDto, int resourceId) {
        ResourceEntity resource = resourceRepository.findById(resourceId)
                                                    .orElseThrow(() -> new EntityNotFoundException("Resource"));
        OpinionEntity opinion = opinionRequestDtoToOpinionEntityMapper.from(opinionRequestDto);
        opinion.setResource(resource);
        opinion.setAuthor(currentUserService.getUser());
        OpinionEntity responseOpinion = opinionRepository.save(opinion);
        OpinionDto response = opinionEntityToOpinionDtoMapper.from(responseOpinion);
        response.setResourceId(resourceId);
        return response;

    }

    public OpinionDto updateOpinion(int id, OpinionRequestDto opinionDto, int resourceId) {
        if (!resourceRepository.existsById(resourceId)) {
            throw new EntityNotFoundException("Resource");
        }

        OpinionEntity opinion = opinionRepository.findById(id)
                                                 .orElseThrow(() -> new EntityNotFoundException("Opinion"));

        if (isCurrentUserTheAuthor(opinion)) {
            throw new IllegalUserException();
        }

        opinion.setRating(opinionDto.getRating());
        opinion.setContent(opinion.getContent());
        OpinionEntity responseOpinion = opinionRepository.save(opinion);
        OpinionDto response = opinionEntityToOpinionDtoMapper.from(responseOpinion);
        response.setResourceId(resourceId);
        return response;
    }

    public void deleteOpinion(int id, int resourceId) {
        if (!resourceRepository.existsById(resourceId)) {
            throw new EntityNotFoundException("Resource");
        }

        OpinionEntity opinion = opinionRepository.findById(id)
                                                 .orElseThrow(() -> new EntityNotFoundException("Opinion"));

        if (isCurrentUserTheAuthor(opinion)) {
            throw new IllegalUserException();
        }
        opinionRepository.deleteById(id);

    }

    private boolean isCurrentUserTheAuthor(OpinionEntity opinion) {
        return currentUserService.getUser().getId() != opinion.getAuthor().getId();
    }
}

package pl.akai.bookcrossing.ebook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.akai.bookcrossing.ebook.database.EbookEntity;
import pl.akai.bookcrossing.ebook.database.EbookRepository;
import pl.akai.bookcrossing.ebook.dto.EbookDetailsDto;
import pl.akai.bookcrossing.ebook.dto.EbookListDto;
import pl.akai.bookcrossing.ebook.dto.EbookRequestDto;
import pl.akai.bookcrossing.ebook.mapper.EbookEntityToEbookDetailsDtoMapper;
import pl.akai.bookcrossing.ebook.mapper.EbookEntityToEbookListDtoMapper;
import pl.akai.bookcrossing.tag.service.TagService;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class EbookService {

    private final EbookRepository ebookRepository;
    private final TagService tagService;
    private final EbookEntityToEbookListDtoMapper ebookEntityToEbookListDtoMapper;
    private final EbookEntityToEbookDetailsDtoMapper ebookEntityToEbookDetailsDtoMapper;

    public Page<EbookListDto> getEbooksByPageable(Pageable pageable) {
        return ebookRepository.findAll(pageable)
                              .map(ebookEntityToEbookListDtoMapper::from);
    }

    public EbookDetailsDto getEbookById(int id) {
        return ebookRepository.findById(id)
                              .map(ebookEntityToEbookDetailsDtoMapper::from)
                              .orElseThrow(() -> new EntityNotFoundException("Ebook"));
    }

    public EbookDetailsDto updateEbook(int id, EbookRequestDto ebookDto) {
        EbookEntity book = ebookRepository.findById(id)
                                          .orElseThrow(() -> new EntityNotFoundException("Ebook"));
        book.setAuthor(ebookDto.getAuthor());
        book.setTitle(ebookDto.getTitle());
        book.setDescription(ebookDto.getDescription());
        book.setTags(tagService.getTagEntitiesFromStringList(ebookDto.getTags()));
        EbookEntity responseEbook = ebookRepository.save(book);
        return ebookEntityToEbookDetailsDtoMapper.from(responseEbook);
    }
}

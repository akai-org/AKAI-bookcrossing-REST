package pl.akai.bookcrossing.tag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.akai.bookcrossing.tag.database.TagEntity;
import pl.akai.bookcrossing.tag.database.TagRepository;
import pl.akai.bookcrossing.tag.dto.TagDto;
import pl.akai.bookcrossing.tag.mapper.TagEntityToTagDtoMapper;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagEntityToTagDtoMapper tagEntityToTagDtoMapper;

    public List<TagDto> getAllTags() {
        return tagRepository.findAll()
                            .stream()
                            .map(tagEntityToTagDtoMapper::from)
                            .toList();
    }

    public List<TagEntity> getTagEntitiesFromStringList(List<String> tagNames) {
        List<String> tagNamesNormalized = normalizeTagNames(tagNames);
        List<TagEntity> tagEntities = tagRepository.findAllByNameIn(tagNamesNormalized);
        for (TagEntity tagFromDb : tagEntities) {
            tagNamesNormalized.remove(tagFromDb.getName());
        }
        tagEntities.addAll(createTagNames(tagNamesNormalized));
        return tagEntities;
    }

    private List<String> normalizeTagNames(List<String> tagNames) {
        return tagNames.stream()
                       .map(String::toUpperCase)
                       .map(String::trim)
                       .filter(s -> s.length() > 0)
                       .distinct()
                       .collect(toCollection(ArrayList::new));
    }

    private List<TagEntity> createTagNames(List<String> tagNames) {
        return tagNames.stream()
                       .map(TagEntity::new)
                       .toList();
    }
}

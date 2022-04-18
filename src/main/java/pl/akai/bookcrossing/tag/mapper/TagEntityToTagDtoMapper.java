package pl.akai.bookcrossing.tag.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.tag.database.TagEntity;
import pl.akai.bookcrossing.tag.dto.TagDto;


@Mapper(componentModel = "spring")
public interface TagEntityToTagDtoMapper {

    TagDto from(TagEntity tagEntity);

}
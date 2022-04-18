package pl.akai.bookcrossing.tag.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.tag.database.TagEntity;

@Mapper(componentModel = "spring")
public interface StringToTagEntityMapper {

    TagEntity from(String name);

}

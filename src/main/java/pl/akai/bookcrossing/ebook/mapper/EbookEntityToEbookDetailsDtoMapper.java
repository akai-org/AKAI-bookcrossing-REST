package pl.akai.bookcrossing.ebook.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.ebook.database.EbookEntity;
import pl.akai.bookcrossing.ebook.dto.EbookDetailsDto;
import pl.akai.bookcrossing.tag.mapper.TagEntityToTagDtoMapper;

@Mapper(componentModel = "spring", uses = TagEntityToTagDtoMapper.class)

public interface EbookEntityToEbookDetailsDtoMapper {

    EbookDetailsDto from(EbookEntity ebookEntity);

}

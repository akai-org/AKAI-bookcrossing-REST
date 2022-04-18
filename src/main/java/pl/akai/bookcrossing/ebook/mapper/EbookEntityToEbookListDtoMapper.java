package pl.akai.bookcrossing.ebook.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.ebook.database.EbookEntity;
import pl.akai.bookcrossing.ebook.dto.EbookListDto;
import pl.akai.bookcrossing.tag.mapper.TagEntityToTagDtoMapper;

@Mapper(componentModel = "spring", uses = TagEntityToTagDtoMapper.class)
public interface EbookEntityToEbookListDtoMapper {

    EbookListDto from(EbookEntity bookEntity);

}

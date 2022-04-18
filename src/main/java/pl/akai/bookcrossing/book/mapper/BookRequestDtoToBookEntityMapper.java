package pl.akai.bookcrossing.book.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.book.dto.BookRequestDto;
import pl.akai.bookcrossing.tag.mapper.StringToTagEntityMapper;

@Mapper(componentModel = "spring", uses = StringToTagEntityMapper.class)
public interface BookRequestDtoToBookEntityMapper {

    BookEntity from(BookRequestDto bookDto);

}

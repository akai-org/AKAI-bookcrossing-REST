package pl.akai.bookcrossing.book.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.book.dto.BookDetailsDto;
import pl.akai.bookcrossing.user.mapper.UserEntityToUserDtoMapper;

@Mapper(componentModel = "spring", uses = UserEntityToUserDtoMapper.class)
public interface BookEntityToBookDetailsDtoMapper {

    BookDetailsDto from(BookEntity bookEntity);

}

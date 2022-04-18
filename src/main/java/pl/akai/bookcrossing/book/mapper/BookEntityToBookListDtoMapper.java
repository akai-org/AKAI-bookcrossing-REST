package pl.akai.bookcrossing.book.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.book.dto.BookListDto;
import pl.akai.bookcrossing.user.mapper.UserEntityToUserDtoMapper;

@Mapper(componentModel = "spring", uses = UserEntityToUserDtoMapper.class)
public interface BookEntityToBookListDtoMapper {

    BookListDto from(BookEntity bookEntity);

}

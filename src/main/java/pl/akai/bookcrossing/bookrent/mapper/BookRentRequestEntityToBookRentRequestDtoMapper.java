package pl.akai.bookcrossing.bookrent.mapper;

import org.mapstruct.Mapper;
import pl.akai.bookcrossing.book.mapper.BookEntityToBookListDtoMapper;
import pl.akai.bookcrossing.bookrent.database.BookRentRequestEntity;
import pl.akai.bookcrossing.bookrent.dto.BookRentRequestDto;
import pl.akai.bookcrossing.user.mapper.UserEntityToUserDtoMapper;

@Mapper(componentModel = "spring", uses = {UserEntityToUserDtoMapper.class, BookEntityToBookListDtoMapper.class})
public interface BookRentRequestEntityToBookRentRequestDtoMapper {

    BookRentRequestDto from(BookRentRequestEntity bookRentRequestEntity);

}

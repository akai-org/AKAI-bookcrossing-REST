package pl.akai.bookcrossing.bookrent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.book.dto.BookListDto;
import pl.akai.bookcrossing.user.dto.UserDto;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRentRequestDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("requester")
    private UserDto requester;

    @JsonProperty("book")
    private BookListDto book;

}

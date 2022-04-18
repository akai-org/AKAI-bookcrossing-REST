package pl.akai.bookcrossing.book.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.tag.dto.TagDto;
import pl.akai.bookcrossing.user.dto.UserDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("description")
    private String description;

    @JsonProperty("currentOwner")
    private UserDto currentOwner;

    @JsonProperty("originalOwner")
    private UserDto originalOwner;

    @JsonProperty("isAvailable")
    private boolean available;

    @JsonProperty("tags")
    List<TagDto> tags;

}

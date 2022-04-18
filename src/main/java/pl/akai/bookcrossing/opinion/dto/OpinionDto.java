package pl.akai.bookcrossing.opinion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.user.dto.UserDto;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("resourceId")
    private int resourceId;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("content")
    private String content;

    @JsonProperty("author")
    private UserDto author;

}

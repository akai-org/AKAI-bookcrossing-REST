package pl.akai.bookcrossing.opinion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpinionRequestDto {

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("content")
    private String content;

}

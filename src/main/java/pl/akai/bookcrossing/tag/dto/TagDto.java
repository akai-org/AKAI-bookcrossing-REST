package pl.akai.bookcrossing.tag.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

}

package pl.akai.bookcrossing.ebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.tag.dto.TagDto;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EbookDetailsDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("description")
    private String description;

    @JsonProperty("googleId")
    private String googleId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tags")
    List<TagDto> tags;

}

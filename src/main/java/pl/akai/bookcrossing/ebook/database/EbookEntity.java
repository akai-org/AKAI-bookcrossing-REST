package pl.akai.bookcrossing.ebook.database;

import lombok.*;
import pl.akai.bookcrossing.common.database.ResourceEntity;
import pl.akai.bookcrossing.opinion.database.OpinionEntity;
import pl.akai.bookcrossing.tag.database.TagEntity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ebook")
@DiscriminatorValue("ebook")
public class EbookEntity extends ResourceEntity {

    @Column(name = "google_id", nullable = false)
    private String googleId;

    @Column(name = "url", nullable = false)
    private String url;

    @Builder
    public EbookEntity(Integer id,
                       String title,
                       String author,
                       String description,
                       List<TagEntity> tagList,
                       List<OpinionEntity> opinionList,
                       String googleId,
                       String url) {
        super(id, title, author, description, tagList, opinionList);
        this.googleId = googleId;
        this.url = url;
    }
}

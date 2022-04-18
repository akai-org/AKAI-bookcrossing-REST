package pl.akai.bookcrossing.book.database;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import pl.akai.bookcrossing.bookrent.database.BookRentRequestEntity;
import pl.akai.bookcrossing.common.database.ResourceEntity;
import pl.akai.bookcrossing.opinion.database.OpinionEntity;
import pl.akai.bookcrossing.tag.database.TagEntity;
import pl.akai.bookcrossing.user.database.UserEntity;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name= "book")
@DiscriminatorValue("book")
public class BookEntity extends ResourceEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="current_owner_id", nullable=false)
    private UserEntity currentOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="original_owner_id", nullable=false)
    private UserEntity originalOwner;

    @Column(name = "available", nullable = false)
    private boolean isAvailable;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @Cascade(CascadeType.DELETE)
    private List<BookRentRequestEntity> rentRequests;

    @Builder
    public BookEntity(int id,
                      String title,
                      String author,
                      String description,
                      List<TagEntity> tags,
                      List<OpinionEntity> opinions,
                      UserEntity currentOwner,
                      UserEntity originalOwner,
                      boolean isAvailable) {
        super(id, title, author, description, tags, opinions);
        this.currentOwner = currentOwner;
        this.originalOwner = originalOwner;
        this.isAvailable = isAvailable;
    }
}

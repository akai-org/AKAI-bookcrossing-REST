package pl.akai.bookcrossing.user.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.book.database.BookEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentOwner")
    private List<BookEntity> currentOwnedBooks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "originalOwner")
    private List<BookEntity> originalOwnedBooks;

}

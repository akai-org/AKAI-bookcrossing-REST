package pl.akai.bookcrossing.bookrent.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.akai.bookcrossing.book.database.BookEntity;
import pl.akai.bookcrossing.user.database.UserEntity;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_rent_request")
public class BookRentRequestEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="requester_id", nullable=false)
    private UserEntity requester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id", nullable=false)
    private BookEntity book;

}

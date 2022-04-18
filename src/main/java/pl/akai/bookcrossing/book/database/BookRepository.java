package pl.akai.bookcrossing.book.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    Page<BookEntity> findAllByTags_Id(int id, Pageable pageable);

    Page<BookEntity> findAllByCurrentOwner_Id(int id, Pageable pageable);

    Page<BookEntity> findAllByOriginalOwner_Id(int id, Pageable pageable);

}

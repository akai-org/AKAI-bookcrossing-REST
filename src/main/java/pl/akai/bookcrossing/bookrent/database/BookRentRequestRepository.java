package pl.akai.bookcrossing.bookrent.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRentRequestRepository extends JpaRepository<BookRentRequestEntity, Integer> {

    Page<BookRentRequestEntity> findAllByBook_CurrentOwner_Id(int requester, Pageable pageable);

    boolean existsByBook_IdAndRequester_Id(int bookId, int requesterId);

}

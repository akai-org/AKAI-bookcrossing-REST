package pl.akai.bookcrossing.opinion.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionEntity, Integer> {

    Page<OpinionEntity> findAllByResource_Id(int id, Pageable pageable);

}

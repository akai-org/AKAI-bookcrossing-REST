package pl.akai.bookcrossing.tag.database;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    List<TagEntity> findAllByNameIn(List<String> names);

}

package pl.akai.bookcrossing.common.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {
}

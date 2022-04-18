package pl.akai.bookcrossing.ebook.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EbookRepository extends JpaRepository<EbookEntity, Integer> {

    void deleteAllByGoogleIdIn(Collection<String> googleId);

}

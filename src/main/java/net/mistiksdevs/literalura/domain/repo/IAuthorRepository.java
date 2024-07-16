package net.mistiksdevs.literalura.domain.repo;

import net.mistiksdevs.literalura.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.deathYear < :year")
    List<Author> findByAliveYear(Integer year);
}

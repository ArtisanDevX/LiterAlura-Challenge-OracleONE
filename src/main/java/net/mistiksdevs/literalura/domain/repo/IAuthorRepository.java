package net.mistiksdevs.literalura.domain.repo;

import net.mistiksdevs.literalura.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.deathYear > :year")
    List<Author> findByAliveYear(@Param("year") Integer year);

    @Query("SELECT a FROM Author a WHERE LOWER(a.name) = LOWER(:name)")
    Optional<Author> findByName(@Param("name") String name);
}

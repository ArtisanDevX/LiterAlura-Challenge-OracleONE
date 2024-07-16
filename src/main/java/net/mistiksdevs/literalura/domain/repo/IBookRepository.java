package net.mistiksdevs.literalura.domain.repo;

import net.mistiksdevs.literalura.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findByTitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    Optional<Book> findByAuthor(@Param("author") String author);

    @Query("SELECT b FROM Book b WHERE b.language = :language")
    Optional<Book> findByLanguage(@Param("language") String language);
}
package net.mistiksdevs.literalura.domain.repo;

import net.mistiksdevs.literalura.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    Book findByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE b.language = :language")
    Book findByLanguage(String language);
}

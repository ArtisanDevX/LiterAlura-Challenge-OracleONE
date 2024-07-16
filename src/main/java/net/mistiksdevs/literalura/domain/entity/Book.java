package net.mistiksdevs.literalura.domain.entity;

import jakarta.persistence.*;
import net.mistiksdevs.literalura.network.model.BookData;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
    }

    public Book(BookData bookData, Author author) {
        this.title = bookData.title();
        this.language = bookData.languages().get(0);
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return String.format("""
                ***************************************
                Informacion del Libro:
                - Titulo: %s
                - Lenguaje: %s
                - Autor: %s
                ***************************************
                """, title, language, author.getName());
    }
}

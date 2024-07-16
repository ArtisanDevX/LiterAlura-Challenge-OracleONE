package net.mistiksdevs.literalura.app;

import net.mistiksdevs.literalura.domain.entity.Author;
import net.mistiksdevs.literalura.domain.entity.Book;
import net.mistiksdevs.literalura.domain.repo.IAuthorRepository;
import net.mistiksdevs.literalura.domain.repo.IBookRepository;
import net.mistiksdevs.literalura.network.model.AuthorData;
import net.mistiksdevs.literalura.network.model.BookData;
import net.mistiksdevs.literalura.network.service.GutendexAPI;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private final Scanner input = new Scanner(System.in);
    private final GutendexAPI api = new GutendexAPI();
    private IAuthorRepository authorRepository;
    private IBookRepository bookRepository;

    public void initialize(IAuthorRepository authorRepository, IBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        while (true) {
            printMenu();
            System.out.print("Ingrese una opción: ");
            int option = input.nextInt();
            input.nextLine(); // Consume the newline

            switch (option) {
                case 1:
                    // Add book
                    saveBook();
                    break;
                case 2:
                    // List books
                    listBooks();
                    break;
                case 3:
                    // List authors
                    listAuthors();
                    break;
                case 4:
                    // List alive authors
                    listAliveAuthors();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }

    private void printMenu() {
        String menu = """
                 ***************************
                 **      LiterAlura       **
                 ***************************
                 1- Añadir libro a la base de datos
                 2- Listar los libros guardados
                 3- Listar los autores guardados
                 4- Listar los autores vivos hasta cierta fecha
                 0- Salir
                """;
        System.out.println(menu);
    }

    private void saveBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = input.nextLine();
        Optional<BookData> book = api.getBook(title.toLowerCase());
        if (book.isPresent()) {
            Author author = authorHandler(book.get().authors().get(0));
            Optional<Book> registeredBook = bookRepository.findByTitle(book.get().title());
            if (registeredBook.isPresent()) {
                System.out.println("Libro ya guardado");
            } else {
                Book bookToSave = new Book(book.get(), author);
                bookRepository.save(bookToSave);
                System.out.println("Libro guardado");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
        System.out.print("Presione enter para continuar...");
        input.nextLine();
    }

    private Author authorHandler(AuthorData authorData) {
        Optional<Author> registeredAuthor = authorRepository.findByName(authorData.name());
        if (registeredAuthor.isPresent()) {
            return registeredAuthor.get();
        } else {
            Author authorToSave = new Author(authorData);
            authorRepository.save(authorToSave);
            return authorToSave;
        }
    }

    private void listBooks() {
        // Listing all books on database
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros guardados");

        } else {
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
        System.out.print("Presione enter para continuar...");
        input.nextLine();
    }

    private void listAuthors() {
//        Listing all authors on database
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            System.out.println("No hay autores guardados");
        } else {
            for (Author author : authors) {
                System.out.println(author.toString());
            }
        }
        System.out.print("Presione enter para continuar...");
        input.nextLine();
    }

    private void listAliveAuthors() {
        System.out.print("Ingrese el año: ");
        int year = input.nextInt();
        input.nextLine(); // Consume the newline
//        Listing all alive authors on database
        List<Author> authors = authorRepository.findByAliveYear(year);
        if (authors.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + year);
        } else {
            for (Author author : authors) {
                System.out.println(author.toString());
            }
        }
        System.out.print("Presione enter para continuar...");
        input.nextLine();
    }
}

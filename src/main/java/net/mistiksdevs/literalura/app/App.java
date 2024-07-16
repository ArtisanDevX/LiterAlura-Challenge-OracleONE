package net.mistiksdevs.literalura.app;

import net.mistiksdevs.literalura.network.model.BookData;
import net.mistiksdevs.literalura.network.service.GutendexAPI;

import java.util.Optional;
import java.util.Scanner;

public class App {
    private final Scanner input = new Scanner(System.in);
    private final GutendexAPI api = new GutendexAPI();

    public void Init() {
        printMenu();
        System.out.print("Ingrese una opción: ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                // Add book
                saveBook();
                break;
            case 2:
                // List books
                System.out.println("Listando libros...");
                break;
            case 3:
                // List authors
                System.out.println("Listando autores...");
                break;
            case 4:
                // List alive authors
                System.out.println("Listando autores vivos...");
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción inválida");
                Init();
                break;
        }
    }

    private void printMenu() {
        String menu = """
                                \s
                 ***************************
                 **      LiterAlura       **
                 ***************************
                 1- Añadir libro a la base de datos
                 2- Listar los libros guardados
                 3- Listar los autores guardados
                 4- Listar los autores vivos hasta cierta fecha
                 0- Salir
                                \s
                \s""";
        System.out.println(menu);
    }

    private void saveBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = input.nextLine();
        Optional<BookData> book = api.getBook(title);
        if (book.isPresent()) {
//            System.out.println("Libro encontrado: \n" + book.get());
            // Save book to database

        } else {
            System.out.println("Libro no encontrado");
        }
    }
}

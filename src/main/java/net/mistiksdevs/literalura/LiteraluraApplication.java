package net.mistiksdevs.literalura;

import net.mistiksdevs.literalura.app.App;
import net.mistiksdevs.literalura.domain.repo.IAuthorRepository;
import net.mistiksdevs.literalura.domain.repo.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private IAuthorRepository authorRepository;
	@Autowired
	private IBookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App app = new App();
		app.initialize(authorRepository, bookRepository);
	}
}

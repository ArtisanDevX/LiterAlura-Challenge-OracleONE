package net.mistiksdevs.literalura.network.service;

import net.mistiksdevs.literalura.network.converter.DataConverter;
import net.mistiksdevs.literalura.network.model.BookData;
import net.mistiksdevs.literalura.network.model.ResultsData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class GutendexAPI {
    private final HttpClient client = HttpClient.newHttpClient();
    private final DataConverter converter = new DataConverter();

    public Optional<BookData> getBook(String title) {
        String BASE_URL = "https://gutendex.com/books/?search=";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + title))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (response == null) return Optional.empty();
        ResultsData resultsData = converter.convert(response.body(), ResultsData.class);
        return Optional.ofNullable(resultsData.books().get(0));
    }
}

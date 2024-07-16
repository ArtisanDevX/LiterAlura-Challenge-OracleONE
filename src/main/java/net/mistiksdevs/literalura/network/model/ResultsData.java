package net.mistiksdevs.literalura.network.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultsData(
        @JsonAlias("results") List<BookData> books
) {
}

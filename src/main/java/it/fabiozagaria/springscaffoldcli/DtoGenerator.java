package it.fabiozagaria.springscaffoldcli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DtoGenerator {

    public List<Path> generate(String resourceName, Path outputDirectory, DtoType dtoType) throws IOException {
        validateResourceName(resourceName);

        List<String> classNames = classNamesFor(resourceName, dtoType);
        List<Path> outputFiles = classNames.stream()
                .map(className -> outputDirectory.resolve(className + ".java"))
                .toList();

        for (Path outputFile : outputFiles) {
            if (Files.exists(outputFile)) {
                throw new IllegalArgumentException("Il file esiste già: " + outputFile);
            }
        }

        Files.createDirectories(outputDirectory);
        for (int index = 0; index < outputFiles.size(); index++) {
            Files.writeString(outputFiles.get(index), render(classNames.get(index)));
        }

        return outputFiles;
    }

    private void validateResourceName(String resourceName) {
        if (!resourceName.matches("[A-Z][A-Za-z0-9]*")) {
            throw new IllegalArgumentException(
                    "Il nome della risorsa deve essere PascalCase e contenere solo lettere o numeri."
            );
        }
    }

    private List<String> classNamesFor(String resourceName, DtoType dtoType) {
        return switch (dtoType) {
            case SIMPLE -> List.of(resourceName + "Dto");
            case REQUEST -> List.of(resourceName + "Request");
            case RESPONSE -> List.of(resourceName + "Response");
            case BOTH -> List.of(resourceName + "Request", resourceName + "Response");
        };
    }

    private String render(String className) {
        return "public record " + className + "() {\n}\n";
    }
}

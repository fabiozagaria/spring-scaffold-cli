package it.fabiozagaria.springscaffoldcli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DtoGeneratorTest {

    private DtoGeneratorTest() {
    }

    public static void main(String[] args) throws Exception {
        generatesDtoFile();
        rejectsInvalidResourceName();
        rejectsExistingFile();
        System.out.println("Tutti i test superati.");
    }

    private static void generatesDtoFile() throws Exception {
        Path directory = Files.createTempDirectory("spring-scaffold-cli-test-");
        List<Path> generatedFiles = new DtoGenerator().generate("Expense", directory, DtoType.SIMPLE);

        assertEquals("public record ExpenseDto() {\n}\n", Files.readString(generatedFiles.get(0)));
    }

    private static void rejectsInvalidResourceName() throws Exception {
        Path directory = Files.createTempDirectory("spring-scaffold-cli-test-");
        assertThrows(() -> new DtoGenerator().generate("expense", directory, DtoType.BOTH));
    }

    private static void rejectsExistingFile() throws Exception {
        Path directory = Files.createTempDirectory("spring-scaffold-cli-test-");
        DtoGenerator generator = new DtoGenerator();
        generator.generate("Expense", directory, DtoType.SIMPLE);

        assertThrows(() -> generator.generate("Expense", directory, DtoType.SIMPLE));
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Atteso: " + expected + ", ricevuto: " + actual);
        }
    }

    private static void assertThrows(ThrowingRunnable action) throws Exception {
        try {
            action.run();
            throw new AssertionError("Era attesa un'eccezione.");
        } catch (IllegalArgumentException expected) {
            // Comportamento atteso.
        }
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        void run() throws Exception;
    }
}

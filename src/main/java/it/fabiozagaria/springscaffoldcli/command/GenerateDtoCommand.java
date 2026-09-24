package it.fabiozagaria.springscaffoldcli.command;

import it.fabiozagaria.springscaffoldcli.DtoGenerator;
import it.fabiozagaria.springscaffoldcli.DtoType;
import picocli.CommandLine;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "dto",
        mixinStandardHelpOptions = true,
        description = "Genera DTO Java."
)
public final class GenerateDtoCommand implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", description = "Nome della risorsa in PascalCase.")
    private String resourceName;

    @CommandLine.Option(
            names = {"-o", "--output"},
            defaultValue = "src/generated",
            description = "Directory dei file generati. Default: ${DEFAULT-VALUE}."
    )
    private Path outputDirectory;

    @CommandLine.Option(
            names = "--type",
            defaultValue = "BOTH",
            converter = DtoTypeConverter.class,
            description = "Tipo: ${COMPLETION-CANDIDATES}. Default: ${DEFAULT-VALUE}."
    )
    private DtoType dtoType;

    @Override
    public Integer call() throws Exception {
        List<Path> generatedFiles = new DtoGenerator().generate(resourceName, outputDirectory, dtoType);
        generatedFiles.forEach(file -> System.out.println("Creato: " + file));
        return 0;
    }
}

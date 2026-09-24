package it.fabiozagaria.springscaffoldcli;

import it.fabiozagaria.springscaffoldcli.command.GenerateCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "spring-scaffold",
        mixinStandardHelpOptions = true,
        version = "spring-scaffold 0.1.0",
        description = "Genera codice ripetitivo per API Spring Boot.",
        subcommands = GenerateCommand.class
)
public final class SpringScaffoldCli implements Runnable {

    private SpringScaffoldCli() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SpringScaffoldCli()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
}

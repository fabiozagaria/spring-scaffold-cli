package it.fabiozagaria.springscaffoldcli.command;

import picocli.CommandLine;

@CommandLine.Command(
        name = "generate",
        description = "Genera componenti per una risorsa.",
        subcommands = GenerateDtoCommand.class
)
public final class GenerateCommand implements Runnable {

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
}

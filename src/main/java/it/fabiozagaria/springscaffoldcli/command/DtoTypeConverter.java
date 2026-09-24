package it.fabiozagaria.springscaffoldcli.command;

import it.fabiozagaria.springscaffoldcli.DtoType;
import picocli.CommandLine;

import java.util.Locale;

public final class DtoTypeConverter implements CommandLine.ITypeConverter<DtoType> {

    @Override
    public DtoType convert(String value) {
        try {
            return DtoType.valueOf(value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new CommandLine.TypeConversionException(
                    "Tipo non valido: " + value + ". Usa simple, request, response oppure both."
            );
        }
    }
}

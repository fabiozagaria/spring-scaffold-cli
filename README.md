# Spring Scaffold CLI

Generatore Java di codice ripetitivo per API Spring Boot, pensato per mantenere il codice generato leggibile e modificabile.

## Primo obiettivo

Dato il nome di una risorsa, generare DTO Java validi in una directory scelta dall'utente.

```bash
mvn package
java -jar target/spring-scaffold-cli-0.1.0-SNAPSHOT.jar generate dto Expense
```

Di default genera `src/generated/ExpenseRequest.java` e `src/generated/ExpenseResponse.java`.

```bash
java -jar target/spring-scaffold-cli-0.1.0-SNAPSHOT.jar generate dto Expense --type simple
java -jar target/spring-scaffold-cli-0.1.0-SNAPSHOT.jar generate dto Expense --type request --output src/api/dto
```

## Perché iniziamo dal DTO

È un output piccolo ma verificabile: gestisce naming, filesystem, validazione degli argomenti, template e sottocomandi. Le feature successive riuseranno lo stesso nucleo per entity, repository, service e controller.

## Regole del progetto

- Java 17.
- Il generatore non sovrascrive file esistenti.
- Il codice prodotto deve compilare senza dipendere dalla CLI.
- Ogni comando ha messaggi di errore espliciti e un test.

## Roadmap iniziale

1. `generate dto <Nome> [--type] [--output]`.
2. Validazione del nome e generazione sicura dei file.
3. Test JUnit e gestione degli errori CLI.
4. `generate resource <Nome>` con template coordinati.

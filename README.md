# Spring Scaffold CLI

Generatore Java di codice ripetitivo per API Spring Boot, pensato per mantenere il codice generato leggibile e modificabile.

## Stato e ruolo

**MVP DTO presente; nuove funzionalità sospese.** Questo progetto esplora tooling Java, argomenti CLI, naming, filesystem e template. È distinto da JobFlow, che riguarda l'elaborazione asincrona di job.

Il comando attuale genera record Java vuoti di tipo DTO, request o response. Non genera ancora entity, repository, service o controller.

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

È un output piccolo ma verificabile: gestisce naming, filesystem, validazione degli argomenti, template e sottocomandi. Una generazione coordinata di entity, repository, service e controller resta un'evoluzione possibile, fuori dal MVP corrente.

## Regole del progetto

- Java 17.
- Il generatore controlla l'esistenza dei file di destinazione e rifiuta quelli già presenti; non è documentata una garanzia atomica rispetto a scritture concorrenti.
- Il codice prodotto deve compilare senza dipendere dalla CLI.
- Ogni comando ha messaggi di errore espliciti e un test.

## Funzionalità e verifiche

Sono presenti `generate dto`, selezione del tipo, directory di output, validazione del nome e test JUnit del generatore.

```bash
mvn test
mvn package
```

## Confine del MVP

Generare DTO validi e verificabili senza dipendenza dalla CLI. I record prodotti non contengono ancora campi o mapping del dominio. Il comando `generate resource` non è implementato e non è un'attività attiva.

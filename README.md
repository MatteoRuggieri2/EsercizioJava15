
ScanAutoEnum

La classe deve essere codificata utilizzando le classi di Collection.

```java
Class ScanAutoEnum implements Auto
EnumAuto(String fileName) // Un solo costruttore
```

Leggere un file con informazioni da decodificare.
Il file, per ogni riga, contiene n valori, tutti di lunghezza variabile, separati da space:

```txt
X Y1 Y2 … Yn

X può contenere i valori delle auto gestite
AUDI
FIAT
...
TESLA
WS
```

Y contiene un possibile modello di auto (A4, TIPO etc.) specifico per la fabbrica di auto

Esempio di file

```txt
FIAT PANDA
FIAT TIPO
AUDI QUATTRO
```

 1) Si vuole analizzare il file, scartare le righe con fabbriche di auto non definite e/o di modelli non previsti.
 2) Si vogliono ottenere le informazioni del file in input.
 3) Il problema deve essere risolto a livello DICHIARATIVO senza specifiche IF nel programma, con una Enumeration `EnumAuto`

Enumeration `EnumAuto`

```java
STELLANTIS("TIPO", "PANDA", "500")
, AUDI("A4")
…
, TESLA("T1");
```

4) EnumAuto deve esporre il metodo `String[] getModelli()`
5) Si definisca una Exception AutoFileInputEmpty da lanciare in caso di file vuoto o inesistente
6) Scartare le righe vuote

7) Definire l’interface Auto per esporre le funzionalità richieste

```java
interface Auto
String[] rowsWrong(); // Righe errate as-is
String[] rowsGood(); // Righe corrette
String[] autoProducers() // [FIAT,TESLA,..] ordine crescente
String[] autoProducersDefined(); // FIAT/TESLA/..
boolean existsProducer(String autoProducer)
boolean existsModel(String autoProducer, String autoModel)
```

Enumeration EnumAuto

```java
STELLANTIS("TIPO", "PANDA", "500")
, AUDI("A4")
…
, TESLA("T1");
```
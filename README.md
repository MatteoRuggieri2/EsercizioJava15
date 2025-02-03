# Collection

## ScanAutoEnum

L'esercizio deve essere svolto utilizzando le classi di `Collection`.

Creare la classe `ScanAutoEnum` con relativo JUnit di test `ScanAutoEnumTest`.
Al suo interno inserire un solo costruttore.

```java
class ScanAutoEnum implements Auto {
    ScanAutoEnum(String fileName) // Un solo costruttore
}
```

Leggere il file "*auto-brands.txt*", al suo interno, per ogni riga, sono contenuti ***n*** valori, tutti di lunghezza variabile, separati da spazi.

Esempio di file .txt

```txt
STELLANTIS TIPO PANDA
AUDI QUATTRO
SKODA FABIA
```

### Gestione degli errori

Si definisca una *Exception* `AutoFileInputEmpty` da lanciare in caso di file vuoto o inesistente.

### Output

Stampare le righe del file scartando le righe con fabbriche di auto non definite e/o di modelli non previsti e le righe vuote.

### Consigli

Il problema deve essere risolto a livello **DICHIARATIVO** senza specifiche "if" nel programma, con una Enumeration `EnumAuto`.

## Files

**Auto.java**

```java
public interface Auto {
    String[] rowsWrong(); // Righe errate as-is
    String[] rowsGood(); // Righe corrette
    String[] autoProducers(); // [FIAT,TESLA,..] ordine crescente
    String[] autoProducersDefined(); // FIAT/TESLA/..
    boolean existsProducer(String autoProducer);
    boolean existsModel(String autoProducer, String autoModel);
}
```

**EnumAuto.java**

```java
public enum EnumAuto {
    STELLANTIS("TIPO", "PANDA", "500"),
    AUDI("A4"),
    TESLA("T1");
    
    private String[] arrModello;
    
    private EnumAuto(String ... modello) {
        this.arrModello = modello;
    }
    
    public String[] getModelli() {
        return this.arrModello;
    }
}
```

**auto-brands.txt**

```txt
BMW
STELLANTIS TIPO PANDA 500
AUDI A4 A5 A6
VW
CITROEN
SKODA FABIA
PORSCHE GTRS3 CAYENNE TAYCAN

```

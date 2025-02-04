package esercizi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ScanAutoEnum implements Auto {
	
	//TODO
	/* - guarda specifiche delle liste Collection
	 * - controllare metodi già fatti
	 * - completare metodi non definiti
	 * - fare i test dei metodi */
	
	
	
	String fileLine;
	
	// Array List con tutte le righe del file salvate
	List<String> allFileRows = new ArrayList<String>();
	
	// HashSet con tutti i brand univoci all'interno del file
	Set<String> allFileBrands = new HashSet<String>();
	
	// Elenco righe sbagliate (in base all'enum)
	List<String> allFileWrongRows = new ArrayList<String>();
	
	// Elenco righe corrette (in base all'enum)
	List<String> allFileGoodRows = new ArrayList<String>();
	
	String brand;
	
	public ScanAutoEnum(String fileName) {
		readFile(fileName);
		getBrandFromFileRows(this.allFileRows);
		compareFileRowsWithEnumAuto(this.allFileRows);
		System.out.println("Righe giuste: \n" + allFileGoodRows);
		System.out.println("Righe sbagliate: \n" + allFileWrongRows);
	}

	@Override
	public String[] rowsWrong() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] rowsGood() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] autoProducers() {
		// Non può convertire LinkedHashSet in un array di stringhe
		return this.allFileBrands.toArray(new String[0]);
	}

	@Override
	public String[] autoProducersDefined() {
		// TODO Auto-generated method stub
		return null;
	}

	// Ritorna i produttori che ci sono anche nell'enum
	@Override
	public boolean existsProducer(String autoProducer) {
		// TODO Auto-generated method stub
		return false;
	}

	// Ritorna i modelli che ci sono anche nell'enum
	@Override
	public boolean existsModel(String autoProducer, String autoModel) {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Questa funzione legge il file in base all'indirizzo che gli passiamo in formato stringa
	private void readFile(String fileName) {
		String[] arrItems;
		
		try {
			
			// Apro il file
			FileReader autoBrandsFile = new FileReader(fileName);
			
			// Preparo il file alla lettura
			BufferedReader bufferedReader = new BufferedReader(autoBrandsFile);
			
			while ((this.fileLine = bufferedReader.readLine()) != null) {
				this.allFileRows.add(this.fileLine);
			}
			
		} catch (IOException e) {
			System.err.println("Si è verificato un errore durante la lettura del file: " + e.getMessage());
		}
	
	}
	
	// Questa funzione prende tutti i brand dalle righe del file e li salva
	private void getBrandFromFileRows(List<String> fileRows) {
		
		// Per ogni riga splitto e prendo il primo elemento, poi salvo il brand nell'HashSet "allFileBrand"
		String[] splittedRow;
		
		for (String row : fileRows) {
			
			splittedRow = stringSplitter(row, " +");
			
			// Salvo il brand della riga
			this.allFileBrands.add(splittedRow[0]);
		}
		
		// Stampo tutti i brand
		for (String brand : allFileBrands) {
			System.out.println(brand);
		}
		
	}
	
	// Questa funzione fa il compare tra le righe del file e i valori nell'enum "EnumAuto"
	private void compareFileRowsWithEnumAuto(List<String> fileRows) {
		
		String[] splittedRow;
		String rowBrand = "";
		List<String> rowModels = new ArrayList<String>();
		List<String> currentEnumModels = new ArrayList<String>();
		
		for (String row : fileRows) {
			
			// Controllo se il brand della riga e i modelli della riga corrispondono ad un'enumerazione
			
			// Splitto la riga
			splittedRow = stringSplitter(row, " +");
			
			// Prendo il brand della riga
			rowBrand = splittedRow[0];

			// Prendo i modelli della riga
			rowModels = getAutoModels(splittedRow);
			
			System.out.println("modelli della riga: " + rowModels);
			
			// Per ogni elemento di enum
			for (EnumAuto enumBrandName : EnumAuto.values()) {
				
				// Se il nome del brand e i modelli della riga sono uguali a quelli dell'enum
				if (rowModels.size() > 0 && rowBrand.equals(enumBrandName.name()) && compareRowModelsToEnumModels(rowModels, Arrays.asList(enumBrandName.getModelli()))) {
					// Aggiungo la riga a quelle corrette
					this.allFileGoodRows.add(row);
					continue;
				} else {
					// NON SO PERCHE' VENGONO STAMPATI DOPPIONI
					// Aggiungo la riga a quelle sbagliate
					this.allFileWrongRows.add(row);
				}
				
			}
			
		}

	}
	
	/* Questa funzione prende la lista fornita come argomento e rimuove il primo elemento, ovvero il brand. 
	 * Se ho quindi una riga con solo il brand verrà restituita una lista vuota */
	private List<String> getAutoModels(String[] splittedRow) {
		
		List<String> autoModels = new ArrayList();
		
		for (int i = 1; i < splittedRow.length; i++) {
			autoModels.add(splittedRow[i]);
		}
		
		return autoModels;
	}
	
	// Questa funzione confronta i modelli della riga passata con quelli dell'elemento nell'enum
	private boolean compareRowModelsToEnumModels(List<String> rowModels, List<String> enumModels) {
		return rowModels.equals(enumModels);
	}
	
	// Questa funzione splitta la stringa con lo splitter passato come parametro
	private String[] stringSplitter(String string, String splitter) {
		String[] splittedString;
		
		// Splitto la riga e la ritorno
		splittedString = string.strip().split(" +");
		return splittedString;
	}
	
	
}




















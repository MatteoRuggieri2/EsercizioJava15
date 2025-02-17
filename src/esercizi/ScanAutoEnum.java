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

import exceptions.AutoFileInputEmptyException;

public class ScanAutoEnum implements Auto {
	
	//TODO
	/* - guarda specifiche delle liste Collection
	 * - controllare metodi già fatti
	 * - completare metodi non definiti
	 * - fare i test dei metodi */
	
	
	
	
	String fileLine;
	
	String brand;
	
	// Array List con tutte le righe del file salvate (eccetto quelle vuote)
	List<String> allFileRows = new ArrayList<String>();
	
	// HashSet con tutti i brand univoci all'interno del file
	Set<String> allFileBrands = new LinkedHashSet<String>();
	
	// Elenco righe sbagliate (in base all'enum)
	List<String> allFileWrongRows = new ArrayList<String>();
	
	// Elenco righe corrette (in base all'enum)
	List<String> allFileGoodRows = new ArrayList<String>();
	
	
	public ScanAutoEnum(String fileName) throws AutoFileInputEmptyException {
		readFile(fileName);
		getBrandFromFileRows(this.allFileRows);
		compareFileRowsWithEnumAuto(this.allFileRows);
		System.out.println("Righe giuste: \n" + this.allFileGoodRows);
		System.out.println("Righe sbagliate: \n" + this.allFileWrongRows);
	}

	// Questo metodo ritorna le righe errate del file
	@Override
	public String[] rowsWrong() {
		// TODO Auto-generated method stub
		return null;
	}

	// Questo metodo ritorna le righe corrette del file
	@Override
	public String[] rowsGood() {
		// TODO Auto-generated method stub
		return null;
	}

	// Questo metodo ritorna tutti i produttori di auto contenuti nel file
	@Override
	public String[] autoProducers() {
		/* Di base toArray() senza parametri converte in un array di Object,
		mettendo invece un array di String come argomento, grazie ai generics
		ritorna un array di String */
		return this.allFileBrands.toArray(new String[0]);
	}

	/* Questo metodo ritorna tutti i produttori definiti
	 * all'interno dell'enumerazione "EnumAuto" */
	@Override
	public String[] autoProducersDefined() {
		String autoProducersDefined = "";
		for (EnumAuto enumProducer : EnumAuto.values()) {
			autoProducersDefined += enumProducer.name() + " ";
		}
		return autoProducersDefined.split(" ");
	}

	// Questo metodo ritorna "true" se il produttore passato esiste, altrimenti "false"
	@Override
	public boolean existsProducer(String autoProducer) {
		return EnumAuto.checkBrand(autoProducer);
	}

	// Questo metodo ritorna "true" se il produttore e il modello passati esistono, altrimenti "false"
	@Override
	public boolean existsModel(String autoProducer, String autoModel) {
		
		if (!existsProducer(autoProducer)) {
			return false;
		}
		
		EnumAuto enumProducer = EnumAuto.valueOf(autoProducer.toUpperCase());
		String[] producerModels = enumProducer.getModelli();
		
		for (String model : producerModels) {
			if (autoModel.equalsIgnoreCase(model)) {
				return true;
			}
		}
		
		return false;
	}
	
	/* Questa funzione legge il file in base al path che gli passiamo,
	salva ogni riga come elemento di this.allFileRows eccetto quelle vuote. */
	public void readFile(String fileName) throws AutoFileInputEmptyException {
		
		boolean emptyFile = true;
		
		try {
			
			// Apro il file
			FileReader autoBrandsFile = new FileReader(fileName);
			
			// Preparo il file alla lettura
			BufferedReader bufferedReader = new BufferedReader(autoBrandsFile);
			
			while ((this.fileLine = bufferedReader.readLine()) != null) {
				if (!this.fileLine.trim().isEmpty()) {
					emptyFile = false;
					this.allFileRows.add(this.fileLine);
				}
			}
			
			bufferedReader.close();
			
			if (emptyFile) {
				throw new AutoFileInputEmptyException("Il file analizzato risulta vuoto.");
			}
			
		} catch (IOException e) {
			throw new AutoFileInputEmptyException("Si è verificato un errore durante la lettura del file: " + e.getMessage());
		}
	
	}
	
	// Questa funzione prende tutti i brand dalle righe del file e li salva
	private void getBrandFromFileRows(List<String> fileRows) {
		
		// Per ogni riga splitto e prendo il primo elemento, poi salvo il brand nell'HashSet "allFileBrand"
		String[] splittedRow;
		
		for (String row : fileRows) {
			
			splittedRow = row.split(" +");
			
			// Salvo il brand della riga
			this.allFileBrands.add(splittedRow[0]);
		}
		
	}
	
	
	// TODO -> CHECK
	// Questa funzione fa il compare tra le righe del file e i valori nell'enum "EnumAuto"
	private void compareFileRowsWithEnumAuto(List<String> fileRows) {
		
		String[] splittedRow;
		String rowBrand = "";
		String[] rowModels;
		
		for (String row : fileRows) {
			
			// Controllo se il brand della riga e i modelli della riga corrispondono ad un'enumerazione
			
			splittedRow = row.split(" +");
			rowBrand = splittedRow[0];
			rowModels = getAutoModels(splittedRow);
			
			// Controllo se esiste il brand
			existsProducer(rowBrand);
			
			// Controllo se tutti i modelli esistono in quel brand
			checkBrandModels(rowBrand, rowModels);
			
			
			
			
			
			
//			// Per ogni elemento di enum
//			for (EnumAuto enumBrandName : EnumAuto.values()) {
//				
//				// Se il nome del brand e i modelli della riga sono uguali a quelli dell'enum
//				if (rowModels.length > 0 && rowBrand.equals(enumBrandName.name()) && compareRowModelsToEnumModels(rowModels, enumBrandName.getModelli())) {
//					// Aggiungo la riga a quelle corrette
//					this.allFileGoodRows.add(row);
//					continue;
//				} else {
//					// NON SO PERCHE' VENGONO STAMPATI DOPPIONI
//					// Aggiungo la riga a quelle sbagliate
//					this.allFileWrongRows.add(row);
//				}
//				
//			}
			
		}

	}
	
	/* Questa funzione prende la lista fornita come argomento e rimuove il primo elemento, ovvero il brand. 
	 * Se ho quindi una riga con solo il brand verrà restituita una lista vuota */
	protected String[] getAutoModels(String[] splittedRow) {
		
		List<String> autoModels = new ArrayList<String>();
		
		for (int i = 1; i < splittedRow.length; i++) {
			autoModels.add(splittedRow[i]);
		}
		
		/* "toArray()" senza parametri ritorna un Object[] perché Java non può determinare
		automaticamente il tipo specifico dell'array in fase di runtime.
		"toArray(T[] a)" permette di specificare il tipo dell'array, e se la dimensione
		dell'array passato è troppo piccola, ne verrà creato uno nuovo con la dimensione corretta. */
		return autoModels.toArray(new String[0]);
	}
	
//	// Questa funzione confronta i modelli della riga passata con quelli dell'elemento nell'enum
//	protected boolean compareRowModelsToEnumModels(String[] rowModels, String[] enumModels) {
//		return rowModels.equals(enumModels);
//	}
	
	/* Questo metodo, oltre a verificare l'esistenza del brand passato come parametro
	 * verifica che quel produttore produca tutti i modelli forniti nell'array.
	 * Se produce tutti i modelli passati ritornerà TRUE, altrimenti FALSE. */
	protected boolean checkBrandModels(String brand, String[] models) {
		if (!existsProducer(brand)) { return false; }
		
		EnumAuto brandEnum = EnumAuto.valueOf(brand.toUpperCase());
		
		for (String model : models) {
			if (!brandEnum.isModelDefined(model)) {
				return false;
			}
		}
		
		return true;
	}
	
}




















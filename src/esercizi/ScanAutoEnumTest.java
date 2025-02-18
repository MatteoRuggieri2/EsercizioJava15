package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.AutoFileInputEmptyException;

class ScanAutoEnumTest {
	
	ScanAutoEnum scanAutoEnum;
	String autoBrandsFileName = "src/text_files/auto-brands.txt";
	String blankAutoBrandsFileName = "src/text_files/auto-brands-only-spaces.txt";
	String emptyAutoBrandsFileName = "src/text_files/auto-brands-empty.txt";
	String autoBrandsWrongFileName = "src/text_files/wrong-file-name.txt";

	

	@BeforeEach
	void setUp() throws Exception {
		System.err.println("\n--> SET UP - JUnit");
		scanAutoEnum = new ScanAutoEnum(autoBrandsFileName);
	}
	
	@Test
	void autoFileInputEmptyExceptionTest() {
		
		assertThrows(AutoFileInputEmptyException.class, () -> {
			scanAutoEnum = new ScanAutoEnum(blankAutoBrandsFileName);
		}, "AutoFileInputEmptyException: Il file analizzato risulta vuoto.");
		
		assertThrows(AutoFileInputEmptyException.class, () -> {
			scanAutoEnum = new ScanAutoEnum(emptyAutoBrandsFileName);
		}, "AutoFileInputEmptyException: Il file analizzato risulta vuoto.");
		
		assertThrows(AutoFileInputEmptyException.class, () -> {
			scanAutoEnum = new ScanAutoEnum(autoBrandsWrongFileName);
		}, "AutoFileInputEmptyException: Si è verificato un errore durante"
				+ " la lettura del file: src/text_files/wrong-file-name.txt"
				+ " (No such file or directory)");
	}
	
	@Test
	void autoProducersDefinedTest() {
		
		String[] expectedAutoProducers = {
				"STELLANTIS",
				"AUDI",
				"TESLA",
		};
		
		assertArrayEquals(expectedAutoProducers, scanAutoEnum.autoProducersDefined());
	}
	
	@Test
	void readFileTest() {
		
		String[] expectedRows = {
				"BMW",
				"STELLANTIS TIPO PANDA 500",
				"AUDI A4 A5 A6",
				"VW",
				"CITROEN",
				"SKODA FABIA",
				"PORSCHE GTRS3 CAYENNE TAYCAN",
				"TESLA T1",
		};
		
		assertArrayEquals(scanAutoEnum.allFileRows.toArray(), expectedRows);
	}

	@Test
	void existsProducerTest() {
		assertTrue(scanAutoEnum.existsProducer("Stellantis"));
		assertTrue(scanAutoEnum.existsProducer("stellantis"));
		assertTrue(scanAutoEnum.existsProducer("STELLANTIS"));
		assertTrue(scanAutoEnum.existsProducer("Audi"));
		assertTrue(scanAutoEnum.existsProducer("audi"));
		assertTrue(scanAutoEnum.existsProducer("AUDI"));
		assertTrue(scanAutoEnum.existsProducer("Tesla"));
		assertTrue(scanAutoEnum.existsProducer("tesla"));
		assertTrue(scanAutoEnum.existsProducer("TESLA"));
		assertFalse(scanAutoEnum.existsProducer("Bmw"));
		assertFalse(scanAutoEnum.existsProducer("bmw"));
		assertFalse(scanAutoEnum.existsProducer("BMW"));
	}
	
	@Test
	void existsModelTest() {
		assertTrue(scanAutoEnum.existsModel("Stellantis", "Tipo"));
		assertTrue(scanAutoEnum.existsModel("stellantis", "tipo"));
		assertTrue(scanAutoEnum.existsModel("STELLANTIS", "TIPO"));
		assertFalse(scanAutoEnum.existsModel("Stellantis", "600"));
		assertFalse(scanAutoEnum.existsModel("Mercedes", "Panda"));
		assertFalse(scanAutoEnum.existsModel("Porsche", "Taycan"));
	}
	
	@Test
	void getBrandFromFileRowsTest() {
		
		String[] expectedBrands = {
				"BMW",
				"STELLANTIS",
				"AUDI",
				"VW",
				"CITROEN",
				"SKODA",
				"PORSCHE",
				"TESLA",
		};
		
		assertArrayEquals(scanAutoEnum.allFileBrands.toArray(), expectedBrands);
	}
	
	@Test
	void getAutoModelsTest() {
		String[] splittedRow = {
				"PORSCHE",
				"GTRS3",
				"CAYENNE",
				"TAYCAN",
		};
		
		String[] expectedModels = {
				"GTRS3",
				"CAYENNE",
				"TAYCAN",
		};
		
		assertArrayEquals(expectedModels, scanAutoEnum.getAutoModels(splittedRow));
	}
	
	@Test
	void checkBrandModelsTest() {
		
		String[] modelsArr = {
				"Tipo",
				"Panda",
				"500",
		};
		
		assertTrue(scanAutoEnum.checkBrandModels("Stellantis", modelsArr));
		assertFalse(scanAutoEnum.checkBrandModels("Tesla", modelsArr));
		assertFalse(scanAutoEnum.checkBrandModels("Renault", modelsArr));
	}
	
	@Test
	void rowAnalyzerTest() {
		
		String[] expectedGoodRows = {
				"STELLANTIS TIPO PANDA 500",
				"TESLA T1",
		};
		
		String[] expectedWrongRows = {
				"BMW",
				"AUDI A4 A5 A6",
				"VW",
				"CITROEN",
				"SKODA FABIA",
				"PORSCHE GTRS3 CAYENNE TAYCAN",
		};
		
		assertArrayEquals(expectedGoodRows, scanAutoEnum.allFileGoodRows.toArray());
		assertArrayEquals(expectedWrongRows, scanAutoEnum.allFileWrongRows.toArray());
	}
	
	@Test
	void rowAnalyzerTest2() {
		
		String[] fileRowsTest = {
				"FERRARI",
				"AUDI A4 A5 A6",
				"CITROEN",
				"TESLA T1",
				"LAMBORGHINI URACAN",
				"PORSCHE GTRS3 CAYENNE TAYCAN",
				"PAGANI",
		};
		
		String[] expectedGoodRows = {
				"TESLA T1",
		};
		
		String[] expectedWrongRows = {
				"FERRARI",
				"AUDI A4 A5 A6",
				"CITROEN",
				"LAMBORGHINI URACAN",
				"PORSCHE GTRS3 CAYENNE TAYCAN",
				"PAGANI",
		};
		
		scanAutoEnum.allFileGoodRows.clear();
		scanAutoEnum.allFileWrongRows.clear();
		scanAutoEnum.rowsAnalyzer(Arrays.asList(fileRowsTest));
		assertArrayEquals(expectedGoodRows, scanAutoEnum.allFileGoodRows.toArray());
		assertArrayEquals(expectedWrongRows, scanAutoEnum.allFileWrongRows.toArray());
	}

}

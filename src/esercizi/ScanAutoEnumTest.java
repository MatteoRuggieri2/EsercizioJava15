package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScanAutoEnumTest {
	
	static ScanAutoEnum scanAutoEnum;
	static String autoBrandsFileName = "src/text_files/auto-brands.txt";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		scanAutoEnum = new ScanAutoEnum(autoBrandsFileName);
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

}

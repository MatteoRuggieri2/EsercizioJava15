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

}

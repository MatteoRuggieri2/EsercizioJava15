package esercizi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ScanAutoEnumTest {
	
	static ScanAutoEnum scanAutoEnum;
	static String autoBrandsFileName = "src/text_files/auto-brands.txt";

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		scanAutoEnum = new ScanAutoEnum(autoBrandsFileName);
//	}

	@Test
	void testAutoProducers() {
		scanAutoEnum = new ScanAutoEnum(autoBrandsFileName);
	}

}

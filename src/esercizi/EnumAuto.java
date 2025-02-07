package esercizi;

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
	
	/* Questo metodo controlla che il brand sia incluso nell'enumerazione "EnumAuto" */
	public boolean checkBrand(String brand) {
		for (EnumAuto enumBrand : EnumAuto.values()) {
			if (enumBrand.name().equals(brand)) {   // Con .name() prendo il nome dell'enumerazione
				return true;
			}
		}
		
		return false;
	}
	
	// Metodo che controlli l'esistenza del modello nel brand
}

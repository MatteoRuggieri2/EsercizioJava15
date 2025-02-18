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
	static public boolean checkBrand(String brand) {
		for (EnumAuto enumBrand : EnumAuto.values()) {
			if (enumBrand.name().equalsIgnoreCase(brand)) {   // Con .name() prendo il nome dell'enumerazione
				return true;
			}
		}
		
		return false;
	}
	
	/* Questo metodo ritorna un valore booleano se trova il modello
	 * passato come parametro all'interno dell'enumerazione corrente.
	 * es. Se passo "TIPO" e l'enum corrente è "STELLANTIS" tornerà "true" */
	public boolean isModelDefined(String model) {
		
		for (String modello : arrModello) {
			if (modello.equalsIgnoreCase(model)) {
				return true;
			}
		}
		
		return false;
	}
}

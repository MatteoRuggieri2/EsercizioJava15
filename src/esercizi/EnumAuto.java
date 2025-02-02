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
}

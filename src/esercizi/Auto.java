package esercizi;

public interface Auto {
	String[] rowsWrong(); // Righe errate as-is
	String[] rowsGood(); // Righe corrette
	String[] autoProducers(); // [FIAT,TESLA,..] ordine crescente
	String[] autoProducersDefined(); // FIAT/TESLA/..
	boolean existsProducer(String autoProducer);
	boolean existsModel(String autoProducer, String autoModel);
}

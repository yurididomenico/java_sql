import java.util.HashMap;

public class Anagrafica {
	
	HashMap<String, String> utenti = new HashMap<String, String>();
	
	String codicefiscale;
	String nome;
	
	// ----- SETTERS & GETTERS ----- //
	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale.toUpperCase();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCodicefiscale() {
		return codicefiscale;
	}

	public String getNome() {
		return nome;
	}
	
	// ----- CONSTRUCTOR ----- //
	Anagrafica() {}
	
	// ----- METHODS ----- //
	public String inserisciAnagrafica() {
		
		if (!utenti.containsKey(getCodicefiscale())) {
			utenti.put(getCodicefiscale(), main.PrimaMaiuscola(getNome()));
			return "OK";
		}
		
		return "KO";
	}

	public String cercaNome() {
		
		for (HashMap.Entry<String, String> utente : utenti.entrySet()) {
	        if (utente.getKey().equals(getCodicefiscale())) {
	        	return utente.getValue();
	        }
	    }
		
		return "KO";
	}
	
	public String cancellaAnagrafica() {
		if (utenti.containsKey(codicefiscale)) {
			utenti.remove(codicefiscale);
			return "OK";
		}

		return "KO";
	}
	
	public int numeroPersone() {
		return utenti.size();
	}
	
	public void stampaNomi() {
		for (HashMap.Entry<String, String> utente : utenti.entrySet()) {
	        System.out.println(utente.getKey() + " -> " + utente.getValue());
	    }
	}
}

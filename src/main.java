import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		try {
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/anagrafica_schema",
					"root",
					"A6ced1d6!!"
			);

			Statement statement = connection.createStatement();

			corniceTitolo("Anagrafica");

			menu(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	// ----- MENU ----- //
	public static void menu(Statement statement) {

		Scanner scan = new Scanner(System.in);

		int scelta = 0;

		do {
			System.out.println("Menu:\n");

			System.out.println(
					"\t1 – Inserisci anagrafica\r\n"
					+ "\t2 – Cerca nome\r\n"
					+ "\t3 – Cancella anagrafica\r\n"
					+ "\t4 – Stampa numero di persone inserite\r\n"
					+ "\t5 – Stampa elenco anagrafico \r\n"
					+ "\t6 – Esci");

			System.out.print("\nScegli: ");

			try {
                scelta = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n⚠ Valori consentiti: 1 -> 6 ⚠\n");
                scan.nextLine();
                continue;
            }

			System.out.println();

			switch (scelta) {
				case 1 -> inserisciAnagrafica(statement);
				case 2 -> cercaNome(statement);
				case 3 -> cancellaAnagrafica(statement);
				case 4 -> numeroPersone(statement);
				case 5 -> stampaNomi(statement);
				case 6 -> {
					System.out.println("\n" + "-".repeat(50));
					System.out.println("\nEsci");
				}
				default -> System.out.println("⚠ Valori consentiti: 1 -> 6 ⚠");
			}

			System.out.println("\n" + "-".repeat(50));
		} while(scelta != 6);
	}

	// ----- METODI STRINGHE ----- //
	public static void corniceTitolo(String title) {
		System.out.println("-".repeat(title.length()));
		System.out.println(title);
		System.out.println("-".repeat(title.length()) + "\n");
	}

	public static String PrimaMaiuscola(String stringa) {
		if (stringa == null || stringa.isEmpty()) {
			return stringa;
		}

		return stringa.substring(0, 1).toUpperCase() + stringa.substring(1).toLowerCase();
	}

	// ----- METODI MANIPOLAZIONE ----- //

	public static void inserisciAnagrafica(Statement statement) {
		Scanner scan = new Scanner(System.in);

		corniceTitolo("Inserisci Anagrafica");

		System.out.print("Codice Fiscale: ");
		String cod_fis = scan.nextLine().toUpperCase();

		System.out.print("Nome: ");
		String username = scan.nextLine();

		inserisciUtente(statement, cod_fis, username);

		System.out.println();
	}

	public static void cercaNome(Statement statement) {
		Scanner scan = new Scanner(System.in);

		String title = "Cerca nome";
		main.corniceTitolo(title);

		System.out.print("Inserire il Codice Fiscale: ");


	}

	public static void cancellaAnagrafica(Statement statement) {
		Scanner scan = new Scanner(System.in);

		String title = "Cancella anagrafica";
		main.corniceTitolo(title);

		System.out.print("Inserire il Codice Fiscale: ");

	}

	public static void numeroPersone(Statement statement) {
		String title = "Stampa numero di persone inserite";
		main.corniceTitolo(title);

	}

	public static void stampaNomi(Statement statement) {
		String title = "Stampa elenco anagrafico";
		main.corniceTitolo(title);

	}

	public static void inserisciUtente(Statement statement, String cod_fis, String username) { // Da spostare
		try {
			String checkuser_query = "SELECT * FROM utenti WHERE codice_fiscale = '" + cod_fis + "'";
			ResultSet resultSet = statement.executeQuery(checkuser_query);
			if (resultSet.next()) {
				System.out.println("L'utente con codice fiscale " + cod_fis + " è già presente nel db.");
			} else {
				String insertQuery = "INSERT INTO utenti (codice_fiscale, username) VALUES ('" + cod_fis + "', '" + username + "')";
				statement.executeUpdate(insertQuery);
				System.out.println("Utente inserito con successo!");
			}
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento nella tabella:");
			e.printStackTrace();
		}
	}
}
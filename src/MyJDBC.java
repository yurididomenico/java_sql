import java.sql.*;
import java.util.Scanner;

public class MyJDBC {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/login_schema",
                    "root",
                    "A6ced1d6!!"
            );
            Statement statement = connection.createStatement();

//            cornice("Inserimento utente");
//
//            System.out.print("Nome: ");
//            String nome_utente = scanner.nextLine();
//            System.out.print("Password: ");
//            String password_utente = scanner.nextLine();
//
//            inserisciUtente(statement, nome_utente, password_utente);

//            svuotaTabella(statement);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("username") + " - ");
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void svuotaTabella(Statement statement) {
        try {
            String delete = "TRUNCATE TABLE users";
            statement.executeUpdate(delete);  // Usa executeUpdate per eseguire l'operazione
            System.out.println("Tabella svuotata con successo!");
        } catch (SQLException e) {
            System.out.println("Errore durante lo svuotamento della tabella:");
            e.printStackTrace();  // Stampa l'errore per il debugging
        }
    }

    public static void inserisciUtente(Statement statement, String utente, String password) {
        try {
            String insertQuery = "INSERT INTO users (username, password) VALUES ('" + utente + "', '" + password + "')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            System.out.println("Errore durante l'inserimento nella tabella:");
            e.printStackTrace();
        }
    }

    public static void cornice(String messaggio) {
        System.out.println(
                "-".repeat(messaggio.length()) + "\n" +
                messaggio + "\n" +
                "-".repeat(messaggio.length())
                );
    }
}

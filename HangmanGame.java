import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

public class HangmanGame {
    private static int punktestand = 0;
    private static int spieleGewonnen = 0;
    private static Instant startZeit;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startZeit = Instant.now();

        while (true) {
            System.out.println("\n--- hauptmenü ---");
            System.out.println("1. spiel starten");
            System.out.println("2. erklärungen anzeigen");
            System.out.println("3. spiel verlassen");
            System.out.print("bitte wähle eine option: ");

            String eingabe = scanner.nextLine();

            switch (eingabe) {
                case "1":
                    System.out.print("schwierigkeitsgrad wählen (easy / hard): ");
                    String level = scanner.nextLine().toLowerCase();
                    HangmanRound spiel = new HangmanRound(level);
                    boolean gewonnen = spiel.start();
                    if (gewonnen) {
                        punktestand++;
                        spieleGewonnen++;
                    }
                    break;

                case "2":
                    erklaerungenAnzeigen();
                    break;

                case "3":
                    beenden();
                    return;

                default:
                    System.out.println("ungültige eingabe.");
            }
        }
    }

    private static void erklaerungenAnzeigen() {
        System.out.println("\n--- erklärungen ---");
        System.out.println("hangman ist ein wortspiel.");
        System.out.println("du musst das geheime wort erraten, indem du buchstaben eingibst.");
        System.out.println("bei jedem falschen buchstaben verlierst du einen versuch.");
        System.out.println("du hast 6 versuche. bei null verlierst du.");
        System.out.println("bei gewinn bekommst du einen punkt.");
    }

    private static void beenden() {
        Instant endeZeit = Instant.now();
        Duration dauer = Duration.between(startZeit, endeZeit);
        long minuten = dauer.toMinutes();
        long sekunden = dauer.getSeconds() % 60;

        System.out.println("\nspiel wird beendet...");
        System.out.println("gesamtspielzeit: " + minuten + " minuten und " + sekunden + " sekunden");
        System.out.println("punkte: " + punktestand);
        System.out.println("spiele gewonnen: " + spieleGewonnen);
        System.out.println("danke fürs spielen!");
        scanner.close();
    }
}

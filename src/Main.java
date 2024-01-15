import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hangman {
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String RESET = "\033[0m";  // Text Reset
    private static String word = null;
    private static String hyphen = null;
    private static int count = 0;
    private final static boolean GET_WORD_FROM_FILE = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        word = getRandomWord();
        hyphen = new String(
                new char[word.length()]
        ).replace("\0", "-");
//        System.out.println(word);
        System.out.println("Welcome to Hangman");

        while (count < 7 && hyphen.contains("-")) {
            System.out.println("Your word now looks like this: " + hyphen);
            System.out.println("You have a " + (7 - count) + " guesses left.");

            String guessWord = sc.next();
            System.out.println("Your guess: " + BLUE_BOLD + guessWord + RESET);

            guessWord = guessWord.toUpperCase();
            hang(guessWord);
        }

        System.out.println("The word was: " + word);
        sc.close();
    }

    public static String getRandomWord() {
        String[] words = new String[]{};

        if (!GET_WORD_FROM_FILE) {
            words = new String[];

        } else {
            BufferedReader reader;

            try {
                reader = new BufferedReader(new FileReader("HangmanWordsList.txt"));
                List<String> listOfStrings = new ArrayList<String>();
                String line = reader.readLine();

                while (line != null) {
                    listOfStrings.add(line);
                    line = reader.readLine();
                }

                reader.close();

                words = listOfStrings.toArray(new String[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        return words[(int) (Math.random() * words.length)].toUpperCase();
        return words[level][(int) (Math.random() * words[level].length)];
    }

    public static void hang(String guessWord) {
        StringBuilder newHyphen = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessWord.charAt(0)) {
                newHyphen.append(guessWord.charAt(0));
            } else if (hyphen.charAt(i) != '-') {
                newHyphen.append(word.charAt(i));
            } else {
                newHyphen.append("-");
            }
        }

        if (hyphen.contentEquals(newHyphen)) {
            count++;

            System.out.println("There are no " + guessWord + "'s in the word.");

//            hangmanImage();

            if (count == 7) {
                System.out.println("Your man is hung.");
            }
        } else {
            hyphen = newHyphen.toString();
            System.out.println("Correct guess!");
        }

        if (hyphen.equals(word)) {
            System.out.println("Victory!");
        }
    }

    public static void hangmanImage() {
        if (count == 1) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("___|___");
            System.out.println();
        }
        if (count == 2) {
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 3) {
            System.out.println("   ____________");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   | ");
            System.out.println("___|___");
        }
        if (count == 4) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 5) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |           |");
            System.out.println("   |           |");
            System.out.println("   |");
            System.out.println("___|___");
        }
        if (count == 6) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |          /|\\");
            System.out.println("   |              ");
            System.out.println("___|___           ");
        }
        if (count == 7) {
            System.out.println("   ____________");
            System.out.println("   |          _|_");
            System.out.println("   |         /   \\");
            System.out.println("   |        |     |");
            System.out.println("   |         \\_ _/");
            System.out.println("   |          _|_");
            System.out.println("   |         / | \\");
            System.out.println("   |          / \\ ");
            System.out.println("___|___      /   \\");
        }
    }
}
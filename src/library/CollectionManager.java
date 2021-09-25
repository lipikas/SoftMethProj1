package library;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Creates a Collection of Albums based on the Input Stream.
 *
 * @author Lipika
 */

public class CollectionManager {

    public static final int ADD_MAX_COUNT = 4;
    public static final int MAX_SIZE = 2;

    /**
     * Given input, scans for method names and calls respective add, remove, lend, & etc methods for albums.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Collection Manager starts running.");
        Collection list = new Collection();

        while (scan.hasNext()) {
            String input = scan.nextLine();
            if(isWhiteSpace(input)) continue;
            if (input.length() <= MAX_SIZE) {
                int val = commandCheck(input, list);// checks for invalid commands
                if (val == 1) break; // breaks for quit command
                else continue; // Invalid and 1 char case
            }
            StringTokenizer token = new StringTokenizer(input, ",");
            if (token.countTokens() <= 1) {
                System.out.println("Invalid command!");
                continue;
            }

            String method = token.nextToken(); // more than 1 token
            inputVerification(method, token, list);
        }
    }

    /**
     * Checks if input is a newline, tab, or space.
     *
     * @param input is input strean given by user
     * @return true if input is a Whitespace, otherwise return false
     */
    private boolean isWhiteSpace(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c != ' ' && c != '\n' && c != '\r' && c != '\t')  return false;
        }
        return true;
    }

    /**
     * Given input, calls respective methods and checks for invalid commands.
     *
     * @param input is args[0] in the input given by user.
     * @return 1 if quit is called, -1 if Collection is empty or Invalid command occurs.
     */
    private int commandCheck(String input, Collection list) {
        if (input.compareTo("Q") == 0) { // quit command
            System.out.println("Collection Manager terminated.");
            return 1; // break
        } else if (input.compareTo("P") == 0) { // print
            return checkPrint(list);
        } else if (input.compareTo("PD") == 0) { // print by date
            return checkPrintByDate(list);
        } else if (input.compareTo("PG") == 0) { // print by genre
            return checkPrintByGenre(list);
        } else {
            System.out.println("Invalid command!");
            return -1; // continue
        }
    }

    /**
     * Given input, calls respective album methods to create album obj and execute other Album methods
     *
     * @param method Refers to method name.
     * @param token  Input stream.
     */
    private void inputVerification(String method, StringTokenizer token, Collection list) {
        if (method.compareTo("A") == 0 && token.countTokens() == ADD_MAX_COUNT) { // add album
            if (checkAddMethod(token, list)) return;
        } else if (token.countTokens() == MAX_SIZE) {
            if (method.compareTo("D") == 0) { // delete album
                deleteCheck(token, list);
            } else if (method.compareTo("L") == 0) {// lend method
                lendCheck(token, list);
            } else if (method.compareTo("R") == 0) {// return album
                returnCheck(token, list);
            } else {
                System.out.println("Invalid command!");
            }
        } else {
            System.out.println("Invalid command!");
        }
    }

    /**
     * Checks if Album exists in Collection.
     *
     * @param list  - Refers to Collection object.
     * @param album - Refers to Album object.
     * @return true if Album exists or else return false.
     */
    private boolean checkAlbumExists(Collection list, Album album) {
        for (int i = 0; i < list.getAlbumNumber(); i++) {
            if (list.getAlbums()[i].equals(album)) return true;
        }
        return false;// album is not in collection
    }

    /**
     * Checks if album exists in collection, otherwise adds album to Collection
     *
     * @param token is input given by user.
     * @param list  is the Collection obj which refers to Album []
     * @return true if date is invalid, return false otherwise
     */
    private boolean checkAddMethod(StringTokenizer token, Collection list) {
        String title = token.nextToken();
        String artist = token.nextToken();// defines tokens
        String genre = token.nextToken();
        String releaseDate = token.nextToken();

        Date date = new Date(releaseDate);
        if (!date.isValid()) {
            System.out.println("Invalid Date!");
            return true;
        }// invalid date
        Album album = new Album(title, artist, genre, releaseDate);
        String albumDetails = album.toString();
        if (!list.add(album)) System.out.println(albumDetails + " >> is already in the collection."); // adds album
        else System.out.println(albumDetails + " >> added.");
        return false;
    }

    /**
     * Checks if album is not in collection, otherwise deletes album from Collection
     *
     * @param token is input given by user.
     * @param list  is the Collection obj which refers to Album []
     */
    private void deleteCheck(StringTokenizer token, Collection list) {
        String title = token.nextToken();
        String artist = token.nextToken();
        if (list.getAlbumNumber() > 0) {
            Album album = new Album(title, artist);

            if (list.remove(album)) System.out.println(title + "::" + artist + " >> deleted.");
            else System.out.println(title + "::" + artist + " >> is not in the collection.");
        } else {
            System.out.println("The collection is empty!");
        }
    }

    /**
     * Checks if album has been lent, otherwise lends the album.
     *
     * @param token is input given by user.
     * @param list  is the Collection obj which refers to Album []
     */
    private void lendCheck(StringTokenizer token, Collection list) {
        if (list.getAlbumNumber() > 0) {
            String title = token.nextToken();
            String artist = token.nextToken();

            if (list.lendingOut(new Album(title, artist))) { // lends the album
                System.out.println(title + "::" + artist + " >> lending out and set to not available.");
            } else if (!checkAlbumExists(list, new Album(title, artist))) { // album not in collection
                System.out.println(title + "::" + artist + " >> is not in the collection.");
            } else { // album is being lent out
                System.out.println(title + "::" + artist + " >> is not available.");
            }
        } else {
            System.out.println("The collection is empty!");
        }
    }

    /**
     * Checks if album has been returned or not.
     *
     * @param token is input given by user
     * @param list  is the Collection obj which refers to Album []
     */
    private void returnCheck(StringTokenizer token, Collection list) {
        if (list.getAlbumNumber() > 0) {
            String title = token.nextToken();
            String artist = token.nextToken();
            if (list.returnAlbum(new Album(title, artist))) { // returns album
                System.out.println(title + "::" + artist + " >> returning and set to available.");
            } else if (!checkAlbumExists(list, new Album(title, artist))) { // not in collection
                System.out.println(title + "::" + artist + " >> is not in the collection.");
            } else { // album was never lent
                System.out.println(title + "::" + artist + " >> return cannot be completed.");
            }
        } else {
            System.out.println("The collection is empty!");
        }
    }

    /**
     * Checks if collection isn't empty and prints albums.
     *
     * @param list is the Collection obj which refers to Album []
     * @return -1 if empty collection, return 0 otherwise
     */
    private int checkPrint(Collection list) {
        if (list.getAlbumNumber() > 0) {
            System.out.println("*List of albums in the collection.");
            list.print();
            System.out.println("*End of list");
        } else {
            System.out.println("The collection is empty!");
            return -1;
        }
        return 0;
    }

    /**
     * Checks if collection isn't empty and prints albums by genre.
     *
     * @param list is input given by user
     * @return -1 if empty collection, return 0 otherwise
     */
    private int checkPrintByGenre(Collection list) {
        if (list.getAlbumNumber() > 0) {
            System.out.println("*Album collection by genre.");
            list.printByGenre();
            System.out.println("*End of list");
        } else {
            System.out.println("The collection is empty!");
            return -1;
        }
        return 0;
    }

    /**
     * Checks if collection isn't empty and prints albums by release date.
     *
     * @param list is input given by user
     * @return -1 if empty collection, return 0 otherwise
     */
    private int checkPrintByDate(Collection list) {
        if (list.getAlbumNumber() > 0) {
            System.out.println("*Album collection by the release dates.");
            list.printByReleaseDate();
            System.out.println("*End of list");
        } else {
            System.out.println("The collection is empty!");
            return -1;
        }
        return 0;
    }
}
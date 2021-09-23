package library;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Creates a Collection of Albums based on the Input Stream.
 *
 * @author Lipika
 */

public class CollectionManager {

    public static final int addMaxCount = 4;
    public static final int methodMaxCount = 3;
    public static final int addOutOfBound = 3;
    public static final int addLessThanMax = 2;
    public static final int minInputSize = 2;

    /**
     * Given input, scans for method names and calls respective add, remove, lend, & etc methods for albums.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Collection Manager starts running.");
        Collection list = new Collection();

        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.length() <= minInputSize) {
                int val = commandCheck(input, list);// checks for invalid commands
                if (val == 1) break; // breaks for quit command
                else continue; // Invalid and 1 char case
            }
            StringTokenizer token = new StringTokenizer(input, ",");
            if (token.countTokens() <= 1) {
                System.out.println("Invalid command! 2");
                continue;
            }

            String method = token.nextToken(); // more than 1 token
            inputMethodVerificaiton(method, token, list);
        }
    }

    /**
     * Given input, calls reseptvie methods and checks for invalid commands.
     *
     * @param input Input given my user.
     * @return 1 if Quit is called, -1 if Collection is empty or Invalid command occurs. Otherwise returns 0.
     */
    private int commandCheck(String input, Collection list) {
        if (input.compareTo("Q") == 0) { // quit command
            System.out.println("Collection Manager terminated.");
            return 1; // break
        } else if (input.compareTo("P") == 0) { // print
            return checkPrint(input, list);
        } else if (input.compareTo("PD") == 0) { // print by date
            if (list.getAlbumNumber() > 0) {
                System.out.println("*Album collection by the release dates.");
                System.out.println("*End of list");
            } else {
                System.out.println("The collection is empty!");
                return -1;
            }
        } else if (input.compareTo("PG") == 0) { // print by genre
            return checkPrintGenre(input, list);
        } else {
            System.out.println("Invalid command! 1");
            return -1; // continue
        }
        return 0;
    }

    /**
     * Given input, it calls respective album methods to create album obj and execute other Album methods
     *
     * @param method Refers to method name.
     * @param token  Input stream.
     * @return true if invalid command else false
     */
    private void inputMethodVerificaiton(String method, StringTokenizer token, Collection list) {
        if (method.compareTo("A") == 0 && token.countTokens() == addMaxCount) { // add album
            if (checkAddMethod(token, list)) return;
        } else if (token.countTokens() == methodMaxCount) {
            if (method.compareTo("D") == 0) { // delete album
                deleteCheck(token, list);
            } else if (method.compareTo("L") == 0) {// lend method
                lendCheck(token, list);
            } else if (method.compareTo("R") == 0) {// return album
                lendCheck(token, list);
            } else {
                System.out.println("Invalid command! 4");
            }
        } else {
            System.out.println("Invalid command! 3");
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
    //            if (list2.countTokens() > addOutOfBound || list2.countTokens() < addLessThanMax) {
//                System.out.println("Invalid Date!");
//                return;
//            }

//            int dateList[] = new int[3];
//            int i = 0;
//            while (i < dateList.length) { // checks if fields are integers
//                try {
//                    dateList[i] = Integer.parseInt(list2.nextToken());
//                    i++;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid Date!");
//                    break;
//                }
//            }
//            if (i != dateList.length) return; // caught exception

    private boolean checkAddMethod(StringTokenizer token, Collection list) {
        String title = token.nextToken();
        String artist = token.nextToken();// defines tokens
        String genre = token.nextToken();
        String releaseDate = token.nextToken();

        StringTokenizer list2 = new StringTokenizer(releaseDate, "/");
        Date date = new Date(releaseDate);
        if (!date.isValid()) {
            System.out.println("Invalid Date!");
            return true;
        }// invalid date
        Album album = new Album(title, artist, genre, releaseDate);
        if (!list.add(album)) System.out.println(album.toString() + " >> is already in the collection."); // adds album
        else System.out.println(album.toString() + " >> added.");
        return false;
    }

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

    private int checkPrint(String input, Collection list) {
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

    private int checkPrintGenre(String input, Collection list) {
        if (list.getAlbumNumber() > 0) {
            System.out.println("*Album collection by the release dates.");
            System.out.println("*End of list");
        } else {
            System.out.println("The collection is empty!");
            return -1;
        }
        return 0;
    }
}
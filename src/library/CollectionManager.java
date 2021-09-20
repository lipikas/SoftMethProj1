package library;

import java.util.Scanner;
import java.util.StringTokenizer;

/**Creates a Collection of Albums based on the Input Stream
 * @author Lipika
 */
public class CollectionManager {
    /**
     * Given input, scans for method names and calls respective add, remove, lend, & etc methods for albums.
     */
    public void run() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Collection Manager starts running.");
        Collection list = new Collection();

        while(scan.hasNext()){
            String input = scan.next();
            int val = commandCheck(input);
            if (val == 1 && input.length() <= 2) break; // checks for null & Invalid commands
            if (val == -1) continue; // Invlaid and null case

            StringTokenizer token = new StringTokenizer(scan.next(), ",");
            String method = token.nextToken();
            if (checkMethod(method,token)) continue;
        }

    }

    /**
     * Given input, calls reseptvie methods and checks for invalid commands.
     * @param input Input given my user.
     * @return 1 if Quit is called, -1 if Collection is empty or Invalid command occurs. Otherwise returns 0.
     */
    private int commandCheck(String input){
        if(input.compareTo("Q")==0){
            System.out.println("Collection Manager terminated.");
            return 1; // break
        }
        else if (input.compareTo("P")==0){
            // print
        }
        else if (input.compareTo("PD")==0){
            // print by date
        }
        else if (input.compareTo("PG")==0){
            // print by genre
        }
        else {
            System.out.println("Invalid command!");
            return -1; // continue
        }
        return 0;
    }

    /**
     * Given input, it calls respective album methods to create album obj and execute other Album methods
     * @param method Refers to method name.
     * @param token Input stream.
     * @return true if invalid command else false
     */
    private boolean checkMethod(String method, StringTokenizer token){
        if (method.compareTo("A") == 0 && token.countTokens() == 5){
            // add method
        }
        else if (token.countTokens() == 3){
            if (method.compareTo("D") == 0){
                // delete Album
            }
            else if (method.compareTo("L")==0){
                // lend method
            }
            else if (method.compareTo("R")==0){
                // return album
            }
        }
        else {
            System.out.println("Invalid command!");
            return true;// need to continue
        }
        return false;
    }
}
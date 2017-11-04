import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import db.Database;

public class Main {
    private static final String EXIT = "exit";
    private static final String PROMPT = "> ";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Database db = new Database();
        String input;
        while (true) {
            //get input
            System.out.print(PROMPT);
            input = in.readLine();

            if (input != null && input.toLowerCase().equals(EXIT)) { //exit case
                break;
            } else { //pass to db
                //output database response
                System.out.print(db.transact(input));
            }
        }

        in.close();
    }
}

package third_week.PatternRecognition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InteractionsWithFile {
    // read all information from file and return String which contains that information
    public static String readFileToString (String fileName) {
        String readedFromFile = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            StringBuilder sb = new StringBuilder();
            do {
                line = br.readLine();
                if(line == null)
                    break;
                if(sb.length() != 0) // save new line symbol 
                 sb.append("\n");
                sb.append(line);
            } while(true);

            readedFromFile = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return readedFromFile;
    }
}

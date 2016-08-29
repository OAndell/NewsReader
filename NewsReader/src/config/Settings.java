package config;

import java.io.*;

/**
 * Created by Oscar on 2016-07-04.
 */
public class Settings {

    public static String[] readFile(){
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/config/subreddits.txt")));
            InputStream filepath = Settings.class.getResourceAsStream("/src/config/subreddits.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            String line;
            String[] lines = new String[2];
            for (int i = 0;(line = reader.readLine()) != null; i++) {
                lines[i] = line;
            }
            reader.close();
            return lines;
        } catch (IOException e) {
            System.out.println("No file found");
        }
        return null;
    }
}

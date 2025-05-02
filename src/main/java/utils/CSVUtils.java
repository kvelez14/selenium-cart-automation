package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    private static final String FILE_PATH = "testdata/signup_users.csv";

    public static void writeUser(String email, String password) {
        try {
            // Get the absolute path to your testdata folder
            String projectRoot = System.getProperty("user.dir");  // This is usually the root of your project
            File dir = new File(projectRoot + File.separator + "testdata");

            if (!dir.exists()) {
                dir.mkdirs(); // Create folder if it doesn't exist
            }

            // Now create the file inside that folder
            FileWriter writer = new FileWriter(dir + File.separator + "signup_users.csv", true);
            writer.append(email).append(",").append(password).append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String[]> readUsers(){
        List<String[]> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null){
                users.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}

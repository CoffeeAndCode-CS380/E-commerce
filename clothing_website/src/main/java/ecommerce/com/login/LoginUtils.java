package ecommerce.com.login;

import javax.imageio.IIOException;
import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * this is a utility class for all the operations related to login. such as, saving login information in a text file
 * generating random user ID, checking for passwords, checking for existing usernames, validating username and password
 */
public class LoginUtils {
    private static final String CHARS  = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * this method saves user credentials along with the date accessed in a text file named "usersInfo.txt" if they are valid.
     * valid represents the case that username is not already taken or the password matches the inputted username if taken before.
     * @param userName
     * @param passWord
     * @return true if the credentials are valid and false if not
     */
    public static boolean saveLoginInfo(String userName, String passWord){
        String time = getCurrentTime();
        File f = new File("usersInfo.txt");
        if (f.exists() && !f.isDirectory()){ //check if the file exists
            if (!doesUserNameExist(userName, f)){ //if the username does not already exist in the file
                String userID = generateUserID();
                writeInfoToFile(userName, passWord, userID, time, f); // save it to file
                return true;
            } else{ //if the file and the username exist
                if(doPasswordsMatch(userName, passWord, f)){ //if username exists and passwords match
                    getExistingUserId(userName, f); // if the username exists, get the existing ID from file.
                }
            }
        } else { //if the file doesn't exist that will be the first user in the file.
            String userID = generateUserID();
            writeInfoToFile(userName, passWord, userID, time, f);
            return true;
        }
        return false;
    }

    /**
     * this method checks if the inputted password matches the one already in the file for the case that a user is logging back in
     * @param userName
     * @param passWord
     * @param f
     * @return true if input matches set password otherwise false
     */
    public static boolean doPasswordsMatch(String userName, String passWord, File f){
        for (String[] entry : allUserEntries(f)){
            if (entry[1].trim().equals(passWord.trim())){ // if the username exists, check if the passwords match
                return entry[1].trim().equals(passWord.trim());
            }
        }
        return false; //passwords do not match
    }

    /**
     * this methods returns the userId if the username exists in the file.
     * @param userName
     * @param f
     * @return userid if the username is in the file otherwise returns null
     */
    public static String getExistingUserId(String userName, File f){
        for (String[] entry : allUserEntries(f)){
            if (entry[0].trim().equals(userName.trim())){ //trim ignores the spaces
                return entry[2]; //the username already exists in the file
            }//checks if the username already exists
        }
        return null; //usernames do not match
    }

    /**
     * this method places all the inputs by user in the text file and separates them with comma
     * @param f
     * @return a list of user entries
     */
    public static List<String[]> allUserEntries(File f){
        List<String[]> userEntries = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null) { //reading the file line by line
                String[] parts = line.split(",");
                userEntries.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userEntries;
    }

    /**
     * this method checks if the username already exists in the text file
     * @param userName
     * @param f
     * @return true if the username already exists otherwise false
     */
    public static boolean doesUserNameExist(String userName, File f){
        for (String[] entry : allUserEntries(f)){
            if (entry[0].trim().equals(userName.trim())){ //trim ignores the spaces
                return true; //the username already exists in the file
            }//checks if the username already exists
        }
        return false;
    }

    /**
     * this method writes user information to the text file
     * @param userName
     * @param passWord
     * @param userID
     * @param time
     * @param f
     */
    private static void writeInfoToFile(String userName, String passWord, String userID, String time, File f){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(userName + "," + passWord + "," + userID + "," + time + "\n");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this method looks through the file and generates random unique ID for each user
     * @return id
     */
    private static String generateUserID(){ //needs to check in the file if that ID already exits
        File f = new File("usersInfo.txt");

        // 1) collect existing IDs
        Set<String> existing = new HashSet<>();
        if (f.exists()) {
            for (String[] entry : allUserEntries(f)) {
                if (entry.length > 2) {
                    existing.add(entry[2].trim());
                }
            }
        }

        // 2) loop until we get a new 6-char ID
        String id;
        do {
            StringBuilder sb = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
            }
            id = sb.toString();
        } while (existing.contains(id));
        return id;
    }

    /**
     * this method checks if the username meets the requirements.
     * requirements are having at least on letter in the username
     * @param userName
     * @return boolean
     */
    public static boolean isUserNameValid(String userName){
        // String regex = "\".*[a-zA-Z]+.*";
        String regex1=".*[a-zA-Z]+.*";
        // Pattern pattern = Pattern.compile(regex);
        //  Matcher matcher = pattern.matcher(userName);
        boolean isValid = userName.matches(regex1);

        return isValid;
    }

    /**
     * this method checks if the password is at least 6 char long
     * @param passWord
     * @return true if the password is 6 character or longer
     */
    public static boolean validatePassword (String passWord){
        return passWord.length() >= 6;
    }

    /**
     * this methods get the time that the user logged in.
     * @return the accessed time formatted
     */
    private static String getCurrentTime(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //giving the date a pattern
        String formattedTime = time.format(formatter); //converting that pattern to String
        return formattedTime; // the method should return a String
    }

    /*public static boolean isUserNameTaken(String userName){
        File f = new File("usersInfo.txt");
        return f.exists() && doesUserNameExist(userName, f);
    }*/
}

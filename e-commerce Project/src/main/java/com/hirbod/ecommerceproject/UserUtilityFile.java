package com.hirbod.ecommerceproject;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserUtilityFile {
    public static boolean saveLoginInfo(String username, String passWord){
        String time = getCurrentTime();
        File f = new File("usersInfo.txt");
        if (f.exists() && !f.isDirectory()){ //check if the file exists
            if (doesUserNameExist(username, f) == false){ //if the username does not already exist in the file
                writeInfoToFile(username, passWord, time, f); // save it to file
                return true;
            }
        } else { // if the file does not exist make one and write there
            //if the file does not exist, the first input will be the first data in the file
            //so, we don't need to check if it already exists in the file.
            writeInfoToFile(username, passWord, time, f);
            return true;
        }
        return false;
    }

    private static boolean doesUserNameExist(String userName, File f){ //checks if the username already exists
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null){ //reading the file line by line
                String[] parts = line.split(","); //Splitting each part by a comma. username, password, loginDate
                if (userName.trim().equals(parts[0])){ //trim ignores the spaces
                    return true; //the username already exists in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // the username does not exist
    }

    private static void writeInfoToFile(String username, String passWord, String time, File f){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(username + "," + passWord + "," + time + "\n");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String getCurrentTime(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //giving the date a pattern
        String formattedTime = time.format(formatter); //converting that pattern to String
        return formattedTime; // the method should return a String
    }
}

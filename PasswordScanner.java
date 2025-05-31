
package week13;

import java.io.*;
import java.util.*;

public class PasswordScanner {

    public static void main(String[] args) {
        ArrayList<String> valid = new ArrayList<>();
        ArrayList<String> invalid = new ArrayList<>();

        // Load passwords
        ArrayList<String> passwords = openList("pwd.txt");

        for (String currentpass : passwords) {
            if (hasUpper(currentpass) && hasLower(currentpass) && hasNumber(currentpass) && hasSpecial(currentpass)
                    && currentpass.length() >= 8) {
                valid.add(currentpass);
            } else {
                invalid.add(currentpass);
            }
        }

        saveItems(valid, "validpwd.txt");
        saveItems(invalid, "invalidpwd.txt");
    }

    public static ArrayList<String> openList(String filename) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            String currentLine;
            while ((currentLine = buffer.readLine()) != null) {
                data.add(currentLine.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
        return data;
    }

    public static boolean hasUpper(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) return true;
        }
        return false;
    }

    public static boolean hasLower(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isLowerCase(c)) return true;
        }
        return false;
    }

    public static boolean hasNumber(String input) {
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }

    public static boolean hasSpecial(String input) {
        Set<Character> special = new HashSet<>(Arrays.asList('@', '#', '%', '-', '&', '*'));
        for (char c : input.toCharArray()) {
            if (special.contains(c)) return true;
        }
        return false;
    }

    public static void saveItems(ArrayList<String> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String line : data) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + filename);
        }
    }
}

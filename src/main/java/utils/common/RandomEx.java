package utils.common;

import java.util.*;

public class RandomEx {

    public static List<String> invalidPhoneNumberList() {

        return null;
    }

    public static String randomStringNotNumber(int strLength) {

        // Define the character pool
        String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Create an instance of the Random class
        Random random = new Random();

        // StringBuilder to build the random string
        StringBuilder randomString = new StringBuilder(strLength);

        // Populate the StringBuilder with random characters from the pool
        for (int i = 0; i < strLength; i++) {
            int randomIndex = random.nextInt(charPool.length());
            randomString.append(charPool.charAt(randomIndex));
        }

        // Convert StringBuilder to String
        return randomString.toString();
    }

    public static String randomSpecialCharacter(int strLength) {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
        Random random = new Random();
        StringBuilder randomChars = new StringBuilder();

        for (int i = 0; i < strLength; i++) {
            int randomIndex = random.nextInt(specialCharacters.length());
            randomChars.append(specialCharacters.charAt(randomIndex));
        }

        return randomChars.toString();
    }

    public static String randomNumber(int strLength) {
        String specialCharacters = "0123456789";
        Random random = new Random();
        StringBuilder randomChars = new StringBuilder();

        for (int i = 0; i < strLength; i++) {
            int randomIndex = random.nextInt(specialCharacters.length());
            randomChars.append(specialCharacters.charAt(randomIndex));
        }

        return randomChars.toString();
    }

    //* Default phone number starts from 0
    public static String generateRandomPhone(int strLength, boolean isValid) {

        String[] excludedArray = {"86", "96", "97", "98", "32", "33", "34", "35", "36", "37", "38", "39", "88", "91", "94", "81", "82", "83", "84", "85", "89", "90", "93", "70", "76", "77", "78", "79", "92", "56", "58", "99", "59", "87"};
        Set<String> excludedPrefixes = new HashSet<>();

        Collections.addAll(excludedPrefixes, excludedArray);

        if (strLength < 4) {
            throw new IllegalArgumentException("The length of the phone number must be greater than 4");
        }

        Random random = new Random();
        String prefix = excludedArray[random.nextInt(excludedArray.length)];

        if (!isValid) {
            do {
                prefix = String.format("%02d", random.nextInt(100)); // 2-digit number
            } while (excludedPrefixes.contains(prefix));
        }

        // Minus the first 0
        int remainingLength = strLength - prefix.length() - 1;

        if (remainingLength < 0) {
            throw new IllegalArgumentException("Invalid phone number length with prefix");
        }

        StringBuilder remainingNumbers = new StringBuilder();
        for (int i = 0; i < remainingLength; i++) {
            remainingNumbers.append(random.nextInt(10));
        }

        return "0" + prefix + remainingNumbers;
    }

    public static List<String> randomPassword(String value) {

        List<String> invalidPasswords = new ArrayList<>();
        invalidPasswords.add(value.toUpperCase());
        invalidPasswords.add(value.toLowerCase());

        return invalidPasswords;
    }
}

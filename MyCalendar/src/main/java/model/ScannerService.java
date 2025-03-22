package model;

import java.util.Scanner;

public class ScannerService {
    private static Scanner scanner = new Scanner(System.in);

    public static void resetScannerService() {
        scanner = new Scanner(System.in);
    }

    public static String escapedNextLine() {
        String input = scanner.nextLine();
        StringBuilder escapedString = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                escapedString.append("\\");
            }
            escapedString.append(c);
        }
        return escapedString.toString();
    }
}

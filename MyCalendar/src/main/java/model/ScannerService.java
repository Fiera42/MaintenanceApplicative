package model;

import java.util.Scanner;

public class ScannerService {
    private static Scanner scanner = new Scanner(System.in);

    public static void resetScannerService() {
        scanner = new Scanner(System.in);
    }

    public static String nextLine() {
        return scanner.nextLine();
    }
}

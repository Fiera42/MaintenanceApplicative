package view;

import model.ScannerService;

public class InputHelper {
    /**
     * Ask the user for a numeric input, retry until the input is correct
     * @param text The displayed text
     * @param min The minimum value (included)
     * @param max The maximum value (included)
     * @return The user input
     */
    public static int askInputRange(String text, int min, int max) {
        System.out.print("Entrez " + text + " ("+min+"-"+max+") : ");
        int value = 0;
        boolean error = false;
        try {
            value = Integer.parseInt(ScannerService.escapedNextLine());
        } catch (NumberFormatException e) {
            error = true;
        }
        while (value < min || value > max || error) {
            error = false;
            System.out.print("Entrez " + text + " ("+min+"-"+max+") : ");
            try {
                value = Integer.parseInt(ScannerService.escapedNextLine());
            } catch (NumberFormatException e) {
                error = true;
            }
        }
        return value;
    }
}

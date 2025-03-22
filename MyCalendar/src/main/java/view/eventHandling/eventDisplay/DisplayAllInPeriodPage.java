package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
import view.Page;

import java.time.LocalDateTime;

public class DisplayAllInPeriodPage implements Page {
    @Override
    public String getTitle() {
        return "Afficher TOUS les événements";
    }

    @Override
    public void display() {
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = ScannerService.askInputRange("le mois", 1, 12);
        int day = ScannerService.askInputRange("le jour", 1, 31);
        int hour = ScannerService.askInputRange("l'heure", 1, 23);
        int minute = ScannerService.askInputRange("la minute", 0, 59);
        System.out.print("Entrez la durée (en minute) : ");
        int duration = Integer.parseInt(ScannerService.escapedNextLine());

        LocalDateTime periodStart = LocalDateTime.of(year, month, day, hour, minute);
        LocalDateTime periodEnd = periodStart.plusMinutes(duration);

        CalendarService.getInstance().displayEventsOfPeriod(periodStart, periodEnd);
    }
}

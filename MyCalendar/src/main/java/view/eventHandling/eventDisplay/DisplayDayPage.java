package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
import view.InputHelper;
import view.Page;

import java.time.LocalDateTime;

public class DisplayDayPage implements Page {
    @Override
    public String getTitle() {
        return "Afficher les événements d'un JOUR précis";
    }

    @Override
    public void display() {
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = InputHelper.askInputRange("le mois", 1, 12);
        int day = InputHelper.askInputRange("le jour", 1, 31);

        LocalDateTime debutJour = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutJour, finJour);
    }
}

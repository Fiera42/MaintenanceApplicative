package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
import view.InputHelper;
import view.Page;

import java.time.LocalDateTime;

public class DisplayMonthPage implements Page {
    @Override
    public String getTitle() {
        return "Afficher les événements d'un MOIS précis";
    }

    @Override
    public void display() {
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = InputHelper.askInputRange("le mois", 1, 12);

        LocalDateTime debutMois = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutMois, finMois);
    }
}

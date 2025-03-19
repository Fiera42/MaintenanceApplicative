package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
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
        int anneeMois = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(ScannerService.nextLine());
        if(mois == 0) mois++;
        while (mois > 12) {
            System.out.print("Entrez le mois (1-12) : ");
            mois = Integer.parseInt(ScannerService.nextLine());
            if(mois == 0) mois++;
        }

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutMois, finMois);
    }
}

package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
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
        int anneeJour = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(ScannerService.nextLine());
        if(moisJour == 0) moisJour++;
        while (moisJour > 12) {
            System.out.print("Entrez le mois (1-12) : ");
            moisJour = Integer.parseInt(ScannerService.nextLine());
            if(moisJour == 0) moisJour++;
        }
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(ScannerService.nextLine());
        if(jour == 0) jour++;
        while (jour > 31) {
            System.out.print("Entrez le jour (1-31) : ");
            jour = Integer.parseInt(ScannerService.nextLine());
            if(jour == 0) jour++;
        }

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutJour, finJour);
    }
}

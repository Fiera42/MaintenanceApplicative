package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.users.AuthSystem;
import view.Page;

import java.time.LocalDateTime;

public class NewPeriodicEventPage implements Page {
    @Override
    public String getTitle() {
        return "Ajouter un évènement périodique";
    }

    @Override
    public void display() {
        System.out.print("Titre de l'événement : ");
        String titre3 = ScannerService.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee3 = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv3 = Integer.parseInt(ScannerService.nextLine());
        if(moisRdv3 == 0) moisRdv3++;
        while (moisRdv3 > 12) {
            System.out.print("Entrez le mois (1-12) : ");
            moisRdv3 = Integer.parseInt(ScannerService.nextLine());
            if(moisRdv3 == 0) moisRdv3++;
        }
        System.out.print("Jour (1-31) : ");
        int jourRdv3 = Integer.parseInt(ScannerService.nextLine());
        if(jourRdv3 == 0) jourRdv3++;
        while (jourRdv3 > 31) {
            System.out.print("Entrez le jour (1-31) : ");
            jourRdv3 = Integer.parseInt(ScannerService.nextLine());
            if(jourRdv3 == 0) jourRdv3++;
        }
        System.out.print("Heure début (0-23) : ");
        int heure3 = Integer.parseInt(ScannerService.nextLine());
        while (heure3 > 23) {
            System.out.print("Heure début (0-23) : ");
            heure3 = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Minute début (0-59) : ");
        int minute3 = Integer.parseInt(ScannerService.nextLine());
        while (minute3 > 59) {
            System.out.print("Minute début (0-59) : ");
            minute3 = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Fréquence (en jours) : ");
        int frequence = Integer.parseInt(ScannerService.nextLine());

        CalendarService.getInstance().ajouterEvent("PERIODIQUE", titre3, AuthSystem.getCurrentUser(),
                LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3), 0,
                "", "", frequence);

        System.out.println("Événement ajouté.");
    }
}

package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.events.PersonalEvent;
import model.users.AuthSystem;
import view.Page;

import java.time.LocalDateTime;

public class NewPersonalEventPage implements Page {
    @Override
    public String getTitle() {
        return "Ajouter un rendez-vous perso";
    }

    @Override
    public void display() {
        System.out.print("Titre de l'événement : ");
        String titre = ScannerService.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(ScannerService.nextLine());
        if(moisRdv == 0) moisRdv++;
        while (moisRdv > 12) {
            System.out.print("Entrez le mois (1-12) : ");
            moisRdv = Integer.parseInt(ScannerService.nextLine());
            if(moisRdv == 0) moisRdv++;
        }
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(ScannerService.nextLine());
        if(jourRdv == 0) jourRdv++;
        while (jourRdv > 31) {
            System.out.print("Entrez le jour (1-31) : ");
            jourRdv = Integer.parseInt(ScannerService.nextLine());
            if(jourRdv == 0) jourRdv++;
        }
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(ScannerService.nextLine());
        while (heure > 23) {
            System.out.print("Heure début (0-23) : ");
            heure = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(ScannerService.nextLine());
        while (minute > 59) {
            System.out.print("Minute début (0-59) : ");
            minute = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(ScannerService.nextLine());

        CalendarService.getInstance().addEvent(
                new PersonalEvent(titre, AuthSystem.getCurrentUser(), LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree)
        );

        System.out.println("Événement ajouté.");
    }
}

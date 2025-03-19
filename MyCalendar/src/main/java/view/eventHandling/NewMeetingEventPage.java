package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.users.AuthSystem;
import view.Page;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class NewMeetingEventPage implements Page {
    @Override
    public String getTitle() {
        return "Ajouter une réunion";
    }

    @Override
    public void display() {
        System.out.print("Titre de l'événement : ");
        String titre2 = ScannerService.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee2 = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv2 = Integer.parseInt(ScannerService.nextLine());
        if(moisRdv2 == 0) moisRdv2++;
        while (moisRdv2 > 12) {
            System.out.print("Entrez le mois (1-12) : ");
            moisRdv2 = Integer.parseInt(ScannerService.nextLine());
            if(moisRdv2 == 0) moisRdv2++;
        }
        System.out.print("Jour (1-31) : ");
        int jourRdv2 = Integer.parseInt(ScannerService.nextLine());
        if(jourRdv2 == 0) jourRdv2++;
        while (jourRdv2 > 31) {
            System.out.print("Entrez le jour (1-31) : ");
            jourRdv2 = Integer.parseInt(ScannerService.nextLine());
            if(jourRdv2 == 0) jourRdv2++;
        }
        System.out.print("Heure début (0-23) : ");
        int heure2 = Integer.parseInt(ScannerService.nextLine());
        while (heure2 > 23) {
            System.out.print("Heure début (0-23) : ");
            heure2 = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Minute début (0-59) : ");
        int minute2 = Integer.parseInt(ScannerService.nextLine());
        while (minute2 > 59) {
            System.out.print("Minute début (0-59) : ");
            minute2 = Integer.parseInt(ScannerService.nextLine());
        }
        System.out.print("Durée (en minutes) : ");
        int duree2 = Integer.parseInt(ScannerService.nextLine());
        System.out.println("Lieu :");
        String lieu = ScannerService.nextLine();

        String participants = AuthSystem.getCurrentUser();

        System.out.println("Ajouter un participant ? (oui / non)");
        while (ScannerService.nextLine().equalsIgnoreCase("o") || ScannerService.nextLine().equalsIgnoreCase("oui"))
        {
            System.out.print("Participants : " + participants);
            participants += ", " + ScannerService.nextLine();
        }

        try {
            CalendarService.getInstance().ajouterEvent("REUNION", titre2, AuthSystem.getCurrentUser(),
                    LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2), duree2,
                    lieu, participants, 0);

            System.out.println("Événement ajouté.");
        }
        catch (DateTimeException e) {
            System.out.print("Problème : " + e.getMessage());
        }

    }
}

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
        System.out.print("Entrez le titre de l'événement : ");
        String title = ScannerService.escapedNextLine();
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = ScannerService.askInputRange("le mois", 1, 12);
        int day = ScannerService.askInputRange("le jour", 1, 31);
        int hour = ScannerService.askInputRange("l'heure de début", 0, 23);
        int minute = ScannerService.askInputRange("la minute de début", 0, 59);

        System.out.print("Entrez la durée (en minutes) : ");
        int duration = Integer.parseInt(ScannerService.escapedNextLine());

        var res = CalendarService.getInstance().addEvent(
                new PersonalEvent(title, AuthSystem.getCurrentUser(), LocalDateTime.of(year, month, day, hour, minute), duration)
        );

        if(res) {
            System.out.println("Événement ajouté.");
        }
        else {
            System.out.println("Chevauchement d'événement !");
        }
    }
}

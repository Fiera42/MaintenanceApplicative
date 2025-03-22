package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.events.PeriodicEvent;
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
        System.out.print("Entrez le titre de l'événement : ");
        String title = ScannerService.escapedNextLine();
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = ScannerService.askInputRange("le mois", 1, 12);
        int day = ScannerService.askInputRange("le jour", 1, 31);
        int hour = ScannerService.askInputRange("l'heure de début", 0, 23);
        int minute = ScannerService.askInputRange("la minute de début", 0, 59);

        System.out.print("Entrez la fréquence (en jours) : ");
        int frequency = Integer.parseInt(ScannerService.escapedNextLine());

        CalendarService.getInstance().addEvent(
                new PeriodicEvent(title, AuthSystem.getCurrentUser(), LocalDateTime.of(year, month, day, hour, minute), 0, frequency)
        );

        System.out.println("Événement ajouté.");
    }
}

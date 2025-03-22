package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.events.MeetingEvent;
import model.users.AuthSystem;
import view.InputHelper;
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
        System.out.print("Entrez le titre de l'événement : ");
        String title = ScannerService.escapedNextLine();
        System.out.print("Entrez l'année (AAAA) : ");
        int year = Integer.parseInt(ScannerService.escapedNextLine());
        int month = InputHelper.askInputRange("le mois", 1, 12);
        int day = InputHelper.askInputRange("le jour", 1, 31);
        int hour = InputHelper.askInputRange("l'heure de début", 0, 23);
        int minute = InputHelper.askInputRange("la minute de début", 0, 59);

        System.out.print("Entrez la durée (en minutes) : ");
        int duration = Integer.parseInt(ScannerService.escapedNextLine());

        System.out.println("Entrez le lieu :");
        String lieu = ScannerService.escapedNextLine();

        String participants = AuthSystem.getCurrentUser();
        System.out.println("Ajouter un participant ? (oui / non)");
        while (ScannerService.escapedNextLine().equalsIgnoreCase("o") || ScannerService.escapedNextLine().equalsIgnoreCase("oui"))
        {
            System.out.print("Participants : " + participants);
            participants += ", " + ScannerService.escapedNextLine();
        }

        try {
            CalendarService.getInstance().addEvent(
                    new MeetingEvent(title, AuthSystem.getCurrentUser(), LocalDateTime.of(year, month, day, hour, minute), duration, lieu, participants)
            );

            System.out.println("Événement ajouté.");
        }
        catch (DateTimeException e) {
            System.out.print("Problème : " + e.getMessage());
        }

    }
}

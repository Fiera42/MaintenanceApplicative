package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import model.events.MeetingEvent;
import model.users.AuthSystem;
import view.Page;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
        int month = ScannerService.askInputRange("le mois", 1, 12);
        int day = ScannerService.askInputRange("le jour", 1, 31);
        int hour = ScannerService.askInputRange("l'heure de début", 0, 23);
        int minute = ScannerService.askInputRange("la minute de début", 0, 59);

        System.out.print("Entrez la durée (en minutes) : ");
        int duration = Integer.parseInt(ScannerService.escapedNextLine());

        System.out.println("Entrez le lieu :");
        String lieu = ScannerService.escapedNextLine();

        // Optimize for insertion since it's going to be changed anyway by the builder
        List<String> participants = new LinkedList<>();
        participants.add(AuthSystem.getCurrentUser());
        System.out.println("Ajouter un participant ? (oui / non)");
        String response = ScannerService.escapedNextLine();
        while (response.equalsIgnoreCase("o") || response.equalsIgnoreCase("oui"))
        {
            System.out.print("Participant : " + MeetingEvent.participantsListToString(participants));
            participants.add(ScannerService.escapedNextLine());
            System.out.println("Ajouter un participant ? (oui / non)");
            response = ScannerService.escapedNextLine();
        }

        try {
            var res = CalendarService.getInstance().addEvent(
                    new MeetingEvent(title, AuthSystem.getCurrentUser(), LocalDateTime.of(year, month, day, hour, minute), duration, lieu, participants)
            );

            if(res) {
                System.out.println("Événement ajouté.");
            }
            else {
                System.out.println("Chevauchement d'événement !");
            }
        }
        catch (DateTimeException e) {
            System.out.print("Problème : " + e.getMessage());
        }

    }
}

package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
import view.Page;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DisplayWeekPage implements Page {
    @Override
    public String getTitle() {
        return "Afficher les événements d'une SEMAINE précise";
    }

    @Override
    public void display() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(ScannerService.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(ScannerService.nextLine());
        if(semaine == 0) semaine++;
        while (semaine > 52) {
            System.out.print("Entrez le numéro de semaine (1-52) : ");
            semaine = Integer.parseInt(ScannerService.nextLine());
            if(semaine == 0) semaine++;
        }

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutSemaine, finSemaine);
    }
}

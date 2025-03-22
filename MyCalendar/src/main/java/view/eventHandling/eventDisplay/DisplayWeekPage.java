package view.eventHandling.eventDisplay;

import model.CalendarService;
import model.ScannerService;
import view.InputHelper;
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
        int anneeSemaine = Integer.parseInt(ScannerService.escapedNextLine());
        int week = InputHelper.askInputRange("le numéro de semaine", 1, 52);

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), week)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        CalendarService.getInstance().displayEventsOfPeriod(debutSemaine, finSemaine);
    }
}

package model;

import model.events.PeriodicEvent;
import model.events.PersonalEvent;
import model.events.MeetingEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarService {
    // ------------------------------------------------------- Instance
    private static CalendarService instance = new CalendarService();

    public static CalendarService getInstance() {
        return instance;
    }

    private CalendarService() {}

    public static void resetCalendarService() {
        instance = new CalendarService();
    }

    // ------------------------------------------------------- Class

    private final EventList eventList = new EventList();

    public void ajouterEvent(String type, String title, String owner, LocalDateTime start, int duration,
                             String place, String participants, int frequency) {

        Event e = switch (type) {
            case "PERIODIQUE" -> new PeriodicEvent(title, owner, start, duration, frequency);
            case "REUNION" -> new MeetingEvent(title, owner, start, duration, place, participants);
            case "RDV_PERSONNEL" -> new PersonalEvent(title, owner, start, duration);
            default -> null;
        };

        eventList.addEvent(e);
    }

    public List<Event> eventsDansPeriod(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : eventList) {
            if(e instanceof PeriodicEvent periodicEvent) {
                LocalDateTime temp = e.start;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(periodicEvent.frequency);
                }
            }else if (!e.start.isBefore(debut) && !e.start.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public void displayEvents() {
        for (Event e : eventList) {
            System.out.println(e.description());
        }
    }

    public void displayEventsOfPeriod(LocalDateTime debut, LocalDateTime fin) {
        var evenements = eventsDansPeriod(debut, fin);
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
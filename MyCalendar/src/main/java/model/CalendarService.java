package model;

import model.events.PeriodicEvent;

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

    public boolean addEvent(Event event) {
        return eventList.addEvent(event);
    }

    public List<Event> eventsDansPeriod(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : eventList) {
            if(e instanceof PeriodicEvent periodicEvent) {
                LocalDateTime temp = e.start.value();
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(periodicEvent.frequency.value());
                }
            }else if (!e.start.value().isBefore(debut) && !e.start.value().isAfter(fin)) {
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
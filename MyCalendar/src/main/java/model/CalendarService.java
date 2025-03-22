package model;

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

    public boolean removeFromID(String id) {
        id = id.trim().toLowerCase();
        for(int i = 0; i < eventList.size(); i++) {
            if(eventList.getEvent(i).eventId.value().toString().equals(id)) {
                return eventList.removeEvent(eventList.getEvent(i));
            }
        }
        return false;
    }

    public List<Event> eventsDansPeriod(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : eventList) {
            if(e.isStartingInPeriod(debut, fin)) {
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
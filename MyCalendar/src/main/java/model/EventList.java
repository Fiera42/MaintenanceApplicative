package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EventList implements Iterable<Event> {

    public List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        // TODO : check if no conflict
        events.add(event);
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }

    @Override
    public void forEach(Consumer<? super Event> action) {
        events.forEach(action);
    }

    @Override
    public Spliterator<Event> spliterator() {
        return events.spliterator();
    }

    public boolean conflict(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes);
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes);

        if (e1.type.equals("PERIODIQUE") || e2.type.equals("PERIODIQUE")) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut)) {
            return true;
        }
        return false;
    }
}

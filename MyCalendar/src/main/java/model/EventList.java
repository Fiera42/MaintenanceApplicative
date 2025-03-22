package model;

import model.events.PeriodicEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EventList implements Iterable<Event> {

    private final List<Event> events = new ArrayList<>();

    public boolean addEvent(Event event) {
        // TODO : check if no conflict
        events.add(event);
        return true;
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
        LocalDateTime fin1 = e1.start.plusMinutes(e1.duration);
        LocalDateTime fin2 = e2.start.plusMinutes(e2.duration);


        if(e1 instanceof PeriodicEvent || e2 instanceof PeriodicEvent) {
            return false; // Simplification abusive
        }

        if (e1.start.isBefore(fin2) && fin1.isAfter(e2.start)) {
            return true;
        }
        return false;
    }
}

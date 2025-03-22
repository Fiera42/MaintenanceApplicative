package model;

import model.events.ImportantEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EventList implements Iterable<Event> {

    private final List<Event> events = new ArrayList<>();

    public boolean addEvent(Event event) {
        // This type of event can be added regardless of the calendar
        if(event instanceof ImportantEvent) {
            events.add(event);
            return true;
        }

        var periodStart = event.start.value();
        var periodEnd = periodStart.plusMinutes(event.duration.value());
        for(Event e : events) {
            // Check in both way to handle periodic events
            if(e.isOverlappingWithPeriod(periodStart, periodEnd)) {
                return false;
            }
            if(event.isOverlappingWithPeriod(e.start.value(), e.start.value().plusMinutes(event.duration.value()))) {
                return false;
            }
        }
        events.add(event);
        return true;
    }

    public boolean removeEvent(Event event) {
        int size = events.size();
        events.remove(event);
        return size != events.size();
    }

    public int size() {
        return events.size();
    }

    public Event getEvent(int index) {
        return events.get(index);
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
}

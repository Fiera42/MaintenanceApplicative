package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EventList implements Iterable<Event> {

    private final List<Event> events = new ArrayList<>();

    public boolean addEvent(Event event) {
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

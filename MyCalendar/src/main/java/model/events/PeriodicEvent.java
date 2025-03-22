package model.events;

import model.Event;
import model.eventsValueObject.ImmutableInteger;

import java.time.LocalDateTime;

public class PeriodicEvent extends Event {
    /**
     * Frequency of the event IN DAY
     */
    public ImmutableInteger frequency;

    /**
     * Create a new periodic event that happen every [frequency] days
     * @param title The name of the event
     * @param owner Who created the event
     * @param start The starting date of the event
     * @param duration The duration of the event
     * @param frequency The event appear in the calendar every [frequency] days
     */
    public PeriodicEvent(String title, String owner, LocalDateTime start, int duration, int frequency) {
        super(title, owner, start, duration);
        this.frequency = new ImmutableInteger(frequency);
    }

    @Override
    public String description() {
        return "Événement périodique : " + title.value() + " tous les " + frequency.value() + " jours";
    }

    @Override
    public boolean isStartingInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        return false;
    }

    @Override
    public boolean isOverlappingWithPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        return false;
    }
}

package model.events;

import model.Event;

import java.time.LocalDateTime;

/**
 * An event that can be added regardless of the calendar state
 */
public class ImportantEvent extends Event {
    public ImportantEvent(String title, String owner, LocalDateTime start, int duration) {
        super(title, owner, start, duration);
    }

    @Override
    public String description() {
        return "RDV : " + title.value() + " à " + start.value().toString() + "(id: " + eventId.value().toString() + ")";
    }

    @Override
    public boolean isStartingInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        return !this.start.value().isBefore(periodStart) && !this.start.value().isAfter(periodEnd);
    }

    @Override
    public boolean isOverlappingWithPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        LocalDateTime eventEnd = start.value().plusMinutes(duration.value());

        // Use not before/after for equal dates
        return !eventEnd.isBefore(periodStart) && !this.start.value().isAfter(periodEnd);
    }
}

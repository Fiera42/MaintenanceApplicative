package model.events;

import model.Event;

import java.time.LocalDateTime;

public class PeriodicEvent extends Event {
    /**
     * Frequency of the event IN DAY
     */
    public int frequency;

    public PeriodicEvent(String title, String owner, LocalDateTime start, int duration, int frequency) {
        super(title, owner, start, duration);
        this.frequency = frequency;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequency + " jours";
    }
}

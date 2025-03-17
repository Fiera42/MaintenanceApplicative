package model.events;

import model.Event;

import java.time.LocalDateTime;

public class PersonalEvent extends Event {
    public PersonalEvent(String title, String owner, LocalDateTime start, int duration) {
        super(title, owner, start, duration);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + start.toString();
    }
}

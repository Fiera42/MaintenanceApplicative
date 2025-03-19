package model.events;

import model.Event;

import java.time.LocalDateTime;

public class MeetingEvent extends Event {
    public String place;
    public String participants; // séparés par virgules (pour REUNION uniquement)

    public MeetingEvent(String title, String owner, LocalDateTime start, int duration, String place, String participants) {
        super(title, owner, start, duration);
        this.place = place;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + place + " avec " + participants;
    }
}

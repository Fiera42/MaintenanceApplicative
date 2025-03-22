package model.events;

import model.Event;
import model.eventsValueObject.ImmutableString;

import java.time.LocalDateTime;

public class MeetingEvent extends Event {
    public ImmutableString place;
    public ImmutableString participants; // séparés par virgules (pour REUNION uniquement)

    public MeetingEvent(String title, String owner, LocalDateTime start, int duration, String place, String participants) {
        super(title, owner, start, duration);
        this.place = new ImmutableString(place);
        this.participants = new ImmutableString(participants);
    }

    @Override
    public String description() {
        return "Réunion : " + title.value() + " à " + place.value() + " avec " + participants.value();
    }
}

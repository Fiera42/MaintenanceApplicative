package model;

import model.eventsValueObject.ImmutableInteger;
import model.eventsValueObject.ImmutableLocalDateTime;
import model.eventsValueObject.ImmutableString;

import java.time.LocalDateTime;

public abstract class Event {
    protected ImmutableString title;
    protected ImmutableString owner;
    protected ImmutableLocalDateTime start;
    protected ImmutableInteger duration;

    public Event(String title, String owner, LocalDateTime start, int duration) {
        this.title = new ImmutableString(title);
        this.owner = new ImmutableString(owner);
        this.start = new ImmutableLocalDateTime(start);
        this.duration = new ImmutableInteger(duration);
    }

    public abstract String description();
}
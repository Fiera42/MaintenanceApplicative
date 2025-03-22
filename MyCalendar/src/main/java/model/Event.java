package model;

import model.eventsValueObject.ImmutableInteger;
import model.eventsValueObject.ImmutableLocalDateTime;
import model.eventsValueObject.ImmutableString;
import model.eventsValueObject.ImmutableUUID;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event {
    protected final ImmutableString title;
    protected final ImmutableString owner;
    protected final ImmutableLocalDateTime start;
    protected final ImmutableInteger duration;
    protected final ImmutableUUID eventId;

    public Event(String title, String owner, LocalDateTime start, int duration) {
        this.title = new ImmutableString(title);
        this.owner = new ImmutableString(owner);
        this.start = new ImmutableLocalDateTime(start);
        this.duration = new ImmutableInteger(duration);
        this.eventId = new ImmutableUUID(UUID.randomUUID());
    }

    public abstract String description();
    public abstract boolean isStartingInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);
    public abstract boolean isOverlappingWithPeriod(LocalDateTime periodStart, LocalDateTime periodEnd);
}
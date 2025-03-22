package model;

import java.time.LocalDateTime;

public abstract class Event {
    protected String title;
    protected String owner;
    protected LocalDateTime start;
    protected int duration;

    public Event(String title, String owner, LocalDateTime start, int duration) {
        this.title = title;
        this.owner = owner;
        this.start = start;
        this.duration = duration;
    }

    public abstract String description();
}
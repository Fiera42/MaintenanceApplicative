package model;

import java.time.LocalDateTime;

public abstract class Event {
    public String title;
    public String owner;
    public LocalDateTime start;
    public int duration;

    public Event(String title, String owner, LocalDateTime start, int duration) {
        this.title = title;
        this.owner = owner;
        this.start = start;
        this.duration = duration;
    }

    public abstract String description();
}
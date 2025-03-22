package model.events;

import model.Event;
import model.eventsValueObject.ImmutableString;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingEvent extends Event {
    public ImmutableString place;
    /**
     * The list of people included in the meeting. This is an unmodifiable list : throw "UnsupportedOperationException" when a modification occur
     */
    public final List<ImmutableString> participants;

    public MeetingEvent(String title, String owner, LocalDateTime start, int duration, String place, List<String> participants) {
        super(title, owner, start, duration);
        this.place = new ImmutableString(place);
        this.participants = participants.stream().map(ImmutableString::new).toList();
    }

    @Override
    public String description() {
        return "Réunion : " + title.value() + " à " + place.value() + " avec " + participantsListToString(participants.stream().map(ImmutableString::value).toList());
    }

    public static String participantsListToString(List<String> participants) {
        StringBuilder string = new StringBuilder();
        var iterator = participants.iterator();
        while (iterator.hasNext()) {
            string.append(iterator.next());
            if (iterator.hasNext()) {
                string.append(", ");
            }
        }
        return string.toString();
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

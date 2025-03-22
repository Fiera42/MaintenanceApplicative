import model.events.MeetingEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for the overlapping feature")
public class EventOverlapTest {

    @Test
    @DisplayName("MeetingEvent no overlap")
    public void meetingEventNoOverlap() {
        var event = new MeetingEvent("title",
                "owner",
                LocalDateTime.of(2000, 10, 1, 12, 0),
                60,
                "place",
                List.of()
                );

        // Check before the event
        var periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
        var periodEnd = LocalDateTime.of(2000, 10, 1, 11, 59);
        assertFalse(
                event.isOverlappingWithPeriod(periodStart, periodEnd),
                """
                        Check before the event
                        periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
                        periodEnd = LocalDateTime.of(2000, 10, 1, 11, 59);
                        event = LocalDateTime.of(2000, 10, 1, 12, 0); (duration=60)"""
                );

        // Check after the event
        periodStart = LocalDateTime.of(2000, 10, 1, 13, 1);
        periodEnd = LocalDateTime.of(3000, 10, 1, 13, 0);
        assertFalse(
                event.isOverlappingWithPeriod(periodStart, periodEnd),
                """
                        Check after the event
                        periodStart = LocalDateTime.of(2000, 10, 1, 13, 1);
                        periodEnd = LocalDateTime.of(3000, 10, 1, 13, 0);
                        event = LocalDateTime.of(2000, 10, 1, 12, 0); (duration=60)"""
        );
    }

    @Test
    @DisplayName("MeetingEvent overlap")
    public void meetingEventOverlap() {
        var event = new MeetingEvent("title",
                "owner",
                LocalDateTime.of(2000, 10, 1, 12, 0),
                60,
                "place",
                List.of()
        );

        // Check during the event
        var periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
        var periodEnd = LocalDateTime.of(3000, 10, 1, 12, 0);
        assertTrue(
                event.isOverlappingWithPeriod(periodStart, periodEnd),
                """
                        Check during the event
                        periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
                        periodEnd = LocalDateTime.of(3000, 10, 1, 12, 0);
                        LocalDateTime.of(2000, 10, 1, 12, 0); (duration=60)"""
                );

        // Check before the event
        periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
        periodEnd = LocalDateTime.of(2000, 10, 1, 12, 0);
        assertTrue(
                event.isOverlappingWithPeriod(periodStart, periodEnd),
                """
                        Check before the event
                        periodStart = LocalDateTime.of(1000, 10, 1, 12, 0);
                        periodEnd = LocalDateTime.of(2000, 10, 1, 12, 0);
                        LocalDateTime.of(2000, 10, 1, 12, 0); (duration=60)"""
                );

        // Check after the event
        periodStart = LocalDateTime.of(2000, 10, 1, 13, 0);
        periodEnd = LocalDateTime.of(3000, 10, 1, 13, 0);
        assertTrue(
                event.isOverlappingWithPeriod(periodStart, periodEnd),
                """
                        Check after the event
                        periodStart = LocalDateTime.of(2000, 10, 1, 13, 0);
                        periodEnd = LocalDateTime.of(3000, 10, 1, 13, 0);
                        LocalDateTime.of(2000, 10, 1, 12, 0); (duration=60)"""
                );
    }
}

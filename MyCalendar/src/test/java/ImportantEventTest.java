import model.CalendarService;
import model.events.ImportantEvent;
import model.events.PersonalEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Test the ImportantEvent class")
public class ImportantEventTest {

    @Test
    @DisplayName("Check if ignoring overlapping")
    public void overlappingBypassCheck() {
        CalendarService.getInstance().addEvent(
                new PersonalEvent(
                        "title",
                        "owner",
                        LocalDateTime.of(2000, 10, 2, 12, 0),
                        1_000_000_000
                )
        );

        assertTrue(
                CalendarService.getInstance().addEvent(
                        new ImportantEvent(
                                "title",
                                "owner",
                                LocalDateTime.of(2000, 10, 1, 12, 0),
                                1_000_000_000
                        )
                )
        );

        CalendarService.resetCalendarService();
    }

    @Test
    @DisplayName("Check if other events are still checked against the important event")
    public void noOverlappingBypassForOthers() {
        CalendarService.getInstance().addEvent(
                new ImportantEvent(
                        "title",
                        "owner",
                        LocalDateTime.of(2000, 10, 2, 12, 0),
                        1_000_000_000
                )
        );

        assertFalse(
                CalendarService.getInstance().addEvent(
                        new PersonalEvent(
                                "title",
                                "owner",
                                LocalDateTime.of(2000, 10, 1, 12, 0),
                                1_000_000_000
                        )
                )
        );

        CalendarService.resetCalendarService();
    }
}

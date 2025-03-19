package view.eventHandling.eventDisplay;

import model.CalendarService;
import view.Page;

public class DisplayAllPage implements Page {
    @Override
    public String getTitle() {
        return "Afficher TOUS les événements";
    }

    @Override
    public void display() {
        CalendarService.getInstance().displayEvents();
    }
}

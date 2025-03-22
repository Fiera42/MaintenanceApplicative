package view.eventHandling;

import model.users.AuthSystem;
import view.MenuPage;
import view.Page;
import view.eventHandling.eventDisplay.DisplayMenu;

public class MainMenu implements MenuPage {
    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Page[] getMenuItems() {
        return new Page[] {new DisplayMenu(), new NewPersonalEventPage(), new NewMeetingEventPage(), new NewPeriodicEventPage(), new NewImportantEventPage(), new LogOutPage()};
    }

    @Override
    public void display() {
        System.out.println("\nBonjour, " + AuthSystem.getCurrentUser());
        System.out.println("=== Menu Gestionnaire d'Événements ===");
    }
}

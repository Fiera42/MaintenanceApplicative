package view.eventHandling.eventDisplay;

import view.MenuPage;
import view.Page;

public class DisplayMenu implements MenuPage {
    @Override
    public String getTitle() {
        return "Voir les événements";
    }

    @Override
    public Page[] getMenuItems() {
        return new Page[] {new DisplayAllPage(), new DisplayAllInPeriodPage(), new DisplayMonthPage(), new DisplayWeekPage(), new DisplayDayPage(), new GoBackPage()};
    }

    @Override
    public void display() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
    }
}

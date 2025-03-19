import model.CalendarService;
import model.ScannerService;
import model.users.AuthSystem;
import view.MenuPage;
import view.Page;
import view.eventHandling.MainMenu;
import view.login.WelcomeMenu;

public class Main {
    public static void main(String[] args) {
        ScannerService.resetScannerService();
        CalendarService.resetCalendarService();
        AuthSystem.resetAuthSystem();

        AuthSystem.addUser("Roger", "Chat");
        AuthSystem.addUser("Pierre", "KiRouhl");

        Page currentPage = new WelcomeMenu();
        while (true) {
            if(currentPage instanceof MenuPage menu) {
                menu.display();
                var menuItems = menu.getMenuItems();
                for(int i = 0; i < menuItems.length; i++) {
                    System.out.println((i+1) + " - " + menuItems[i].getTitle());
                }
                System.out.println("Votre choix : ");
                String choice = ScannerService.nextLine();
                int choiceIndex = Integer.parseInt(choice) - 1;
                if(choiceIndex < 0 || choiceIndex >= menuItems.length) choiceIndex = menuItems.length - 1;
                currentPage = menuItems[choiceIndex];
            }
            else {
                currentPage.display();
                currentPage = (AuthSystem.getCurrentUser().isEmpty()) ? new WelcomeMenu() : new MainMenu();
            }
        }
    }
}

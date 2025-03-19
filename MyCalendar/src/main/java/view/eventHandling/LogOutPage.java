package view.eventHandling;

import model.ScannerService;
import model.users.AuthSystem;
import view.Page;

public class LogOutPage implements Page {
    @Override
    public String getTitle() {
        return "Se déconnecter";
    }

    @Override
    public void display() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        if(ScannerService.nextLine().trim().equalsIgnoreCase("o") || ScannerService.nextLine().equalsIgnoreCase("oui")) {
            AuthSystem.logout();
        }
    }
}

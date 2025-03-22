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
        if(ScannerService.escapedNextLine().trim().equalsIgnoreCase("o") || ScannerService.escapedNextLine().equalsIgnoreCase("oui")) {
            AuthSystem.logout();
        }
    }
}

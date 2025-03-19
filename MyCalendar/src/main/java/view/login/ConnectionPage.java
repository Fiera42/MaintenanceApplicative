package view.login;

import model.ScannerService;
import model.users.AuthSystem;
import view.Page;

public class ConnectionPage implements Page {
    @Override
    public String getTitle() {
        return "Se connecter";
    }

    @Override
    public void display() {
        System.out.print("Nom d'utilisateur: ");
        String userName = ScannerService.nextLine();
        while (!AuthSystem.userExists(userName)) {
            System.out.print("Nom d'utilisateur: ");
            userName = ScannerService.nextLine();
        }

        System.out.print("Mot de passe: ");
        String motDePasse = ScannerService.nextLine();
        AuthSystem.login(userName, motDePasse);
    }
}

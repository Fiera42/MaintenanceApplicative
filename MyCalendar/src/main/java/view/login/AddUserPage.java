package view.login;

import model.ScannerService;
import model.users.AuthSystem;
import view.Page;

public class AddUserPage implements Page {
    @Override
    public String getTitle() {
        return "Créer un compte";
    }

    @Override
    public void display() {
        System.out.print("Nom d'utilisateur: ");
        String userName = ScannerService.nextLine();
        while (AuthSystem.userExists(userName)) {
            System.out.print("Nom d'utilisateur: ");
            userName = ScannerService.nextLine();
        }

        System.out.print("Mot de passe: ");
        String motDePasse = ScannerService.nextLine();
        System.out.print("Répéter mot de passe: ");
        if (ScannerService.nextLine().equals(motDePasse)) {
            AuthSystem.addUserAndConnect(userName, motDePasse);
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
        }
    }
}

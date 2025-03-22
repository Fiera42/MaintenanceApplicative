package oldApp;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class OldMain {
    public static void main(String[] args) {
        OldCalendarManager calendar = new OldCalendarManager();
        Scanner scanner = new Scanner(System.in);
        String utilisateur = null;

        String[] utilisateurs = new String[99];
        String[] motsDePasses = new String[99];
        int nbUtilisateurs = 0;

        while (true) {

            if (utilisateur == null) {
                System.out.println("  _____         _                   _                __  __");
                System.out.println(" / ____|       | |                 | |              |  \\/  |");
                System.out.println(
                        "| |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __");
                System.out.println(
                        "| |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|");
                System.out.println(
                        "| |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | || (_| || | | || (_| || (_| ||  __/| |");
                System.out.println(
                        " \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|");
                System.out.println(
                        "                                                                                   __/ |");
                System.out.println(
                        "                                                                                  |___/");

                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Votre choix : ");

                switch (scanner.nextLine()) {
                    case "1":
                        System.out.print("Nom d'utilisateur: ");
                        utilisateur = scanner.nextLine();

                        if (utilisateur.equals("Roger")) {
                            System.out.print("Mot de passe: ");
                            String motDePasse = scanner.nextLine();
                            if (!motDePasse.equals("Chat")) {
                                utilisateur = null;
                            }
                        } else {
                            if (utilisateur.equals("Pierre")) {
                                System.out.print("Mot de passe: ");
                                String motDePasse = scanner.nextLine();
                                if (!motDePasse.equals("KiRouhl")) {
                                    utilisateur = null;
                                }
                            } else {
                                boolean userExist = false;
                                for (int i = 0; i < nbUtilisateurs; i++) {
                                    if (utilisateurs[i] != null && utilisateurs[i].equals(utilisateur)) {
                                        userExist = true;
                                        break;
                                    }
                                }

                                while (!userExist) {
                                    System.out.print("Nom d'utilisateur: ");
                                    utilisateur = scanner.nextLine();

                                    for (int i = 0; i < nbUtilisateurs; i++) {
                                        if (utilisateurs[i] != null && utilisateurs[i].equals(utilisateur)) {
                                            userExist = true;
                                            break;
                                        }
                                    }
                                }

                                System.out.print("Mot de passe: ");
                                String motDePasse = scanner.nextLine();

                                for (int i = 0; i < nbUtilisateurs; i++) {
                                    if (utilisateurs[i].equals(utilisateur)) {
                                        if(!motsDePasses[i].equals(motDePasse)) {
                                            utilisateur = null;
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case "2":
                        System.out.print("Nom d'utilisateur: ");
                        utilisateur = scanner.nextLine();

                        boolean alreadyExists = false;
                        for (int i = 0; i < nbUtilisateurs; i++) {
                            if (utilisateurs[i] != null && utilisateurs[i].equals(utilisateur)) {
                                alreadyExists = true;
                                break;
                            }
                        }

                        while (alreadyExists) {
                            System.out.print("Nom d'utilisateur: ");
                            utilisateur = scanner.nextLine();

                            alreadyExists = false;
                            for (int i = 0; i < nbUtilisateurs; i++) {
                                if (utilisateurs[i] != null && utilisateurs[i].equals(utilisateur)) {
                                    alreadyExists = true;
                                    break;
                                }
                            }
                        }
                        System.out.print("Mot de passe: ");
                        String motDePasse = scanner.nextLine();
                        System.out.print("Répéter mot de passe: ");
                        if (scanner.nextLine().equals(motDePasse)) {
                            utilisateurs[nbUtilisateurs] = utilisateur;
                            motsDePasses[nbUtilisateurs] = motDePasse;
                            nbUtilisateurs++;
                        } else {
                            System.out.println("Les mots de passes ne correspondent pas...");
                            utilisateur = null;
                        }
                        break;
                }
            }
            boolean continuer = true;
            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.println("Votre choix : ");

                String choix = scanner.nextLine();

                switch (choix) {
                    case "1":
                        System.out.println("\n=== Menu de visualisation d'Événements ===");
                        System.out.println("1 - Afficher TOUS les événements");
                        System.out.println("2 - Afficher les événements d'un MOIS précis");
                        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
                        System.out.println("4 - Afficher les événements d'un JOUR précis");
                        System.out.println("5 - Retour");
                        System.out.println("Votre choix : ");

                        choix = scanner.nextLine();

                        switch (choix) {
                            case "1":
                                calendar.afficherEvenements();
                                break;

                            case "2":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeMois = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int mois = Integer.parseInt(scanner.nextLine());
                                while (mois <= 0 || mois > 12) {
                                    System.out.print("Entrez le mois (1-12) : ");
                                    mois = Integer.parseInt(scanner.nextLine());
                                }

                                LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                                LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
                                break;

                            case "3":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeSemaine = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le numéro de semaine (1-52) : ");
                                int semaine = Integer.parseInt(scanner.nextLine());
                                while (semaine <= 0 || semaine > 52) {
                                    System.out.print("Entrez le numéro de semaine (1-52) : ");
                                    semaine = Integer.parseInt(scanner.nextLine());
                                }

                                LocalDateTime debutSemaine = LocalDateTime.now()
                                        .withYear(anneeSemaine)
                                        .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                                        .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                                        .withHour(0).withMinute(0);
                                LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
                                break;

                            case "4":
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int moisJour = Integer.parseInt(scanner.nextLine());
                                while (moisJour <= 0 || moisJour > 12) {
                                    System.out.print("Entrez le mois (1-12) : ");
                                    moisJour = Integer.parseInt(scanner.nextLine());
                                }
                                System.out.print("Entrez le jour (1-31) : ");
                                int jour = Integer.parseInt(scanner.nextLine());
                                while (jour <= 0 || jour > 31) {
                                    System.out.print("Entrez le jour (1-31) : ");
                                    jour = Integer.parseInt(scanner.nextLine());
                                }

                                LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                                LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
                                break;
                        }
                        break;

                    case "2":
                        // Ajout simplifié d'un RDV personnel
                        System.out.print("Entrez le titre de l'événement : ");
                        String titre = scanner.nextLine();
                        System.out.print("Entrez l'année (AAAA) : ");
                        int annee = Integer.parseInt(scanner.nextLine());
                        System.out.print("Entrez le mois (1-12) : ");
                        int moisRdv = Integer.parseInt(scanner.nextLine());
                        while (moisRdv <= 0 || moisRdv > 12) {
                            System.out.print("Entrez le mois (1-12) : ");
                            moisRdv = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez le jour (1-31) : ");
                        int jourRdv = Integer.parseInt(scanner.nextLine());
                        while (jourRdv <= 0 || jourRdv > 31) {
                            System.out.print("Entrez le jour (1-31) : ");
                            jourRdv = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez l'heure de début (0-23) : ");
                        int heure = Integer.parseInt(scanner.nextLine());
                        while (heure > 23) {
                            System.out.print("Entrez l'heure de début (0-23) : ");
                            heure = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la minute de début (0-59) : ");
                        int minute = Integer.parseInt(scanner.nextLine());
                        while (minute > 59) {
                            System.out.print("Entrez la minute de début (0-59) : ");
                            minute = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la durée (en minutes) : ");
                        int duree = Integer.parseInt(scanner.nextLine());

                        calendar.ajouterEvent("RDV_PERSONNEL", titre, utilisateur,
                                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree,
                                "", "", 0);

                        System.out.println("Événement ajouté.");
                        break;

                    case "3":
                        // Ajout simplifié d'une réunion
                        System.out.print("Entrez le titre de l'événement : ");
                        String titre2 = scanner.nextLine();
                        System.out.print("Entrez l'année (AAAA) : ");
                        int annee2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Entrez le mois (1-12) : ");
                        int moisRdv2 = Integer.parseInt(scanner.nextLine());
                        while (moisRdv2 <= 0 || moisRdv2 > 12) {
                            System.out.print("Entrez le mois (1-12) : ");
                            moisRdv2 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez le jour (1-31) : ");
                        int jourRdv2 = Integer.parseInt(scanner.nextLine());
                        while (jourRdv2 <= 0 || jourRdv2 > 31) {
                            System.out.print("Entrez le jour (1-31) : ");
                            jourRdv2 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez l'heure de début (0-23) : ");
                        int heure2 = Integer.parseInt(scanner.nextLine());
                        while (heure2 > 23) {
                            System.out.print("Entrez l'heure de début (0-23) : ");
                            heure2 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la minute de début (0-59) : ");
                        int minute2 = Integer.parseInt(scanner.nextLine());
                        while (minute2 > 59) {
                            System.out.print("Entrez la minute de début (0-59) : ");
                            minute2 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la durée (en minutes) : ");
                        int duree2 = Integer.parseInt(scanner.nextLine());
                        System.out.println("Entrez le lieu :");
                        String lieu = scanner.nextLine();
                        
                        String participants = utilisateur;

                        System.out.println("Ajouter un participant ? (oui / non)");
                        while (scanner.nextLine().equalsIgnoreCase("o") || scanner.nextLine().equalsIgnoreCase("oui"))
                        {
                            System.out.print("Participants : " + participants);
                            participants += ", " + scanner.nextLine();
                        }

                        try {
                            calendar.ajouterEvent("REUNION", titre2, utilisateur,
                                    LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2), duree2,
                                    lieu, participants, 0);

                            System.out.println("Événement ajouté.");
                        } catch (DateTimeException e) {
                            System.out.print("Problème : " + e.getMessage());
                        }

                        break;

                        case "4":
                        // Ajout simplifié d'une réunion
                        System.out.print("Entrez le titre de l'événement : ");
                        String titre3 = scanner.nextLine();
                        System.out.print("Entrez l'année (AAAA) : ");
                        int annee3 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Entrez le mois (1-12) : ");
                        int moisRdv3 = Integer.parseInt(scanner.nextLine());
                        while (moisRdv3 <= 0 || moisRdv3 > 12) {
                            System.out.print("Entrez le mois (1-12) : ");
                            moisRdv3 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez le jour (1-31) : ");
                        int jourRdv3 = Integer.parseInt(scanner.nextLine());
                        while (jourRdv3 <= 0 || jourRdv3 > 31) {
                            System.out.print("Entrez le jour (1-31) : ");
                            jourRdv3 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez l'heure de début (0-23) : ");
                        int heure3 = Integer.parseInt(scanner.nextLine());
                        while (heure3 > 23) {
                            System.out.print("Entrez l'heure de début (0-23) : ");
                            heure3 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la minute de début (0-59) : ");
                        int minute3 = Integer.parseInt(scanner.nextLine());
                        while (minute3 > 59) {
                            System.out.print("Entrez la minute de début (0-59) : ");
                            minute3 = Integer.parseInt(scanner.nextLine());
                        }
                        System.out.print("Entrez la fréquence (en jours) : ");
                        int frequence = Integer.parseInt(scanner.nextLine());

                        calendar.ajouterEvent("PERIODIQUE", titre3, utilisateur,
                                LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3), 0,
                                "", "", frequence);

                        System.out.println("Événement ajouté.");
                        break;

                    default:
                        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                        if(scanner.nextLine().trim().equalsIgnoreCase("o") || scanner.nextLine().equalsIgnoreCase("oui")) {
                            continuer = false;
                            utilisateur = null;
                        }
                }
            }
        }
    }

    private static void afficherListe(List<OldEvent> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (OldEvent e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}

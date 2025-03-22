package view.eventHandling;

import model.CalendarService;
import model.ScannerService;
import view.Page;

public class RemoveEventFromIdPage implements Page {
    @Override
    public String getTitle() {
        return "Supprimer un événement";
    }

    @Override
    public void display() {
        System.out.print("Entrez l'id de l'événement : ");
        String id = ScannerService.escapedNextLine();
        var res = CalendarService.getInstance().removeFromID(id);
        if(res) {
            System.out.println("L'événement " + id + " à bien été supprimer.");
        }
        else {
            System.out.println("Aucun événement avec l'id " + id + "n'a été trouvé.");
        }
    }
}

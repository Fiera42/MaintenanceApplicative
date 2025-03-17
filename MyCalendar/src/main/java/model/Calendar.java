package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private final EventList eventList = new EventList();

    public void ajouterEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                             String lieu, String participants, int frequenceJours) {
        Event e = new Event(type, title, proprietaire, dateDebut, dureeMinutes, lieu, participants, frequenceJours);
        eventList.addEvent(e);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : eventList) {
            if (e.type.equals("PERIODIQUE")) {
                LocalDateTime temp = e.dateDebut;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(e.frequenceJours);
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public void afficherEvenements() {
        for (Event e : eventList) {
            System.out.println(e.description());
        }
    }
}
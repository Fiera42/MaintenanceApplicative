package trivia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trivia.game.Player;
import trivia.utils.CircularLinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Testing the CircularlinkedList class for...")
public class CircularLinkedListTest {

    @Test
    @DisplayName("Insert first")
    public void testInsertionFirst() {
        var players = new Player[] {new Player("P1"), new Player("P2"), new Player("P3")};
        var list = new CircularLinkedList<Player>();

        for(Player player : players) {
            list.addFirst(player);
            assertEquals(player, list.getHead());
        }

        for(int i = players.length - 1; i >= 0; i--) {
            assertEquals(players[i], list.next());
        }
    }

    @Test
    @DisplayName("Insert last")
    public void testInsertionLast() {
        var players = new Player[] {new Player("P1"), new Player("P2"), new Player("P3")};
        var list = new CircularLinkedList<Player>();

        for(Player player : players) {
            list.addLast(player);
            assertEquals(player, list.getHeadNode().getPrev().getData());
        }

        for (Player player : players) {
            assertEquals(player, list.next());
        }
    }

    @Test
    @DisplayName("Remove")
    public void testRemove() {
        var players = new Player[] {new Player("P1"), new Player("P2"), new Player("P3")};
        var list = new CircularLinkedList<Player>();
        for(Player player : players) {
            list.addFirst(player);
        }

        list.remove(players[0]);
        assertEquals(players[2], list.next());
        assertEquals(players[1], list.next());

        list.remove(players[2]);
        assertEquals(players[1], list.next());
        assertEquals(players[1], list.next());

        list.remove(players[1]);
        assertNull(list.getHead());
    }
}

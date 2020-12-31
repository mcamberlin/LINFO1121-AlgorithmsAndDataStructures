import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertTrue;

;

/**
 * Ces tests sont des exemples des vrais tests effectués lors de la correction.
 * Réussir ces tests vous donnera au moins 7/20 à la question.
 * Ne pas les réussir vous donnera au PLUS 10/20.
 * <p>
 * 3/20 points sont des tests supplémentaires vérifiant que votre implémentation fonctionne.
 * Ces tests sont lancés quoi qu'il arrive lors de la correction finale.
 * Ils ne devraient pas poser de problème si vous passez les tests ci-dessous de manière honnête, c'est à dire sans
 * hardcoder les solutions (note: si nous découvrons cela, cela sera bien sûr un 0...).
 * <p>
 * 10/20 points sont des tests de performances. Ils ne sont lancés QUE si votre implémentation passe TOUT
 * les tests précédents.
 * <p>
 * Notez que durant l'examen, INGInious ne lance que les tests présents ici. Les autres seront lancés après l'examen.
 */
public class E1Q1Test {
    @Test
    public void exampleTest() {
        List<List<String>> contacts = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Carole"),
                Arrays.asList("Bob", "Eve", "Frank"),
                Arrays.asList("Eve", "Alice", "Frank"),
                Arrays.asList("Frank", "Bob")
        );
        List<String> badCitizens = E1Q1.badCitizens(contacts, 3);
        assertThat(badCitizens, containsInAnyOrder("Alice", "Bob"));
    }

    @Test
    public void egdeCasesTest() {
        List<List<String>> contacts = Arrays.asList();
        assertTrue(E1Q1.badCitizens(contacts, 2).isEmpty());

        List<List<String>> contacts2 = Arrays.asList(
                Collections.singletonList("Alice"),
                Collections.singletonList("Alice"),
                Collections.singletonList("Alice"),
                Collections.singletonList("Alice")
        );
        assertTrue(E1Q1.badCitizens(contacts2, 1).isEmpty());
        assertTrue(E1Q1.badCitizens(contacts2, 2).isEmpty());

        List<List<String>> contacts3 = Arrays.asList(
                Arrays.asList("Alice", "Bob", "Carole"),
                Arrays.asList("Alice", "Bob", "Carole"),
                Arrays.asList("Alice", "Bob", "Carole"),
                Arrays.asList("Alice", "Bob", "Carole")
        );
        assertThat(E1Q1.badCitizens(contacts3, 1), containsInAnyOrder("Alice", "Bob", "Carole"));
        assertTrue(E1Q1.badCitizens(contacts3, 2).isEmpty());
    }

    @Test
    public void moreComplexTest() {
        List<List<String>> contacts = Arrays.asList(
                Arrays.asList("F", "R", "C", "J", "P", "M"),
                Arrays.asList("R", "O", "M", "S", "C", "D", "X"),
                Arrays.asList("P", "D", "N", "Y", "S"),
                Arrays.asList("J", "R", "U", "K", "E", "S"),
                Arrays.asList("O", "I", "C", "Q", "R", "S", "U"),
                Arrays.asList("W", "P", "R", "T", "D", "Q"),
                Arrays.asList("X", "K", "S", "P", "L", "W", "E"),
                Arrays.asList("E", "Q", "Y", "P", "D", "J"),
                Arrays.asList("E", "Q", "F", "U"),
                Arrays.asList("S", "N", "R", "F"),
                Arrays.asList("K", "J", "U"),
                Arrays.asList("V", "P", "R", "W", "B", "U"),
                Arrays.asList("U", "P", "A", "E", "I", "L"),
                Arrays.asList("R", "O", "Z"),
                Arrays.asList("N", "B", "R", "Y", "Z", "V")
        );
        System.out.println("\"" + String.join("\", \"", E1Q1.badCitizens(contacts, 21)) + "\"");
        assertThat(E1Q1.badCitizens(contacts, 3), containsInAnyOrder("A", "B", "C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        assertThat(E1Q1.badCitizens(contacts, 4), containsInAnyOrder("A", "B", "C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        assertThat(E1Q1.badCitizens(contacts, 5), containsInAnyOrder("B", "C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "U", "V", "W", "X", "Y", "Z"));
        assertThat(E1Q1.badCitizens(contacts, 6), containsInAnyOrder("B", "C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "U", "V", "W", "X", "Y"));
        assertThat(E1Q1.badCitizens(contacts, 7), containsInAnyOrder("B", "C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "U", "V", "W", "X", "Y"));
        assertThat(E1Q1.badCitizens(contacts, 8), containsInAnyOrder("C", "D", "E", "F", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "U", "W", "X", "Y"));
        assertThat(E1Q1.badCitizens(contacts, 9), containsInAnyOrder("C", "D", "E", "F", "I", "J", "O", "P", "Q", "R", "S", "U", "W", "X", "Y"));
        assertThat(E1Q1.badCitizens(contacts, 10), containsInAnyOrder("C", "D", "E", "J", "P", "Q", "R", "S", "U", "W", "X", "Y"));
        assertThat(E1Q1.badCitizens(contacts, 11), containsInAnyOrder("C", "D", "E", "J", "P", "Q", "R", "S", "U", "W"));
        assertThat(E1Q1.badCitizens(contacts, 12), containsInAnyOrder("D", "E", "P", "Q", "R", "S", "U", "W"));
        assertThat(E1Q1.badCitizens(contacts, 13), containsInAnyOrder("D", "E", "P", "Q", "R", "S", "U"));
        assertThat(E1Q1.badCitizens(contacts, 14), containsInAnyOrder("E", "P", "R", "S", "U"));
        assertThat(E1Q1.badCitizens(contacts, 15), containsInAnyOrder("P", "R", "S", "U"));
        assertThat(E1Q1.badCitizens(contacts, 16), containsInAnyOrder("P", "R", "S"));
        assertThat(E1Q1.badCitizens(contacts, 17), containsInAnyOrder("P", "R", "S"));
        assertThat(E1Q1.badCitizens(contacts, 18), containsInAnyOrder("P", "R"));
        assertThat(E1Q1.badCitizens(contacts, 19), containsInAnyOrder("P", "R"));
        assertThat(E1Q1.badCitizens(contacts, 20), containsInAnyOrder("P", "R"));
        assertThat(E1Q1.badCitizens(contacts, 21), containsInAnyOrder(new String[0]));
    }

    @Test
    public void randomTest() {
        Random r = new Random(8622);
        int nPersons = 20;
        int bubbleSize = 4;
        HashSet<Integer>[] bubbles = new HashSet[nPersons];
        ArrayList<String> violations = new ArrayList<>();
        for (int i = 0; i < nPersons; i++) {
            bubbles[i] = new HashSet<>();
            bubbles[i].add(i);
        }
        for (int i = 0; i < nPersons * bubbleSize / 2; i++) {
            int n1 = r.nextInt(nPersons);
            int n2 = r.nextInt(nPersons);
            if (n1 != n2) {
                bubbles[n1].add(n2);
                bubbles[n2].add(n1);
            }
        }
        for (int i = 0; i < nPersons; i++) {
            if (bubbles[i].size() > bubbleSize + 1) {
                violations.add(i + "");
            }
        }
        // create random contacts in the bubbles
        List<List<String>> contacts = new LinkedList<>();
        for (int i = 0; i < nPersons; i++) {
            //for (int sample = 0; sample < 100; sample++) {
            for (Integer p2 : bubbles[i]) {
                if (p2 != i) {
                    List<String> contact = new LinkedList<String>();
                    contact.add(i + "");
                    contact.add(p2.toString());
                    contacts.add(contact);
                }
            }
            //}
        }
        List<String> bad = E1Q1.badCitizens(contacts, bubbleSize);
        assertThat(bad, containsInAnyOrder(violations.toArray()));
    }
}

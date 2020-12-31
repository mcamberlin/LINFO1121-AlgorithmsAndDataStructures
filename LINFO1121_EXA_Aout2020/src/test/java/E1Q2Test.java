import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Ces tests sont des exemples des vrais tests effectués lors de la correction.
 * Réussir ces tests vous donnera au moins 7/20 à la question.
 * Ne pas les réussir vous donnera au PLUS 10/20.
 *
 * 3/20 points sont des tests supplémentaires vérifiant que votre implémentation fonctionne.
 * Ces tests sont lancés quoi qu'il arrive lors de la correction finale.
 * Ils ne devraient pas poser de problème si vous passez les tests ci-dessous de manière honnête, c'est à dire sans
 * hardcoder les solutions (note: si nous découvrons cela, cela sera bien sûr un 0...).
 *
 * 10/20 points sont des tests de performances. Ils ne sont lancés QUE si votre implémentation passe TOUT
 * les tests précédents.
 *
 * Notez que durant l'examen, INGInious ne lance que les tests présents ici. Les autres seront lancés après l'examen.
 */
public class E1Q2Test {
    HashMap<StationTime, LinkedList<StationTime>> simpleExample1() {
        HashMap<StationTime, LinkedList<StationTime>> relations = new HashMap<>();
        relations.put(new StationTime("Bxl-midi", 10), new LinkedList<>(Arrays.asList(
                new StationTime("Bruges", 20),
                new StationTime("Ostende", 25),
                new StationTime("Blankenberg", 26),
                new StationTime("Namur", 12)
        )));

        relations.put(new StationTime("Bruges", 12), new LinkedList<>(Arrays.asList(
                new StationTime("Ostende", 14),
                new StationTime("Blankenberg", 16),
                new StationTime("Namur", 30)
        )));

        relations.put(new StationTime("Namur", 13), new LinkedList<>(Arrays.asList(
                new StationTime("Paris", 28),
                new StationTime("Charleroi", 16)
        )));

        relations.put(new StationTime("Namur", 15), new LinkedList<>(Arrays.asList(
                new StationTime("Paris", 26),
                new StationTime("Charleroi", 16)
        )));

        return relations;
    }

    HashMap<StationTime, LinkedList<StationTime>> simpleExample2() {
        HashMap<StationTime, LinkedList<StationTime>> relations = simpleExample1();
        relations.put(new StationTime("Bxl-midi", 15), new LinkedList<>(Arrays.asList(
                new StationTime("Bruges", 25),
                new StationTime("Ostende", 30),
                new StationTime("Blankenberg", 31),
                new StationTime("Namur", 17)
        )));
        relations.put(new StationTime("Bruges", 26), new LinkedList<>(Arrays.asList(
                new StationTime("Ostende", 29),
                new StationTime("Blankenberg", 31),
                new StationTime("Namur", 45),
                new StationTime("Berlin", 36)
        )));
        relations.get(new StationTime("Bxl-midi", 10)).add(new StationTime("Berlin", 29));
        return relations;
    }

    HashMap<String, Position> simpleExemple1Location() {
        HashMap<String, Position> stationPositions = new HashMap<>();
        stationPositions.put("Bxl-midi", new Position(0, 0));
        stationPositions.put("Bruges", new Position(-3, -3));
        stationPositions.put("Ostende", new Position(-3, -2));
        stationPositions.put("Blankenberg", new Position(-3, -1));
        stationPositions.put("Namur", new Position(1, 0));
        stationPositions.put("Charleroi", new Position(1, -1));
        stationPositions.put("Paris", new Position(6, -3));
        stationPositions.put("Berlin", new Position(0, 12));
        return stationPositions;
    }

    @Test
    public void exampleTest() {
        String result = E1Q2.farthestPointReachable(simpleExample1(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 27);
        assertEquals("Paris", result);

        String result2 = E1Q2.farthestPointReachable(simpleExample1(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 26);
        assertEquals("Paris", result2);

        String result3 = E1Q2.farthestPointReachable(simpleExample1(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 25);
        assertEquals("Bruges", result3);
    }

    @Test
    public void example2Test() {
        String result = E1Q2.farthestPointReachable(simpleExample2(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 29);
        assertEquals("Berlin", result);

        String result2 = E1Q2.farthestPointReachable(simpleExample2(), simpleExemple1Location(), new StationTime("Bxl-midi", 11), 29);
        assertEquals("Bruges", result2);

        String result3 = E1Q2.farthestPointReachable(simpleExample2(), simpleExemple1Location(), new StationTime("Bxl-midi", 11), 30);
        assertEquals("Bruges", result3);

        String result4 = E1Q2.farthestPointReachable(simpleExample2(), simpleExemple1Location(), new StationTime("Bxl-midi", 11), 40);
        assertEquals("Berlin", result4);
    }

    public HashMap<StationTime, LinkedList<StationTime>> tricky1() {
        HashMap<StationTime, LinkedList<StationTime>> relations = new HashMap<>();
        relations.put(new StationTime("Bxl-midi", 10), new LinkedList<>());
        relations.put(new StationTime("Bxl-midi", 12), new LinkedList<>(Arrays.asList(
                new StationTime("Bruges", 20)
        )));
        return relations;
    }

    @Test
    public void tricky1Test() {
        String result = E1Q2.farthestPointReachable(tricky1(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 29);
        assertEquals("Bruges", result);
        String result2 = E1Q2.farthestPointReachable(tricky1(), simpleExemple1Location(), new StationTime("Bxl-midi", 5), 19);
        assertEquals("Bxl-midi", result2);
    }
}

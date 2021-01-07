import java.util.*;

public class E1Q2 {
    /**
     * Etant donné une liste de relations entre des gares (le train démarre de la gare `from` au temps `startTime`
     * et arrive à la gare `to` au temps `endTime`) et les positions des gares, ainsi qu'une gare et un temps de départ,
     * quelle est la gare la plus éloignée que vous pouvez atteindre avant (<=) le temps maxTime?
     * <p>
     * Il n'y a pas à vous inquiéter de plusieurs points:
     * - les voyageurs peuvent repartir au moment exact où ils arrivent en gare;
     * - toutes les liaisons sont directes;
     * - les horaires ne sont pas périodiques, pas besoin de les répeter chaque jour.
     * - dans tous les cas, startTime < endTime et from != to dans les relations.
     * - il n'y a jamais de doublons (i.e. deux relations strictement égales)
     * <p>
     * Utilisez la fonction `distance` pour calculer la distance entre deux gares.
     * <p>
     * La question est évaluée sur 20:
     * - 13 points si l'algorithme fonctionne
     * - 4 points si l'algorithme fonctionne rapidement (complexité raisonnable)
     * - 3 points supplémentaire si l'algorithme fonctionne très rapidement (complexité optimale)
     * <p>
     * Nous laissons les termes complexité raisonnable/optimale volontairement flou. A vous, sur la base de vos
     * connaissance, de trouver parmi la famille d'algorithme approprié lequel est optimal.
     * <p>
     * Un indice: vous l'aurez deviné, il s'agit clairement d'un problème de graphe. Mais pas n'importe quel graphe:
     * les noeuds sont particuliers, car ils ne représentent pas un emplacement dans l'espace, mais un emplacement
     * dans l'espace temps (par exemple (Bruxelles-midi, 08h48)).
     * <p>
     * N'oubliez pas que si j'arrive à Bxl-midi au temps i, je peux prendre n'importe quel train partant de Bxl-midi
     * partant au temps j >= i.
     * <p>
     * By the way, saviez-vous que la fonction TreeMap.subMap (https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html#subMap-K-boolean-K-boolean-)
     * existe?
     *
     * @param relations        une liste de relations, liant, une gare+un temps (la clé) (par exemple, Bxl-midi, 12h43)
     *                         à une liste de trains partant à ce moment là, représenté par une liste d'objet StationTime
     *                         indiquant à quelle endroit/heure arrivent ces trains.
     *                         <p>
     *                         Les gares sont représentées par des String ("Bxl-midi") et le temps par des entiers positifs.
     * @param stationPositions la position des gares
     * @param startPoint       l'endroit/temps d'où l'on démarre
     * @param maxTime          le dernier moment où l'on peut arriver à la gare de destination
     * @return la gare la plus lointaine atteignable en partant de startPoint avant le temps maxTime.
     */
    public static String farthestPointReachable(HashMap<StationTime, LinkedList<StationTime>> relations, Map<String, Position> stationPositions,
                                                StationTime startPoint, int maxTime)
    {
        //TODO 1. Fill the graph
        HashMap<StationTime, HashSet<StationTime>> graph = new HashMap<>();
        graph.put(startPoint, new HashSet<>());

        // Parcourir toute la liste de relations pour compléter graph
        for (StationTime stationTime : relations.keySet())
        {
            if (!graph.containsKey(stationTime))
            {
                graph.put(stationTime, new HashSet<StationTime>());
                for (StationTime stationTime2 : relations.get(stationTime))
                {
                    if (!graph.containsKey(stationTime2))
                    {
                        graph.put(stationTime2, new HashSet<StationTime>());
                    }
                    graph.get(stationTime).add(stationTime2);
                }
            }

        }

        //TODO 2. Fill the graph to be able to stay in the same station to wait for a later train
        for (StationTime stationTime1 : graph.keySet())
        {
            for (StationTime stationTime2 : graph.keySet()) //getKey(stationTime1)
            {
                if (stationTime1.pos.equals(stationTime2.pos) && stationTime1.time < stationTime2.time)
                    graph.get(stationTime1).add(stationTime2); // peut rester sur place jusqu'au départ d'un prochain train
            }
        }

        //TODO 3. Implement a DFS

        //idea:
        //  iterate the graph with a DFS or BFS (here DFS is used)
        // check the arrival time. Is it before @maxTime ?
        //  update as iteration goes by, the farthest station

        // Tools for algorithm
        HashSet<StationTime> marked = new HashSet<>(); //boolean[] marked = new boolean[size];
        Stack<StationTime> stack = new Stack<>();

        String farthestStation = startPoint.pos;
        marked.add(startPoint); //marked[startPoint] = true;
        stack.add(startPoint);

        while (!stack.isEmpty())
        {
            StationTime current = stack.pop();
            if (current.time <= maxTime)
            {
                // Update farthestStation if a new further station is found
                Position currentPosition = stationPositions.get(current.pos);
                Position startPosition = stationPositions.get(startPoint.pos);
                Position farthestPosition = stationPositions.get(farthestStation);
                int distStartFarthest = Position.distance(startPosition, farthestPosition);
                int distStartCurrent = Position.distance(startPosition, currentPosition);

                if (distStartFarthest <= distStartCurrent)
                {
                    farthestStation = current.pos;
                }
            }
            //simple DFS
            if (graph.containsKey(current))
            {
                for (StationTime destination : graph.get(current))
                {
                    if (!marked.contains(destination)) // destination have not yet been visited
                    {
                        marked.add(destination);
                        stack.add(destination);
                    }
                }
            }

        }
        return farthestStation;
    }
}

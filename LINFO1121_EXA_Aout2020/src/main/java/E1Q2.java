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
     * - dans tout les cas, startTime < endTime et from != to dans les relations.
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
        //TODO
        //1. Compléter le graphe: relations pour indiquer pour rester à la meme gare à une certaine heure jusqu'à une autre

        HashMap<StationTime, HashSet<StationTime>> graph = new HashMap<>();
        graph.put(startPoint, new HashSet<>());

        for (StationTime stationTime : relations.keySet())
        {
            if (!graph.containsKey(stationTime))
            {
                graph.put(stationTime, new HashSet<StationTime>());
            }

            LinkedList<StationTime> adjacentStationTimes = relations.get(stationTime);
            for (StationTime adjacentStationTime : adjacentStationTimes)
            {
                graph.get(stationTime).add(adjacentStationTime);
            }
        }

        // regarder y a une heure et une gare fixée, il n'y a pas d'autres trains qui partent mais à une heure différente
        for (StationTime stationTime1 : graph.keySet())
        {
            for (StationTime stationTime2 : graph.keySet())
            {
                if (!stationTime1.equals(stationTime2) && stationTime1.pos.equals(stationTime2.pos))
                // Il est possible de prendre des trains à des heures différentes en restant sur place
                {
                    // faire un lien vers du noeud avec la plus petite heure vers l'autre pour symboliser le fait qu'on puisse rester sur place et attendre
                    if (stationTime1.time < stationTime2.time)
                    {
                        graph.get(stationTime1).add(stationTime2);
                    } else
                    {
                        graph.get(stationTime2).add(stationTime1);
                    }

                }
            }
        }

        // Pour déterminer la gare la plus éloignée, atteignable avant (<=) le temps maxTime, on peut effectuer un BFS et maintenir 2 compteurs, un pour la distance
        // et un pour le temps écoulé. Et selectionner celui avec la plus grande distance une fois que le temps maximum est atteint.

        // effectuer un BFS
        Map<StationTime, Boolean> marked = new TreeMap<>();
        Map<StationTime, Integer> distTo = new TreeMap<>();
        Map<StationTime, Integer> timeSpent = new TreeMap<>();

        for (StationTime st : graph.keySet())
        {
            marked.put(st, false);
            distTo.put(st, Integer.MIN_VALUE);
            timeSpent.put(st, 0);
        }

        Queue<StationTime> queue = new ArrayDeque<>();
        queue.add(startPoint);

        marked.put(startPoint, true);
        distTo.put(startPoint, 0);
        timeSpent.put(startPoint, 0);

        String farthestStation = startPoint.pos;
        int farthestDistance = 0;

        while (!queue.isEmpty())
        {
            StationTime current = queue.remove();

            for (StationTime stationTime : graph.get(current))
            {
                System.out.println(stationTime);
                if (marked.containsKey(stationTime) && !marked.get(stationTime))
                {
                    marked.put(stationTime, true);
                    distTo.put(stationTime, distTo.get(current) + Position.distance(stationPositions.get(current.pos), stationPositions.get(stationTime.pos)));
                    timeSpent.put(stationTime, timeSpent.get(current) + stationTime.time);
                    if (distTo.get(stationTime) > farthestDistance)
                    {
                        farthestDistance = distTo.get(stationTime);
                        farthestStation = stationTime.pos;
                    }
                    if (timeSpent.get(stationTime) <= maxTime)
                    {
                        queue.add(stationTime);
                    }
                }
            }
        }
        return farthestStation;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E1Q1 {
    /**
     * Le gouvernement belge a imposé à la population de limiter les contact, via des "bulles".
     * <p>
     * Le principe est assez simple. Si vous avez un contact avec quelqu'un, vous
     * êtes dès lors dans sa bulle, et il est dans la votre.
     * <p>
     * Prenons un exemple:
     * - Lundi, Alice voit Bob et Carole
     * - Mardi, Bob voit Eve et Frank
     * - Mercredi, Eve voit Alice et Frank
     * - Jeudi, Frank et Bob mangent un bout ensemble
     * <p>
     * Les bulles résultantes sont
     * - Bulle d'Alice = [Bob, Carole, Eve, Frank]
     * - Bulle de Bob = [Alice, Carole, Eve, Frank]
     * - Bulle de Carole = [Alice, Bob]
     * - Bulle de Eve = [Alice, Bob, Frank]
     * - ...
     * <p>
     * Notez bien que la relation est symétrique
     * (si Alice est dans la bulle de Bob, alors Bob est dans la bulle d'Alice)
     * mais pas transitive (si Bob est dans la bulle d'Alice,
     * et que Carole est dans la bulle de Bob, Carole n'est pas forcément dans celle d'Alice)
     * <p>
     * Etant donné qu'au plus n personnes peuvent être dans la bulle de quelqu'un sans
     * qu'elle ne soit hors-la-loi, et étant donné une liste de contacts,
     * donnez la liste des mauvais citoyens qui sont hors-la-loi.
     * <p>
     * Notez que n peut être très, très grand, pour anticiper d'éventuelles futures mesures du
     * gouvernement.
     * <p>
     * La question est sur 20 points:
     * - 10 points pour la validité (votre programme retourne toujours la bonne réponse)
     * - 10 points pour la performance (votre programme fonctionne dans le temps optimal)
     * <p>
     * La performance a atteindre pour cet algorithme est O(n*m^2),
     * où n=contacts.length et m=max_i contacts.get(i).length.
     *
     * @param contacts une liste de contacts pris.
     *                 Dans l'exemple ci-dessus, elle contenait 4 listes, contenant à chaque fois
     *                 les noms des participants à chaque contact.
     * @param n        un entier > 0.
     * @return une liste de personnes ayant strictement plus que n personnes dans leur bulle
     */
    public static List<String> badCitizens(List<List<String>> contacts, int n)
    {
        //1. parcourir toutes les listes et tous les contacts.
        // creer pour chaque personne un ensemble de personnes avec qui elle a eu un contact rapproché
        // Pas d'ordre à maintenir, juste pas de doublons => HashSet

        // A chaque personne représentée par un string correspond une liste de personnes avec qui elle a entretenu un contact
        HashMap<String, HashSet<String>> bulles = new HashMap<>();

        List<String> badCitizens = new ArrayList<>();
        for (List<String> contact : contacts)
        {
            for (String s : contact)
            {
                if (!bulles.containsKey(s))
                {
                    bulles.put(s, new HashSet<String>());
                }

                for (String b : contact) // Pour chaque personne avec qui s a eu un contact
                {
                    if (!s.equals(b) && !bulles.get(s).contains(b)) // Si pas déjà dedans, alors ajoute le !
                    {
                        bulles.get(s).add(b); // s et b ont eu un contact
                    }
                }

                if (bulles.get(s).size() > n && !badCitizens.contains(s))
                {
                    badCitizens.add(s);
                }
            }
        }
        return badCitizens;
    }
}

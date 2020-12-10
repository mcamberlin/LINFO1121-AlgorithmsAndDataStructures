import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class HuffmanTest {

    private void collectLeafNodes(List<Huffman.Node> nodes, Huffman.Node n) {

        Stack<Huffman.Node> open = new Stack<Huffman.Node>();
        open.add(n);

        while (!open.isEmpty()) {
            Huffman.Node curr = open.pop();
            if (curr.isLeaf()) nodes.add(curr);
            else {
                open.add(curr.right);
                open.add(curr.left);
            }
        }
    }

    private int[] getRandomInstance(int size, int seed) {
        int[] input = new int[size];
        for (int i = 0; i < size; i++) {
            input[i] = i + 1;
        }
        Random rnd = new Random(seed);
        for (int i = 0; i < size; i++) {
            int idx1 = rnd.nextInt(size);
            int idx2 = rnd.nextInt(size);
            int tmp = input[idx1];
            input[idx1] = input[idx2];
            input[idx2] = tmp;
        }
        return input;
    }


    @Test
    public void noCharactersDeleted() {
        int[] input = getRandomInstance(256, 0);

        Huffman.Node root = Huffman.buildTrie(input.length, input);
        LinkedList<Huffman.Node> leafNodes = new LinkedList<>();
        collectLeafNodes(leafNodes, root);
        assertTrue(input.length == leafNodes.size());

    }


    @Test
    public void leafNodesHaveTheCorrectFrequencies() {
        int[] input = getRandomInstance(256, 1);
        Huffman.Node root = Huffman.buildTrie(input.length, input);
        LinkedList<Huffman.Node> leafNodes = buildLinkedListOfNodes(root);
        for (Huffman.Node n : leafNodes) {
            assertFalse(n.freq != input[n.ch]);
        }
    }

    public LinkedList<Huffman.Node> buildLinkedListOfNodes(Huffman.Node root){
        LinkedList<Huffman.Node> leafNodes = new LinkedList<>();
        if(root.isLeaf()){
            leafNodes.add(root);
        }
        else{
            leafNodes.addAll(buildLinkedListOfNodes(root.left));
            leafNodes.addAll(buildLinkedListOfNodes(root.right));
        }

        return leafNodes;
    }


    @Test
    public void internalNodesHaveTheCorrectFrequencies() {
        int[] input = getRandomInstance(256, 2);
        Huffman.Node root = Huffman.buildTrie(input.length, input);
        assertTrue(checkSumChildrenFreqs(root,input));
    }


    private boolean checkSumChildrenFreqs(Huffman.Node n,int[] input) {
        Stack<Huffman.Node> open = new Stack<Huffman.Node>();
        open.add(n);
        boolean hasleaf = input.length>0;
        boolean flag = false;
        while (!open.isEmpty()) {
            Huffman.Node curr = open.pop();
            if (!curr.isLeaf()) {
                flag = true;
                if (curr.freq == 0 || curr.freq != curr.left.freq + curr.right.freq) return false;
                open.add(curr.right);
                open.add(curr.left);
            }
        }
        return flag==hasleaf;
    }


    @Test
    public void minimalWeightedExternalPathLength() {
        int[] input = getRandomInstance(256, 3);
        Huffman.Node root1 = Huffman.buildTrie(input.length, input);
        assertTrue(weightedExternalPathLength(root1, 0) == 255040);
    }

    @Test
    public void complexityOk() {
        int[] input = getRandomInstance(65536, 3);

        boolean timeOk = new TimeLimitedCodeBlock() {
            @Override
            public void codeBlock() {
                Huffman.Node root1 = Huffman.buildTrie(input.length, input);
            }
        }.run(300);
        assertTrue(timeOk);
    }


    private int weightedExternalPathLength(Huffman.Node n, int depth) {
        Stack<Map.Entry<Huffman.Node, Integer>> open = new Stack<Map.Entry<Huffman.Node, Integer>>();
        open.add(new AbstractMap.SimpleEntry<Huffman.Node, Integer>(n, 0));
        int res = 0;
        while (!open.isEmpty()) {
            Map.Entry<Huffman.Node, Integer> nodeDepth = open.pop();

            Huffman.Node curr = nodeDepth.getKey();
            if (curr.isLeaf()) res += curr.freq * nodeDepth.getValue();
            else {
                open.add(new AbstractMap.SimpleEntry<Huffman.Node, Integer>(curr.right, nodeDepth.getValue() + 1));
                open.add(new AbstractMap.SimpleEntry<Huffman.Node, Integer>(curr.left, nodeDepth.getValue() + 1));
            }
        }
        return res;
    }

}
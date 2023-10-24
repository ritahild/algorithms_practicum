import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class huffman_encoding {
    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;

    static PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    static TreeMap<Character, String> codes = new TreeMap<>();
    static String text = "";
    static String encoded = "";

    static int ASCII[] = new int[128];


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = (readFromFile) ? new Scanner(new File("input.txt")) : new Scanner(System.in);
        int decision = 1;
        while (decision != -1) {
            if (Decision(scanner, decision)) continue;
            decision = consol(scanner);
        }
    }

    private static int consol(Scanner scanner) {
        int decision;

        decision = Integer.parseInt(scanner.nextLine());
        if (readFromFile)
            System.out.println("Decision: ");
        return decision;
    }

    private static boolean Decision(Scanner scanner, int decision) {
        if (decision == 1) {
            if (handleNewText(scanner)) return true;
        } else if (decision == 2) {
            if (EncodingNewText(scanner)) return true;

        }
        return false;
    }



    private static boolean EncodingNewText(Scanner scanner) {
        System.out.println("text to encode:");
        text = scanner.nextLine();
        System.out.println("Text to Encode: " + text);

        if (!IsSameCharacterSet()) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        encodeText();
        return false;
    }

    private static boolean handleNewText(Scanner scanner) {
        int oldTextLength = text.length();
        System.out.println("text:");
        text = scanner.nextLine();
        if (newTextBasedOnOldOne && (oldTextLength != 0 && !IsSameCharacterSet())) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        ASCII = new int[128];
        nodes.clear();
        codes.clear();
        encoded = "";

        System.out.println("Text: " + text);
        castul(nodes, true);
        buildTree(nodes);
        generateCodes(nodes.peek(), "");

        printCodes();
        encodeText();

        return false;



    }

    private static boolean IsSameCharacterSet() {
        boolean flag = true;
        for (int i = 0; i < text.length(); i++)
            if (ASCII[text.charAt(i)] == 0) {
                flag = false;
                break;
            }
        return flag;
    }



    private static void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++)
            encoded += codes.get(text.charAt(i));
        System.out.println("Encoded Text: " + encoded);
    }

    private static void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    private static void printCodes() {
        System.out.println("Printing Codes");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    private static void castul(PriorityQueue<Node> vector, boolean printIntervals) {


        for (int i = 0; i < text.length(); i++)
            ASCII[text.charAt(i)]++;

        for (int i = 0; i < ASCII.length; i++)
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));

            }
    }

    private static void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null)
                generateCodes(node.right, s + "1");

            if (node.left != null)
                generateCodes(node.left, s + "0");

            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}

class Node {
    Node left, right;
    double value;
    String character;

    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }

}
import java.util.HashMap;

public class huffman_decoding {


    public static class Decoder {
        private Node root;


        public Decoder() {
            this.root = new Node('\0');
        }

        public void buildTree(HashMap<Character, String> codes) {
            for (char symbol : codes.keySet()) {
                insert(symbol, codes.get(symbol));
            }
        }

        private void insert(char symbol, String code) {
            Node current = root;
            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new Node('\0');
                    }
                    current = current.left;
                } else if (bit == '1') {
                    if (current.right == null) {
                        current.right = new Node('\0');
                    }
                    current = current.right;
                }
            }
            current.data = symbol;
        }

        public String decode(String encodedString) {
            StringBuilder result = new StringBuilder();
            Node current = root;
            for (char bit : encodedString.toCharArray()) {
                if (bit == '0') {
                    current = current.left;
                } else if (bit == '1') {
                    current = current.right;
                }
                if (current.data != '\0') {
                    result.append(current.data);
                    current = root;
                }
            }
            return result.toString();
        }

        public static void main(String[] args) {
            HashMap<Character, String> codes = new HashMap<>();
            codes.put(' ', "1011");
            codes.put('.', "1110");
            codes.put('D', "1000");
            codes.put('c', "000");
            codes.put('d', "001");
            codes.put('e', "1001");
            codes.put('i', "010");
            codes.put('m', "1100");
            codes.put('n', "1010");
            codes.put('o', "1111");
            codes.put('s', "011");
            codes.put('u', "1101");


            Decoder decoder = new Decoder();
            decoder.buildTree(codes);

            String encodedString = "100011110001001101000111111011001010011000010110011010111110";
            String decodedString = decoder.decode(encodedString);

            System.out.println(decodedString);
        }
    }
    static class Node {
        char data;
        Node left, right;

        public Node(char data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
}

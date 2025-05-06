public class MorseTree {
    private final TreeNode root;

    public MorseTree() {
        this.root = new TreeNode("");
        montaArvore();
    }


    private void montaArvore() {
        insert(".", "E");
        insert("-", "T");
        insert("..", "I");
        insert(".-", "A");
        insert("-.", "N");
        insert("--", "M");

        insert("...", "S");
        insert("..-", "U");
        insert(".-.", "R");
        insert(".--", "W");
        insert("-..", "D");
        insert("-.-", "K");
        insert("--.", "G");
        insert("---", "O");

        insert("....", "H");
        insert("...-", "V");
        insert("..-.", "F");
        insert(".-..", "L");
        insert(".--.", "P");
        insert(".---", "J");
        insert("-...", "B");
        insert("-..-", "X");
        insert("-.--", "Y");
        insert("--..", "Z");
        insert("--.-", "Q");
    }

    public void insert(String code, String value) {
        TreeNode current = root;
        for (char c : code.toCharArray()) {
            if (c == '.') {
                if (current.getLeft() == null) {
                    current.setLeft(new TreeNode(""));
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(new TreeNode(""));
                }
                current = current.getRight();
            }
        }
        current.setValue(value);
    }

    public String decode(String morseMessage) {
        StringBuilder result = new StringBuilder();
        String[] tokens = morseMessage.trim().split(" ");
        for (String token : tokens) {
            if ("/".equals(token)) {
                result.append(' ');
            } else {
                result.append(decodeSymbol(token));
            }
        }
        return result.toString();
    }

    private String decodeSymbol(String code) {
        TreeNode current = root;
        for (char c : code.toCharArray()) {
            current = (c == '.') ? current.getLeft() : current.getRight();
            if (current == null) {
                return "?";
            }
        }
        return current.getValue();
    }

    public String encode(String text) {
        StringBuilder sb = new StringBuilder();
        String[] words = text.toUpperCase().split("\\s+");
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            for (int i = 0; i < word.length(); i++) {
                String morse = encodeSymbol(word.substring(i, i + 1));
                if (morse != null) {
                    sb.append(morse);
                    if (i < word.length() - 1) sb.append(' ');
                }
            }
            if (w < words.length - 1) sb.append(" / ");
        }
        return sb.toString();
    }

    private String encodeSymbol(String target) {
        return encodeRecursive(root, target, "");
    }

    private String encodeRecursive(TreeNode node, String target, String path) {
        if (node == null) return null;
        if (target.equals(node.getValue())) {
            return path;
        }
        String left = encodeRecursive(node.getLeft(), target, path + '.');
        if (left != null) return left;
        return encodeRecursive(node.getRight(), target, path + '-');
    }

    public TreeNode getRoot() {
        return root;
    }
}

public class MorseTree {
    private final TreeNode root;

    public MorseTree() {
        this.root = new TreeNode("");
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
            if (token.equals("/")) {
                result.append(" ");
            } else {
                result.append(decodeSymbol(token));
            }
        }
        return result.toString();
    }

    private String decodeSymbol(String code) {
        TreeNode current = root;
        for (char c : code.toCharArray()) {
            if (current == null) return "";
            current = (c == '.') ? current.getLeft() : current.getRight();
        }
        return (current != null) ? current.getValue() : "";
    }

    public String encode(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.toUpperCase().split("\\s+");
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            for (int i = 0; i < word.length(); i++) {
                String ch = String.valueOf(word.charAt(i));
                String code = encodeSymbol(ch);
                if (code != null && !code.isEmpty()) {
                    result.append(code);
                }
                if (i < word.length() - 1) {
                    result.append(" ");
                }
            }
            if (w < words.length - 1) {
                result.append(" / ");
            }
        }
        return result.toString();
    }

    private String encodeSymbol(String target) {
        return encodeRecursive(root, target, "");
    }

    private String encodeRecursive(TreeNode node, String target, String path) {
        if (node == null) return null;
        if (target.equals(node.getValue())) {
            return path;
        }
        String left = encodeRecursive(node.getLeft(), target, path + ".");
        if (left != null) return left;
        return encodeRecursive(node.getRight(), target, path + "-");
    }

    public TreeNode getRoot() {
        return root;
    }
}

import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static final String[][] MAPPINGS = {
        {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."},
        {"E", "."},  {"F", "..-."}, {"G", "--."},  {"H", "...."},
        {"I", ".."}, {"J", ".---"}, {"K", "-.-"},  {"L", ".-.."},
        {"M", "--"}, {"N", "-."},   {"O", "---"},  {"P", ".--."},
        {"Q", "--.-"},{"R", ".-."}, {"S", "..."},  {"T", "-"},
        {"U", "..-"},{"V", "...-"}, {"W", ".--"},  {"X", "-..-"},
        {"Y", "-.--"},{"Z", "--.."}
    };

    public static void main(String[] args) throws IOException {
        MorseTree morseTree = new MorseTree();
        for (String[] map : MAPPINGS) {
            morseTree.insert(map[1], map[0]);
        }
        MorseTreeImageGenerator.saveTreeImage(morseTree.getRoot(), "tree.png");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Decodificar Morse => Texto");
        System.out.println("2 - Codificar Texto => Morse");
        System.out.print("Selecione a opção: ");
        String option = scanner.nextLine().trim();
        if (option.equals("1")) {
            String morseInput = scanner.nextLine();
            String decoded = morseTree.decode(morseInput);
            System.out.println(decoded);
            MorseTreeImageGenerator.saveTreeImageWithPath(morseTree.getRoot(), morseInput, "decode.png");
            
        } else if (option.equals("2")) {
            String textInput = scanner.nextLine();
            String encoded = morseTree.encode(textInput);
            System.out.println(encoded);
            MorseTreeImageGenerator.saveTreeImageWithPath(morseTree.getRoot(), encoded, "encode.png");
        } else {
            System.out.println("Opção inválida.");
        }
        System.out.println("Verificar imagens geradas :D");
        scanner.close();
    }
}

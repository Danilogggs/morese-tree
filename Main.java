import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MorseTree morseTree = null;

        try {
            morseTree = new MorseTree();
            MorseTreeImageGenerator.saveTreeImage(morseTree.getRoot(), "tree.png");
            System.out.println("Arvore: tree.png");
        } catch (IOException e) {
            System.err.println("Erro ao salvar tree.png: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Decodificar Morse para Texto");
        System.out.println("2 - Codificar Texto para Morse");
        System.out.print("Selecione a opção: ");
        String option = scanner.nextLine().trim();

        if (option.equals("1")) {
            System.out.print("Digite o código Morse ('/' entre palavras, se for mais de uma): ");
            String morseInput = scanner.nextLine();
            String decoded = morseTree.decode(morseInput);
            System.out.println("Texto decodificado: " + decoded);
            try {
                MorseTreeImageGenerator.saveTreeImageWithPath(
                        morseTree.getRoot(), morseInput, "decode.png"
                );
                System.out.println("Arvore decode: decode.png");
            } catch (IOException e) {
                System.err.println("Erro ao salvar decode.png: " + e.getMessage());
            }

        } else if (option.equals("2")) {
            System.out.print("Digite o texto: ");
            String textInput = scanner.nextLine();
            String encoded = morseTree.encode(textInput);
            System.out.println("Código Morse: " + encoded);
            try {
                MorseTreeImageGenerator.saveTreeImageWithPath(
                        morseTree.getRoot(), encoded, "encode.png"
                );
                System.out.println("Arquivo encopde: encode.png ");
            } catch (IOException e) {
                System.err.println("Erro ao salvar encode.png: " + e.getMessage());
            }

        } else {
            System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}

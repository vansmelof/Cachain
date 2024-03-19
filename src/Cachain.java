import java.util.ArrayList;
import com.google.gson.GsonBuilder;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Cachain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int dificuldade = 5;

    public static void main(String[] args) {

        blockchain.add(new Block(" Olá, sou a primeira block", "0"));
        System.out.println("Tentando minerar block 1...");
        blockchain.get(0).minerar(dificuldade);

        blockchain.add(new Block(" Olá, sou a segunda block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tentando minerar block 2...");
        blockchain.get(1).minerar(dificuldade);

        blockchain.add(new Block(" Olá, sou a terceira block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Tentando minerar block 3...");
        blockchain.get(2).minerar(dificuldade);

        System.out.println("\nBlockchain é válida: " + validarChain());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\n A block chain: ");
        System.out.println(blockchainJson);
    }
    public static Boolean validarChain() {
        Block blockAtual;
        Block blockAnterior;
        String hashAlvo = new String(new char[dificuldade]).replace('\0', '0');

        //loop para percorrer a blockchain
        for (int i = 1; i < blockchain.size(); i++) {
            blockAtual = blockchain.get(i);
            blockAnterior = blockchain.get(i - 1);
            //comparar as hashs registradas com as geradas no calculo:
            if (!blockAtual.hash.equals(blockAtual.calcularHash())) {
                System.out.println("Hash inválida!");
                return false;
            }
            if (!blockAnterior.hash.equals(blockAtual.hashAnterior)) {
                System.out.println("Hash anterior inválida!");
                return false;
            }
            if(!blockAtual.hash.substring(0, dificuldade).equals(hashAlvo)){
                System.out.println("Essa block não foi minada");
                return false;
            }
        }
        return true;
    }
}
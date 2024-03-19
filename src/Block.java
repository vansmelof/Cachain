import java.util.Date;

public class Block {

    public String hash;
    public String hashAnterior;
    private String dados;
    private long marcadorTempo;
    private int nonce;

    public Block(String dados, String hashAnterior) {
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.marcadorTempo = new Date().getTime();

        this.hash = calcularHash();
    }

    public String calcularHash(){
        String hashNovo = StringUtil.Sha256(
                hashAnterior +
                        Long.toString(marcadorTempo) +
                        Integer.toString(nonce) +
                        dados
        );
        return hashNovo;
    }

    public void minerar(int dificuldade){
        //cria uma string com dificuldade * 0
        String alvo = new String(new char[dificuldade]).replace('\0', '0');
        while(!hash.substring(0, dificuldade).equals(alvo)){
            nonce++;
            hash = calcularHash();
        }
        System.out.println("Block Minado!!!: " + hash);
    }
}

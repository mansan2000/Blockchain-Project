package Blockchain;

public class Main {
    public static void main(String[] args)  {
        Chain.updateFromLedger();
        Mine block = new Mine("Alex gives Chad $10");
        block.createHash();
        block.mine();
    }
}

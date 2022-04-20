package Blockchain;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Chain.updateFromLedger();
        Mine block = new Mine("another block datanothe");
        block.createHash();
        block.mine();
    }
}

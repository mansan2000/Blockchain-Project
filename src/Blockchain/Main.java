package Blockchain;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Chain.updateFromLedger();
        Mine block = new Mine("a bunch of different data and more and more data","I send you money");
        block.createHash();
        System.out.println(block.getHash());
        block.mine();
        System.out.println(block.getHash());
    }
}

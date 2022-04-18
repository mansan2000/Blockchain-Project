package Blockchain;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Block block = new Block("","Hello, World!");
        block.createHash();
        System.out.println(block.getHash());
        block.mine();
        System.out.println(block.getHash());
    }
}

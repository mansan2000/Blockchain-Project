package Blockchain;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Block block = new Block("a bunch of different data and more and more data","Hello, world!");
        block.createHash();
        System.out.println(block.getHash());
        block.mine();
        System.out.println(block.getHash());
    }
}

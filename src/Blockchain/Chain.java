package Blockchain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Chain {
    private String previousHash, newHash, transactions;
//    private static Block prospectiveBlock;
    private int nonce;
    private static ArrayList<Block> chain = new ArrayList<>();
    public Chain(Block prospectiveBlock){

//        this.prospectiveBlock = prospectiveBlock;
    }
    public static void updateFromLedger(){


            try {
                Scanner inputFile = new Scanner(new File("ledger.txt"));
                while (inputFile.hasNextLine()) {

                    Scanner s = new Scanner(inputFile.nextLine());
//                    System.out.println(s.nextLine());
                    addEachBlock(s);

//                   inputFile.nextLine();
//                    counter++;

                }
                inputFile.close();
            } catch (FileNotFoundException ff) {
                System.out.println("\n\n\n\n\nException " + ff);
            }
    }
    public static void addEachBlock(Scanner s){
        s.useDelimiter(",");

        try {
//            while (s.hasNext()) {
                Block x = new Block(s.next(),s.next(),s.next(),s.next());
                Chain.addBlock(x);
//            System.out.println("this"+x.getNonce());
                writeToLedger();

//            }
        } catch (Exception e) {
            System.out.println("Exception   "+e);
        }
    }
    public static void writeToLedger(){
        try {
            PrintWriter ledger = new PrintWriter("ledger.txt");
            for (Block x : chain) {
//                System.out.println("nonce: "+x.getNonce());
                ledger.println(x.getPreviousHash()+","+x.getHash()+","+x.getTransactions()+","+x.getNonce());

            }
            ledger.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void addBlock(Block x){
        if(chain.size()<1){
            chain.add(x);
        }
        else if (validate(x)) {
            chain.add(x);
        }
        else {
            System.out.println("Not a valid Block");
        }
    }
    public static boolean validate(Block prospectiveBlock){

        return prospectiveBlock.getHash().equals(createHash(prospectiveBlock.getTransactions(), prospectiveBlock.getNonce()));
    }
    public static String createHash(String transactions, String nonce) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String contents = transactions+nonce;
            byte[] hash = digest.digest(contents.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X", b));
            }
            result = String.valueOf(sb).toLowerCase(Locale.ROOT);
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
//        return result;
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

    public void setChain(ArrayList<Block> chain) {
        this.chain = chain;
    }
    public static String getPreviousHash(){
        return chain.get(chain.size()-1).getHash();
    }
}

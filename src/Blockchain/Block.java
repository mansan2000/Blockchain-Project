package Blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Locale;

public class Block {
    private String previousHash, timeStamp, hash, data;
    private long comparableValueOfHash;
    private int nonce;
    public Block(String previousHash, String data){
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.timeStamp= String.valueOf(date);
        this.data=data;
        this.previousHash=previousHash;
        this.nonce =0;
        this.hash="";

    }
    public void createHash() {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String contents = this.data+this.nonce;
            byte[] hash = digest.digest(contents.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X", b));
            }
            result = String.valueOf(sb).toLowerCase(Locale.ROOT);
        }catch (Exception e){
            System.out.println(e);
        }
        this.hash = result;
//        return result;
    }


    public void mine(){
        long startTime = System.currentTimeMillis();
        int counter = 1;
        comparableValueOfHash = hash.hashCode();

//      Creates a BigInteger with a value of 2 so that it can be used with the compareTo method that requires BigInteger
        BigInteger baseTwo = new BigInteger("2");

//      Exponent that sets the difficulty of mining
        int exponent = 232;

//      Number value of hash so that it can be mathematically compared
        BigInteger hashValue = new BigInteger(hash,16);

//      A BigInteger with the value of 2^ exponent
        BigInteger powValue = baseTwo.pow(exponent);

//      rehashes hash increasing the nonce by one each time until the value of hash is less than powValue
        while (hashValue.compareTo(powValue)>=0){
            counter++;
            this.nonce++;
            createHash();
            hashValue = new BigInteger(hash,16);
            comparableValueOfHash = hash.hashCode();
            System.out.println(hash);
        }


        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;
        double i = (double) time;
        System.out.println("That took " + (endTime - startTime) + " milliseconds"+"\nNumber of hashes tried: "+ nonce +"\ncalculations per second:   "+counter/(i/1000));
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}



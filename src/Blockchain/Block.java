package Blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Block {
    private String previousHash, timeStamp, hash, data;
    private long comparableValueOfHash;
    private int proofOfWork;
    public Block(String previousHash, String data){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.timeStamp= String.valueOf(date);
        this.data=data;
        this.previousHash=previousHash;
        this.proofOfWork=0;
        this.hash="";

    }
    public void createHash() {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String contents = this.data+ this.proofOfWork;
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


//        this.proofOfWork = (long) 3522424073L;
        int counter = 0;
//        BigInteger x = new BigInteger(hash, 10);

//        System.out.println("Big int: "+x);
//        while (!hash.matches("^0000.*")){
//        long longHash = Long.valueOf(hash);
        comparableValueOfHash = hash.hashCode();
        String valueOfPow = String.valueOf(Math.pow(2,240));
        BigInteger x = new BigInteger("2");
        int ex = 242;

        BigInteger value = new BigInteger(hash,16);
        BigInteger pow = x.pow(ex);
        System.out.println(valueOfPow);
//        Long x = Long.parseLong(hash.toUpperCase(),16);
        System.out.println("x: "+ value);
//        BigInteger longValueOfHash =  BigInteger.valueOf(longHash);
//        System.out.println("this: "+ Math.pow(2,236.94631));
//        System.out.println(longValueOfHash)
        System.out.println("value: "+value);
        System.out.println("pow: "+pow);
        while (value.compareTo(pow)>=0){
            counter++;
//            2,30.3518
//            this.proofOfWork = this.proofOfWork+2;
            this.proofOfWork++;
            createHash();
            value = new BigInteger(hash,16);
            comparableValueOfHash = hash.hashCode();
            System.out.println(hash);
//            System.out.println(data+pcroofOfWork);
        }
        long endTime = System.currentTimeMillis();

        long time = endTime - startTime;
        double i = (double) time;
//        System.out.println(counter/(i/1000));
//        double x = (i/1000)/counter;
//        System.out.println(x);
        System.out.println("That took " + (endTime - startTime) + " milliseconds"+"\nNumber of hashes tried: "+counter+"\ncalculations per second:   "+counter/(i/1000));
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}



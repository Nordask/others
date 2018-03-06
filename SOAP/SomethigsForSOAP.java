package SOAP;

import java.security.MessageDigest;
import java.util.Base64;


public class SomethigsForSOAP {

    public static String buildPasswordDigest(String password, String nonce, String dateTime){
        MessageDigest sha1;
        String passwordDigest=null;
        try {
            sha1= MessageDigest.getInstance("SHA-1");
            byte[] hash = MessageDigest.getInstance("SHA-1").digest(password.getBytes("UTF-8"));

            sha1.update(Base64.getDecoder().decode(nonce));
            sha1.update(dateTime.getBytes("utf-8"));
            sha1.update(password.getBytes("utf-8"));

            passwordDigest = new String(Base64.getEncoder().encode(sha1.digest()));
            sha1.reset();
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        return passwordDigest;
    }

    public static void main(String args[]){
        /*
        password
        A1+RA1G1EUSTUiuKB9frWZziaic=
         */
        System.out.println(SomethigsForSOAP.buildPasswordDigest("password",
                                                    "shmYpOKrJP7WC3DPWE1Phg==",
                                                    "2018-03-06T09:42:19.678Z"));

        /*
        vvolodia
        XFV7lrIT1nXwfWhXcCzCOZyPxAY=
         */
        System.out.println(SomethigsForSOAP.buildPasswordDigest("vvolodia",
                "7dMmSZRPRn8akc4slSmEfg==",
                "2018-03-06T09:43:43.790Z"));
    }
}

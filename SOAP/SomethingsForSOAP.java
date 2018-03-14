package SOAP;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;


public class SomethingsForSOAP {

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
    /*
    public static String buildPasswordDigest(String password, String nonce) {
        DateFormat datePattern = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSS ZZZ");
        Calendar cal = Calendar.getInstance();
        return buildPasswordDigest(password, password, datePattern.format(cal.getTime()));
    }
    */

    public static String buildPasswordDigest(String password) {
        //String currentTime = getCurrentTime();
        //String nonce = generateNonceByCurrentTime(currentTime);
        return buildPasswordDigest(password, generateNonce(), getCurrentTime());
    }

    private static String generateNonce()
    {

        //SecureRandom random = SecureRandom.getInstance("");
        SecureRandom random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());
        byte[] nonceBytes = new byte[16];
        random.nextBytes(nonceBytes);

        return Base64.getEncoder().encodeToString(nonceBytes);
    }

    private static String generateNonceByCurrentTime(String currentTime)
    {
        //String dateTimeString = getCurrentTime();
        byte[] nonceBytes = currentTime.getBytes();//new byte[16];
        return Base64.getEncoder().encodeToString(nonceBytes);
    }

    private static String getCurrentTime(){
        DateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS ZZZ");
        Calendar cal = Calendar.getInstance();
        return datePattern.format(cal.getTime());
    }
        public static void main(String args[]){
        /*
        password
        A1+RA1G1EUSTUiuKB9frWZziaic=
         */
        System.out.println(SomethingsForSOAP.buildPasswordDigest("password",
                                                    "shmYpOKrJP7WC3DPWE1Phg==",
                                                    "2018-03-06T09:42:19.678Z"));

        /*
        vvolodia
        XFV7lrIT1nXwfWhXcCzCOZyPxAY=
         */
        System.out.println(SomethingsForSOAP.buildPasswordDigest("vvolodia",
                "7dMmSZRPRn8akc4slSmEfg==",
                "2018-03-06T09:43:43.790Z"));

        DateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS ZZZ");
        Calendar cal = Calendar.getInstance();
        System.out.println(datePattern.format(cal.getTime()));
        System.out.println(buildPasswordDigest("Vvolodia"));
    }
}

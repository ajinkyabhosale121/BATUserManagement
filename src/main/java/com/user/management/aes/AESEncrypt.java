package com.user.management.aes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
 
public class AESEncrypt {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        
        try {
        	
//        	File aesfile = new File("AESKey.txt");
//        	
//        	if (aesfile.exists())
//        	{
//        		FileInputStream input = new FileInputStream(aesfile);
//        		byte[] in = new byte[(int)aesfile.length()];
//        		input.read(in);
//        		secretKey = new SecretKeySpec(in, "AES");
//        		input.close();
//        		
//        	}
//        	else
//        	{
//	            key = myKey.getBytes("UTF-8");
//	            sha = MessageDigest.getInstance("SHA-1");
//	            key = sha.digest(key);
//	            key = Arrays.copyOf(key, 16);
//	            secretKey = new SecretKeySpec(key, "AES");
//	            FileOutputStream output = new FileOutputStream("AESKey.txt");
//	            output.write(secretKey.getEncoded());
//	            output.close();
//        	}
        	
        		key = myKey.getBytes("UTF-8");
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key, 16);
	            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}

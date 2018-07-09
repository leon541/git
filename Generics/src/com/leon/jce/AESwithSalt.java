package com.leon.jce;

import java.security.spec.KeySpec;  
  
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.SecretKeySpec;  
  
/**
 * 
 * This class is an utility class to encrypt/decrypt with AES/CBC with fixed  
 * @author Lwang
 *
 */

public class AESwithSalt { 
	/*
	 * fixed parameters
	 */
    private  int interationCount = 1000;  
    private  int keyLength = 128;  // 256 is not supported by default JRE
    private  int saltLenth = keyLength;  
    /*
     * variables  
     */
    private byte salt[];  // salt   
    private byte iv[];    // iv
    private String password;  // password
  
    private Cipher cipher_encrypt;
    private Cipher cipher_decrypt;
 
    private static AESwithSalt instance;
    
    public static AESwithSalt getInstance() {
    	if(instance == null) {
    		instance = new AESwithSalt();
    		try {
    		instance.initCiphers();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	return instance;
    }
    
    private  AESwithSalt() {
    	this ("password", "salt", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 });
    }
    
	private  AESwithSalt (String password, String salt, byte [] iv) {
    	this.password = password;
    	this.salt = salt.getBytes();
    	this.iv =  iv;
    }
    
	private AESwithSalt (String password, String salt, byte [] iv, int keyLength, int count, int saltLength) {
    	this.password = password;
    	this.salt = salt.getBytes();
    	this.iv =  iv;
    	this.keyLength = keyLength;
    	this.interationCount = count;
    	this.saltLenth = saltLength;
    }
	
    
    public void initCiphers() throws Exception {
    	KeySpec keySpec = new PBEKeySpec(password.toCharArray(),salt,interationCount,keyLength);  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");  
        byte[] keyBytes = keyFactory.generateSecret(keySpec).getEncoded();  
        SecretKey key = new SecretKeySpec(keyBytes, "AES");           
      
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        
        cipher_encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");  
        cipher_encrypt.init(Cipher.ENCRYPT_MODE,key,ivParams);  
    	
        cipher_decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher_decrypt.init(Cipher.DECRYPT_MODE,key,ivParams);
       
    }
    //= Cipher.getInstance("AES/CBC/PKCS5Padding");  
    //IvParameterSpec ivParams = new IvParameterSpec(iv);  
      
    /*
     *  binary bytes to Hex String 
     */
    public static String byte2HexStr(byte[] b) {
    	String hs="";
    	String stmp="";
    	for (int n=0;n<b.length;n++) {
    		stmp=(Integer.toHexString(b[n] & 0XFF));
    		if (stmp.length()==1) {
    			hs=hs+"0"+stmp;
    		}else {
    			hs=hs+stmp;
    		}
    	}
    	return hs;
    }

    /*
     *  binary bytes to Hex String 
     */
    public static byte[] hexStr2byte(String hexString) {
    	if(hexString == null || hexString.length() == 0) 
    		return new byte[0];
    	int cnt = 0, len = 0;
		len = hexString.length()/2;
		byte[]  enUrl = new byte[len];
		// get the actual encrypted URL info
		for(cnt=0; cnt< hexString.length();){
			String mid = null;
			mid = hexString.substring(cnt, cnt+2);
			enUrl[cnt/2] = (byte)Integer.parseInt(mid,16);
			cnt = cnt+2;
		}
    	return enUrl;
    }
    
    /** 
     * encrypt with AES/CBC/PKCS5Padding with salt/iv and then base64
     */
    public String encrypt(String plaintext) throws Exception{     
    	if(cipher_encrypt != null) {  
    		byte[] ciphertext = cipher_encrypt.doFinal(plaintext.getBytes("UTF-8"));  
    		return byte2HexStr(ciphertext);
    	} else {
    		return null;
    	}
    }  
      
    /** 
     * decrypt base64 encoded string with AES/CBC/PKCS5Padding with salt/iv 
     */
    public String decrypt(String encry) throws Exception{                  
        if(cipher_decrypt != null) {
        	byte decry[] = cipher_decrypt.doFinal( hexStr2byte(encry));  
        	return new String(decry,"UTF-8");  
        } else {
        	return null;
        }
    }
    
 
	public void printParamters() {
		System.out.println("------------------parameters---------------------");
		System.out.println("password : " + this.password);
		System.out.println("salt     : " + new String(this.salt));
		System.out.print("iv:      : " );
		for (int one: iv) {
			System.out.print(String.valueOf(one));
			System.out.print(" ");
		}
		System.out.println("\nkey len  : " + this.keyLength);
		System.out.println("salt len : " + this.saltLenth);
		System.out.println("interation count: " + this.interationCount);
		System.out.println("------------------parameters---------------------");
	}
	
	public static void main(String [] argv) throws Exception {
		AESwithSalt aes = AESwithSalt.getInstance();
		aes.printParamters();
		//String url = "1234";
	    //String url = "messageID=100000000891&recipientID=100000000891&action=preview&username=lwang@tom.com&queuename=url-analysis&deviceID=4222E7A2-3852-770C-EB3D-E15E7805AD6F&userPriviledge=1";
		String url = "messageID=100000000675&recipientID=100000000675&action=preview&username=lwang@tom.com&queuename=spam&deviceID=4222E7A2-3852-770C-EB3D-E15E7805AD6F&userPriviledge=1";
		System.out.println("------------------original---------------------");
		System.out.println("length:" +url.length());
		System.out.println(url);
		System.out.println("------------------encrypt---------------------");
		
		String encrypted = aes.encrypt(url);
		System.out.println("length:" +encrypted.length());
		System.out.println(encrypted);
		
		System.out.println("------------------decrypt---------------------");
		String decrypt = aes.decrypt(encrypted);
		System.out.println("length:" +decrypt.length());
		System.out.println(decrypt);
		
	}
	
}  
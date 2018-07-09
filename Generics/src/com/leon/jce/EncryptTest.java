package com.leon.jce;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptTest {


	public static void main(String[] args) throws Exception {
		String key = "keykeykeykeykeykey";
		String before = "1234567890";
		EncryptTest et = new EncryptTest();
		byte [] encrypt =  et.encrypt( before.getBytes(), et.getSharedKey());
		System.out.println("-----------------encrypt--------------------");
		System.out.println(new String(encrypt));

		System.out.println("-----------------decrypt--------------------");

		byte [] decry = et.decrypt(encrypt, et.getSharedKey());
		String decryString = new String(decry);
		System.out.println("length:"+decryString.length());
		System.out.println(decryString);
		
		System.out.println("length-trim:"+decryString.trim().length());
		System.out.println(decryString.trim());
		

		System.out.println("-----------------decrypt more --------------------");
		//https://10.206.12.109:9449/pem/pages/digestProcess/digestProcess.jsf?content=6c988a07765e1d753949e0ee9732947f59cae603786db8a2307528fea175928d8409b3bd788a915c719ece6cf1d1c6f3aceb307b5ff5acbecb75f13bfe087e4da2c35c7386a2a6265fcde27ee3e405e4cdd65333a21ad8a84451ffb492474f095942acbd5f8f63a6d3364c96045ff19beb04b14c9439ef0b782f21e19424f2e7198419877d63b21dde2025e42b35ab3c62c9c6a2bff447bd0eab1d9deee981cc22681511368634fb750ff72bd2a1a392
		String toDecryption = "6c988a07765e1d753949e0ee9732947f59cae603786db8a2307528fea175928d8409b3bd788a915c719ece6cf1d1c6f3aceb307b5ff5acbecb75f13bfe087e4da2c35c7386a2a6265fcde27ee3e405e4cdd65333a21ad8a84451ffb492474f095942acbd5f8f63a6d3364c96045ff19beb04b14c9439ef0b782f21e19424f2e7198419877d63b21dde2025e42b35ab3c62c9c6a2bff447bd0eab1d9deee981cc22681511368634fb750ff72bd2a1a392" ;


		String de = et.decryptContent(toDecryption);
		System.out.println("length:"+de.length());
		System.out.println(de);

		// TODO Auto-generated method stub

	}

	public byte[] encrypt(byte[] data, byte[] key){
		try{
			if(data == null || key == null || data.length == 0 || key.length == 0){
				System.out.println("data or key is empty, it will return null!");
				return null;
			}
			Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			//Cipher desCipher = Cipher.getInstance("DES/ECB/NoPadding");
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);

			// Initialize the same cipher for decryption
			desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

			byte[] encryptedData = desCipher.doFinal(data);

			return encryptedData;

		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return null;
	}	

	public byte[] decrypt(byte[] data, byte[] key){
		try{
			if(data == null || key == null || data.length == 0 || key.length == 0){
				System.out.println("data and key is empty, it will return null!");
				return null;
			}
			Cipher desCipher = Cipher.getInstance("DES/ECB/NoPadding");
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(dks);
			// Initialize the same cipher for decryption
			desCipher.init(Cipher.DECRYPT_MODE, secretKey);
			// Decrypt the ciphertext
			byte[] decryptedData = desCipher.doFinal(data);
			return decryptedData;

		}catch (Exception e){
			System.out.println("Decrypt failed based on algorithm: DES/ECB/NoPadding, it will try to use DES/ECB/PKCS5Padding!");
			try {
				Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");			
				DESKeySpec dks = new DESKeySpec(key);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey secretKey = keyFactory.generateSecret(dks);
				// Initialize the same cipher for decryption
				desCipher.init(Cipher.DECRYPT_MODE, secretKey);
				// Decrypt the ciphertext
				byte[] decryptedData = desCipher.doFinal(data);
				return decryptedData;

			} catch (Exception e1) {
				System.out.println("Exception happen as decrypt data.");
				e1.printStackTrace();
			}
			return null;
		}
	}


	public byte[] getSharedKey() throws Exception{
		//String license="TSTNNC95NP2KK9F6";
		String license="TST36CS3K36VCQCQ";
		String pemTag="ESGPEM";
		byte[] key=null;
		byte[] sharedKey=new byte[8];
		int cnt=5, i=0;

		license = license + pemTag;

		key = this.md5(license);

		while(i < 8){
			sharedKey[i] = key[cnt];
			i++;
			cnt++;
		}
		return sharedKey;
	}

	public byte[] md5(String inputString){ 
		return encodeByMD5(inputString); 
	}

	private byte[] encodeByMD5(String originString){ 
		if(originString != null){ 

			try{ 
				MessageDigest md = MessageDigest.getInstance("MD5"); 

				byte[] results = md.digest(originString.getBytes()); 

				return results; 
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}	

	public String decryptContent(String content) throws Exception{

		String decodeStr,decryptStr;
		byte[] byteDecode,byteDecrypt;
		byte[] encryptedStr,sharedKey;
		byte[] enUrl;
		//String plainText = "messageID=1500000000035&recipientID=1500000000037&action=preview&username=jason@wbsn.com&queuename=virus";
		String rawContent;

		//byte[] testKey = new byte[] { 0x42, (byte)0xe2, 0x52, 0x6e, (byte) 0xf5, 0x04, 0x6a, 0x2e};
		sharedKey = getSharedKey();

		/* for test to the encryption */
		//byte[] temp = plainText.getBytes("utf-8");
		//encryptedStr = encrypt(temp, sharedKey);

		int cnt = 0, len = 0;
		len = content.length()/2;
		enUrl = new byte[len];

		// get the actual encrypted URL info
		for(cnt=0; cnt < content.length();){
			String mid = null;
			mid = content.substring(cnt, cnt+2);
			enUrl[cnt/2] = (byte)Integer.parseInt(mid,16);
			cnt = cnt+2;
		}

		// For test
		//String encryptedString = new String(encryptedStr);

		/* for test */
		//Base64 decoder = new Base64();
		//String base64EncodeStr = new String(decoder.encode(encryptedStr));
		//rawContent = java.net.URLEncoder.encode(encryptedString, "UTF-8");
		//String rawContentStr = new String( rawContent);

		/* base64 decode */
		//String base64DecodeStr = new String(decoder.decode(this.content.getBytes()));

		byteDecrypt = decrypt(enUrl, sharedKey);
		if(byteDecrypt!=null){
			decryptStr =  new String(byteDecrypt);
			return decryptStr;
		}
		return null;
	}


}
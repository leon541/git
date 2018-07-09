package com.leon.ssl;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.security.KeyStore;  
 
import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSocket;  
import javax.net.ssl.TrustManagerFactory;  
 
/** 
* SSL Client 
* @author Leo 
*/  
public class Client {  
 
   private static final String DEFAULT_HOST                    = "127.0.0.1";  
   private static final int    DEFAULT_PORT                    = 7777;  
 
   private static final String CLIENT_KEY_STORE_PASSWORD       = "password";  
   private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "password";  
 
   private SSLSocket           sslSocket;  
 
   /** 
    *  
    * @param args 
    */  
   public static void main(String[] args) {  
      Client client = new Client();
      System.out.println("client ---" );
       client.init();  
       System.out.println("client inited" );
       client.process();  
       System.out.println("client processed" );
   }  
 
  
   public void process() {  
       if (sslSocket == null) {  
           System.out.println("ERROR");  
           return;  
       }  
       try {  
           InputStream input = sslSocket.getInputStream();  
           OutputStream output = sslSocket.getOutputStream();  
 
           BufferedInputStream bis = new BufferedInputStream(input);  
           BufferedOutputStream bos = new BufferedOutputStream(output);  
 
           bos.write("1234567890".getBytes("utf-8"));  
           bos.flush();  
 
           byte[] buffer = new byte[200];  
           int length =  bis.read(buffer);  
           System.out.println(new String(buffer, 0, length));  
 
           sslSocket.close();  
       } catch (IOException e) {  
           System.out.println(e);  
       }  
   }  
 
 
   public void init() {  
       try {  
           SSLContext ctx = SSLContext.getInstance("SSL");  
 
           KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
           TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");  
 
           KeyStore ks = KeyStore.getInstance("JKS");  
           KeyStore tks = KeyStore.getInstance("JKS");  
 
           ks.load(new FileInputStream("bin/client.jks"), CLIENT_KEY_STORE_PASSWORD.toCharArray());  
           //tks.load(new FileInputStream("bin/client_trust.jks"), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());  
           tks.load(new FileInputStream("bin/client.jks"), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());
           
           kmf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());  
           tmf.init(tks);  
 
           ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);  
 
           sslSocket = (SSLSocket) ctx.getSocketFactory().createSocket(DEFAULT_HOST, DEFAULT_PORT);  
       } catch (Exception e) {  
           System.out.println(e);  
       }  
   }  
 
}  
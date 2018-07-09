package com.leon.ssl;

import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.Socket;  
import java.security.KeyStore;  
 
import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLServerSocket;  
import javax.net.ssl.TrustManagerFactory;  


public class Server implements Runnable{  


   private static final int    DEFAULT_PORT                    = 7777;  
   private static final String SERVER_KEY_STORE_PASSWORD       = "password";  
   private static final String SERVER_TRUST_KEY_STORE_PASSWORD = "password";  
 
   private SSLServerSocket     serverSocket;  
 
   /** 
    * @param args 
    */  
   public static void main(String[] args) {  
       Server server = new Server();  
       server.init();  
       Thread thread = new Thread(server);  
       thread.start();  
   }  
 
   public synchronized void start() {  
       if (serverSocket == null) {  
           System.out.println("ERROR");  
           return;  
       }  
       while (true) {  
           try {  
               Socket s = serverSocket.accept();  
               InputStream input = s.getInputStream();  
               OutputStream output = s.getOutputStream();  
 
               BufferedInputStream bis = new BufferedInputStream(input);  
               BufferedOutputStream bos = new BufferedOutputStream(output);  
 
               byte[] buffer = new byte[200];  
               int length = bis.read(buffer);  
               System.out.println("------receive:--------"+new String(buffer, 0, length).toString());  
 
               bos.write("yes".getBytes("utf-8"));  
               bos.flush();  
 
               s.close();  
           } catch (Exception e) {  
               System.out.println(e);  
           }  
       }  
   }  
   public void init() {  
       try {  
           SSLContext ctx = SSLContext.getInstance("SSL");  
 
           KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");  
           TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");  
 
           KeyStore ks = KeyStore.getInstance("JKS");  
           KeyStore tks = KeyStore.getInstance("JKS");  
 
           ks.load(new FileInputStream("bin/server.jks"), SERVER_KEY_STORE_PASSWORD.toCharArray());  
           tks.load(new FileInputStream("bin/server_trust.jks"), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());  
 
           kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());  
           tmf.init(tks);  
 
           ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);  
 
           serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(DEFAULT_PORT);  
           serverSocket.setNeedClientAuth(true);   
       } catch (Exception e) {  
           System.out.println(e);  
       }  
   }  
 
   public void run() {  
       // TODO Auto-generated method stub  
       start();  
   }  
}  
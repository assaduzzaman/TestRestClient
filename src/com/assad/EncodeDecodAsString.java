package com.assad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;





public class EncodeDecodAsString {
	
	
	public static void main(String [] args) throws FileNotFoundException, IOException {
		/**
		publicKeyToBase64();
		privateKeyToBase64();
		*/
		
//		PrivateKey privateKey = getPrivateKey();
		
//		System.out.println(privateKey);
		
//		getCipherText();
		getOriginalText();
	}
	
	
    public static byte[] decodeHeader(String base64Str) {
        //Decode the Auth Header to get the username and password
    	byte[] decoded = null;
    	try {
    		decoded = Base64.decodeBase64(base64Str.getBytes("UTF-8"));
         
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    	
    	return decoded;
    }
    public static String getBase64Str(byte [] tobeEncode) {
    	return new String(Base64.encodeBase64(tobeEncode));
    }
    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
        
    public static String publicKeyToBase64() throws FileNotFoundException, IOException {

 
		 return getBase64Key(PUBLIC_KEY_FILE);    	
    }
    public static String privateKeyToBase64() throws FileNotFoundException, IOException {
    	

 
		return getBase64Key(PRIVATE_KEY_FILE);     	
    }
    
    public static String getBase64Key(String keyLocation) throws FileNotFoundException, IOException {
		byte[] bytes = getKeyByte(keyLocation);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);  
		return encodedString;
    }
    

    public static byte [] getKeyByte(String keyLocation) throws FileNotFoundException, IOException{
    	ObjectInputStream inputStream = null;
    	
    	File file = new File(keyLocation);
    	
    	byte[] bytes = loadFile(file);
    	
    	return bytes;
    	
//    	inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
    }
    
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);
 
	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }
 
	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }
 
	    is.close();
	    return bytes;
	}   
	
	
	
	public static PrivateKey getPrivateKey() {
		 final PrivateKey privateKey = (PrivateKey)decodeMetaData("rO0ABXNyABRqYXZhLnNlY3VyaXR5LktleVJlcL35T7OImqVDAgAETAAJYWxnb3JpdGhtdAASTGphdmEvbGFuZy9TdHJpbmc7WwAHZW5jb2RlZHQAAltCTAAGZm9ybWF0cQB+AAFMAAR0eXBldAAbTGphdmEvc2VjdXJpdHkvS2V5UmVwJFR5cGU7eHB0AANSU0F1cgACW0Ks8xf4BghU4AIAAHhwAAACejCCAnYCAQAwDQYJKoZIhvcNAQEBBQAEggJgMIICXAIBAAKBgQCbPNAiYKrd4vQGSWNp7hxyQy0Jo0sr+uFFBKQfwCCOTQLGim/u2hqQbmqOslSt5db3KEgi/HG1+BO9LwLx7p65m5yhs3vAOHPMD//OYZr1sU0B6irp8w7mM+9A0MxIa0VyOOyOpVsv6l4V2+k1So4hkUIy7eFfLhgxYfmZF8L1vQIDAQABAoGARfPVZmhhFrOaeuq/HZDqyKJ0vQ8UZLEcqdk5jAUrpnQVeBoR7nPh3IulAMBjYCleAOnp97oCE5nCa1Gv5g09AHrwq25OWIbNOddjVRvjOwU1aFTryzxkaqyspSaC8NMGghaYdyC8V5BILotz92fogAgc00xpYsv3TSTVl7wD6RECQQDH0YmUqP3f3pGCPzlOaM9KNzeuahul8sJWVyMndp2HsUlS2eZPyEsKmObBNLY4LjWahtGWv+LGUBof+QzL9y9XAkEAxuJxsWA9f7/oO1JHexO3KfNhODcc8NhBiYy6FZY8m5nNUIAttziZhYPv7lW91VBsQNLKW6HtHqKq7y0iWjBbCwJBAMHMxd6MZObbTE+CGIem+d2mXnfoa/Zw0/G/w9XDopf36ADWXB1nSvj1QWGESXOFxfGg7uiCJWVoTV5TRAkTLjMCQCdWtQxTr/Zr3CCXtsHWdX1bwZgf9p6A9/2gE1Kfw983PdA1elVqYZtyLGwlE4Z1AOSVjScw0TklDdB/SfMy618CQEYnLCtZYAn0/SxkRAaI/yeojtDkSO+FxiV/DB5qe7fuWRMYnHB0bPPbnvJz8d+2mNHcOO5n5beoQ5Uj29Hho690AAZQS0NTIzh+cgAZamF2YS5zZWN1cml0eS5LZXlSZXAkVHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAB1BSSVZBVEU=");
		 return privateKey;
	}
	
	public static PublicKey getPublicKey() {
		final PublicKey publicKey = (PublicKey)decodeMetaData("rO0ABXNyABRqYXZhLnNlY3VyaXR5LktleVJlcL35T7OImqVDAgAETAAJYWxnb3JpdGhtdAASTGphdmEvbGFuZy9TdHJpbmc7WwAHZW5jb2RlZHQAAltCTAAGZm9ybWF0cQB+AAFMAAR0eXBldAAbTGphdmEvc2VjdXJpdHkvS2V5UmVwJFR5cGU7eHB0AANSU0F1cgACW0Ks8xf4BghU4AIAAHhwAAAAojCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAmzzQImCq3eL0Bkljae4cckMtCaNLK/rhRQSkH8Agjk0Cxopv7toakG5qjrJUreXW9yhIIvxxtfgTvS8C8e6euZucobN7wDhzzA//zmGa9bFNAeoq6fMO5jPvQNDMSGtFcjjsjqVbL+peFdvpNUqOIZFCMu3hXy4YMWH5mRfC9b0CAwEAAXQABVguNTA5fnIAGWphdmEuc2VjdXJpdHkuS2V5UmVwJFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0AAZQVUJMSUM=");
		return publicKey;
	}
    
    
	public static final Object decodeMetaData(String base64){
		  final Object serialisedObject;
		  final ByteArrayInputStream dataStream=new ByteArrayInputStream(Base64.decodeBase64(base64));
		  try {
		    final ObjectInputStream objectStream=new ObjectInputStream(dataStream);
		    serialisedObject=objectStream.readObject();
		  }
		 catch (  IOException e) {
		    throw new RuntimeException("Could not deserialise object",e);
		  }
		catch (  ClassNotFoundException e) {
		    throw new RuntimeException("Could not deserialise object",e);
		  }
		  return serialisedObject;
		}
	
	public static String getCipherText() {
		final byte[] cipherText = encrypt("{\"array\":[1,2,3],\"boolean\":true,\"null\":null,\"number\":123,\"object\":{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"},\"string\":\"Hello World\"}", getPublicKey());
	
		byte[] encoded = Base64.encodeBase64(cipherText);
		String encodedString = new String(encoded);
		
		return encodedString;
	}
	
	  public static final String ALGORITHM = "RSA";
	
	public static String getOriginalText() throws UnsupportedEncodingException {
		byte [] decoded = Base64.decodeBase64("Ci2aTYblheI8YeLuRIhqlXSQRpLH5r5cS9WwazyTRhLxhG9bMhkkIDKvcVlRa8MoiKoiLI4NfVLH6PrhFhcwADeOMZWGKr89yoeC8xJgH8e5ch3scT+5W6/aQOVbtjWxMgn0F7q8EhaoHlU8vPTwpcIf8qXoJRmaoTy1YnmSF7g=".getBytes("UTF-8"));
		
		final String plainText = decrypt(decoded, getPrivateKey());		
		
		
		
		
		return plainText;
		
		
	}
	
	  public static byte[] encrypt(String text, PublicKey key) {
		    byte[] cipherText = null;
		    try {
		      // get an RSA cipher object and print the provider
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);
		      // encrypt the plain text using the public key
		      cipher.init(Cipher.ENCRYPT_MODE, key);
		      cipherText = cipher.doFinal(text.getBytes());
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return cipherText;
		  }
	  
	  public static String decrypt(byte[] text, PrivateKey key) {
		    byte[] dectyptedText = null;
		    try {
		      // get an RSA cipher object and print the provider
		      final Cipher cipher = Cipher.getInstance(ALGORITHM);
		 
		      // decrypt the text using the private key
		      cipher.init(Cipher.DECRYPT_MODE, key);
		      dectyptedText = cipher.doFinal(text);
		 
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		 
		    return new String(dectyptedText);
		  }
    
    
}

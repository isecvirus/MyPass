/*    */ package org.virus.mpm.util.AES;
/*    */ 
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.security.GeneralSecurityException;
/*    */ import java.security.InvalidAlgorithmParameterException;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.spec.InvalidKeySpecException;
/*    */ import java.security.spec.KeySpec;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.crypto.BadPaddingException;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.IllegalBlockSizeException;
/*    */ import javax.crypto.NoSuchPaddingException;
/*    */ import javax.crypto.SecretKey;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.PBEKeySpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Encryption
/*    */ {
/* 27 */   private static final byte[] salt = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/* 28 */   private static final byte[] iv = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*    */   
/*    */   public static byte[] encrypt(String msg, String key) throws InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
/*    */     try {
/* 32 */       IvParameterSpec ivspec = new IvParameterSpec(iv);
/*    */       
/* 34 */       SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
/* 35 */       KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
/* 36 */       SecretKey temp = factory.generateSecret(spec);
/* 37 */       SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), "AES");
/*    */       
/* 39 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 40 */       cipher.init(1, secretKey, ivspec);
/*    */       
/* 42 */       return cipher.doFinal(msg.getBytes(vars.charset));
/* 43 */     } catch (InvalidKeyException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException|InvalidAlgorithmParameterException|IllegalBlockSizeException error) {
/* 44 */       Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, (String)null, error);
/* 45 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] encrypt(byte[] msg, String key) throws InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
/*    */     try {
/* 51 */       IvParameterSpec ivspec = new IvParameterSpec(iv);
/*    */       
/* 53 */       SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
/* 54 */       KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
/* 55 */       SecretKey temp = factory.generateSecret(spec);
/* 56 */       SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), "AES");
/*    */       
/* 58 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 59 */       cipher.init(1, secretKey, ivspec);
/*    */       
/* 61 */       return cipher.doFinal(msg);
/* 62 */     } catch (InvalidKeyException|BadPaddingException|InvalidKeySpecException|NoSuchAlgorithmException|NoSuchPaddingException|InvalidAlgorithmParameterException|IllegalBlockSizeException error) {
/* 63 */       Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, (String)null, error);
/* 64 */       return null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static byte[] encrypt(Path path, String key) {
/*    */     try {
/* 70 */       byte[] bytes = Files.readAllBytes(path);
/*    */       
/* 72 */       return encrypt(bytes, key);
/* 73 */     } catch (Exception error) {
/* 74 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/AES/Encryption.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.virus.mpm.util.AES;
/*    */ 
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.security.spec.KeySpec;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.SecretKey;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.PBEKeySpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Decryption
/*    */ {
/* 19 */   private static final byte[] salt = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/* 20 */   private static final byte[] iv = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*    */   
/*    */   public String decrypt(byte[] encrypted_text, String key) {
/*    */     try {
/* 24 */       IvParameterSpec ivspec = new IvParameterSpec(iv);
/*    */       
/* 26 */       SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
/* 27 */       KeySpec spec = new PBEKeySpec(key.toCharArray(), salt, 65536, 256);
/* 28 */       SecretKey temp = factory.generateSecret(spec);
/* 29 */       SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), "AES");
/*    */       
/* 31 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
/* 32 */       cipher.init(2, secretKey, ivspec);
/*    */       
/* 34 */       return new String(cipher.doFinal(encrypted_text));
/* 35 */     } catch (Exception error) {
/* 36 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String decrypt(Path path, String key) {
/*    */     try {
/* 47 */       byte[] bytes = Files.readAllBytes(path);
/*    */       
/* 49 */       return decrypt(bytes, key);
/* 50 */     } catch (Exception error) {
/* 51 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/AES/Decryption.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
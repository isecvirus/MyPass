/*    */ package org.virus.mpm.util.AES;
/*    */ 
/*    */ import java.nio.charset.Charset;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class vars
/*    */ {
/*    */   static final String algorithm = "AES";
/*    */   static final int iterationCount = 65536;
/*    */   static final int keyLength = 256;
/* 19 */   static final Charset charset = StandardCharsets.UTF_8;
/*    */   static final int encryption_mode = 1;
/*    */   static final String encryption_algorithm = "PBKDF2WithHmacSHA256";
/*    */   static final String encryption_transformation = "AES/CBC/PKCS5Padding";
/*    */   static final int decryption_mode = 2;
/*    */   static final String decryption_algorithm = "PBKDF2WithHmacSHA256";
/*    */   static final String decryption_transformation = "AES/CBC/PKCS5PADDING";
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/AES/vars.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
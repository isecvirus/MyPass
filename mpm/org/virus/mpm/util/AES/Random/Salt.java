/*    */ package org.virus.mpm.util.AES.Random;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Salt
/*    */ {
/*    */   private static final int salt_len = 18;
/*    */   
/*    */   public static byte[] getRandom() {
/* 14 */     byte[] salt = new byte[18];
/*    */     
/* 16 */     for (int i = 0; i < 18; i++) {
/* 17 */       Random rb = new Random();
/* 18 */       rb.nextBytes(salt);
/*    */     } 
/*    */     
/* 21 */     return salt;
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/AES/Random/Salt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
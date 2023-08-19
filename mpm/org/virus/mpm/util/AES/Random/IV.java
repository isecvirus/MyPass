/*    */ package org.virus.mpm.util.AES.Random;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IV
/*    */ {
/*    */   private static final int iv_len = 16;
/*    */   
/*    */   public static byte[] getRandom() {
/* 13 */     byte[] iv = new byte[16];
/*    */     
/* 15 */     for (int j = 0; j < 16; j++) {
/* 16 */       Random rb = new Random();
/* 17 */       rb.nextBytes(iv);
/*    */     } 
/*    */     
/* 20 */     return iv;
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/AES/Random/IV.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
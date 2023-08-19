/*    */ package org.virus.mpm;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Scanner;
/*    */ import java.util.Set;
/*    */ import org.json.JSONObject;
/*    */ import org.virus.mpm.util.AES.Decryption;
/*    */ import org.virus.mpm.util.parser.DirectoryScanner;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Mpm
/*    */ {
/* 38 */   private static DirectoryScanner dir_scaner = new DirectoryScanner();
/* 39 */   private static Scanner scanner = new Scanner(System.in);
/*    */   private static Path selected_file;
/* 41 */   private static Decryption decryptor = new Decryption();
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 46 */       List<Path> this_dir = dir_scaner.current();
/*    */       
/* 48 */       this_dir.forEach(file -> System.out.println(String.format("  %s %s", new Object[] { Integer.valueOf(this_dir.indexOf(file) + 1), file })));
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 53 */         System.out.print("MPM: ");
/* 54 */         int selected = scanner.nextInt();
/*    */         
/* 56 */         selected_file = this_dir.get(selected - 1);
/* 57 */       } catch (Exception e) {
/* 58 */         System.err.print("Invalid selection!");
/* 59 */         System.exit(0);
/*    */       } 
/*    */       
/* 62 */       if (selected_file != null) {
/* 63 */         System.out.print("Password * ");
/* 64 */         String password = scanner.next();
/*    */         
/* 66 */         String data = decryptor.decrypt(selected_file, password);
/*    */         
/* 68 */         if (data != null) {
/* 69 */           JSONObject json = new JSONObject(data);
/* 70 */           JSONObject entities = json.getJSONObject("entities");
/* 71 */           Set<String> entities_arr = entities.keySet();
/*    */           
/* 73 */           for (int e = 0; e < entities_arr.size(); e++) {
/* 74 */             String entity = ((String[])entities_arr.toArray((T[])new String[0]))[e];
/* 75 */             System.out.println(String.format("     %s %s", new Object[] { Integer.valueOf(e + 1), entity }));
/*    */           } 
/*    */           while (true) {
/*    */             try {
/*    */               while (true)
/* 80 */               { System.out.print("Entity #");
/* 81 */                 int selected = scanner.nextInt();
/* 82 */                 String entity = entities_arr.toArray()[selected - 1].toString();
/* 83 */                 String entity_password = entities.getJSONObject(entity).getString("password");
/*    */                 
/* 85 */                 StringSelection selection = new StringSelection(entity_password);
/* 86 */                 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null); }  break;
/* 87 */             } catch (Exception error) {
/* 88 */               System.err.println(error.getMessage());
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/* 93 */     } catch (Exception error) {
/* 94 */       error.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/Mpm.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.virus.mpm.util.parser;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.nio.file.DirectoryStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DirectoryScanner
/*    */ {
/*    */   public List<Path> current() throws IOException {
/* 18 */     List<Path> files = new ArrayList<>();
/* 19 */     Path dir = Paths.get(System.getProperty("user.dir"), new String[0]);
/*    */     
/* 21 */     DirectoryStream<Path> stream = Files.newDirectoryStream(dir); 
/* 22 */     try { for (Path file : stream) {
/* 23 */         if (Files.isRegularFile(file, new java.nio.file.LinkOption[0])) {
/* 24 */           files.add(file.getFileName());
/*    */         }
/*    */       } 
/* 27 */       if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null)
/*    */         try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/* 29 */      return files;
/*    */   }
/*    */   
/*    */   public List<Path> scan(String dir) throws IOException {
/* 33 */     List<Path> files = new ArrayList<>();
/*    */     
/* 35 */     DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(dir, new String[0])); 
/* 36 */     try { for (Path file : stream) {
/* 37 */         if (Files.isRegularFile(file, new java.nio.file.LinkOption[0])) {
/* 38 */           files.add(file);
/*    */         }
/*    */       } 
/* 41 */       if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null)
/*    */         try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/* 43 */      return files;
/*    */   }
/*    */ }


/* Location:              /home/virus/mpm/mpm-1.0.0-beta.jar!/org/virus/mpm/util/parser/DirectoryScanner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
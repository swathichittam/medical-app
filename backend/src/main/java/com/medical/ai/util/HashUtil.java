package com.medical.ai.util;
import org.springframework.web.multipart.MultipartFile;
import java.security.MessageDigest;
import java.util.Base64;

public class HashUtil {
 public static String generateHash(MultipartFile file){
  try{
   MessageDigest md = MessageDigest.getInstance("MD5");
   return Base64.getEncoder().encodeToString(md.digest(file.getBytes()));
  }catch(Exception e){ throw new RuntimeException(); }
 }
}

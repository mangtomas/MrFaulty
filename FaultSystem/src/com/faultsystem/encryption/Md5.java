package com.faultsystem.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class Md5 {
    public String str;

 public String md5s(String plainText) {
  try {
   MessageDigest md = MessageDigest.getInstance("MD5");
   md.update(plainText.getBytes());
   byte b[] = md.digest();

   int i;

   StringBuffer buf = new StringBuffer("");
   for (int offset = 0; offset < b.length; offset++) {
    i = b[offset];
    if (i < 0)
     i += 256;
    if (i < 16)
     buf.append("0");
    buf.append(Integer.toHexString(i));
   }
   str = buf.toString();
  // System.out.println("result: " + buf.toString());// 32bit en
     // System.out.println(str);
  // System.out.println("result: " + buf.toString().substring(8, 24));// 16bit enc
  } catch (NoSuchAlgorithmException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();

  }
  return str;
 }

 public static void main(String agrs[]) {
  Md5 md51 = new Md5();
 String a = md51.md5s("asd");//encoding 4
     System.out.println("asd "+a);
 }
}
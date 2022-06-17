 package br.com.univesp.mercadocell.mercadocell.message;

 import lombok.AllArgsConstructor;
 import lombok.Data;

 @AllArgsConstructor
 @Data
 public class ResponseFile {
     private String name;
     private String url;
     private String type;
     private long size;

 }

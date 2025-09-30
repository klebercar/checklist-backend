package com.hotel.checklist.service;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import java.net.*; import java.net.http.*; import java.util.*; import com.fasterxml.jackson.databind.*;
@Service
public class StorageService {
  @Value("${app.storage.supabase.url}") String supabaseUrl;
  @Value("${app.storage.supabase.key}") String serviceKey;
  @Value("${app.storage.supabase.bucket}") String bucket;
  private HttpClient http(){ return HttpClient.newHttpClient(); }
  public Uploaded store(byte[] bytes, String originalName){
    try{
      String key="photos/"+UUID.randomUUID()+"-"+originalName.replaceAll("\\s+","_");
      var req=HttpRequest.newBuilder().uri(URI.create(supabaseUrl+"/storage/v1/object/"+bucket+"/"+key))
        .header("Authorization","Bearer "+serviceKey).header("Content-Type","application/octet-stream")
        .PUT(HttpRequest.BodyPublishers.ofByteArray(bytes)).build();
      var resp=http().send(req,HttpResponse.BodyHandlers.ofString());
      if(resp.statusCode()>=300) throw new RuntimeException("upload falhou: "+resp.statusCode()+" "+resp.body());
      return new Uploaded(key, sign(key, 7*24*3600));
    }catch(Exception e){ throw new RuntimeException(e); }
  }
  public String sign(String key,int expires) throws Exception{
    var body="{\"expiresIn\": "+expires+"}";
    var req=HttpRequest.newBuilder().uri(URI.create(supabaseUrl+"/storage/v1/object/sign/"+bucket+"/"+key))
      .header("Authorization","Bearer "+serviceKey).header("Content-Type","application/json")
      .POST(HttpRequest.BodyPublishers.ofString(body)).build();
    var resp=http().send(req,HttpResponse.BodyHandlers.ofString());
    if(resp.statusCode()>=300) throw new RuntimeException("sign falhou: "+resp.body());
    var node=new ObjectMapper().readTree(resp.body());
    return supabaseUrl+node.get("signedURL").asText();
  }
  public record Uploaded(String key,String url){}
}

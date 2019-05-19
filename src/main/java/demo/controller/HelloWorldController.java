package demo.controller;

import com.google.gson.Gson;
import demo.proto.OuterClass;
import demo.util.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xerial.snappy.Snappy;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

@RestController public class HelloWorldController {
  private static final Gson GSON = new Gson();

  @RequestMapping("/test")
  public void test(HttpServletRequest request) {
    OuterClass.ProtoBufBody protoBufBody = OuterClass.ProtoBufBody.newBuilder().setName("This is my name")
        .setPassword("This is password").build();
    String protoBufBodyStr = new String(protoBufBody.toByteArray());
    //protoBufBodyStr = new String(Base64.getEncoder().encode(protoBufBodyStr.getBytes()));
    byte[] compressed = protoBufBodyStr.getBytes();
    try {

      compressed = Snappy.compress(protoBufBody.toByteArray());
     // System.out.println("Compress is correct? " + Snappy.uncompressString(compressed) == );
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }
    HttpUtil.post("http://localhost:8080/hello", new String(compressed));
  }


  @RequestMapping("/hello") public String index(HttpServletRequest request) {
    String requestStr = "";
    try {
      requestStr = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
      //requestStr = new String(Base64.getDecoder().decode(requestStr.getBytes()));
      System.out.println("Request => " + requestStr);
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }
   // OuterClass.ProtoBufBody uncompressed = GSON.fromJson(requestStr, OuterClass.ProtoBufBody.class);
    //System.out.println("ProtoBufBody " + uncompressed);
    byte[] uncompressed = null;
    try {
      uncompressed = Snappy.uncompress(requestStr.getBytes("UTF-8"));
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }
    OuterClass.ProtoBufBody body = null;
    try {
      body = OuterClass.ProtoBufBody.parseFrom(uncompressed);
      System.out.println("Body " + body);
    } catch (Exception e) {
      System.out.println("Parse Exception " + e.getLocalizedMessage());
    }
    System.out.println("Hello World V5!");
    return "";
  }
}

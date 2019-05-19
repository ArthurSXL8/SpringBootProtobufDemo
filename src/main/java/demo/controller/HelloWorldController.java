package demo.controller;

import demo.proto.OuterClass;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController public class HelloWorldController {
  @RequestMapping("/hello") public String index() {
    System.out.println("Hello World V5!");
    OuterClass.ProtoBufBody body =
        OuterClass.ProtoBufBody.newBuilder().setName("myName").setPassword("myPassword").build();
    System.out.println("ProtoBufBody " + body.toString());
    return "";
  }
}

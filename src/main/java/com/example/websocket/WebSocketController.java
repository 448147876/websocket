package com.example.websocket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WebSocket webSocket;




    @RequestMapping("/{name}")
    public String webSocket(@PathVariable String name, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            //通过Model进行对象数据的传递
            model.addAttribute("username",name);
            return "socket";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }



    @GetMapping("/send/{name}")
    @ResponseBody
    public String websoket1(@PathVariable String name,String context) throws IOException {
        webSocket.sendMessageTo(context,name);
        return "发送到："+name+",成功!";
    }

    @RequestMapping("/sendAll/{name}")
    public String hello(String context) throws IOException {
        webSocket.sendMessageAll(context,"jack");
        return "发送到所有成功!";
    }

}

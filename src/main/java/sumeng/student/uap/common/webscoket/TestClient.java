/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: TestClient
 * Author: sumeng
 * Date: 2020/3/30 11:28
 */
package sumeng.student.uap.common.webscoket;/**
 * @ClassName: TestClient
 * @Description: 1
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/30 11:28
 */

import com.alibaba.fastjson.JSON;
import org.java_websocket.handshake.ServerHandshake;
import sumeng.student.uap.common.entity.Message;

import java.net.URI;
import java.util.Date;
import java.util.Scanner;

/**
 *
 *
 * @date 2020/3/30  11:28
 * @author 12068
 */
public class TestClient {

    public static void main( String[] args ) {
        String userId;
        String receiveId;
        String message;
         Boolean coonFalg = false;
        System.out.println("输入用户id");
        Scanner scanner=new Scanner(System.in);
        userId=scanner.nextLine();
        URI uri = URI.create("ws://192.168.31.112:8080/webChat/"+userId);
        ChatClient client = new ChatClient(uri) {
            @Override
            public void onMessage(String message) {
                Message chatMessage = JSON.parseObject(message,Message.class);
                System.out.println(chatMessage.getSendUserId()+":"+chatMessage.getMessageContant());
                //System.out.println(message);
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                super.onOpen(handshakedata);
            }
        };
        try {
            //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
            client.connectBlocking();
            coonFalg =true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (coonFalg){
            System.out.println("请输入想要聊天人的Id:");
            receiveId=scanner.nextLine();
            System.out.println("开始和:"+receiveId+"聊天了");
            client.send(initmessage(userId,receiveId,"1",0));
            Boolean chat=true;
            while (chat){
                message=scanner.nextLine();
                client.send(initmessage(userId,receiveId,message,1));
            }
        }else {
            System.out.println("连接服务器失败!");
        }

    }

    public static String initmessage(String userId,String receiveId,String message,int type){
        Message chatMessage = new Message();
        chatMessage.setMessageContant(message);
        chatMessage.setSendUserId(userId);
        chatMessage.setReceiveUserId(receiveId);
        chatMessage.setSendTime(new Date());
        chatMessage.setReceiveType(type);
        return JSON.toJSONString(chatMessage);
    }
}

/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: WebSocketServer
 * Author: sumeng
 * Date: 2020/4/2 23:57
 */
package sumeng.student.uap.common.webscoket;/**
 * @ClassName: WebSocketServer
 * @Description:
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/4/2 23:57
 */

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import sumeng.student.uap.attendance.service.MyMemberService;
import sumeng.student.uap.common.entity.Member;
import sumeng.student.uap.common.entity.Message;
import sumeng.student.uap.user.service.MyMessageService;

import javax.validation.constraints.NotNull;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @date 2020/4/2  23:57
 * @author 12068
 */
@Component
@ServerEndpoint(value ="/webChat/{userId}")
public class WebSocketServer {
    /**
     * 服务层 静态注入
     */
    private static MyMessageService messageService;
    private static MyMemberService memberService;

    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 此处是解决无法注入的关键
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }
    @Autowired
    public void setChatService(MyMessageService messageService,MyMemberService memberService) {
        WebSocketServer.messageService = messageService;
        WebSocketServer.memberService = memberService;
    }

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocketServer类为value保存起来
     */
    private static Map<String, WebSocketServer> allOnlineUser = new ConcurrentHashMap<String, WebSocketServer>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String userId;
    /**
     * 正在聊天
     */
    private int isChat=0;
    /**
     * 聊天对象
     */
    @NotNull
    private String chatUserId="";
    /**
     * 聊天对象类型
     */
    private int chatType;
    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session)
    {
        if(allOnlineUser.get(userId)==null){
            onlineNumber++;
        }
        logger.info("现在来连接的客户id："+session.getId()+"用户名："+userId);
        this.userId = userId;
        this.session = session;
        //设置能接收的消息体字节大小()
        session.setMaxTextMessageBufferSize(104857600);
        logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        allOnlineUser.put(userId, this);

    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("连接发生错误");
        error.printStackTrace();

    }
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        onlineNumber--;
        allOnlineUser.remove(userId);
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     * 消息分发
     * 0:服务器 1:用户 2:群组
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        logger.info("message  length=="+message.length());
        Message chatMessage=JSON.parseObject(message,Message.class);
        switch (chatMessage.getReceiveType()){
            //发送给服务器
            case 0:sendToService(chatMessage);break;
            //发送给用户
            case 1:sendMessage(chatMessage.getReceiveUserId(),chatMessage.getSendUserId(),message);break;
            //发送给群组
            case 2:sendMessageToGroup(chatMessage.getReceiveUserId(),message);break;
            default:break;
        }
        saveMessage(chatMessage);
        System.out.println( session + ": " + message );

    }

    public void saveMessage(Message message){
        if (message.getReceiveType()!=0){
            if (allOnlineUser.get(message.getReceiveUserId())!=null && allOnlineUser.get(message.getReceiveUserId()).chatUserId.equals(message.getSendUserId())){
                message.setIsReceive(1);
                message.setReceiveTime(new Date());
            }
            message.setIsSendSuccess(1);
            messageService.save(message);
        }
    }

    /**
     * 发送给服务器
     */
    public void sendToService(Message message){
        if (message.getMessageContant().equals("1")){
            this.chatType=message.getReceiveType();
            this.chatUserId=message.getReceiveUserId();
            this.isChat=1;
        }else {
            this.chatUserId="";
            this.isChat=0;
        }
    }

    /**
     * 发送消息 给指定用户
     * @param userId
     * @param chatUserId
     * @param message
     * @throws IOException
     */
    public Boolean sendMessage(String userId,String chatUserId,String message)throws IOException{
        if (allOnlineUser.get(userId)==null){
            return false;
        }
        else {
            if (allOnlineUser.get(userId).chatUserId.equals(chatUserId)){
                allOnlineUser.get(userId).session.getBasicRemote().sendText(message);
                return true;
            }
           return false;
        }
    }

    /**
     * 发送消息 给指定群组
     * @param groupId
     * @param message
     * @throws IOException
     */
    public void sendMessageToGroup(String groupId, String message) throws IOException {
        List<Member> members = memberService.getMemberBygroupId(groupId);
        if (members!=null){
            for (Member member : members) {
                sendMessage(member.getUserId(),member.getGroupId(),message);
            }
        }
    }

    /**
     * 发送消息 给全部用户
     * @param message
     * @throws IOException
     */

    public void sendMessageAll(String message) throws IOException {
        for (WebSocketServer item : allOnlineUser.values()) {
            item.session.getBasicRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }
}

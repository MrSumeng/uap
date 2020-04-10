/**
 * Copyright (C), 2019-2020, sumeng
 * FileName: ChatClient
 * Author: sumeng
 * Date: 2020/3/29 10:45
 */
package sumeng.student.uap.common.webscoket;/**
 * @ClassName: ChatClient
 * @Description: ChatClient
 * @Author: sumeng
 * @Version: 1.0.0
 * @Date: 2020/3/29 10:45
 */

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

/**
 *
 *
 * @date 2020/3/29  10:45
 * @author 12068
 */
public class ChatClient extends WebSocketClient {
    public ChatClient(URI serverUri) {
        super(serverUri, new Draft_6455());
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("JWebSocketClientonOpen()");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("JWebSocketClientonMessage()");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("JWebSocketClientonClose()");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("JWebSocketClientonError()");
    }
}

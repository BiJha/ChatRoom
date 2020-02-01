package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>(); // server tracks of all clients at a given time

    private static void sendMessageToAll(Message msg) {
        //TODO: add send message method.  // It will loop through all onlineSessions and send message to all onlineSessions
        msg.setOnlineCount(onlineSessions.size());
        for (Session session : onlineSessions.values()) {
            session.getAsyncRemote().sendText(JSON.toJSONString(msg));
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        // This code will implement when user joins the chat.
        String sessionId = session.getId();
        onlineSessions.put(sessionId, session); // It adds the session. Lets say it was 3 users in a chat and now it becomes 4.
        Message message = new Message();
        message.setType(Message.MessageType.JOIN);
        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        // This code will implement when user send message.

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Message message = new Message();
        message.setType(Message.MessageType.SPEAK);
        message.setUsername(jsonObject.getString("username"));
        message.setMsg(jsonObject.getString("msg"));
        sendMessageToAll(message);

    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        // This code will implement when user leaves the chat.

        String sessionId = session.getId();
        onlineSessions.remove(sessionId); // It removes the session. Lets say it was 3 users in a chat and now it becomes 2.
        Message message = new Message();
        message.setType(Message.MessageType.LEAVE);
        sendMessageToAll(message);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}

package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String  msg;
    private String type;
    private int onlineCount;


    // interface for MessageType which can SPEAK, JOIN and LEAVE.
    public interface MessageType {
        String SPEAK = "SPEAK";
        String JOIN = "JOIN";
        String LEAVE = "LEAVE";
    }

    // getter method for Username.
    public String getUsername() {
        return username;
    }

    // setter method for Username.
    public void setUsername(String username) {
        this.username = username;
    }

    // getter method for msg.
    public String getMsg() {
        return msg;
    }

    // setter method for msg.
    public void setMsg(String msg) {
        this.msg = msg;
    }

    // getter method for type.
    public String getType() {
        return type;
    }

    // setter method for type.
    public void setType(String type) {
        this.type = type;
    }

    // getter method for OnlineCount.
    public int getOnlineCount() {
        return onlineCount;
    }

    // setter method for OnlineCount.
    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }
}

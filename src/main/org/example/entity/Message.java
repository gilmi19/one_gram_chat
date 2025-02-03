package main.org.example.entity;

import main.org.example.enums.MessageType;

////Создать сообщение (текст, входящее/исходящее, кому/от кого)
public class Message {
    private String content;
    private MessageType type;
    private User userFrom;
    private User userTo;

    public Message(String message, MessageType type, User userFrom, User userTo) {
        this.content = message;
        this.type = type;
        this.userFrom = userFrom;
        this.userTo = userTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}

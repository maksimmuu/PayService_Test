package com.example.Model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="transferlogs")
public class Log {

    @Id
    @Column(name="pay_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Account senderAccount;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver_id")
    private int receiverId;

    @Column(name = "receiver_name")
    private String receiverName;

    @Column(name = "send_amount")
    private double sendAmount;

    @Column(name = "logMessage")
    private String logMessage;

    @Column(name="time")
    private Timestamp timestamp;

    public Log(Account senderAccount, String senderName, int receiverId,
               String receiverName, double sendAmount, String logMessage, Timestamp timestamp) {
        this.senderAccount = senderAccount;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.sendAmount = sendAmount;
        this.logMessage = logMessage;
        this.timestamp = timestamp;
    }

    public Log() {

    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public double getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(double sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", senderAccount=" + senderAccount +
                ", senderName='" + senderName + '\'' +
                ", receiverId=" + receiverId +
                ", receiverName='" + receiverName + '\'' +
                ", sendAmount=" + sendAmount +
                ", logMessage='" + logMessage + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

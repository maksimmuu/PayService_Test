package com.example.Model;

import jakarta.persistence.*;

@Entity
@Table(name="transferlogs")
public class Log {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "logMessage")
    private String logMessage;

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", logMessage='" + logMessage + '\'' +
                '}';
    }
}

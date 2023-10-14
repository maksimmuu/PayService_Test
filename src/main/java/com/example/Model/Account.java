package com.example.Model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="account")
public class Account {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="amount")
    private double amount;

    @Column(name="country")
    private String country;

    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.EAGER)
    private List<Log> logs;

    @OneToOne(mappedBy = "accountId")
    private PermissionToTransfer permissionToTransfer;

    public Account(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Account() {}

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PermissionToTransfer getPermissionToTransfer() {
        return permissionToTransfer;
    }

    public void setPermissionToTransfer(PermissionToTransfer permissionToTransfer) {
        this.permissionToTransfer = permissionToTransfer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", country='" + country + '\'' +
                '}';
    }
}

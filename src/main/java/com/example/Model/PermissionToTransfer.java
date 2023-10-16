package com.example.Model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permissionToTransfer")
public class PermissionToTransfer implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name="account_id", referencedColumnName = "id")
    private Account accountId;

    @Column(name="block_account")
    private boolean blockAccount;

    public PermissionToTransfer(){}

    public PermissionToTransfer(Account accountId, boolean blockAccount) {
        this.accountId = accountId;
        this.blockAccount = blockAccount;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public boolean isBlockAccount() {
        return blockAccount;
    }

    public void setBlockAccount(boolean blockAccount) {
        this.blockAccount = blockAccount;
    }

    @Override
    public String toString() {
        return "PermissionToTransfer{" +
                "accountId=" + accountId +
                ", blockAccount=" + blockAccount +
                '}';
    }
}

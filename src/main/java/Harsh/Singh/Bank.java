package Harsh.Singh;

import java.util.Random;

public class Bank {
    Customer customer;
    private long balance;
    private long deposit;
    private long withdraw;
    private String userId;
    private long accountNo;

    public Bank() {}

    public Bank(Customer customer, long balance, long deposit, long withdraw, String userId, long accountNo) {
        this.customer = customer;
        this.balance = balance;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.userId = userId;
        this.accountNo = accountNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(long withdraw) {
        this.withdraw = withdraw;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "customer=" + customer +
                ", balance=" + balance +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", userId='" + userId + '\'' +
                ", accountNo=" + accountNo +
                '}';
    }
}

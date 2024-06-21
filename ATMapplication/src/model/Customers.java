package model;

public class Customers {
    private int accNo;
    private String name;
    private int pin;
    private double balance;

    public Customers(int accNo, String name, int pin, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

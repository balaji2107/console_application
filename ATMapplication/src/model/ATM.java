package model;

public class ATM {
    private double totalAmount;
    private int thousandCount;
    private int fiveHunderedCount;
    private int hunderedCount;

    public ATM(double totalAmount, int thousandCount, int fiveHunderedCount, int hunderedCount) {
        this.totalAmount = totalAmount;
        this.thousandCount = thousandCount;
        this.fiveHunderedCount = fiveHunderedCount;
        this.hunderedCount = hunderedCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getThousandCount() {
        return thousandCount;
    }

    public void setThousandCount(int thousandCount) {
        this.thousandCount = thousandCount;
    }

    public int getFiveHunderedCount() {
        return fiveHunderedCount;
    }

    public void setFiveHunderedCount(int fiveHunderedCount) {
        this.fiveHunderedCount = fiveHunderedCount;
    }

    public int getHunderedCount() {
        return hunderedCount;
    }

    public void setHunderedCount(int hunderedCount) {
        this.hunderedCount = hunderedCount;
    }
}

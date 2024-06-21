package model;

public class Transfer {
    static int id;
    private int accNo;
    private int Tid;
    private String TRemarks;
    private String Ttype;
    private double Tamount;

    public Transfer(int accNo, String TRemarks, String type, double tamount) {
        this.accNo = accNo;
        Tid = ++id;
        this.TRemarks = TRemarks;
        Ttype = type;
        Tamount = tamount;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public String getTRemarks() {
        return TRemarks;
    }

    public void setTRemarks(String TRemarks) {
        this.TRemarks = TRemarks;
    }

    public String getTtype() {
        return Ttype;
    }

    public void setTtype(String ttype) {
        Ttype = ttype;
    }

    public double getTamount() {
        return Tamount;
    }

    public void setTamount(double tamount) {
        Tamount = tamount;
    }
}

package com.example.liftcontrol;

import com.example.datalayer.LiftDatabase;
import com.example.model.Lift;

import java.util.List;
import java.util.Scanner;

public class LiftControlView {
    private LiftControlModel liftControlModel;
    public LiftControlView(){
        liftControlModel=new LiftControlModel(this);
    }

    public void init() {
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<5;i++){
            System.out.println("Enter the "+(i+1)+" Lift name: ");
            String name=sc.nextLine();
            System.out.println("Enter the lift position: ");
            int pos=sc.nextInt();
            sc.nextLine();
            Lift lift=new Lift(name,pos);
            liftControlModel.addLifts(lift);
        }
        createOption();
    }

    private void createOption() {
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("1.Display Lift Position \n2.Assign Lift \n3.exit");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    displayLiftPosition();
                    break;
                case 2:
                    getUserPosition();
                    break;
                case 3:
                    System.out.println("--Thank You---");
                    return;
                default:
                    System.out.println("Please Enter valid input");
            }
        }
    }

    private void getUserPosition() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the User Current Floor: ");
        int current=sc.nextInt();
        System.out.println("Enter the User Destination Floor: ");
        int dest=sc.nextInt();
        liftControlModel.assignLiftUp(current, dest);
    }

    public void displayLiftPosition() {

        List<Lift> lifts= LiftDatabase.getInstance().getLifts();
        System.out.println("---Lifts Current position---\n");
        for (Lift lift:lifts){
            System.out.println("Lift name: "+lift.liftName);
            System.out.println("Position: "+lift.position);
        }
    }
}

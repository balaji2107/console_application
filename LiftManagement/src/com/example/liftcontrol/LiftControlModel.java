package com.example.liftcontrol;

import com.example.datalayer.LiftDatabase;
import com.example.model.Lift;
import java.util.List;

public class LiftControlModel {
    private LiftControlView liftControlView;
    public LiftControlModel(LiftControlView liftControlView) {
        this.liftControlView=liftControlView;
    }

    public void addLifts(Lift lift) {
        LiftDatabase.getInstance().addLift(lift);
    }


    public void assignLiftUp(int current, int dest) {
        List<Lift> lifts=LiftDatabase.getInstance().getLifts();
        Lift lift=null;
        for(Lift lift1:lifts) {
            if(lift1.position==-1){
                continue;
            }
            if((current>=0 && current<=5) && (dest>=0 && dest<=5)){
                if(lift1.liftName.equalsIgnoreCase("l1") || lift1.liftName.equalsIgnoreCase("l2") || lift1.liftName.equalsIgnoreCase("l5")){
                    if(lift != null){
                        int getposition= Math.abs(current-lift1.position);
                        int pos=Math.abs(current-lift.position);
                        if(getposition==pos){
                            if(current<lift1.position){
                                lift=lift1;
                            }
                        }
                        else if(getposition<pos){
                            lift=lift1;
                        }
                    }
                    else {
                        lift=lift1;
                    }
                }
            }
            else if( (current==0 || current>=6 && current<=10) && (dest==0 || dest>=6 && dest<=10)){
                if(lift1.liftName.equalsIgnoreCase("l3") || lift1.liftName.equalsIgnoreCase("l4") || (dest!=0 && lift1.liftName.equalsIgnoreCase("l5")))
                {

                    if(lift != null){
                        int getposition= Math.abs(current-lift1.position);
                        int pos=Math.abs(current-lift.position);
                        if(getposition==pos){
                            if(current<lift1.position){
                                lift=lift1;
                            }
                        }
                        else if(getposition<pos){
                            lift=lift1;
                        }
                    }
                    else {
                        lift=lift1;
                    }
                }
            } else if (((current>=0 && current<=10) && (dest>=0 && dest<=10))) {
                if(lift1.liftName.equalsIgnoreCase("l5")){
                    lift=lift1;
                }
            }
        }
        lift.setPosition(dest);
        System.out.println("Lift "+lift.liftName+" assign");
    }

}

package com.example.datalayer;

import com.example.model.Lift;
import java.util.ArrayList;
import java.util.List;

public class LiftDatabase {
    public static LiftDatabase liftDatabase;
    List<Lift> lifts=new ArrayList<>();

    public static LiftDatabase getInstance(){
        if(liftDatabase==null){
            liftDatabase=new LiftDatabase();
        }
        return liftDatabase;
    }

    public List<Lift> getLifts(){
        return lifts;
    }
    public void addLift(Lift lift){
        lifts.add(lift);
    }

}
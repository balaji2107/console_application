import com.example.liftcontrol.LiftControlView;

public class LiftManagement {
    public static void main(String[] args) {
        LiftManagement liftManagement=new LiftManagement();
        liftManagement.create();
    }
    void create(){
        LiftControlView liftControlView=new LiftControlView();
        liftControlView.init();
    }
}

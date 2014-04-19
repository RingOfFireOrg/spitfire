package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author PyroTech
 */
public class Control {
    Runnable r;
    long start, stop;
    boolean state=false;
    boolean aPreVal=false;
    
    public Control(Runnable runnable) {
        r = runnable;
    }
    public void toggleState(boolean a, boolean b){
        if(a && !aPreVal){
            if(state){
                r.stop();
                state=false;
            }else{
                move(b);
                state=true;
            }
        }else{
            move(b);
        }
        aPreVal = a;
    }
    public void move(boolean b){
        if(b){
            r.start(-1);
        }else{
            r.start(1);
        }
    }
    public void tankDrive(double lStick, double rStick){
        r.start(lStick,rStick);
    }
    public void setTime(long sta, long sto) {
        start = sta;
        stop = sto;
    }
    
    public void timedRun(double speed) {   
        if (isInTime()) {
            r.start(speed);
        } else {
            r.stop();
        }
    }
    
    public void posToggle(boolean a, double pos1, double pos2)
    {   
        
        if (a == true){
            r.start(pos1);
        } else {
            r.start(pos2);
        }
        
    }
    /*
    public void timedDrive(double x, double y, double z) {
        if(isInTime()) {
            r.start(x,y,z,0);
        } else {
            r.stop();
        }  
    }
    */
    public boolean isInTime(){
        long currentTime = System.currentTimeMillis(); 
        return ((currentTime >= start) && (currentTime <= stop));
    }
    

    
    public void start(double s) {
        r.start(s);
    } 
    /*
    public void drive(double x, double y, double z, double gyro) {
        r.drive(x,y,z,0);
    }
    */
    public void stop() {
        r.stop();
    }
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Spitfire2014 extends SimpleRobot {
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    Joystick leftStick = new Joystick(1);
    Joystick rightStick = new Joystick(2);
    Joystick controlStick = new Joystick(3);
    
    JoystickButton toggleBridge = new JoystickButton(controlStick, 2);
    JoystickButton trigger = new JoystickButton(controlStick, 1);
    
    Compressor compressor = new Compressor(1,1); //(int pressureSwitchChannel, int compressorRelayChannel
    MySolenoid bridge = new MySolenoid(1,3,4);
    
    Victor shooter = new Victor(5);
    
    Servo banana= new Servo(10);
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        boolean pressureSwitchVal = true, pressureSwitchPreval = true;
        compressor.start();
        boolean toggleBridgePreval = false,toggleBridgeVal=false;
        while(isOperatorControl() && isEnabled()) {
            shooter.set((controlStick.getThrottle()-1)/2);
            
            if(trigger.get()) {
                banana.set(0.1);
            } else {
                banana.set(0.35);
            }
            
            //Compressor
            pressureSwitchVal = compressor.getPressureSwitchValue();
            
            if(pressureSwitchVal && !pressureSwitchPreval) {                                          //
                compressor.stop();
                
            } else if (!pressureSwitchVal && pressureSwitchPreval) {            
                compressor.start();
            }
            
            pressureSwitchPreval = pressureSwitchVal;
            
           //New solenoid code 
           toggleBridgeVal=toggleBridge.get();
           if(toggleBridgeVal && !toggleBridgePreval){
               if(bridge.get()){
                   bridge.retract();
               }else{
                   bridge.extend();
                           }
               }
           }
           toggleBridgePreval=toggleBridgeVal;
    /**
     * This function is called once each time the robot enters test mode.
     
    public void test() {
    */
    }
}





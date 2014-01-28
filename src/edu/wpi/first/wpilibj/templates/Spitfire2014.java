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
    
    JoystickButton retractButton = new JoystickButton(controlStick, 3);
    JoystickButton expandButton = new JoystickButton(controlStick, 2);
    JoystickButton trigger = new JoystickButton(controlStick, 1);
    
    Compressor compressor = new Compressor(1,1);
    MySolenoid bridge = new MySolenoid(3,3,4);
    
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
        
        boolean retractedState = true, extendedState = false, retractButtonVal, expandButtonVal;
        boolean pistonPrevalueR = true, pistonPrevalueE = false;
        
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
            
            //Solenoid Actuating
            retractButtonVal = retractButton.get();
            expandButtonVal = expandButton.get();
            
            //determines the desired state of the system based on buttons
            if (retractButtonVal && !expandButtonVal) {
                retractedState = true;
                extendedState = false;
                SmartDashboard.putString("Kicker:", " Retracted");
            } else if (!retractButtonVal && expandButtonVal) {
                retractedState = false;
                extendedState = true;
                SmartDashboard.putString("Kicker:", " Extended");
            } else if (retractButtonVal && expandButtonVal) {
                SmartDashboard.putString("Kicker:", " Both bottons pressed");
            }
            
            //Extends and Retracts the piston only when not previously extended or retracted
            if (retractedState && !pistonPrevalueR) {
                bridge.retract();
            } else if (extendedState && !pistonPrevalueE) {
                bridge.extend();
            } else {
                //nothing
            }
            
            pistonPrevalueE = extendedState;
            pistonPrevalueR = retractedState;
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}





/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
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
    JoystickButton collectorA = new JoystickButton(controlStick, 9);
    JoystickButton collectorB = new JoystickButton(controlStick, 10);
    JoystickButton conveyorA = new JoystickButton(controlStick, 11);
    JoystickButton conveyorB = new JoystickButton(controlStick, 12);
        
    
    MainCompressor mainCompressor = new MainCompressor(1,1);
    Piston bridge = new Piston(1,3,4);
    RunnableDrive robotDrive = new RunnableDrive(1,3);
    Servo banana = new Servo(10);
    RunnableVictor shooter = new RunnableVictor(5);
    Control shooterControl = new Control(shooter);
    Relay collector = new Relay(2);
    Relay conveyor = new Relay(8);
    Control driveControl = new Control(robotDrive);
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        mainCompressor.begin();
        boolean toggleBridgePreval = false,toggleBridgeVal=false,bridgeState=false;
        while(isOperatorControl() && isEnabled()) {
            mainCompressor.update();
            shooterControl.start((controlStick.getThrottle()-1)/2);
            if(conveyorA.get()){
                conveyor.set(Relay.Value.kForward);
            }else{
                conveyor.set(Relay.Value.kOff);
            }
            if(collectorA.get()){
                collector.set(Relay.Value.kForward);
            }else{
                collector.set(Relay.Value.kOff);
            }
            if(trigger.get()) {
                banana.set(0.1);
            } else {
                banana.set(0.35);
            }
            driveControl.tankDrive(leftStick.getY(), rightStick.getY());
           toggleBridgeVal=toggleBridge.get();
           if(toggleBridgeVal && !toggleBridgePreval){
               bridgeState=!bridgeState
               if(bridgeState){
                   bridge.Retract();
               }else{
                   bridge.Extend();
               }
           }
           toggleBridgePreval=toggleBridgeVal;
        }
    /**
     * This function is called once each time the robot enters test mode.
     
    public void test() {
    */
    }
}





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.RobotDrive;



/**
 *
 * @author PyroTech
 */
public class RunnableDrive implements Runnable {
    private RobotDrive drive;
    public RunnableDrive(int pwm1, int pwm2){
        drive = new RobotDrive(pwm1,pwm2);
    }
    public RunnableDrive(int pwm1, int pwm2, int pwm3, int pwm4){
        drive = new RobotDrive(pwm1, pwm2, pwm3, pwm4);
    }
    public void start(double s, double t){
        drive.tankDrive(s, t);
    }
    public void start(double s){}
    public void stop(){}
}

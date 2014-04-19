/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author PyroTech
 */
public class RunnableVictor implements Runnable{
    Victor motor;
    public RunnableVictor(int pwm) {
        motor = new Victor(pwm);
    }
    
    public void start(double s) {
        motor.set(s);
    }
    
    public void stop() {
        motor.set(0);
    }
    public void start(double s, double t){}
}

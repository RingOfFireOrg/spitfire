/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author PyroTech
 */
public class Spikey {
    Relay Spike;
    boolean buttonPreVal=false, state=false;
    public Spikey(int pwm){
        Spike = new Relay(pwm);
    }
    public void set(boolean buttonA, boolean buttonB){
        if(buttonA && !buttonPreVal){
            state=!state;
        }
        if(state){
            if(buttonB){
                Spike.set(Relay.Value.kReverse);
            }else{
                Spike.set(Relay.Value.kForward);
            }
        }else{
            Spike.set(Relay.Value.kOff);
        }
    buttonPreVal=buttonA;
    }
}

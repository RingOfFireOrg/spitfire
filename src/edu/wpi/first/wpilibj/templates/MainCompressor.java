/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
/**
 *
 * @author PyroTech
 */
public class MainCompressor {
    boolean pressureSwitchVal, pressureSwitchPreval;
    private Compressor thisCompressor;
    
    public MainCompressor(int switchPWM, int compressorPWM){
        thisCompressor = new Compressor(switchPWM, compressorPWM);
    }
    
    public void begin(){
        thisCompressor.start();
        pressureSwitchVal = thisCompressor.getPressureSwitchValue();
        pressureSwitchPreval = pressureSwitchVal;
    }
    
    public void update(){
            pressureSwitchVal = thisCompressor.getPressureSwitchValue();
            
            if(pressureSwitchVal && !pressureSwitchPreval) {                                          //
                thisCompressor.stop();
                
            } else if (!pressureSwitchVal && pressureSwitchPreval) {            
                thisCompressor.start();
            }
            
            pressureSwitchPreval = pressureSwitchVal;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.hingedpickup;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.subsystems.HingedPickUpSubsystem;

/**
 *
 * @author Kabi
 */
public class RetractHingedPickUp extends CommandBase{

    protected void initialize() {
        hingedPickUpSubsystem.retractPickup();
    }

    protected void execute() {
        hingedPickUpSubsystem.runMotor(HingedPickUpSubsystem.PICK_UP_MOTOR_SPEED);
        
    }
    
    protected boolean isFinished() {
        return hingedPickUpSubsystem.isPickUpRetracted();
    }

    protected void end() {
        hingedPickUpSubsystem.stopMotor();
    }

    protected void interrupted() {
    }
    
}

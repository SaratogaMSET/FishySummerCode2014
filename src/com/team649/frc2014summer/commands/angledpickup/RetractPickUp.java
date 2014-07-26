/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.angledpickup;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.subsystems.AngledPickUpSubsystem;

/**
 *
 * @author Kabi
 */
public class RetractPickUp extends CommandBase{

    protected void initialize() {
        angledPickUpSubsystem.retractPickup();
    }

    protected void execute() {
        angledPickUpSubsystem.runMotor(AngledPickUpSubsystem.PICK_UP_MOTOR_SPEED);
        
    }
    

    protected boolean isFinished() {
        return angledPickUpSubsystem.isPickUpRetracted();
    }

    protected void end() {
        angledPickUpSubsystem.stopMotor();
    }

    protected void interrupted() {
    }
    
}

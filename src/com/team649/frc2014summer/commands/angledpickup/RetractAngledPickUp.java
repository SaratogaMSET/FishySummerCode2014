/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.angledpickup;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.subsystems.AngledPickUpSubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kabi
 */
public class RetractAngledPickUp extends CommandBase{

    private Timer clock;

    protected void initialize() {
        angledPickUpSubsystem.retractPickup();
        clock = new Timer();
        clock.start();
    }

    protected void execute() {
        angledPickUpSubsystem.runMotor(AngledPickUpSubsystem.PICK_UP_MOTOR_SPEED);
        
    }
    
    protected boolean isFinished() {
        return(clock.get() == 1000);
    }

    protected void end() {
        angledPickUpSubsystem.stopMotor();
    }

    protected void interrupted() {
    }
    
}

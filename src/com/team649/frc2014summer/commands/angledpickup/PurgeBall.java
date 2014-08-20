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
public class PurgeBall extends CommandBase {

    Timer time;

    protected void initialize() {
        time = new Timer();
    }

    protected void execute() {
        angledPickUpSubsystem.runMotor(AngledPickUpSubsystem.PURGE_MOTOR_SPEED);
    }

    protected boolean isFinished() {
        if (time.get() >= 100 && !angledPickUpSubsystem.haveBallInPickUp()) {
            return true;
        }
        return false;

    }

    protected void end() {
        angledPickUpSubsystem.stopMotor();
    }

    protected void interrupted() {
    }

}

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
public class HoldBall extends CommandBase {

    protected void initialize() {
        angledPickUpSubsystem.runMotor(AngledPickUpSubsystem.HOLD_BALL_MOTOR_SPEED);

    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}

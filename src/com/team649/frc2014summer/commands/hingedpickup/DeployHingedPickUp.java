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
public class DeployHingedPickUp extends CommandBase {

    protected void initialize() {
        hingedPickUpSubsystem.deployPickup();
    }

    protected void execute() {
        hingedPickUpSubsystem.runMotor(HingedPickUpSubsystem.PICK_UP_MOTOR_SPEED);
    }

    protected boolean isFinished() {
        return hingedPickUpSubsystem.haveBall();
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}

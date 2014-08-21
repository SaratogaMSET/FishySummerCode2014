/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.shooter;

import com.team649.frc2014summer.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Kabi
 */
public class PurgeBallFromShooter extends CommandBase {

    Timer time;

    protected void initialize() {
        time = new Timer();
        shooterSubsystem.firePurgePiston();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return (time.get() >= 300);
    }

    protected void end() {
        shooterSubsystem.retractPurgePiston();
    }

    protected void interrupted() {
    }

}

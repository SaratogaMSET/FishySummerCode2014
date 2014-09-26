/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.pivot;

import com.team649.frc2014summer.commands.CommandBase;

/**
 *
 * @author Alex
 */
public class ManualDriveClawPivot extends CommandBase {

    private final double power;

    public ManualDriveClawPivot(double power) {
        this.power = power;
    }

    protected void initialize() {
        clawPivotSubsystem.setPower(power);
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

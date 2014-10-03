/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.rollers;

import com.team649.frc2014summer.commands.CommandBase;

/**
 *
 * @author Kabi
 */
public class RunRollers extends CommandBase {

    private final double speed;
    private boolean useHaveBall;

    public RunRollers(double choosenSpeed, boolean check) {
        speed = choosenSpeed;
        useHaveBall = check;
    }

    protected void initialize() {
        clawRollerSubsystem.runMotor(speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        if(useHaveBall) {
            return CommandBase.clawRollerSubsystem.haveBall();
        }
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

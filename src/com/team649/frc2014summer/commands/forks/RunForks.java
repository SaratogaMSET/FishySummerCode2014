/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.forks;

import com.team649.frc2014summer.commands.CommandBase;

/**
 *
 * @author Kabir
 */
public class RunForks extends CommandBase{

    public double speed;
    
    public RunForks(double chosenSpeed) {
        speed = chosenSpeed;
    }
    protected void initialize() {
        clawForksSubsystem.runForks(speed);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author JJ
 */
class WaitCommand extends Command {

    private long waitTime;
    private long startTime;

    public WaitCommand(int i) {
        waitTime = i;
    }

    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return System.currentTimeMillis() - startTime > waitTime;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

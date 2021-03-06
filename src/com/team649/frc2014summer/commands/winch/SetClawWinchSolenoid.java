/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.winch;

import com.team649.frc2014summer.Display;
import com.team649.frc2014summer.commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author Suneel
 */
public class SetClawWinchSolenoid extends CommandBase {

    private final boolean state;

    public SetClawWinchSolenoid(boolean direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        state = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        clawWinchSubsystem.setSolenoid(state);
        if (state == false) {
            Display.printToOutputStream("shot at: " + DriverStation.getInstance().getMatchTime() + ", " + CommandBase.clawPivotSubsystem.getPotValue());
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

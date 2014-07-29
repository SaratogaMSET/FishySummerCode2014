/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.team649.frc2014summer;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.commands.angledpickup.DeployAngledPickUp;
import com.team649.frc2014summer.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class FishyRobot extends IterativeRobot {

    private Command autonomousCommand;
    Timer time;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        autonomousCommand = CommandBase.oneBallAutonomous();

        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand.start();
        Display.marquee(1, "AUTONOMOOSE TIME!", 0, 5, true, true);
    }

    public void disabledInit() {
        Display.clearMarquees();
        Display.marquee(1, "DISABLED MODE", 0, 5, true, true);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
        time = new Timer();
        time.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        getWatchdog().feed();

        if (CommandBase.oi.driver.isSwitchFrontButtonPressed()) {
            CommandBase.driveForwardRotate(-CommandBase.oi.driver.getDriveForward(), -CommandBase.oi.driver.getDriveRotation());

        } else {
            CommandBase.driveForwardRotate(CommandBase.oi.driver.getDriveForward(), CommandBase.oi.driver.getDriveRotation());
        }
        
        
        if (CommandBase.oi.driver.isTriggerButtonPressed()) {
            CommandBase.driveTrainSubsystem.shiftDriveGear(DriveTrainSubsystem.LOW_SPEED);
            CommandBase.driveTrainSubsystem.resetEncoders();
        } else {
            CommandBase.driveTrainSubsystem.shiftDriveGear(DriveTrainSubsystem.HIGH_SPEED);
        }
        
        if(CommandBase.oi.shooter.isDeployAngledPickUpButtonPressed()) {
            CommandBase.deployAngledPickUp();
        }
        
       if(CommandBase.oi.shooter.isRetractAngledPickUpButtonPressed()) {
           CommandBase.retractAngledPickUp();
       }

        // CommandBase.driveTrainSubsystem.printEncoders();
         }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

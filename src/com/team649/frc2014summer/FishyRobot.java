/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.team649.frc2014summer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.commands.HotVisionWaitCommand;
import com.team649.frc2014summer.commands.pivot.SetClawPosition;
import com.team649.frc2014summer.commands.winch.SetClawWinchSolenoid;
import com.team649.frc2014summer.subsystems.ClawFingerSubsystem;
import com.team649.frc2014summer.subsystems.ClawForksSubsystem;
import com.team649.frc2014summer.subsystems.ClawPivotSubsystem;
import com.team649.frc2014summer.subsystems.ClawRollerSubsystem;
import com.team649.frc2014summer.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class FishyRobot extends IterativeRobot {

    public static final String DO_NOTHING_AUTO_NAME = "doNothingAuto";
    public static final String WAIT_AND_DRIVE_AUTO_NAME = "waitAndDriveAuto";
    public static final String ONE_BALL_SHORT_DRIVE_AUTO_NAME = "oneBallShortDriveAuto";
    // public static final String ONE_BALL_RUNNING_SHOT_AUTO_NAME = "oneBallRunningAuto";
    public static final String TWO_BALL_SHORT_DRIVE_AUTO_NAME = "twoBallShortDriveAuto";
    //  public static final String TWO_BALL_RUNNING_SHOT_AUTO_NAME = "twoBallRunningAuto";

    private SendableChooser autonomousModeChooser;
    private SetClawPosition setClawPositionCommand;
    private Command shootCommand;
    private Command autonomousCommand;
    private Command coilClawWinchCommand;
    private boolean firstPeriodic;
//    Command autonomousCommand;
//    private SupaHotFire supaHotFire;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
//        autonomousCommand = new DriveSetDistanceCommand();
        SmartDashboard.putNumber("waitTime", 1000);
        // Initialize all subsystems
        CommandBase.init();
//        autonomousModeChooser = new SendableChooser();
//        autonomousModeChooser.addObject("Do Nothing Autonomous", DO_NOTHING_AUTO_NAME);
//        autonomousModeChooser.addObject("Wait and Drive Autonomous", WAIT_AND_DRIVE_AUTO_NAME);
//        autonomousModeChooser.addDefault("One Ball Short Drive Autonomous", ONE_BALL_SHORT_DRIVE_AUTO_NAME);
//        //       autonomousModeChooser.addDefault("One Ball Driving Shot Autonomous", ONE_BALL_RUNNING_SHOT_AUTO_NAME);
//        autonomousModeChooser.addDefault("Two Ball Short Drive Autonomous", TWO_BALL_SHORT_DRIVE_AUTO_NAME);
////        autonomousModeChooser.addObject("Two Ball Running Autonomous", TWO_BALL_RUNNING_SHOT_AUTO_NAME);
//        SmartDashboard.putData("Autonomous", autonomousModeChooser);
//        SmartDashboard.putData(new HotVisionWaitCommand());
    }

    public void disabledInit() {
        Display.clearMarquees();
        Display.marquee(1, "DISABLED MODE", 0, 5, true, true);
        CommandBase.driveTrainSubsystem.disablePid();
    }

    public void disabledPeriodic() {
        Display.clear();
        Display.update();
    }

    public void autonomousInit() {
        Display.clearMarquees();
//        Display.marquee(1, "AUTONOMOUS MODE", 0, 5, true, true);
//        Display.marquee(2, "WOOOOOO", 0, 10, true, false);
//        Display.marquee(3, "GO FIISHH", 0, 2, true, true);
//        Display.marquee(4, "YEEAAHHHH", 0, 7, true, false);
//        Display.marquee(5, "AUTONOMOOSE MODE", 2, 5, true, true);
//        Display.marquee(6, "YOU CAN DO IT!!!!", 5, 5, true, false);
        //final String selectedAuto = (String) autonomousModeChooser.getSelected();
        //Display.printToOutputStream("selected auto: " + selectedAuto);
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        CommandBase.driveTrainSubsystem.driveFwdRot(0, 0);
        CommandBase.driveTrainSubsystem.disablePid();
        CommandBase.clawPivotSubsystem.getClawPID().disable();
        CommandBase.clawPivotSubsystem.setPower(0);
        CommandBase.clawRollerSubsystem.runMotor(0);
        CommandBase.clawWinchSubsystem.stopMotor();
        Scheduler.getInstance().removeAll();
        
        
        autonomousCommand = CommandBase.shootHotGoalShortDriveAutonomous();
        autonomousCommand.start();
        setSolenoidsToDefault();
        firstPeriodic = true;
    }

    /*
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Display.clear();
        Scheduler.getInstance().run();
//        Display.queue(CommandBase.clawPivotSubsystem.getPotValue() + "");
        CommandBase.driveTrainSubsystem.printEncoders();
        Display.update();
    }

    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        if (setClawPositionCommand != null) {
            setClawPositionCommand.cancel();
        }
        Display.clearMarquees();
        CommandBase.driveTrainSubsystem.startEncoders();
        Display.marquee(1, "2014 ENABLED", 5, 5, true, true);
        setSolenoidsToDefault();
    }

    private void setSolenoidsToDefault() {
        CommandBase.setFingerPosition(ClawFingerSubsystem.DOWN).start();
        new SetClawWinchSolenoid(true).start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try {
            getWatchdog().feed();
            Display.clear();
            Scheduler.getInstance().run();
            //
            //
            //
            // MANUAL
            //
            //
            //
/*
             if (CommandBase.oi.manual.isFingerButtonPressed()) {
             //fingerSolenoid.set(fingerState ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
             if (CommandBase.clawFingerSubsystem.state == ClawFingerSubsystem.DOWN) {
             CommandBase.clawFingerSubsystem.setFingerPosition(ClawFingerSubsystem.UP);
             } else {
             CommandBase.clawFingerSubsystem.setFingerPosition(ClawFingerSubsystem.DOWN);
             }
             }

             if (CommandBase.oi.manual.isManualWinchButtonPressed()) {
             CommandBase.clawWinchSubsystem.runMotor();
             } else {
             CommandBase.clawWinchSubsystem.stopMotor();
             }

             if (CommandBase.oi.manual.isShooterSafetyButtonPressed() && CommandBase.oi.manual.isShooterTriggerButtonPressed()) {
             CommandBase.clawWinchSubsystem.setSolenoid(false);
             } else {
             CommandBase.clawWinchSubsystem.setSolenoid(true);
             }

             if (CommandBase.oi.manual.getRollerButtonsPressed() == -1) {
             CommandBase.clawRollerSubsystem.runMotor(ClawRollerSubsystem.ROLLER_SPIN_INTAKE_SPEED);
             CommandBase.clawForksSubsystem.runForks(ClawForksSubsystem.FORK_RUN_SPEED);
             } else if (CommandBase.oi.manual.getRollerButtonsPressed() == 1) {
             CommandBase.clawRollerSubsystem.runMotor(ClawRollerSubsystem.ROLLER_SPIN_PURGE_SPEED);
             CommandBase.clawForksSubsystem.runForks(ClawForksSubsystem.FORK_OFF_SPEED);
             } else {
             CommandBase.clawRollerSubsystem.runMotor(ClawRollerSubsystem.ROLLER_SPIN_OFF_SPEED);
             CommandBase.clawForksSubsystem.runForks(ClawForksSubsystem.FORK_OFF_SPEED);
             }
             CommandBase.clawPivotSubsystem.setPower(CommandBase.oi.manual.getManualShooterJoystickY());
            
             */
            //
            //
            //
            //
            //
            //
            //ClawRollerSubsystem.MOTOR_SPEED = SmartDashboard.getNumber("rollerSpeed");
            CommandBase.driveForwardRotate(CommandBase.oi.driver.getDriveForward(), CommandBase.oi.driver.getDriveRotation()).start();
            if (CommandBase.oi.driver.isDrivetrainLowGearButtonPressed()) {
                CommandBase.driveTrainSubsystem.shiftDriveGear(DriveTrainSubsystem.LOW_SPEED);
                CommandBase.driveTrainSubsystem.resetEncoders();
            } else {
                CommandBase.driveTrainSubsystem.shiftDriveGear(DriveTrainSubsystem.HIGH_SPEED);
            }
            //  CommandBase.driveTrainSubsystem.printEncoders();

            if (CommandBase.oi.shooter.isBackwardShootClawPositionButtonPressed()) {
                clawPIDSequence(ClawPivotSubsystem.BACKWARD_SHOOT);
            } else if (CommandBase.oi.shooter.isForwardShootClawPositionButtonPressed()) {
                clawPIDSequence(ClawPivotSubsystem.FORWARD_SHOOT);
            } else if (CommandBase.oi.shooter.isPickupClawPositionButtonPressed()) {
                clawPIDSequence(ClawPivotSubsystem.PICKUP);
            } else if (CommandBase.oi.shooter.isStoreClawPositionButtonPressed()) {
                clawPIDSequence(ClawPivotSubsystem.STORE);
            } else if (CommandBase.oi.shooter.isGoalShootClawPositionButtonPressed()) {
                clawPIDSequence(ClawPivotSubsystem.GOAL_SHOOT);
            } else {
                if (setClawPositionCommand != null) {
                    setClawPositionCommand.cancel();
                    setClawPositionCommand = null;
                }
                if (CommandBase.oi.shooter.isPivotManualOverrideButtonPressed()) {
                    CommandBase.manualDriveClaw(CommandBase.oi.shooter.getShooterJoystickY()).start();
                } else {
                    CommandBase.manualDriveClaw(0).start();
                }

            }

            if (shootCommand == null || !shootCommand.isRunning()) {
                if (CommandBase.oi.shooter.isPurgeButtonPressed()) {
                    CommandBase.runRollers(ClawRollerSubsystem.ROLLER_SPIN_PURGE_SPEED, false).start();
                } else if (CommandBase.oi.shooter.isPickupButtonPressed()) {
                    CommandBase.runRollers(ClawRollerSubsystem.ROLLER_SPIN_INTAKE_SPEED, false).start();
                } else {
                    CommandBase.runRollers(ClawRollerSubsystem.ROLLER_SPIN_OFF_SPEED, false).start();
                }
            }
            if (CommandBase.oi.shooter.isWinchWindButtonPressed() && (coilClawWinchCommand == null || !coilClawWinchCommand.isRunning())) {
                coilClawWinchCommand = CommandBase.manualCoilClawWinch();
                coilClawWinchCommand.start();
            }

            if (CommandBase.oi.shooter.isShooterTriggerButtonPressed() && CommandBase.oi.shooter.isWinchSafetyButtonPressed()) {
                if (shootCommand == null || !shootCommand.isRunning()) {
                    shootCommand = CommandBase.shootBall();
                    shootCommand.start();
                }
            }
//        CommandBase.driveTrainSubsystem.printEncoders();
            Display.queue("WINCH: " + (CommandBase.clawWinchSubsystem.isSwitchPressed() ? "CHARGED" : "UNWOUND"));
            Display.queue("POT: " + CommandBase.clawPivotSubsystem.getPotValue());
            Display.queue(CommandBase.clawPivotSubsystem.getPotStateName());
            if (CommandBase.isCompressorRunning()) {
                Display.queue("COMPRESSOR RUNNING");
            }
            Display.update();

            sleep();
        } catch (Exception e) {
        }
    }

    private void clawPIDSequence(int position) {
        if (setClawPositionCommand != null && setClawPositionCommand.getState() != position) {
            setClawPositionCommand.cancel();
            setClawPositionCommand = null;
        }
        if (setClawPositionCommand == null || !setClawPositionCommand.isRunning()) {

            setClawPositionCommand = new SetClawPosition(position);
            setClawPositionCommand.start();
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    private void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

package com.team649.frc2014summer.commands;

import com.team649.frc2014summer.OI;
import com.team649.frc2014summer.RobotMap;
import com.team649.frc2014summer.commands.angledpickup.DeployAngledPickUp;
import com.team649.frc2014summer.commands.angledpickup.HoldBall;
import com.team649.frc2014summer.commands.angledpickup.PurgeBallFromPickUp;
import com.team649.frc2014summer.commands.angledpickup.RetractAngledPickUp;
import com.team649.frc2014summer.commands.drivetrain.DriveForwardRotate;
import com.team649.frc2014summer.commands.shooter.PurgeBallFromShooter;
import com.team649.frc2014summer.subsystems.AngledPickUpSubsystem;
import com.team649.frc2014summer.subsystems.DriveTrainSubsystem;
import com.team649.frc2014summer.subsystems.HingedPickUpSubsystem;
import com.team649.frc2014summer.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The base for all commands. All atomic commands should subclass CommandBase. CommandBase stores creates and stores each control system. To access a subsystem elsewhere in your code in your code use
 * CommandBase.exampleSubsystem
 *
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static DriveTrainSubsystem driveTrainSubsystem = new DriveTrainSubsystem();
    public static AngledPickUpSubsystem angledPickUpSubsystem = new AngledPickUpSubsystem();
    public static HingedPickUpSubsystem hingedPickUpSubsystem = new HingedPickUpSubsystem();
    public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static Compressor compressor;

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        compressor = new Compressor(RobotMap.PRESSURE_SWTICH_CHANNEL, RobotMap.PRESSURE_RELAY_CHANNEL);
        compressor.start();
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(driveTrainSubsystem);
    }

    public static Command oneBallAutonomous() {
        CommandGroup mainAutonomous = new CommandGroup("mainAutoSeq");
        mainAutonomous.addSequential(mainAutonomous);
        return mainAutonomous;
    }

    public static Command driveForwardRotate(double driveForward, double driveRotation) {
        return new DriveForwardRotate(driveForward, driveRotation);
    }

    public static Command deployAngledPickUp() {
        return new DeployAngledPickUp();
    }

    public static Command retractAngledPickUp() {
        return new RetractAngledPickUp();
    }

    public static Command holdBall() {
        return new HoldBall();
    }

    public static Command purgeBallFromPickUp() {
        return new PurgeBallFromPickUp();
    }

    public static Command purgeBallFromShooter() {
        return new PurgeBallFromShooter();
    }

    public static Command pickUpBall() {
        CommandGroup pickUpBallSequence = new CommandGroup();
        if (angledPickUpSubsystem.haveBallInPickUp()) {
            pickUpBallSequence.addSequential(retractAngledPickUp());

        } else {
            pickUpBallSequence.addSequential(pickUpAndHoldBall());
            pickUpBallSequence.addSequential(retractAngledPickUp());
        }

        return pickUpBallSequence;
    }

    public static Command pickUpAndHoldBall() {
        CommandGroup holdBallSequence = new CommandGroup();
        holdBallSequence.addSequential(deployAngledPickUp());
        holdBallSequence.addSequential(holdBall());
        return holdBallSequence;
    }

    public static Command purgeBall() {
        CommandGroup purgeBallSequence = new CommandGroup();
        purgeBallSequence.addSequential(purgeBallFromPickUp());
        if (!angledPickUpSubsystem.haveBallInPickUp()) {
            purgeBallSequence.addParallel(purgeBallFromShooter());
        }
        return purgeBallSequence;
    }

    
    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}

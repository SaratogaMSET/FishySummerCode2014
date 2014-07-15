package com.team649.frc2014summer.commands;

import com.team649.frc2014summer.OI;
import com.team649.frc2014summer.RobotMap;
import com.team649.frc2014summer.subsystems.DriveTrainSubsystem;
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
    public static DriveTrainSubsystem exampleSubsystem = new DriveTrainSubsystem();
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
        SmartDashboard.putData(exampleSubsystem);
    }

    public static Command oneBallAutonomous() {
        CommandGroup mainAutonomous = new CommandGroup("mainAutoSeq");
        mainAutonomous.addSequential(mainAutonomous);
        return mainAutonomous;
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}

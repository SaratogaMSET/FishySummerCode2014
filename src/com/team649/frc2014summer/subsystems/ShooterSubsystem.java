/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kabi
 */
public class ShooterSubsystem extends Subsystem {
    private SpeedController winchMotor1;
    private SpeedController winchMotor2;
    public static final double MOTOR_SPEED = 1;
    public static final int TIME_TO_FIRE = 0;
    public static final int TIME_TO_COIL = 0;
    
    
    public ShooterSubsystem() {
        winchMotor1 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_1);
        winchMotor2 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_2);
    }
    protected void initDefaultCommand() {
    }
    
}

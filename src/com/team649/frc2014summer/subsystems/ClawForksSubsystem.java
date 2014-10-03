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
 * @author Kabir
 */
public class ClawForksSubsystem extends Subsystem{
    
    private final SpeedController motorLeft;
    private final SpeedController motorRight;
    
    public static final double FORK_RUN_SPEED = .3;
    public static final double FORK_OFF_SPEED = 0;
    
    public ClawForksSubsystem() {
        motorLeft = new Victor(RobotMap.CLAW_FORKS.LEFT_MOTOR);
        motorRight = new Victor(RobotMap.CLAW_FORKS.RIGHT_MOTOR);
    }
    public void runForks(double speed) {
        motorLeft.set(speed);
        motorRight.set(speed);
    }
    protected void initDefaultCommand() {
    }
    
}

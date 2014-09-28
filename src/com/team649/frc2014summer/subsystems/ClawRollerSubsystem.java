/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kabi
 */
public class ClawRollerSubsystem extends Subsystem {

    private final SpeedController motor;
    private final DigitalInput lim1;
    private final DigitalInput lim2;

    public static final double ROLLER_SPIN_SHOOT_SPEED = 1;
    public static final double ROLLER_SPIN_INTAKE_SPEED = -.4;
    public static final double ROLLER_SPIN_PURGE_SPEED = .4;
    public static final double ROLLER_SPIN_OFF_SPEED = 0;
    public static final double ROLLER_SPIN_REALIGN_SPEED = -.2;

    public ClawRollerSubsystem() {
        motor = new Victor(RobotMap.CLAW_ROLLER.MOTOR);
        lim1 = new DigitalInput(RobotMap.CLAW_ROLLER.LIMIT_SWITCH1_INPUT);
        lim2 = new DigitalInput(RobotMap.CLAW_ROLLER.LIMIT_SWITCH2_INPUT);
    }

    protected void initDefaultCommand() {
    }

    public void runMotor(double speed) {
        motor.set(speed);
    }
    
    public boolean haveBall() {
        return(lim1.get() || lim2.get()); 
    }
}

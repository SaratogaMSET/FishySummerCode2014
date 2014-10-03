/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.MaxbotixUltrasonic;
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
    private final MaxbotixUltrasonic ultra;

    public static final double ROLLER_SPIN_SHOOT_SPEED = 1;
    public static final double ROLLER_SPIN_INTAKE_SPEED = -.4;
    public static final double ROLLER_SPIN_PURGE_SPEED = .4;
    public static final double ROLLER_SPIN_OFF_SPEED = 0;
    public static final double ROLLER_SPIN_REALIGN_SPEED = -.2;

    //UltraSonicVals
    public static final boolean returnWithUnits = true;
    public static final double voltageMin = 0;
    public static final double voltageMax = 1;
    public static final double distanceMin = 0;
    public static final double distanceMax = 10;

    public ClawRollerSubsystem() {
        motor = new Victor(RobotMap.CLAW_ROLLER.MOTOR);
        ultra = new MaxbotixUltrasonic(RobotMap.CLAW_ROLLER.ULTRASONICCHANNEL, returnWithUnits, voltageMin, voltageMax, distanceMin, distanceMax);
    }

    protected void initDefaultCommand() {
    }

    public void runMotor(double speed) {
        motor.set(speed);
    }

    public boolean haveBall() {
        if (ultra.GetRangeInInches() <= 5) {
            return true;
        }
        return false;
    }
}

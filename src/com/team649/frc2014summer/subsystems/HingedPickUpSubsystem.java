/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kabi
 */
public class HingedPickUpSubsystem extends Subsystem {

    private SpeedController motor;
    private DoubleSolenoid leftPiston;
    private DoubleSolenoid rightPiston;

    public static final double PICK_UP_MOTOR_SPEED = -0.4;
    public static final double PURGE_MOTOR_SPEED = 0.4;

    private static final DoubleSolenoid.Value LEFT_PISTON_DEPLOYED_STATE = DoubleSolenoid.Value.kForward;
    private static final DoubleSolenoid.Value LEFT_PISTON_RETRACTED_STATE = DoubleSolenoid.Value.kReverse;
    private static final DoubleSolenoid.Value RIGHT_PISTON_DEPLOYED_STATE = DoubleSolenoid.Value.kForward;
    private static final DoubleSolenoid.Value RIGHT_PISTON_RETRACTED_STATE = DoubleSolenoid.Value.kReverse;

    public HingedPickUpSubsystem() {

        motor = new Victor(RobotMap.ANGLED_PICK_UP.MOTOR_PORT);
        leftPiston = new DoubleSolenoid(RobotMap.HINGED_PICK_UP.LEFT_SOLENOID_FORWARD_CHANNEL, RobotMap.HINGED_PICK_UP.LEFT_SOLENOID_REVERSE_CHANNEL);
        rightPiston = new DoubleSolenoid(RobotMap.HINGED_PICK_UP.RIGHT_SOLENOID_FORWARD_CHANNEL, RobotMap.HINGED_PICK_UP.RIGHT_SOLENOID_REVERSE_CHANNEL);
    }

    public void runMotor(double speed) {
        motor.set(speed);
    }

    public void stopMotor() {
        motor.set(0);
    }

    public void deployPickup() {
        leftPiston.set(LEFT_PISTON_DEPLOYED_STATE);
        rightPiston.set(RIGHT_PISTON_DEPLOYED_STATE);
    }

    public void retractPickup() {
        leftPiston.set(LEFT_PISTON_RETRACTED_STATE);
        rightPiston.set(RIGHT_PISTON_RETRACTED_STATE);
    }

    protected void initDefaultCommand() {
    }

}

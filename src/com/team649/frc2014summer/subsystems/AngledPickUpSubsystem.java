/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kabi
 */
public class AngledPickUpSubsystem extends Subsystem{

    private SpeedController motor;
    private DoubleSolenoid piston;

    public static final double PICK_UP_MOTOR_SPEED = -0.4;
    public static final double PURGE_MOTOR_SPEED = 0.4;
    public static final double HOLD_BALL_MOTOR_SPEED = 0.2;

    private static final Value PISTON_DEPLOYED_STATE = DoubleSolenoid.Value.kForward;
    private static final Value PISTON_RETRACTED_STATE = DoubleSolenoid.Value.kReverse;

   
    
    public static final class EncoderBasedRPM {
        
        public static double Kp = 0;
        public static double Ki = 0;
        public static double Kd = 0;
        private static double ABSOLUTE_TOLERANCE = 0;
        public static double MAX_MOTOR_POWER = 0;
    }

    public AngledPickUpSubsystem() {

        motor = new Victor(RobotMap.ANGLED_PICK_UP.MOTOR_PORT);
        piston = new DoubleSolenoid(RobotMap.ANGLED_PICK_UP.LEFT_SOLENOID_FORWARD_CHANNEL, RobotMap.ANGLED_PICK_UP.LEFT_SOLENOID_REVERSE_CHANNEL);
        //encoder = new Encoder(null, null)
        //pid = new PIDController649(kp, ki, kd, source, output)
    }

    public void runMotor(double speed) {
        motor.set(speed);
    }

    public void stopMotor() {
        motor.set(0);
    }

    public void deployPickup() {
        piston.set(PISTON_DEPLOYED_STATE);
    }

    public void retractPickup() {
        piston.set(PISTON_RETRACTED_STATE);
    }

    public boolean haveBallInPickUp() {
        return true;
    }

    protected void initDefaultCommand() {
    }

}

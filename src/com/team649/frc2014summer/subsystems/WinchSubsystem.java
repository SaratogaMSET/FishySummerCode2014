/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.Display;
import com.team649.frc2014summer.RobotMap;
import com.team649.frc2014summer.pid_control.PIDController649;
import com.team649.frc2014summer.pid_control.PIDVelocitySource;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 * @author Kabi
 */
public class WinchSubsystem extends Subsystem implements PIDVelocitySource, PIDOutput {

    private SpeedController winchMotor1;
    private SpeedController winchMotor2;
    private PIDController649 pid;
    private DigitalInput limit1;
    private Encoder encoder;
    private DoubleSolenoid purgePiston;

    public static final double MOTOR_SPEED = 1;
    public static final int TIME_TO_FIRE = 0;
    public static final int TIME_TO_COIL = 0;
    public static final double CLICKS_PAST_LIMIT = 30;

    public static class EncoderBasedPID {

        public static final double MIN_MOTOR_POWER = 0.25;
        public static double Kp = 0;
        public static double Ki = 0;
        public static double Kd = 0;
        public static double MAX_MOTOR_POWER = 0;
        public static final double ABSOLUTE_TOLERANCE = 10;

    }

    public WinchSubsystem() {
        winchMotor1 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_1);
        winchMotor2 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_2);
        pid = new PIDController649(EncoderBasedPID.Kp, EncoderBasedPID.Kd, EncoderBasedPID.Kd, encoder, this);
        pid.setOutputRange(EncoderBasedPID.MIN_MOTOR_POWER, EncoderBasedPID.MAX_MOTOR_POWER);
        pid.setAbsoluteTolerance(EncoderBasedPID.ABSOLUTE_TOLERANCE);
        encoder = new Encoder(RobotMap.SHOOTER.ENCODER_SOURCE_A, RobotMap.SHOOTER.ENCODER_SOURCE_B);
        limit1 = new DigitalInput(RobotMap.SHOOTER.LIMIT_SWITCH_1_PORT);

        purgePiston = new DoubleSolenoid(RobotMap.SHOOTER.PURGE_PISTON_FORWARD_CHANNEL, RobotMap.SHOOTER.PURGE_PISTON_REVERSE_CHANNEL);
        purgePiston.set(DoubleSolenoid.Value.kReverse);
    }

    public void setPower(double motorPower) {
        winchMotor1.set(motorPower);
    }

    public void shoot() {

    }

    public boolean haveBallInShooter() {
        return true;
    }

    public boolean isShooterCharged() {
        return true;
    }
    public void firePurgePiston() {
        purgePiston.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPurgePiston() {
        purgePiston.set(DoubleSolenoid.Value.kReverse);
    }

    public PIDController649 getShooterPID() {
        return pid;
    }
    //Get Info from encoder
    public double pidGet() {
        return getDistance();
    }

    public double getRate() {
        return getVelocity();
    }

    private double getVelocity() {
        return encoder.getRate();
    }

    public double getDistance() {
        return encoder.getDistance();
    }

    //Control Encoder
    public void startEncoder() {
        encoder.start();
    }

    public void resetEncoders() {
        encoder.reset();
    }

    public void printEncoders() {
        Display.queue("Dis " + encoder.getDistance());
        Display.queue("Vel " + encoder.getRate());

    }

    public void pidWrite(double output) {
        Display.println(3, "" + output);
    }

    protected void initDefaultCommand() {
    }

}

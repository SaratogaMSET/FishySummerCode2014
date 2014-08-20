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
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 * @author Kabi
 */
public class ShooterSubsystem extends Subsystem implements PIDOutput{
    private SpeedController winchMotor1;
    private SpeedController winchMotor2;
    private PIDController649 pid; 
    private DigitalInput limit1, limit2;
    private Potentiometer pot;
    
    public static final double MOTOR_SPEED = 1;
    public static final int TIME_TO_FIRE = 0;
    public static final int TIME_TO_COIL = 0;
    private static final double POT_MAX = 0;
    
    public static class PotentiometerBasedPID {
        
        public static final double MIN_MOTOR_POWER = 0.25;
        public static double Kp = 0;
        public static double Ki = 0;
        public static double Kd = 0;
        public static double MAX_MOTOR_POWER = 0;
        
    }
    
     public ShooterSubsystem() {
        winchMotor1 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_1);
        winchMotor2 = new Victor(RobotMap.SHOOTER.MOTOR_PORT_2);
        pid = new PIDController649(PotentiometerBasedPID.Kp, PotentiometerBasedPID.Kd, PotentiometerBasedPID.Kd, pot, this);
        pid.setOutputRange(PotentiometerBasedPID.MIN_MOTOR_POWER, PotentiometerBasedPID.MAX_MOTOR_POWER);
        pot = new AnalogPotentiometer(RobotMap.SHOOTER.POTENTIOMETER_PORT);
        limit1 = new DigitalInput(RobotMap.SHOOTER.LIMIT_SWITCH_1_PORT);
        limit2 = new DigitalInput(RobotMap.SHOOTER.LIMIT_SWITCH_2_PORT);
    }

   
    public void pidWrite(double output) {
        Display.println(3, "" + output);
    }
   
    
    public void reloadShooter() {
        winchMotor1.set(MOTOR_SPEED);
        winchMotor2.set(MOTOR_SPEED);
    }
    
    public void shoot(){
        
    }
    
    public boolean haveBallInShooter() {
        return true;
    }
    
    protected void initDefaultCommand() {
    }
    
}

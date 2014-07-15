
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014.pid_control.PIDController649;
import com.team649.frc2014.pid_control.PIDVelocitySource;
import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem implements PIDVelocitySource, PIDOutput{
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private SpeedController[] motors;
    private Encoder[] encoders;
    private PIDController649 PIDController;
    private DoubleSolenoid shifterSolenoid;
    
    public DriveTrainSubsystem(){
        motors = new SpeedController[RobotMap.DRIVE_TRAIN.MOTORS.length];
        for(int i = 0; i<RobotMap.DRIVE_TRAIN.MOTORS.length; i++) {
            motors[i] = new Victor(RobotMap.DRIVE_TRAIN.MOTORS[i]);
        }
        encoders = new Encoder[RobotMap.DRIVE_TRAIN.ENCODERS.length];
        for(int i = 0; i<RobotMap.DRIVE_TRAIN.ENCODERS.length; i++) {
            encoders[i/2] = new Encoder(RobotMap.DRIVE_TRAIN.ENCODERS[i], RobotMap.DRIVE_TRAIN.ENCODERS[i+1], i == 0, CounterBase.EncodingType.k2X);
            encoders[i/2].setDistancePerPulse(DriveTrainSubsystem.EncoderBasedDriving.ENCODER_DISTANCE_PER_PULSE);
        }
    }
   
    public static final class EncoderBasedDriving {
        private static final double ENCODER_DISTANCE_PER_PULSE = (4 * Math.PI / 128);
    }
    public void pidWrite(double output) {
       
    }

    public double getRate() {
    
    }

    public double pidGet() {
        
    }

    protected void initDefaultCommand() {
     
    }
}


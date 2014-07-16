
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014.pid_control.PIDController649;
import com.team649.frc2014.pid_control.PIDVelocitySource;
import com.team649.frc2014summer.Display;
import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Vector;

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
    private double accel;
    private static final int UPDATE_PERIOD = 100;
    private Vector lastRates;
    
    
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

    public void driveFwdRot(double fwd, double rot) {
        double left = fwd + rot, right = fwd - rot;
        double max = Math.max(1, Math.max(Math.abs(left), Math.abs(right)));
        left /= max;
        right /= max;
        rawDrive(left, right);
    }

   private void rawDrive(double left, double right) {
        int i = 0;
        for (; i < motors.length / 2; i++) {
            motors[i].set(left);
        }

        for (; i < motors.length; i++) {
            motors[i].set(-right);
        }
    }

    private double getDistance() {
         //To change body of generated methods, choose Tools | Templates.
        int numEncoders = encoders.length;
        double encoderSumVal =0;
        for(int i =0; i < numEncoders; i++) {
            encoderSumVal += encoders[i].getDistance();
        }
        return encoderSumVal/numEncoders;
    }
 public int updateAccel() {
        double rate = getVelocity();

        while (lastRates.size() >= SmartDashboard.getNumber("numPoints")) {
            lastRates.removeElementAt(0);
        }
        lastRates.addElement(new Double(rate));
        double avgX = 0;
        for (int i = 0; i < lastRates.size(); i++) {
            avgX += i;
        }
        double avgY = 0;
        for (int i = 0; i < lastRates.size(); i++) {
            avgY += ((Double) lastRates.elementAt(i)).doubleValue();
        }
        double sumTop = 0;
        for (int i = 0; i < lastRates.size(); i++) {
            sumTop += (i - lastRates.size() / 2 + 0.5) * (((Double) lastRates.elementAt(i)).doubleValue() - avgY);
        }

        double sumBot = 0;
        for (int i = 0; i < lastRates.size(); i++) {
            sumBot += (i - avgX) * (i - avgX);
        }

        SmartDashboard.putNumber("veloc", rate);
        this.accel = 1000 * sumTop / sumBot;
        SmartDashboard.putNumber("accel", this.accel);
        return UPDATE_PERIOD;
    }
    private double getVelocity() {
        int numEncoders = encoders.length;
        double encoderSumVal =0;
        for(int i =0; i < numEncoders; i++) {
            encoderSumVal += encoders[i].getRate();
        }
        return encoderSumVal/numEncoders;
    }
   
    public void printEncoders() {
        for(int i=0; i<encoders.length; i++) {
            Display.queue("Dis " + encoders[i].getDistance());
            Display.queue("Vel " + encoders[i].getRate());
        }
    }
    
    public PIDController649 getPID() {
        return PIDController;
    }
    public boolean isRegularPidOnTarget() {
        return PIDController.onTarget();
    }
    public static final class EncoderBasedDriving {
        private static final double ENCODER_DISTANCE_PER_PULSE = (4 * Math.PI / 128);
        private static final double MIN_MOTOR_POWER = 0.25;
    }
    
    public void disablePID() {
        PIDController.disable();
    }
    public void pidWrite(double output) {
         output = (output < 0 ? -1 : 1) * Math.max(Math.abs(output), EncoderBasedDriving.MIN_MOTOR_POWER);
        driveFwdRot(output, 0);
    }

    public double getRate() {
    return getVelocity();
    }

    public double pidGet() {
        return getDistance();
    }
    public void shiftDriveGear(boolean lowGear) {
        if(lowGear) {
            shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else
            shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    protected void initDefaultCommand() {
     
    }
    
    public void startEncoders() {
        
    }
}


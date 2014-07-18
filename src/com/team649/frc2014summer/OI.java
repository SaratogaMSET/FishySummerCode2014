package com.team649.frc2014summer;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    /**
     *
     */
    private Joystick vertical;
    private Joystick horizontal;
    private Joystick ShooterJoystick;
    private double ROTATION_POWER = 1.5;
    public final Driver driver;
    public final Shooter shooter;

    public OI() {
        vertical = new Joystick(RobotMap.LEFT_DRIVE_JOYSTICK);
        horizontal = new Joystick(RobotMap.RIGHT_DRIVE_JOYSTICK);
        ShooterJoystick = new Joystick(RobotMap.SHOOTER_JOYSTICK);
        driver = new Driver();
        shooter = new Shooter();
    }

    public class Driver {

        public static final double MIN_DRIVE_POWER = 0.05;
        
         public double getDriveRotation() {
            final double turnVal = checkJoystickDeadzone(horizontal.getX(), MIN_DRIVE_POWER);
            final double sign = turnVal < 0 ? -1 : 1;
            return MathUtils.pow(Math.abs(turnVal), ROTATION_POWER) * sign;
        } 
         
       public double getDriveForward() {
           double value = -vertical.getY();
           value = checkJoystickDeadzone(value, MIN_DRIVE_POWER);
           return value;
       }
        
        public double checkJoystickDeadzone(double value, double deadzone) {
            if(Math.abs(value) < deadzone) {
                return 0;
            }
            return value;
        }
        
         public boolean isTriggerButtonPressed() {
            return(vertical.getRawButton(1) || horizontal.getRawButton(1));
        }
    }

    public class Shooter {
        
    }

}

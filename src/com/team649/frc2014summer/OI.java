package com.team649.frc2014summer;

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
    private Joystick horizontial;
    private Joystick ShooterJoystick;
    public final Driver driver;
    public final Shooter shooter;

    public OI() {
        vertical = new Joystick(RobotMap.LEFT_DRIVE_JOYSTICK);
        horizontial = new Joystick(RobotMap.RIGHT_DRIVE_JOYSTICK);
        ShooterJoystick = new Joystick(RobotMap.SHOOTER_JOYSTICK);
        driver = new Driver();
        shooter = new Shooter();
    }

    public class Driver {

        public static final double MIN_DRIVE_POWER = 0.05;

    }

    public class Shooter {

    }

}

package com.team649.frc2014summer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int PRESSURE_SWTICH_CHANNEL = 0;
    public static final int PRESSURE_RELAY_CHANNEL = 0;

    public static class DRIVE_TRAIN {
        public static final int FORWARD_SOLENOID_CHANNEL = 1;
        public static final int REVERSE_SOLENOID_CHANNEL = 2;
        //Array holds victor or encoder ports for each of drive train motors
        public static final int[] MOTORS = {1, 2, 3, 4};
        public static final int[] ENCODERS = {1, 2, 3, 4};

    }
}

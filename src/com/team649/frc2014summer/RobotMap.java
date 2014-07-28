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
    public static final int RIGHT_DRIVE_JOYSTICK = 1;
    public static final int LEFT_DRIVE_JOYSTICK = 2;
    public static final int SHOOTER_JOYSTICK = 3;

    public static class DRIVE_TRAIN {

        public static final int FORWARD_SOLENOID_CHANNEL = 1;
        public static final int REVERSE_SOLENOID_CHANNEL = 2;
        //Array holds victor or encoder ports for each of drive train motors
        public static final int[] MOTORS = {1, 2, 3, 4};
        public static final int[] ENCODERS = {1, 2, 3, 4};

    }

    public static class ANGLED_PICK_UP {

        public static final int LEFT_SOLENOID_FORWARD_CHANNEL = 3;
        public static final int LEFT_SOLENOID_REVERSE_CHANNEL = 4;
        public static final int RIGHT_SOLENOID_FORWARD_CHANNEL = 5;
        public static final int RIGHT_SOLENOID_REVERSE_CHANNEL = 6;
        public static final int MOTOR_PORT = 7;

    }

    public static class HINGED_PICK_UP {

        public static final int LEFT_SOLENOID_FORWARD_CHANNEL = 7;
        public static final int LEFT_SOLENOID_REVERSE_CHANNEL = 8;
        public static final int RIGHT_SOLENOID_FORWARD_CHANNEL = 9;
        public static final int RIGHT_SOLENOID_REVERSE_CHANNEL = 10;
        public static final int MOTOR_PORT = 8;

    }

    public static class SHOOTER {

        public static final int MOTOR_PORT_1 = 5;
        public static final int MOTOR_PORT_2 = 6;
        public static final int POTENTIOMETER_PORT = 1;
        public static final int LIMIT_SWITCH_1_PORT = 3;
        public static final int LIMIT_SWITCH_2_PORT = 4;

    }
}

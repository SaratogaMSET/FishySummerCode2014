package com.team649.frc2014summer;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    private Joystick vertical;
    private Joystick horizontal;
    private Joystick shooterJoystick;
    private Joystick fullManualShooterJoystick;

    private double ROTATION_POWER = 1.5;
    public final Driver driver;
    public final Shooter shooter;
    public final Manual manual;

    public OI() {
        this.vertical = new Joystick(RobotMap.JOYSTICK_DRIVER_LEFT);
        this.horizontal = new Joystick(RobotMap.JOYSTICK_DRIVER_RIGHT);
        this.shooterJoystick = new Joystick(RobotMap.JOYSTICK_SHOOTER);
         this.fullManualShooterJoystick = new Joystick(RobotMap.JOYSTICK_SHOOTER_MANUAL);
        driver = new Driver();
        shooter = new Shooter();
       manual = new Manual();
    }

    public class Driver {

        public static final double MIN_DRIVE_POWER = 0.05;

        public double getDriveRotation() {
            final double turnVal = joystickDeadzone(horizontal.getX(), MIN_DRIVE_POWER);
            final double sign = turnVal < 0 ? -1 : 1;
            return MathUtils.pow(Math.abs(turnVal), ROTATION_POWER) * sign;
        }

        public double getDriveForward() {
            double value = -vertical.getY();
            value = joystickDeadzone(value, MIN_DRIVE_POWER);
            return value;
        }

        private double joystickDeadzone(double value, double deadzone) {
            if (Math.abs(value) < deadzone) {
                value = 0;
            }
            return value;
        }

        public boolean isDrivetrainLowGearButtonPressed() {
            return horizontal.getRawButton(1) || vertical.getRawButton(1);
        }

        public boolean isWinchSwitchOverrideButtonPressed() {
            return horizontal.getRawButton(2) || vertical.getRawButton(2);
        }
    }

    public class Shooter {

        public boolean isShooterTriggerButtonPressed() {
            return shooterJoystick.getRawButton(1);
        }

        public boolean isPivotManualOverrideButtonPressed() {
            return shooterJoystick.getRawButton(2);
//            return true;
        }

        public boolean isWinchSafetyButtonPressed() {
            return shooterJoystick.getRawButton(3);
        }

        public boolean isPickupButtonPressed() {
            return shooterJoystick.getRawButton(4);
        }

        public boolean isWinchWindButtonPressed() {
            return shooterJoystick.getRawButton(5);
        }

        public boolean isPurgeButtonPressed() {
            return shooterJoystick.getRawButton(6);
        }

        public boolean isPickupClawPositionButtonPressed() {
            return shooterJoystick.getRawButton(11);
        }

        public boolean isStoreClawPositionButtonPressed() {
            return shooterJoystick.getRawButton(12);
        }

        public boolean isBackwardShootClawPositionButtonPressed() {
            //Display.printToOutputStream(shooterJoystick.getThrottle()+"");
            return shooterJoystick.getRawButton(10);
        }

        public boolean isGoalShootClawPositionButtonPressed() {
            return shooterJoystick.getRawButton(7);
        }

        public boolean isWinchSwitchOverrideButtonPressed() {
            return shooterJoystick.getRawButton(8);
        }

        public boolean isForwardShootClawPositionButtonPressed() {
            return shooterJoystick.getRawButton(9);
        }

        public double getShooterJoystickY() {
            return shooterJoystick.getY();
        }

        public boolean isAutoWinchOn() {
            return (shooterJoystick.getThrottle() >= .5);
        }

    }

    public class Manual {

        public boolean isManualWinchButtonPressed() {
            return (fullManualShooterJoystick.getRawButton(4));
        }

        public double getManualShooterJoystickY() {
            return (fullManualShooterJoystick.getRawAxis(1));
        }

        public double getRollerButtonsPressed() {
            return fullManualShooterJoystick.getRawAxis(6);
        }

        public boolean isFingerButtonPressed() {
            return fullManualShooterJoystick.getRawButton(1);
        }

        public boolean isShooterTriggerButtonPressed() {
            return shooterJoystick.getRawButton(8);
        }

        public boolean isShooterSafetyButtonPressed() {
            return shooterJoystick.getRawButton(2);
        }
    }

}

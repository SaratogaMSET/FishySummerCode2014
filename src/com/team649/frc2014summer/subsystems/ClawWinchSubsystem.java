/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Suneel
 */
public class ClawWinchSubsystem extends Subsystem {

    private static final double MOTOR_SPEED = 1;
    public static final int TIME_TO_FIRE = 250;
    public static final int TIME_TO_ENGAGE_SOLENOID = 250;
    public static final int MAX_COIL_TIME = 3000;

    private final SpeedController motor;

    private DoubleSolenoid engageClaw;

    private static DigitalInput limitSwitch1;
    private static DigitalInput limitSwitch2;
    private static DigitalInput limitSwitch3;

    public ClawWinchSubsystem() {
        motor = new Victor(RobotMap.CLAW_WINCH.MOTOR);
        limitSwitch1 = new DigitalInput(RobotMap.CLAW_WINCH.LIMIT_SWITCH1_INPUT);
        limitSwitch2 = new DigitalInput(RobotMap.CLAW_WINCH.LIMIT_SWITCH2_INPUT);
        limitSwitch3 = new DigitalInput(RobotMap.CLAW_WINCH.LIMIT_SWITCH3_INPUT);
        engageClaw = new DoubleSolenoid(RobotMap.CLAW_WINCH.ENGAGED_SOLENOID_CHANNEL, RobotMap.CLAW_WINCH.LOOSE_SOLENOID_CHANNEL);
    }

    public void runMotor() {
        motor.set(MOTOR_SPEED);
    }

    public void stopMotor() {
        motor.set(0);
    }

    public boolean isSwitchPressed() {
        int voting = 0;
        if (limitSwitch1.get()) {
            voting++;
        }
        if (limitSwitch2.get()) {
            voting++;
        }
        if (limitSwitch3.get()) {
            voting++;
        }
        if (voting > 2) {
            return true;
        }
        return false;
    }

    public void setSolenoid(boolean state) {
        engageClaw.set(state ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public boolean getSolenoidState() {
        return engageClaw.get() == DoubleSolenoid.Value.kForward ? true : false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

}

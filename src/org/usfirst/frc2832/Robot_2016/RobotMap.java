// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by RobotBuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2832.Robot_2016;

import nav6.frc.IMUAdvanced;

import com.ni.vision.NIVision.CalibrationThumbnailType;

import edu.wpi.first.wpilibj.AnalogInput;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Servo;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    public static CANTalon frontLeftMotor;
    public static CANTalon frontRightMotor;
    public static CANTalon backLeftMotor;
    public static CANTalon backRightMotor;
    
    public static CANTalon winchMotor;
    
    public static CANTalon ballIngestLeft;
    public static CANTalon ballIngestRight;
    
    public static AnalogInput laser;
    public static AnalogInput proxSensor;
    
    public static Servo kicker; 
    
    public static RobotDrive driveTrain;

    public static IMUAdvanced imu;
    
    public static final double ENCODER_PULSE_PER_METER = 2800;

    public static void init() {

        frontLeftMotor = new CANTalon(1);
        LiveWindow.addActuator("Drivetrain", "frontLeft", frontLeftMotor);
        
        frontRightMotor = new CANTalon(3);
        LiveWindow.addActuator("Drivetrain", "frontRight", frontRightMotor);
        
        //the following code sets the back motors as slaves/followers to the front
        backLeftMotor = new CANTalon(2);
        backLeftMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        backLeftMotor.set(1);
        backRightMotor = new CANTalon(4);
        backRightMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        backRightMotor.set(3);
        
        driveTrain = new RobotDrive(frontLeftMotor, frontRightMotor);
        
        driveTrain.setSafetyEnabled(true);
        driveTrain.setExpiration(0.1);
        driveTrain.setSensitivity(0.5);
        driveTrain.setMaxOutput(1.0);

        frontLeftMotor.setInverted(true);
        frontRightMotor.setInverted(true);
        
        winchMotor = new CANTalon(8);
        winchMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        
        ballIngestLeft = new CANTalon(5);
        LiveWindow.addActuator("Ball Handler", "ingestLeft",ballIngestLeft);
        ballIngestRight = new CANTalon(4);
        LiveWindow.addActuator("Ball Handler", "ingestRight", ballIngestRight);

        laser = new AnalogInput(0);
        proxSensor = new AnalogInput(6);
        kicker = new Servo(1);
        
        byte update_rate_hz = 50;
        imu = new IMUAdvanced(new SerialPort(57600,SerialPort.Port.kMXP), update_rate_hz);
    }
}

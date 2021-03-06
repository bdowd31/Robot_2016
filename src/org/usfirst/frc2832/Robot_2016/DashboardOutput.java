package org.usfirst.frc2832.Robot_2016;

import org.usfirst.frc2832.Robot_2016.Aimer.Levels;
import org.usfirst.frc2832.Robot_2016.commands.AutonomousCommand;
import org.usfirst.frc2832.Robot_2016.commands.GoToPosition;
import org.usfirst.frc2832.Robot_2016.commands.VisionAimHoriz;
import org.usfirst.frc2832.Robot_2016.commands.InterfaceFlip;
import org.usfirst.frc2832.Robot_2016.commands.ResetEncoderToGround;
import org.usfirst.frc2832.Robot_2016.commands.Shoot;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.MoveForward;
import org.usfirst.frc2832.Robot_2016.commands.autonomous.RotateAngle;
import org.usfirst.frc2832.Robot_2016.vision.CameraServer2832;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Zach
 * Helper class which encapsulates all the stuff we need to throw at the Dashboard
 */
public class DashboardOutput {

	static boolean hasCameraCaught = false;
	//This method is invoked in teleopPeriodic to display all the crap we put on the dashboard
	public static void putPeriodicData()
	{
		SmartDashboard.putNumber("laser",RobotMap.laser.pidGet()); //this value may not be right value?
		SmartDashboard.putNumber("Left Encoder", DriveEncoders.getLeftValue());
		SmartDashboard.putNumber("Right Encoder", DriveEncoders.getRightValue());
		SmartDashboard.putNumber("Combined Enc.", DriveEncoders.getAbsoluteValue());
		SmartDashboard.putNumber("Servo val", Kicker.get());
		SmartDashboard.putNumber("Winch Pos", RobotMap.winchMotor.getEncPosition());
		SmartDashboard.putNumber("Winch Motor Value", RobotMap.winchMotor.get());
		SmartDashboard.putNumber("Gyro.Yaw", RobotMap.imu.getYaw());
		SmartDashboard.putNumber("Gyro.Pitch", RobotMap.imu.getPitch());
		SmartDashboard.putNumber("Gyro.Roll", RobotMap.imu.getRoll());
		SmartDashboard.putNumber("Proximity Sensor", RobotMap.proxSensor.pidGet());
		SmartDashboard.putNumber("winch error", RobotMap.winchMotor.getError());
		SmartDashboard.putBoolean("Forward Lim", RobotMap.winchMotor.isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean("Reverse Lim", RobotMap.winchMotor.isRevLimitSwitchClosed());
		SmartDashboard.putBoolean("Is Aimer Control Enabled?", RobotMap.winchMotor.isControlEnabled());
		SmartDashboard.putNumber("winch setpoint", RobotMap.winchMotor.getSetpoint());
		
		//TODO: best to remove the below code at competition to reduce lag
		//ImageProcessing.process();
		//SmartDashboard.putNumber("contour x pos", ImageProcessing.targetOffset[0]);
		
		// Zach and Doug: We are getting null pointer exceptions from this code now and then.
		// Removing since not used when the raspberry pi is being used.
		/*if (!hasCameraCaught)
		{
			try {
				CameraServer2832.getInstance().setSelectedCamera(InterfaceFlip.isFlipped ? 1 : 0);
			} catch(Exception e) {
				hasCameraCaught = true;
			}
		}*/
		
		SmartDashboard.putData(Scheduler.getInstance());
	}
	
	//This method is invoked in the OI constructor to add one-time things (command buttons, etc.)
	public static void putOneTimeData()
	{
		SmartDashboard.putBoolean("Use Recorded Autonomous", false);
    	SmartDashboard.putData("Autonomous Command", new AutonomousCommand()); 
        SmartDashboard.putData("shoot", new Shoot());  
      	SmartDashboard.putData("Flip Motors", new InterfaceFlip());
      	SmartDashboard.putData("Move Forward 5", new MoveForward(5));
      	SmartDashboard.putData("Rotate 45", new RotateAngle(45));
      	SmartDashboard.putData("Move Forward 3", new MoveForward(3));
      	SmartDashboard.putData("Rotate -45", new RotateAngle(-45));
      	SmartDashboard.putData("Move Backward 5", new MoveForward(-5));
      	SmartDashboard.putData("GoToLevel 0", new GoToPosition(Levels.START));
		SmartDashboard.putData("GoToLevel 1", new GoToPosition(Levels.HIGH));
      	SmartDashboard.putData("GoToLevel 2", new GoToPosition(Levels.LOW));
		SmartDashboard.putData("GoToLevel 3", new GoToPosition(Levels.GROUND));
		SmartDashboard.putData("Reset Encoder to GROUND", new ResetEncoderToGround());
		
//DCM		SmartDashboard.putString("MAC", Robot.GetMacAddress());
	}
}

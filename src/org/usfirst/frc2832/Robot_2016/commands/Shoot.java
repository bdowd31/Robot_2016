package org.usfirst.frc2832.Robot_2016.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc2832.Robot_2016.BallMotors;
import org.usfirst.frc2832.Robot_2016.Kicker;
import org.usfirst.frc2832.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//DO NOT USE THIS
/*
 * Pushes out the ball assuming the ball motors are already running
 * Turns it off after timeout.
 * NOTE: The timeout isn't really a "timeout", it's just time between launching and resetting
 * This is because the servo is stable, it needs no tolerances, relative measurements, or timeouts
 */
public class Shoot extends Command {

	private long timeStart;
	//length of timeout, in milliseconds
	private static final long TIMEOUT = 3000;
	private static double startAngle;
	private static final double ANGLE_TOLERANCE = 0.05; //how many degrees it wants to return within
	private static double  DELAY = 2500; //milliseconds that the ball motors move for before the kicker kicks in
	
	public Shoot()
	{
		//TODO: what does this require?
	}
	
	
	protected void initialize() {
		
		//DELAY = Preferences.getInstance().getDouble("Shooter Delay (Seconds)", 1);//this does not display
		BallMotors.expel(0.5);
		//record time of command start
		timeStart = System.currentTimeMillis();
		Robot.isSpinning = true;
		
	}

	@Override
	protected void execute() {
		//Kicker.resetAfterLaunch();
		
		BallMotors.expel(0.5);
		if ((timeStart + DELAY) < System.currentTimeMillis())
		{
			Kicker.launch();
			Robot.isSpinning=true;
			Robot.isShooting=true;
		}
	}

	@Override
	protected boolean isFinished() {
		//returns true only when timeStart + TIMEOUT < current time
		return ((timeStart + TIMEOUT) < System.currentTimeMillis());
	}

	@Override
	protected void end() {
		Kicker.resetAfterLaunch();
		BallMotors.stopMotors();
		Robot.isShooting=false;
		Robot.isSpinning=false;
	}

	@Override
	protected void interrupted() {
		//Kicker.reset(); //fail-safe
		Robot.isShooting=false;
		Robot.isSpinning=false;
		BallMotors.stopMotors();
	}

}

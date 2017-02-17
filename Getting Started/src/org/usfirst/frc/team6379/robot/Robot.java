package org.usfirst.frc.team6379.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myDrive;
	Spark frontLeft, frontRight, rearLeft, rearRight;
	Joystick driveStick; /** For joystick */
	Timer timer = new Timer();
	Compressor c = new Compressor(0); /** For control of compressor */
	DoubleSolenoid exampleDouble = new DoubleSolenoid(1, 2); /** For control of cylinder */

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		/** sets the spark controllers to each PWM */
		Spark frontLeft = new Spark(0); 
		Spark frontRight = new Spark(1);
		Spark rearLeft = new Spark(2);
		Spark rearRight = new Spark(3);
		myDrive = new RobotDrive(frontLeft, frontRight, rearLeft, rearRight);
		
		/** Sets joystick to the 0 input on computer/device */
		driveStick = new Joystick(0);
	}
	

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	@Override
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (timer.get() < 2.0) {
			 // drive forwards half speed
		} else {
			 // stop robot
		}
	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	@Override
	public void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		myDrive.arcadeDrive(driveStick); /** Used to drive the robot */
		
		/** for controlling the compressor */
		c.setClosedLoopControl(true); 
		c.setClosedLoopControl(false);
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		
		myDrive.arcadeDrive(driveStick); /** Used to drive the robot */
		
		/** for controlling the compressor */
		c.setClosedLoopControl(true);
		c.setClosedLoopControl(false);
		
	}
}

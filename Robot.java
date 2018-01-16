package org.usfirst.frc.team6379.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	RobotDrive myRobot = new RobotDrive(0, 1); // class that handles basic drive
	Joystick leftStick = new Joystick(0); // set to ID 1 in DriverStation
	Button button1 = new JoystickButton(leftStick, 1);
	Timer timer = new Timer();
	CameraServer server;
	
	public Robot() {
		myRobot.setExpiration(0.1);
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
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
		// Drive for 3 seconds
		if (timer.get() < 3.0) {
			myRobot.drive(0.5, 0.0); // drive forwards half speed
		} else {
			myRobot.drive(0.0, 0.0); // stop robot
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
		if (leftStick.getRawButton(1)){
			myRobot.setSafetyEnabled(true);
			myRobot.arcadeDrive(leftStick);
			myRobot.arcadeDrive(-leftStick.getY(), -leftStick.getZ()*.5, true); // gets and inverts joystick input, and cuts power to motors by 1/2
			Timer.delay(0.005); // wait for a motor update time
		}
		else {
			myRobot.setSafetyEnabled(true);
			myRobot.arcadeDrive(leftStick);
			myRobot.arcadeDrive(-leftStick.getY(), -leftStick.getX(), true); // gets and inverts joystick input, and cuts power to motors by 1/2
			Timer.delay(0.005); // wait for a motor update time
			}
		}


	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}

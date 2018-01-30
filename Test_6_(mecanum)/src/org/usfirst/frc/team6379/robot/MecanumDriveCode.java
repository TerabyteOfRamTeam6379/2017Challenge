package org.usfirst.frc.team6379.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;





import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.SpeedController;



public class MecanumDriveCode extends IterativeRobot {
	 	
	SpeedController leftFront1; // Initializing motor 1; front-left motor
	SpeedController leftBack1; // Initializing motor 3; back-left motor
	SpeedController rightFront1;// Initializing motor 2; front-right motor
	SpeedController rightBack1;
	
	MecanumDrive myRobot = new MecanumDrive( leftFront1 ,leftBack1, rightFront1, rightBack1);
	
	 Joystick leftStick = new Joystick(0); // set to ID 1 in DriverStation
	
	Button button1 = new JoystickButton(leftStick, 1);
	
	Timer timer = new Timer();
	CameraServer server;
	
	
	public  MecanumDriveCode() {
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
	public void autonomousInit() {
		timer.reset();
		timer.start();
	}
	
	
	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(1) == 'L')
		{
			if (timer.get() < 2.0) {
				myRobot.driveCartesian(0.5, 0.5, 0.0); // drive forwards half speed
			} else {
				myRobot.driveCartesian(0.0, 0.0, 0.0); // stop robot
		} 
			
			
		}
		
		else {
			myRobot.driveCartesian(0.0, 0.0, 0.0);
		}
		
	}
			
	

	
	@Override
	public void teleopInit() {
	}

	
	@Override
	public void teleopPeriodic() {
		if (leftStick.getRawButton(1)){
			myRobot.setSafetyEnabled(true);
			
			myRobot.driveCartesian(-leftStick.getRawAxis(5), -leftStick.getX()*.5, 0); // gets and inverts joystick input, and cuts power to motors by 1/2
			Timer.delay(0.005); // wait for a motor update time
		}
		else {
			myRobot.setSafetyEnabled(true);
			 
			myRobot.driveCartesian(leftStick.getRawAxis(2), -leftStick.getY(), 0);
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

	public void initSendable(SendableBuilder builder) {
		// TODO Auto-generated method stub
		
	}

	public void stopMotor() {
		// TODO Auto-generated method stub
		
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}



package org.usfirst.frc.team5263.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    Joystick stick = new Joystick(0);
    Jaguar motor = new Jaguar(0);
    Jaguar motor1 = new Jaguar(1);
    Compressor mainCompressor = new Compressor();
    Solenoid pistonExtract = new Solenoid(0);
    Solenoid pistonRetract = new Solenoid(1);
    Relay relay = new Relay(0);
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        mainCompressor.start();
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	motor.set(stick.getRawAxis(2)); //moves motor to speed of 1st thumbstick
    	motor1.set(stick.getRawAxis(5)); //moves motor1 to speed of 2nd thumbstick
    	
    	if(stick.getRawButton(1)) { //if statement to control piston
    		pistonExtract.set(true);
    		pistonRetract.set(false);
    	} else if(stick.getRawButton(2)) {
    		pistonExtract.set(false);
    		pistonRetract.set(true);
    	}else {
    		pistonExtract.set(false);
    		pistonRetract.set(false);
    	}
    	
    	if(stick.getRawButton(3)) { //if statement to control relay
    		relay.set(Relay.Value.kForward);
    	}else if(stick.getRawButton(4)) {
    		relay.set(Relay.Value.kReverse);
    	}else {
    		relay.set(Relay.Value.kOff);
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

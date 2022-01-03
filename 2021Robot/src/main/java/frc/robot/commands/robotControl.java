package frc.robot.commands;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.DifferentialDriveTrainControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.subsystems.DriveTrainSubsystem;


public class robotControl extends CommandBase {
  XboxController controller = new XboxController(0);
  Joystick driver = new Joystick(1);
  DriveTrainSubsystem driveTrain;
 
  Timer timer = new Timer();
  private int config;


  

  /** this class controls a shaq bot hardware config */
  public robotControl(DriveTrainSubsystem driveTrain, Boolean dualMotorShooter) {
    this.driveTrain = driveTrain;
    
    config = 1;
  }

  public robotControl(DriveTrainSubsystem driveTrain) {
    this.driveTrain = driveTrain;
    
    config = 2;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //drivetrain
    double leftSpeed = driver.getRawAxis(1);
    double rightSpeed = driver.getRawAxis(5);
  
    //checks for robotControl config, one is robotcontrol drivetrain, arm, and dualmotor shooter. config 2 is drivetrain, intake, singlemotor shooter
    if (config == 1) {

      //drivetrain
      driveTrain.DriveDifferential(leftSpeed * -1, rightSpeed * -1);
    
    
  
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
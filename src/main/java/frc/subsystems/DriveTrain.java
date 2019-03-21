package frc.subsystems;

import frc.robot.RobotMap;

public class DriveTrain {
    
    public void drive(double xSpeed, double ySpeed, double rotation) {
        RobotMap.robotDrive.driveCartesian(xSpeed, ySpeed, rotation);
    }
  
    public void turnLeft(double power) {
        RobotMap.robotDrive.driveCartesian(0.0D, 0.0D, -power);
    }
  
    public void turnRight(double power) {
        RobotMap.robotDrive.driveCartesian(0.0D, 0.0D, power);
    }
  
    public void translateLeft(double power) {
        RobotMap.robotDrive.driveCartesian(-power, 0.0D, 0.0D);
    }
  
    public void translateRight(double power) {
        RobotMap.robotDrive.driveCartesian(power, 0.0D, 0.0D);
    }
    
}

package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

public class ActuatorController {

    public void clearPressure() {
        RobotMap.actuator.set(Value.kOff);
    }
  
    public void forwards() {
        RobotMap.actuator.set(Value.kForward);
    }
  
    public void reverse() {
        RobotMap.actuator.set(Value.kReverse);
    }

}
package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

public class GripperController {

    private boolean active = false;
    private boolean wasActive = this.active;
    private long currentTime = System.currentTimeMillis();
    private long clearTime = this.currentTime;
    private DoubleSolenoid gripper = RobotMap.gripper;
  
    public void clearGrippers() {
        this.gripper.set(Value.kOff);
    }
  
    public void refreshGripperStatus() {
        this.currentTime = System.currentTimeMillis();
        if(this.active != this.wasActive) {
            this.clearTime = this.currentTime;
            this.wasActive = this.active;
            this.gripper.set(Value.kOff);
        }
    }
  
    public void setGripper(boolean value) {
        this.active = value;
        if(this.currentTime - this.clearTime >= 500L) {
            if(this.active) {
                this.gripper.set(Value.kForward);
            }

            else {
                this.gripper.set(Value.kReverse);
            }
        }

        else {
            this.gripper.set(Value.kOff);
        }
    }
  
    public boolean getGripper() {
        return this.active;
    }
}

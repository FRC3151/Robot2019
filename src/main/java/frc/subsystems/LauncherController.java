package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.RobotMap;

public class LauncherController {

    private boolean active = false;
    private boolean wasActive = this.active;
    private long currentTime = System.currentTimeMillis();
    private long clearTime = this.currentTime;
    private DoubleSolenoid launcher = RobotMap.launcher;
  
    public void clearLauncher() {
        this.launcher.set(Value.kOff);
    }
  
    public void refreshLauncherStatus() {
        this.currentTime = System.currentTimeMillis();
        if(this.active != this.wasActive) {
            this.clearTime = this.currentTime;
            this.wasActive = this.active;
            this.launcher.set(Value.kOff);
        }
    }
  
    public void setLauncher(boolean value) {
        this.active = value;
        if(this.currentTime - this.clearTime >= 500L) {
            if(this.active) {
                this.launcher.set(Value.kForward);
            }
      
            else {
                this.launcher.set(Value.kReverse);
            }
        }

        else {
            this.launcher.set(Value.kOff);
        }
    }
    
}

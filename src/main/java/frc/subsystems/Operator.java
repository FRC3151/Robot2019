package frc.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.DeadzoneUtils;
import frc.robot.RobotMap;
import java.util.Random;

public class Operator {

    private long time = System.currentTimeMillis();
    private long lastTime = this.time;
    private boolean enableGripper = false;
  
    public void checkForIntake() {
        this.time = System.currentTimeMillis();
        if(((RobotMap.operator.getBumper(Hand.kLeft)) || (RobotMap.operator.getBumper(Hand.kRight))) && (this.time - this.lastTime >= 250L)) {
            this.lastTime = this.time;
            this.enableGripper = (!this.enableGripper);
        }
    }
  
    public boolean getGripper() {
        return this.enableGripper;
    }
  
    public boolean getLauncher() {
        return RobotMap.operator.getAButton();
    }
  
    public boolean lineLock() {
        return RobotMap.operator.getBButton();
    }
  
    public double getLift() {
        double fastControl = DeadzoneUtils.deadzone(-RobotMap.operator.getRawAxis(1), 0.04D);
        double slowControl = DeadzoneUtils.deadzone(-RobotMap.operator.getRawAxis(5), 0.04D);
        if(Math.abs(slowControl) > 0.0D) {
            return 0.15D * slowControl;
        }
        return fastControl;
    }
  
    public void vibrate() {
        RobotMap.operator.setRumble(RumbleType.kLeftRumble, 1.0D);
        RobotMap.operator.setRumble(RumbleType.kRightRumble, 1.0D);
    }
  
    public void rVibrate() {
        Random r = new Random();
        double left = 0.75D / (r.nextInt(15) + 1) + 0.25D;
        double right = 0.75D / (r.nextInt(15) + 1) + 0.25D;
        RobotMap.operator.setRumble(RumbleType.kLeftRumble, left);
        RobotMap.operator.setRumble(RumbleType.kRightRumble, right);
    }
  
    public void resetVibration() {
        RobotMap.operator.setRumble(RumbleType.kLeftRumble, 0.0D);
        RobotMap.operator.setRumble(RumbleType.kRightRumble, 0.0D);
    }
    
}

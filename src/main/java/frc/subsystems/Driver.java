package frc.subsystems;

import frc.robot.DeadzoneUtils;
import frc.robot.RobotMap;

public class Driver {

    private boolean reversed = false;
  
    private double xMovement() {
        if(!RobotMap.driver.getTrigger()) {
            double raw = RobotMap.driver.getX();
            double deadzoned = DeadzoneUtils.deadzone(raw, 0.04D);
            double capped = deadzoned * 0.75D;
            double tuning = deadzoned * 0.1D;
            if(getTuning()) {
                if(this.reversed) {
                    return -tuning;
                }
        
                return tuning;
            }

            if(getRush()) {
                if(this.reversed) {
                    return -deadzoned;
                }
                return deadzoned;
            }

            if(this.reversed) {
                return -capped;
            }
            return capped;
        }
        return 0.0D;
    }
  
    private double yMovement() {
        double raw = RobotMap.driver.getY();
        double deadzoned = DeadzoneUtils.deadzone(raw, 0.04D);
        double undoInvert = -deadzoned;
        double capped = undoInvert * 0.75D;
        double tuning = undoInvert * 0.1D;
        if(getTuning()) {
            if(this.reversed) {
                return -tuning;
            }
            return tuning;
        }

        if(getRush()) {
            if(this.reversed) {
                return deadzoned;
            }
            return undoInvert;
        }

        if(this.reversed) {
            return -capped;
        }
        return capped;
    }

    private double rotation() {
        if(RobotMap.driver.getTrigger()) {
            double raw = RobotMap.driver.getX();
            double deadzoned = DeadzoneUtils.deadzone(raw, 0.04D);
            if(this.reversed) {
                return -deadzoned;
            }
            return deadzoned;
        }
        return 0.0D;
    }
  
    public double getX() {
        return xMovement();
    }
  
    public double getY() {
        return yMovement();
    }
  
    public double getRotation() {
        return rotation();
    }
  
    public boolean getTuning() {
        return RobotMap.driver.getRawButton(2);
    }
  
    public boolean getRush() {
        return RobotMap.driver.getRawButton(3);
    }
  
    public void checkInversion() {
        if((RobotMap.driver.getRawButton(8)) && (!RobotMap.driver.getRawButton(9)) && (this.reversed)) {
            this.reversed = false;
        }

        if((RobotMap.driver.getRawButton(9)) && (!RobotMap.driver.getRawButton(8)) && (!this.reversed)) {
            this.reversed = true;
        }
    }
  
    public void backIsFront() {
        this.reversed = true;
    }
  
    public void frontIsFront() {
        this.reversed = false;
    }
    
}

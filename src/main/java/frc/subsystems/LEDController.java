package frc.subsystems;

import frc.robot.RobotMap;

public class LEDController {

    public static enum Color {

        DARK_RED(0.59D),
        RED(0.61D),
        RED_BEATING(0.07D),
        LIGHT_BLUE(0.81D),
        BLUE(0.85D),
        PURPLE(0.89D),
        BLUE_BEATING(0.27D),
        ORANGE(0.65D),
        LIGHT_GREEN(0.73D),
        GREEN(0.77D),
        YELLOW(0.69D),
        WHITE(0.93D),
        BLACK(0.99D),

        BLUE_CHASE(-0.29D),
        RED_CHASE(-0.31D),
        COLOR_WAVE(-0.43D),
        SINELON(-0.77D);
    
        private double output;
    
        private Color(double output) {
            this.output = output;
        }
    
        public double getID() {
            return this.output;
        }
    }
  
    public void setColor(Color color) {
        RobotMap.blinkin.set(color.getID());
    }
}

package frc.robot;

public class DeadzoneUtils {

    public static double deadzone(double value, double deadband) {
        if(Math.abs(value) > deadband) {
            if(value > 0.0D) {
                return (value - deadband) / (1.0D - deadband);
            }
        return (value + deadband) / (1.0D - deadband);
        }
    return 0.0D;
    }
    
}

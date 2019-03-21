package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class RobotMap {

  public static Joystick driver = new Joystick(0);
  public static XboxController operator = new XboxController(1);

  public static Compressor compressor = new Compressor(0);
  public static DoubleSolenoid gripper = new DoubleSolenoid(1, 2);
  public static DoubleSolenoid actuator = new DoubleSolenoid(4, 3);
  public static DoubleSolenoid launcher = new DoubleSolenoid(7, 6);

  public static WPI_TalonSRX front_Left = createTalon(1, NeutralMode.Brake);
  public static WPI_TalonSRX back_Left = createTalon(2, NeutralMode.Brake);
  public static WPI_TalonSRX back_Right = createTalon(8, NeutralMode.Brake);
  public static WPI_TalonSRX front_Right = createTalon(7, NeutralMode.Brake);
  public static WPI_TalonSRX lift_One = createTalon(3, NeutralMode.Brake);
  public static WPI_TalonSRX lift_Two = createTalon(4, NeutralMode.Brake);

  public static Spark blinkin = new Spark(0);

  public static MecanumDrive robotDrive = new MecanumDrive(front_Left, back_Left, front_Right, back_Right);
  
    public static void init() {
        lift_Two.follow(lift_One);
    }
  
    private static WPI_TalonSRX createTalon(int id, NeutralMode mode) {
        WPI_TalonSRX talon = new WPI_TalonSRX(id);
        talon.setNeutralMode(mode);
        talon.configVoltageCompSaturation(12.0D, 1000);
        talon.enableVoltageCompensation(true);
        return talon;
    }
    
}

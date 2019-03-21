package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.subsystems.ActuatorController;
import frc.subsystems.DriveTrain;
import frc.subsystems.Driver;
import frc.subsystems.GripperController;
import frc.subsystems.LEDController;
import frc.subsystems.LEDController.Color;
import frc.subsystems.LauncherController;
import frc.subsystems.Operator;

public class Robot extends TimedRobot {
    Driver driver = new Driver();
    Operator operator = new Operator();
    DriveTrain robotDrive = new DriveTrain();
    Compressor compressor = new Compressor();
    GripperController gripperController = new GripperController();
    LauncherController launcherController = new LauncherController();
    ActuatorController actuatorController = new ActuatorController();
    LEDController LEDControl = new LEDController();
    Alliance alliance = null;
  
    public void robotInit() {
        this.compressor.setClosedLoopControl(false);
    
        UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("Front Facing", "/dev/video0");
        cam.setResolution(160, 120);
        cam.setFPS(30);
        cam.setBrightness(25);
        cam.setExposureAuto();
        cam.setWhiteBalanceAuto();
    
        RobotMap.init();
    
        this.operator.resetVibration();
    
        this.gripperController.clearGrippers();
        this.launcherController.clearLauncher();
        this.actuatorController.clearPressure();
    
        this.driver.frontIsFront();
    }
  
    public void autonomousInit() {
        this.alliance = DriverStation.getInstance().getAlliance();
        if(this.alliance.equals(Alliance.Blue)) {
            this.LEDControl.setColor(Color.BLUE);
        }

        else {
            this.LEDControl.setColor(Color.RED);
        }
        this.compressor.setClosedLoopControl(true);
        this.actuatorController.forwards();
    }

    public void autonomousPeriodic() {
        runningLoop();
        if(this.alliance.equals(Alliance.Blue)) {
            if(DriverStation.getInstance().getMatchTime() % 2.0D == 0.0D) {
                this.LEDControl.setColor(Color.BLUE);
            }
            
            else {
                this.LEDControl.setColor(Color.GREEN);
            }
        }

        else if(DriverStation.getInstance().getMatchTime() % 2.0D == 0.0D) {
            this.LEDControl.setColor(Color.RED);
        }
    
        else {
            this.LEDControl.setColor(Color.ORANGE);
        }
    }
  
    public void teleopInit() {
        if(DriverStation.getInstance().getAlliance().equals(Alliance.Blue)) {
            this.LEDControl.setColor(Color.BLUE_BEATING);
        }
        
        else {
            this.LEDControl.setColor(Color.RED_BEATING);
        }

        this.compressor.setClosedLoopControl(true);
      this.actuatorController.forwards();
    }
  
    public void teleopPeriodic() {
        runningLoop();
        if(DriverStation.getInstance().getMatchTime() <= 15.0D) {
            if(this.alliance.equals(Alliance.Blue)) {
                if(DriverStation.getInstance().getMatchTime() % 2.0D == 0.0D) {
                    this.LEDControl.setColor(Color.BLACK);
                }
                
                else {
                    this.LEDControl.setColor(Color.LIGHT_BLUE);
                }
            }

            else if(DriverStation.getInstance().getMatchTime() % 2.0D == 0.0D) {
                this.LEDControl.setColor(Color.YELLOW);
            }
            
            else {
               this.LEDControl.setColor(Color.DARK_RED);
            }
        }

        else if(this.driver.getRush()) {
            if(this.alliance.equals(Alliance.Blue)) {
                this.LEDControl.setColor(Color.BLUE_CHASE);
            }
            
            else {
                this.LEDControl.setColor(Color.RED_CHASE);
            }
        }

        else if (this.driver.getTuning()) {
            if(this.alliance.equals(Alliance.Blue)) {
                this.LEDControl.setColor(Color.BLUE_BEATING);
            }

            else {
                this.LEDControl.setColor(Color.RED_BEATING);
            }
        }

        else if(this.alliance.equals(Alliance.Blue)) {
            this.LEDControl.setColor(Color.BLUE);
        }
        
        else {
            this.LEDControl.setColor(Color.RED);
        }
    }
  
    private void runningLoop() {
        this.driver.checkInversion();  
        this.operator.checkForIntake();
  
        this.robotDrive.drive(this.driver.getX(), this.driver.getY(), this.driver.getRotation());
    
        RobotMap.lift_One.set(this.operator.getLift());
    
        this.gripperController.refreshGripperStatus();
        this.gripperController.setGripper(this.operator.getGripper());
    
        this.launcherController.refreshLauncherStatus();
        this.launcherController.setLauncher(this.operator.getLauncher());
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimberConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ClimberSubsystem extends SubsystemBase{
    private CANSparkMax Climber1 = new CANSparkMax(Constants.ClimberConstants.ClimbMotor1, MotorType.kBrushless);
    private CANSparkMax Climber2 = new CANSparkMax(Constants.ClimberConstants.ClimbMotor2, MotorType.kBrushless);
    private RelativeEncoder Encoder1;
    private RelativeEncoder Encoder2;
    private SparkMaxPIDController Controller1;
    private SparkMaxPIDController Controller2;


    public ClimberSubsystem(){

        Climber1.setInverted(false);
        Climber2.setInverted(false);
        
        Encoder1 = Climber1.getEncoder();
        Encoder2 = Climber2.getEncoder(); 
    
        Encoder1.setPositionConversionFactor(1/12*ClimberConstants.SpoolDiameter);
        Encoder2.setPositionConversionFactor(1/12*ClimberConstants.SpoolDiameter);

        Controller1 = Climber1.getPIDController(); 
        Controller2= Climber2.getPIDController();

        Controller1.setP(ClimberConstants.Climb1P);
        Controller1.setI(ClimberConstants.Climb1I);
        Controller1.setD(ClimberConstants.Climb1D);
        Controller1.setOutputRange(-1, 1);
        
        
        
        
        Controller1.setP(ClimberConstants.Climb2P);
        Controller1.setI(ClimberConstants.Climb2I);
        Controller1.setD(ClimberConstants.Climb2D);
        Controller2.setOutputRange(-1, 1);







    }


    public void ToMaxHeight(){
        Controller1.setReference(45, ControlType.kPosition);
        Controller2.setReference(45, ControlType.kPosition);
    }
    
    public void ToBottomHeight(){
        Controller1.setReference(1, ControlType.kPosition);
        Controller2.setReference(1, ControlType.kPosition);
    }


    
    


    
}

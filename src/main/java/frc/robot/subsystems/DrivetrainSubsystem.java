package frc.robot.subsystems;


import frc.swervelib.Gyroscope;
import frc.swervelib.GyroscopeHelper;
import frc.swervelib.Mk4SwerveModuleHelper;
import frc.swervelib.Mk4iSwerveModuleHelper;
import frc.swervelib.WCPSSModuleHelper;
import frc.swervelib.SwerveConstants;
import frc.swervelib.SwerveModule;
import frc.swervelib.SwerveSubsystem;
import frc.swervelib.SwerveTalonModuleHelper;
import frc.swervelib.TalonSteerModuleConfiguration;
import frc.swervelib.SwerveDrivetrainModel;
import frc.wpiClasses.QuadSwerveSim;
import frc.ExternalLib.NorthwoodLib.NorthwoodDrivers.RevThroughBore;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;

import java.util.ArrayList;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;



public class DrivetrainSubsystem {
        // in house plug and play swerve code. Copy and paste this file, along with the drivetrain constants section as long as you are using SDS MK4 Modules. 
        // all you need to do is change the constants to the ones that apply to your robot, and drive around. 
        
    

    public static SwerveDrivetrainModel  createSwerveModel(){
        passConstants();

        

        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
        
        ArrayList<SwerveModule> modules = new ArrayList<SwerveModule>(QuadSwerveSim.NUM_MODULES);

        SwerveModule m_frontLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
                // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
                tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0),
                // This can either be STANDARD or FAST depending on your gear configuration
                Mk4iSwerveModuleHelper.GearRatio.L2,
                // This is the ID of the drive motor
                Constants.DriveConstants.FRONT_LEFT_MODULE_DRIVE_MOTOR,
                // This is the ID of the steer motor
                Constants.DriveConstants.FRONT_LEFT_MODULE_STEER_MOTOR,
                // This is the ID of the steer encoder
                Constants.DriveConstants.FRONT_LEFT_MODULE_STEER_ENCODER,
                // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
                Constants.DriveConstants.FRONT_LEFT_MODULE_STEER_OFFSET, "FL"
        );
    
        // We will do the same for the other modules
        SwerveModule m_frontRightModule = Mk4iSwerveModuleHelper.createFalcon500(
                tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(2, 0),
                Mk4iSwerveModuleHelper.GearRatio.L2,
                Constants.DriveConstants.FRONT_RIGHT_MODULE_DRIVE_MOTOR,
                Constants.DriveConstants.FRONT_RIGHT_MODULE_STEER_MOTOR,
                Constants.DriveConstants.FRONT_RIGHT_MODULE_STEER_ENCODER,
                Constants.DriveConstants.FRONT_RIGHT_MODULE_STEER_OFFSET, "FR"
        );
    
        SwerveModule m_backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0),
                Mk4SwerveModuleHelper.GearRatio.L2,
                Constants.DriveConstants.BACK_LEFT_MODULE_DRIVE_MOTOR,
                Constants.DriveConstants.BACK_LEFT_MODULE_STEER_MOTOR,
                Constants.DriveConstants.BACK_LEFT_MODULE_STEER_ENCODER,
                Constants.DriveConstants.BACK_LEFT_MODULE_STEER_OFFSET, "BL"
        );
    
        SwerveModule m_backRightModule = Mk4iSwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(6, 0),
                Mk4iSwerveModuleHelper.GearRatio.L2,
                Constants.DriveConstants.BACK_RIGHT_MODULE_DRIVE_MOTOR,
                Constants.DriveConstants.BACK_RIGHT_MODULE_STEER_MOTOR,
                Constants.DriveConstants.BACK_RIGHT_MODULE_STEER_ENCODER,
                Constants.DriveConstants.BACK_RIGHT_MODULE_STEER_OFFSET, "BR"
        );


        Gyroscope gyro = GyroscopeHelper.createPigeon2CAN(DriveConstants.PIGEON_ID);

        modules.add(m_frontLeftModule);
        modules.add(m_frontRightModule);
        modules.add(m_backLeftModule);
        modules.add(m_backRightModule);
       
    

        
        


        return new SwerveDrivetrainModel(modules, gyro);
        






    }
    public static SwerveSubsystem createSwerveSubsystem(SwerveDrivetrainModel dt) {
        return new SwerveSubsystem(dt);        
    }

 


    
    private static void passConstants() {
        SwerveConstants.MAX_FWD_REV_SPEED_MPS = Constants.DriveConstants.MAX_FWD_REV_SPEED_MPS;
        SwerveConstants.MAX_VOLTAGE = Constants.DriveConstants.MAX_VOLTAGE;
        SwerveConstants.DFLT_START_POSE = Constants.DriveConstants.DFLT_START_POSE;

        SwerveConstants.THETACONTROLLERkP = Constants.AutoConstants.THETACONTROLLERkP;
        SwerveConstants.THETACONTROLLERCONSTRAINTS = Constants.AutoConstants.THETACONTROLLERCONSTRAINTS;

        SwerveConstants.TRACKWIDTH_METERS = Constants.DriveConstants.TRACKWIDTH_METERS;
        SwerveConstants.TRACKLENGTH_METERS = Constants.DriveConstants.WHEELBASE_METERS;
        SwerveConstants.MASS_kg = Constants.DriveConstants.MASS_kg;
        SwerveConstants.MOI_KGM2 = Constants.DriveConstants.MOI_KGM2;
        SwerveConstants.KINEMATICS = Constants.DriveConstants.KINEMATICS;



        SwerveConstants.TRAJECTORYXkP = Constants.AutoConstants.TRAJECTORYXkP;
   

        SwerveConstants.TRAJECTORYYkP = Constants.AutoConstants.TRAJECTORYYkP;
   
    }




    
    
}

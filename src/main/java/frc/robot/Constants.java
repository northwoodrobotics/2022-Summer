// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.ExternalLib.PoofLib.util.InterpolatingDouble;
import frc.ExternalLib.PoofLib.util.InterpolatingTreeMap;

public final class Constants {
    
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



    public static final class VisionConstants{
        public static final double TargetHeight = Units.inchesToMeters(102);
        public static final double blindlightHeight = Units.inchesToMeters(40);
        public static final double blindlightAngle = 23;
        public static final Pose2d GoalPose = new Pose2d(4, 6, Rotation2d.fromDegrees(0));
        public static final double intakeCamHeight = Units.inchesToMeters(38);

    }
    public static final class DriveConstants{
        public static final class AimConstants{ 
            public static final double AimP =.006 ;           
            public static final double AimI =0.005;
            public static final double AimD =0.0027;}
        
        /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
        public static final double TRACKWIDTH_METERS = Units.inchesToMeters(30); // FIXME Measure and set trackwidth
        /**
         * The front-to-back distance between the drivetrain wheels.
         *
         * Should be measured from center to center.
         */
        public static final double WHEELBASE_METERS = Units.inchesToMeters(28); // FIXME Measure and set wheelbase
        public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
                new Translation2d(TRACKWIDTH_METERS / 2.0, WHEELBASE_METERS / 2.0),
                new Translation2d(TRACKWIDTH_METERS / 2.0, -WHEELBASE_METERS / 2.0),
                new Translation2d(-TRACKWIDTH_METERS / 2.0, WHEELBASE_METERS / 2.0),
                new Translation2d(-TRACKWIDTH_METERS / 2.0, -WHEELBASE_METERS / 2.0)
        );
        public static final double WHEEL_DIAMETER_METERS = 0.10033; // .10033 = ~4 inches
        public static final double WHEEL_CIRCUMFERENCE_METERS = WHEEL_DIAMETER_METERS * Math.PI;
        public static final int PIGEON_ID = 28; // FIXME Set Pigeon ID
        public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 5; // FIXME Set front left module drive motor ID
        public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 4; // FIXME Set front left module steer motor ID
        public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 6; // FIXME Set front left steer encoder ID
        public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(127.001953125); // FIXME Measure and set front left steer offset
        public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 8; // FIXME Set front right drive motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 7; // FIXME Set front right steer motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 9; // FIXME Set front right steer encoder ID
        public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(251.1035); // FIXME Measure and set front right steer offse
        public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 11; // FIXME Set back left drive motor ID
        public static final int BACK_LEFT_MODULE_STEER_MOTOR = 10; // FIXME Set back left steer motor ID
        public static final int BACK_LEFT_MODULE_STEER_ENCODER = 12; // FIXME Set back left steer encoder ID
        public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(261.035); // FIXME Measure and set back left steer offset
        public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 15; // FIXME Set back right drive motor ID
        public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 14; // FIXME Set back right steer motor ID
        public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 16; // FIXME Set back right steer encoder ID
        public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(208.65); // FIXME Measure and set back right steer offset        
        // Drivetrain Performance Mechanical limits
        static public final double MAX_FWD_REV_SPEED_MPS = Units.feetToMeters(14.65);
        static public final double MAX_STRAFE_SPEED_MPS = Units.feetToMeters(14.65  ); 
        static public final double MAX_ROTATE_SPEED_RAD_PER_SEC = Units.degreesToRadians(90);
        static public final double MAX_TRANSLATE_ACCEL_MPS2 = MAX_FWD_REV_SPEED_MPS/0.25; //0-full time of 0.25 second
        static public final double MAX_ROTATE_ACCEL_RAD_PER_SEC_2 = MAX_ROTATE_SPEED_RAD_PER_SEC/0.25; //0-full time of 0.25 second
        public static final double MAX_VOLTAGE = 12.0; // Maximum Voltage sent to the drive motors
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // SENSOR CONSTANTS
        // Sensor-related constants - pulled from datasheets for the sensors and gearboxes
        static public final int ENC_PULSE_PER_REV = 2048; // TalonFX integrated sensor
        static public final int WHEEL_ENC_COUNTS_PER_WHEEL_REV = ENC_PULSE_PER_REV;  //Assume 1-1 gearing for now
        static public final int STEER_ENC_COUNTS_PER_MODULE_REV = 4096; // CANCoder
        static public final double WHEEL_ENC_WHEEL_REVS_PER_COUNT  = 1.0/((double)(WHEEL_ENC_COUNTS_PER_WHEEL_REV));
        static public final double steer_ENC_MODULE_REVS_PER_COUNT = 1.0/((double)(STEER_ENC_COUNTS_PER_MODULE_REV));
        static public final Pose2d DFLT_START_POSE = new Pose2d(Units.feetToMeters(24.0), Units.feetToMeters(10.0), Rotation2d.fromDegrees(0));
        static public final double ROBOT_MASS_kg = Units.lbsToKilograms(30);
        static public final double ROBOT_MOI_KGM2 = 1.0/12.0 * ROBOT_MASS_kg * Math.pow((WHEELBASE_METERS*1.1),2) * 2;
        public static final double MASS_kg = Units.lbsToKilograms(30);
        public static final double MOI_KGM2 = 1.0/12.0 * MASS_kg * Math.pow((TRACKWIDTH_METERS*1.1),2) * 2;
        // degrees per second
        public static final double Min_Rotation_Deg = 25;
    }
    public final static class AutoConstants{
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond =Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
        public static final double TRAJECTORYXkP = 20;
        public static final double TRAJECTORYXkI = 0;
        public static final double TRAJECTORYXkD = 0;
        public static final double TRAJECTORYYkP = 20;
        public static final double TRAJECTORYYkI = 0;
        public static final double TRAJECTORYYkD = 0;
        public static final double DriveKS = 1.1152;
        public static final double DriveKV = 0.62013;
        public static final double DriveKA = 0.12412;
        public static final double THETACONTROLLERkP = 1;
        public static final double THETACONTROLLERkI = 0;
        public static final double THETACONTROLLERkD = 0;
        // Constraint for the motion profilied robot angle controller
        public static final TrapezoidProfile.Constraints THETACONTROLLERCONSTRAINTS =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }
    public final static class TurretConstants{
        public static final int TurretMotorID = 34; 
        public static final int TurretZeroID = 6; 
        public static final double TurretP = 0.5;
        public static final double TurretI = 0;
        public static final double TurretD = 0;
        public static final double TurretIZone = 0;
        public static final double TurretFF = 0.045;
        public static final double TurretMaxOutput = 1;
        public static final double MotionMagicAcceleration = 4096; 
        public static final double MotionMagicVelocity = 8192;
        public static final int MotionMagicCurve = 1;
        public static final double TurretMinOutput = -1;
        public static final double TurretGearRatio = 1/125.45;
        public static final double TurretPostionCoffiecient =  TurretGearRatio/2048;
        public static final double TurretForwardSoftLimit = 390; 
        public static final double TurretReverseSoftLimit = -370;

        public static final double rawVisionP = 1; 
        public static final double rawVisionI = 0;
        public static final double rawVisionD = 0.2;
        public static final double turretMinRotation = 5;


    

    }
    public final static class ClimberConstants{
     
        public static final int ClimbMotor1 = 26;
        public static final int ClimbMotor2= 27;
      
 
        public static final double SpoolDiameter = Units.inchesToMeters(0.5);
        public static final double MidRungSetpoint = Units.inchesToMeters(0);
        public static final double Climb1P = 0.5;
        public static final double Climb1D = 0.0;
        public static final double Climb1I = 0.0;
        public static final double Climb1F = 0.045;
        public static final double Climb1MotionAccel = 8192; 
        public static final double Climb1MotionVelocity =8192; 
        public static final double Climb2P = 0.5;
        public static final double Climb2F = 0.045;
        public static final double Climb2D = 0.0;
        public static final double Climb2I = 0.0;
        public static final double Climb2MotionAccel = 8192; 
        public static final double Climb2MotionVelocity =8192; 
        public static final float Climb1SoftForward = 0;
        public static final float Climb1SoftReverse = 0;
        public static final float Climb2SoftForward = 0;
        public static final float Climb2SoftReverse = 0;
        public static final double Climb1GearRatio = 12;
        public static final double Climb2GearRatio = 12; 
    }

    public final static class IntakeConstants{
        public static final int IntakeMotorID = 29;
        public static final int WristMotorID = 32; 
        public static final double IntakeMotorP = 0.01;
        public static final double IntakeMotorI = 0;
        public static final double IntakeMotorD = 0;
        public static final double IntakeMotorFF =0.5;
        public static final int WristLimitSwitch = 6;
        public static final double WristP = 0.5;
        public static final double WristI = 0;
        public static final double WristD = 0;
        public static final double WristIZone = 0;
        public static final double WristFF = 0.045;
        public static final double WristMaxOutput = 1;
        public static final double MotionMagicAcceleration = 4096; 
        public static final double MotionMagicVelocity = 8192;
        public static final int MotionMagicCurve = 1;
        public static final double WristMinOutput = -1;
        public static final double WristGearRatio = 48;
        public static final double WristPositionSensorCoffiecient = 1/2048*WristGearRatio;
    }
    public final static class FeederConstants{
        public static final int FeederMotorID = 22;
        public static final int RejectMotorID = 3;
        public static final int FeederStage1Sensor = 2;
        public static final int FeederStage2Sensor= 1;
        //public static final int IntakeSensor = 0;
    }
    

    public final static class ShooterConstants{
        public static final int ShooterID = 20;
        public static final int ShooterFollowerID = 21;
        public static final int HoodID = 30;
        public static final double HoodP = 0.5;
        public static final double HoodI = 0;
        public static final double HoodD = 0;
        public static final double HoodIZone = 0;
        public static final double HoodFF = 0.045;
        public static final double HoodMaxOutput = 1;
        public static final double MotionMagicAcceleration = 4096; 
        public static final double MotionMagicVelocity = 8192;
        public static final int MotionMagicCurve = 1;
        public static final double HoodMinOutput = -1;
        public static final double HoodGearRatio = 36;
        public static final double HoodPositionSensorCoffiecient = 1/2048* HoodGearRatio;
         /* sensor position coficient is conversion from rotations to "talon units" CTRE devices like the falcon 500 and talon SRX measure in "encoder ticks" per 100ms,
         so we divide the position reading by the encoder's resolution. in the falcon 500 it is 2048 ticks per rotation */
        public static final double HoodVelocitySensorCoffiecient = HoodPositionSensorCoffiecient* (1000/100)*60; 
        /* here we convert from sensor tick changes per second to RPM, which is the position coffiecient times 600, to convert from ticks/100ms to rotatons per minute         */
        public static final double ShooterGearRatio = 1.5;
        public static final double ShooterP = 0.01;
        public static final double ShooterI = 0.0;
        public static final double ShooterD = 0.0;
    
        public static final double Shooter_AllowableError = 200;
        public static final double ShooterCurrentLimit = 10.0;
        public static final double ShooterPositonSensorCoffiecient = 1.0/2048 * 1.5;
        public static final double ShooterVelocitySensorCoffiecient = ShooterPositonSensorCoffiecient* (1000/100)*60;
        public static final double ShooterFF = 0.05;
        public static final double StaticFriction = 0.54;
        public static final InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> ShooterVelocityTable = new InterpolatingTreeMap<>(30);
        static{
            // tune
            
            ShooterVelocityTable.put(new InterpolatingDouble(1.3),new InterpolatingDouble(-5000.0));
            ShooterVelocityTable.put(new InterpolatingDouble(1.8),new InterpolatingDouble(-5500.0));
            ShooterVelocityTable.put(new InterpolatingDouble(1.9),new InterpolatingDouble(-5750.0));
            ShooterVelocityTable.put(new InterpolatingDouble(2.0),new InterpolatingDouble(-6500.0));
            ShooterVelocityTable.put(new InterpolatingDouble(2.2),new InterpolatingDouble(-6500.0));
            ShooterVelocityTable.put(new InterpolatingDouble(3.0),new InterpolatingDouble(-8000.0));
            ShooterVelocityTable.put(new InterpolatingDouble(3.5),new InterpolatingDouble(-9000.0));

            }
        public static final double HoodMinAngle = 0;
        public static final double HoodMaxAngle = 70;
        public static final double HoodOffset = 0.0;
        public static final InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> HoodPositionTable = new InterpolatingTreeMap<>(30);
        static {
            
            HoodPositionTable.put(new InterpolatingDouble(1.6), new InterpolatingDouble(15.0));
            HoodPositionTable.put(new InterpolatingDouble(1.8), new InterpolatingDouble(15.5));
            HoodPositionTable.put(new InterpolatingDouble(2.1), new InterpolatingDouble(18.5)); 
            HoodPositionTable.put(new InterpolatingDouble(2.4), new InterpolatingDouble(25.5));
            HoodPositionTable.put(new InterpolatingDouble(2.5), new InterpolatingDouble(26.5));
            HoodPositionTable.put(new InterpolatingDouble(2.7), new InterpolatingDouble(27.0));
            HoodPositionTable.put(new InterpolatingDouble(3.0), new InterpolatingDouble(29.5));
            HoodPositionTable.put(new InterpolatingDouble(3.5), new InterpolatingDouble(35.0));
            HoodPositionTable.put(new InterpolatingDouble(4.0), new InterpolatingDouble(37.5));            
        }
        

        public static final InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> ShotTime = new InterpolatingTreeMap<>(30);
        static {
            
            ShotTime.put(new InterpolatingDouble(1.6), new InterpolatingDouble(2.0));
            ShotTime.put(new InterpolatingDouble(1.8), new InterpolatingDouble(2.3));
            ShotTime.put(new InterpolatingDouble(2.1), new InterpolatingDouble(2.5)); 
            ShotTime.put(new InterpolatingDouble(2.4), new InterpolatingDouble(2.7));
            ShotTime.put(new InterpolatingDouble(2.5), new InterpolatingDouble(2.8));
            ShotTime.put(new InterpolatingDouble(2.7), new InterpolatingDouble(3.0));
            ShotTime.put(new InterpolatingDouble(3.0), new InterpolatingDouble(3.4));
            ShotTime.put(new InterpolatingDouble(3.5), new InterpolatingDouble(3.9));
            ShotTime.put(new InterpolatingDouble(4.0), new InterpolatingDouble(4.2));            
        }
    }
    
}

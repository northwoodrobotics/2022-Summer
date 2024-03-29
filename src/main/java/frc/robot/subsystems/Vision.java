package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.ExternalLib.JackInTheBotLib.math.MathUtils;
import frc.ExternalLib.NorthwoodLib.NorthwoodDrivers.RevThroughBore;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import java.util.ArrayList;
import java.util.OptionalDouble;

public class Vision extends SubsystemBase{


	// if you are on sushi squad, this is janky, go steal from someone else. 
    NetworkTable limelight;
    ArrayList<Double> avgDistance = new ArrayList<>();

	//RevThroughBore testEncoder = new RevThroughBore(1, "TestEncoder", 0);


	/*public double getTestEncoderReading(){
		return testEncoder.getDistanceDegrees();
	}*/

    public enum LEDMode {
		PIPELINE(0),
		LED_OFF(1),
		LED_BLINK(2),
		LED_ON(3);
		
		private int m;
		
		LEDMode(int mode) {
			m = mode;
		}
		
		public int getMode() {
			return m;
		}


	}
    public enum StreamingMode {
		STANDARD(0),
		PIP_MAIN(1),
		PIP_SECONDARY(2);
		
		private int m;
		
		StreamingMode(int mode) {
			m = mode;
		}
		
		public int getMode() {
			return m;
		}
	}

    public enum CameraMode {
		VISION_PROCESSING(0),
		DRIVER_CAMERA(1);
		
		private int m;
		
		CameraMode(int mode) {
			m = mode;
		}
		
		public int getMode() {
			return m;
		}
	}
    
	public Vision() {
		limelight = NetworkTableInstance.getDefault().getTable("limelight-front");

		setLEDMode(LEDMode.LED_OFF);
		setStreamingMode(StreamingMode.STANDARD);
	}


    @Override
	public void periodic() {
		SmartDashboard.putNumber("Distance to Target", Math.round(getAvgDistance() * 10) / 10.0);
		SmartDashboard.putNumber("Raw Distance To Target", getRobotToTargetDistance());
		avgDistance.add(getRobotToTargetDistance());
		if(avgDistance.size() == 10){
			avgDistance.remove(0);
		}
	}
    public double getRobotToTargetDistance() {
		return (Units.inchesToMeters(98) - Units.inchesToMeters(27)) / Math.tan(Math.toRadians(45 + getTargetAngleY()));
	}

	public double getAvgDistance(){
		double total = 0;
		for(double d : avgDistance){
			total += d;
		}
		return total / avgDistance.size();
	}
	

	/**
	 * @return The X angle to the target
	 */
	public double getTargetAngleX() {
		return limelight.getEntry("tx").getDouble(0);
	}
	
	public OptionalDouble opGetTargetAngleX() {
		return OptionalDouble.of(getTargetAngleX());
	}
	
	
	/**
	 * @return The Y angle to the target
	 */
	public double getTargetAngleY() {
		return limelight.getEntry("ty").getDouble(0);
	}
	
	/**
	 * @return The current latency from the limelight to the robot
	 */
	public double getLatency() {
		return (limelight.getEntry("tl").getDouble(0) + 11) / 1000.0;
	}
	
    public boolean hasTarget() {
		return limelight.getEntry("tv").getDouble(0) == 1;
	}
    public double[] get3DSolution() {
		return limelight.getEntry("camtran").getDoubleArray(new double[]{0, 0, 0, 0, 0, 0});
	}
	
	public double get3DDistance(){
		return Math.sqrt(getTranslationZ()*getTranslationZ() + getTranslationX()*getTranslationX());
	}
	
	public double getArea(){
		return limelight.getEntry("ta").getDouble(0);
	}
    public double getTranslationX() {
		return get3DSolution()[0];
	}
	
	/**
	 * @return The Y distance to the target
	 */
	public double getTranslationY() {
		return get3DSolution()[1];
	}
	
	/**
	 * @return The Z distance to the target
	 */
	public double getTranslationZ() {
		return get3DSolution()[2];
	}
	
	/**
	 * @return Target pitch
	 */
	public double getRotationPitch() {
		return get3DSolution()[3];
	}
	
	/**
	 * @return Target yaw
	 */
	public double getRotationYaw() {
		return get3DSolution()[4];
	}
	
	/**
	 * @return Target roll
	 */
	public double getRotationRoll() {
		return get3DSolution()[5];
	}



	




    
	public void setLEDMode(LEDMode mode) {
		limelight.getEntry("ledMode").setNumber(mode.getMode());
	}
	
	/**
	 * Set the camera mode
	 * @param mode The mode to use
	 */
	public void setCameraMode(CameraMode mode) {
		limelight.getEntry("camMode").setNumber(mode.getMode());
	}
	
	/**
	 * Set the streaming mode
	 * @param mode The mode to use
	 */
	public void setStreamingMode(StreamingMode mode) {
		limelight.getEntry("stream").setNumber(mode.getMode());
	}
	
	/**
	 * Set the processing pipeline
	 * @param id The id of the pipeline to use
	 */
	public void setPipeline(int id) {
		limelight.getEntry("pipeline").setNumber(id);
	}













    
    







    
}

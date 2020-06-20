/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private WPI_TalonFX leftM1;
  private WPI_TalonFX leftM2;
  private WPI_TalonFX rightM1;
  private WPI_TalonFX rightM2;
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  private DifferentialDrive differentialDrive;
  
  public Drivetrain() {
    leftM1 = new WPI_TalonFX(0);
    rightM1 = new WPI_TalonFX(1);
    leftM2 = new WPI_TalonFX(2);
    rightM2 = new WPI_TalonFX(3);
    leftMotors = new SpeedControllerGroup(leftM1, leftM2);
    rightMotors = new SpeedControllerGroup(rightM1, rightM2);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void arcadeDrive(double throttle, double turn){
    //.arcadeDrive() passes the throttle and turn speeds to the motors in
    //a differential drive, automatically figuring out the speeds needed
    //for each motor. It assumes that a positive turn means clockwise,
    //and a negative turn means counterclockwise.
    //Example: A turn speed of 0.4 will set the right motors to
    //a speed of -0.4, and the left motors to 0.4 in order to turn right
    differentialDrive.arcadeDrive(throttle, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

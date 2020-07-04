/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class ArmPID extends PIDSubsystem {
  /**
   * Creates a new ArmPID.
   */
  private TalonFX armMotor;
  
  private Encoder armEncoder;

  private static double kP = 0.25;
  private static double tolerance = 0.1;
  public double offset = 25.0;

  public ArmPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(kP, 0, 0));
    
    getController().setTolerance(tolerance);
    armMotor = new TalonFX(4);
    armEncoder = new Encoder(0, 1);

    armEncoder.setDistancePerPulse(360/4096);
  }

  public void setMotor(double speed){
    armMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void reset(){
    armEncoder.reset();
  }

  public boolean atSetpoint(double setpoint){
    if(armEncoder.getDistance() > setpoint + tolerance  || armEncoder.getDistance() < setpoint - tolerance){
      return false;
    }
    return true;
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    double limit = 0.4;
    if(output > limit){
      output = limit;
    } else if(output < -limit){
      output = -limit;
    }
    setMotor(output);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return armEncoder.getDistance() + offset;
  }
}

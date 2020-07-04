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

public class WristPID extends PIDSubsystem {
  /**
   * Creates a new WristPID.
   */
  private TalonFX wristMotor;
  private TalonFX intakeMotor;
  
  private Encoder wristEncoder;

  private static double kP = 0.25;
  private static double tolerance = 0.1;
  private double offset = -15.0;
  public WristPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(kP, 0, 0));
    getController().setTolerance(tolerance);
    wristMotor = new TalonFX(5);
    intakeMotor = new TalonFX(6);
    wristEncoder = new Encoder(2, 3);
  
    wristEncoder.setDistancePerPulse(360/4096);
  }

  public void setMotor(double speed){
    wristMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void setIntake(double speed){
    intakeMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void reset(){
    wristEncoder.reset();
  }

  public boolean atSetpoint(double setpoint){
    if(wristEncoder.getDistance() > setpoint + tolerance  || wristEncoder.getDistance() < setpoint - tolerance){
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
    return wristEncoder.getDistance() + offset;
  }
}

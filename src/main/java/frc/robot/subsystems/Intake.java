/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private TalonFX intakeMotor;
  private Solenoid intakeSolenoid;
  public Intake() {
    intakeMotor = new TalonFX(5);
    intakeSolenoid = new Solenoid(0);
  }

  public void setSolenoid(boolean mode){
    //This method sets the state of a single solenoid, which
    //the cuts off or allows airflow to any pneumatics it controls
    intakeSolenoid.set(mode);
  }

  public void setMotor(double speed){
    //Percent Output mode sets the value passed to the motor to
    //numbers between -1.0 and 1.0
    intakeMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

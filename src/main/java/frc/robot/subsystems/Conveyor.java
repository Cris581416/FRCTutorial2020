/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  private TalonFX topMotor;
  private TalonFX bottomMotor;

  private DigitalInput sensor;

  public Conveyor() {
    topMotor = new TalonFX(6);
    bottomMotor = new TalonFX(7);
  }

  public void setMotors(double speed){
    topMotor.set(TalonFXControlMode.PercentOutput, speed);
    bottomMotor.set(TalonFXControlMode.PercentOutput, speed);
  }

  public boolean getInput(){
    boolean input = sensor.get();
    return input;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HatchGrabber extends SubsystemBase {
  /**
   * Creates a new HatchGrabber.
   */
  private Solenoid hatchSolenoid;
  public HatchGrabber() {
    hatchSolenoid = new Solenoid(Constants.hatchSolenoidPort);
  }

  public void grabHatch(){
    hatchSolenoid.set(true);
  }

  public void releaseHatch(){
    hatchSolenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

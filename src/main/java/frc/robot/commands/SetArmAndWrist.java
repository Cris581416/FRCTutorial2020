/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPID;
import frc.robot.subsystems.WristPID;

public class SetArmAndWrist extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  /**
   * Creates a new SetArmAndWrist.
   */
  private ArmPID m_arm;
  private WristPID m_wrist;
  private int mode;
  private double setpoint;
  public SetArmAndWrist(ArmPID arm, WristPID wrist, int mode) {
    m_arm = arm;
    m_wrist = wrist;
    this.mode = mode;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
    addRequirements(wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_arm.enable();
    m_wrist.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch(mode){
      case 0:
      setpoint = m_arm.offset;
      break;
      case 1:
      setpoint = 90;
      break;
      case 2:
      setpoint = 135;
      break;
    }

    m_arm.setSetpoint(setpoint);
    m_wrist.setSetpoint(-(setpoint - m_arm.offset));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_wrist.disable();
    m_arm.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_arm.atSetpoint(setpoint) && m_wrist.atSetpoint(-(setpoint - m_arm.offset)));
  }
}

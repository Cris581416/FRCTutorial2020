/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.HatchGrabber;

public class SetGrabber extends CommandBase {
  /**
   * Creates a new SetGrabber.
   */
  private HatchGrabber m_grabber;
  private boolean setter = false;
  public SetGrabber(HatchGrabber grabber) {
    m_grabber = grabber;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.driveController.getAButtonPressed()){
      setter = !setter;
    }

    if(setter){
      m_grabber.grabHatch();
    } else{
      m_grabber.releaseHatch();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

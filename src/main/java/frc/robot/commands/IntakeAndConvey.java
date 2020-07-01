/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;

public class IntakeAndConvey extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  /**
   * Creates a new IntakeAndConvey.
   */
  private Conveyor m_conveyor;
  private Intake m_intake;
  private Boolean ender = false;
  private Timer timer = new Timer();
  public IntakeAndConvey(Conveyor conveyor, Intake intake) {
    m_conveyor = conveyor;
    m_intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(conveyor);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setSolenoid(true);
    m_intake.setMotor(0.5);
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_conveyor.getInput()){
      timer.start();
      m_conveyor.setMotors(0.5);
      if(timer.get() > 1.5){
        ender = true;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.setMotor(0.0);
    m_conveyor.setMotors(0.0);
    m_intake.setSolenoid(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ender;
  }
}

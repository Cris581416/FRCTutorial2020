/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ArmPID;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.WristPID;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.SetArmAndWrist;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final WristPID m_wrist = new WristPID();
  private final ArmPID m_arm = new ArmPID();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static final XboxController driveController = new XboxController(0);

  private static final JoystickButton level0 = new JoystickButton(driveController, Button.kA.value);
  private static final JoystickButton level1 = new JoystickButton(driveController, Button.kB.value);
  private static final JoystickButton level2 = new JoystickButton(driveController, Button.kX.value);
  private static final JoystickButton intakeButton = new JoystickButton(driveController, Button.kBumperLeft.value);
  private static final JoystickButton outtakeButton = new JoystickButton(driveController, Button.kBumperRight.value);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(new Drive(m_drivetrain));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    level0.whenPressed(new SetArmAndWrist(m_arm, m_wrist, 0));
    level1.whenPressed(new SetArmAndWrist(m_arm, m_wrist, 1));
    level2.whenPressed(new SetArmAndWrist(m_arm, m_wrist, 2));
    intakeButton.whileHeld(new Intake(m_wrist));
    outtakeButton.whileHeld(new Outtake(m_wrist));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}

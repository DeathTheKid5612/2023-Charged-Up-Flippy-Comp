// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.ExtenderConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.WristConstants;
import frc.robot.commands.*;
import frc.robot.commands.swervedrive.auto.Autos;
import frc.robot.commands.swervedrive.drivebase.AbsoluteDrive;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExtenderSubsystem;
import frc.robot.subsystems.GripperSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));

  private final ArmSubsystem s_Arm = new ArmSubsystem();
  private final WristSubsystem s_Wrist = new WristSubsystem();
  private final ExtenderSubsystem s_Extender = new ExtenderSubsystem();
  private final GripperSubsystem s_Gripper = new GripperSubsystem();
  // CommandJoystick rotationController = new CommandJoystick(1);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  CommandJoystick driverController = new CommandJoystick(2);
  XboxController operatorXbox = new XboxController(1);

  // CommandJoystick driverController   = new CommandJoystick(3);//(OperatorConstants.DRIVER_CONTROLLER_PORT);
  XboxController driverXbox = new XboxController(0);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();

    AbsoluteDrive closedAbsoluteDrive = new AbsoluteDrive(drivebase,
                                                          // Applies deadbands and inverts controls because joysticks
                                                          // are back-right positive while robot
                                                          // controls are front-left positive
                                                          () -> (Math.abs(driverXbox.getLeftY()) >
                                                                 OperatorConstants.LEFT_Y_DEADBAND)
                                                                ? -driverXbox.getLeftY() : 0,
                                                          () -> (Math.abs(driverXbox.getLeftX()) >
                                                                 OperatorConstants.LEFT_X_DEADBAND)
                                                                ? -driverXbox.getLeftX() : 0,
                                                          () -> -driverXbox.getRightX(),
                                                          () -> -driverXbox.getRightY(),
                                                          false);
    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> (Math.abs(driverController.getY()) > OperatorConstants.LEFT_Y_DEADBAND) ? driverController.getY() : 0,
        () -> (Math.abs(driverController.getX()) > OperatorConstants.LEFT_X_DEADBAND) ? driverController.getX() : 0,
        () -> -driverController.getRawAxis(3), () -> true, false);

    drivebase.setDefaultCommand(closedAbsoluteDrive);
    s_Gripper.setDefaultCommand(new GripperPower(s_Gripper, () -> operatorXbox.getRightTriggerAxis(), () -> operatorXbox.getLeftTriggerAxis()));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    new JoystickButton(driverXbox, XboxController.Button.kBack.value).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new InstantCommand(drivebase::lock, drivebase)));

    
    //Arm Control
    new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onTrue(new ArmUp(s_Arm));
    new JoystickButton(operatorXbox, XboxController.Button.kRightBumper.value).onFalse(new ArmOff(s_Arm));
    new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onTrue(new ArmDown(s_Arm));
    new JoystickButton(operatorXbox, XboxController.Button.kLeftBumper.value).onFalse(new ArmOff(s_Arm));
    

    //Wrist Control
    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onTrue(new WristPower(s_Wrist,WristConstants.wristSpeed));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onTrue(new WristPower(s_Wrist,-WristConstants.wristSpeed));
    new JoystickButton(operatorXbox, XboxController.Button.kA.value).onFalse(new WristPower(s_Wrist,0));
    new JoystickButton(operatorXbox, XboxController.Button.kB.value).onFalse(new WristPower(s_Wrist,0));

    
    //Extender Control
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onTrue(new ExtenderPower(s_Extender, ExtenderConstants.extenderSpeed));
    new JoystickButton(operatorXbox, XboxController.Button.kY.value).onTrue(new ExtenderPower(s_Extender,-ExtenderConstants.extenderSpeed));
    new JoystickButton(operatorXbox, XboxController.Button.kX.value).onFalse(new ExtenderPower(s_Extender,0));
    new JoystickButton(operatorXbox, XboxController.Button.kY.value).onFalse(new ExtenderPower(s_Extender,0));
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return Autos.exampleAuto(drivebase, s_Gripper);
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}

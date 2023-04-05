package frc.robot.commands;

import frc.robot.subsystems.*;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class GripperPower extends CommandBase {
  // The subsystem the command runs on
  private final GripperSubsystem m_GripperSubsystem;

  private final DoubleSupplier rightTrigger;
  private final DoubleSupplier leftTrigger;

  public GripperPower(GripperSubsystem subsystem, DoubleSupplier rightTrigger, DoubleSupplier leftTrigger) {
    m_GripperSubsystem = subsystem;
    this.rightTrigger = rightTrigger;
    this.leftTrigger = leftTrigger;
    addRequirements(m_GripperSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute()
  {
    m_GripperSubsystem.setSpeed(-rightTrigger.getAsDouble()+leftTrigger.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    //m_ArmSubsystem.armOff();
    return true;
  }
}
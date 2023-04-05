package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ArmDown extends CommandBase {
  // The subsystem the command runs on
  private final ArmSubsystem m_ArmSubsystem;

  public ArmDown(ArmSubsystem subsystem) {
    m_ArmSubsystem = subsystem;
    addRequirements(m_ArmSubsystem);
  }

  @Override
  public void initialize() {
    m_ArmSubsystem.armDown();
  }

  @Override
  public void execute()
  {
  }

  @Override
  public boolean isFinished() {
    //m_ArmSubsystem.armOff();
    return true;
  }

}
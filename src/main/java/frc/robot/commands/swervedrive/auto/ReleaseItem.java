package frc.robot.commands.swervedrive.auto;

import frc.robot.subsystems.*;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ReleaseItem extends CommandBase {
  // The subsystem the command runs on
  private final GripperSubsystem m_GripperSubsystem;

  public ReleaseItem(GripperSubsystem subsystem) {
    m_GripperSubsystem = subsystem;
    addRequirements(m_GripperSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute()
  {
    m_GripperSubsystem.setSpeed(.65);
  }

@Override
public void end(boolean interrupted)
{
    m_GripperSubsystem.setSpeed(0);
}

  @Override
  public boolean isFinished() {
    //m_ArmSubsystem.armOff();
    return false;
  }
}
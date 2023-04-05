package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ExtenderPower extends CommandBase {
  // The subsystem the command runs on
  private final ExtenderSubsystem m_ExtenderSubsystem;
  double extenderPower;

  public ExtenderPower(ExtenderSubsystem subsystem, double extenderPower) {
    m_ExtenderSubsystem = subsystem;
    this.extenderPower = extenderPower;
    addRequirements(m_ExtenderSubsystem);
  }

  @Override
  public void initialize() {
    m_ExtenderSubsystem.setExtenderPower(extenderPower);
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
package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class WristPower extends CommandBase {
  // The subsystem the command runs on
  private final WristSubsystem m_WristSubsystem;
  double wristPower;

  public WristPower(WristSubsystem subsystem, double wristPower) {
    m_WristSubsystem = subsystem;
    this.wristPower = wristPower;
    addRequirements(m_WristSubsystem);
  }

  @Override
  public void initialize() {
    m_WristSubsystem.setWristPower(wristPower);
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
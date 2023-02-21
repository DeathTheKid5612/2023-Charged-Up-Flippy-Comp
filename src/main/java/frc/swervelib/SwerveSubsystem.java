// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.swervelib;

import frc.wpiClasses.QuadSwerveSim;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

public class SwerveSubsystem extends SubsystemBase {
  private SwerveModuleState[] states;

  private ArrayList<SwerveModule> modules = new ArrayList<SwerveModule>(QuadSwerveSim.NUM_MODULES);
  public SwerveDrivetrainModel dt;
  private ShuffleboardTab testTab = Shuffleboard.getTab("Test");
  private GenericEntry encoder0 = testTab.add("Front Left: 0", 0).getEntry();
  private GenericEntry encoder1 = testTab.add("Front Right: 1", 0).getEntry();
  private GenericEntry encoder2 = testTab.add("Back Left: 2", 0).getEntry();
  private GenericEntry encoder3 = testTab.add("Back Right: 3", 0).getEntry();
  private GenericEntry gyro1 = testTab.add("Gyro", 0).getEntry();
  

  public SwerveSubsystem(SwerveDrivetrainModel dt) {
    this.dt = dt;
    modules = dt.getRealModules();
  }

  @Override
  public void periodic() {
    states = dt.getSwerveModuleStates();
    gyro1.setDouble(dt.getGyroscopeRotation().getDegrees());
    encoder0.setDouble(dt.getAbsoluteAngle(0));
    encoder1.setDouble(dt.getAbsoluteAngle(1));
    encoder2.setDouble(dt.getAbsoluteAngle(2));
    encoder3.setDouble(dt.getAbsoluteAngle(3));

    if (states != null) {
      SwerveDriveKinematics.desaturateWheelSpeeds(states, SwerveConstants.MAX_FWD_REV_SPEED_MPS);

      modules.get(0).set(states[0].speedMetersPerSecond / SwerveConstants.MAX_FWD_REV_SPEED_MPS * SwerveConstants.MAX_VOLTAGE, states[0].angle.getRadians());
      modules.get(1).set(states[1].speedMetersPerSecond / SwerveConstants.MAX_FWD_REV_SPEED_MPS * SwerveConstants.MAX_VOLTAGE, states[1].angle.getRadians());
      modules.get(2).set(states[2].speedMetersPerSecond / SwerveConstants.MAX_FWD_REV_SPEED_MPS * SwerveConstants.MAX_VOLTAGE, states[2].angle.getRadians());
      modules.get(3).set(states[3].speedMetersPerSecond / SwerveConstants.MAX_FWD_REV_SPEED_MPS * SwerveConstants.MAX_VOLTAGE, states[3].angle.getRadians());

      /*
      dt.m_poseEstimator.update(dt.getGyroscopeRotation(),
      new SwerveModulePosition[] {
        modules.get(0).getPosition(),
        modules.get(1).getPosition(),
        modules.get(2).getPosition(),
        modules.get(3).getPosition()
      });
      */
      
    }

    dt.updateTelemetry();
  }

  @Override
  public void simulationPeriodic() {
    dt.update(DriverStation.isDisabled(), 13.2);
  }
}

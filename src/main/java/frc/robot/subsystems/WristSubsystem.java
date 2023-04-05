package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;
public class WristSubsystem extends SubsystemBase {
    
    CANSparkMax m_Wrist = new CANSparkMax(WristConstants.wristMotorID, WristConstants.wristMotorType);


    public WristSubsystem() {}


    public void setWristPower(double wristPower)
    {
        m_Wrist.set(wristPower);
    }
    
    public void wristOff()
    {
        m_Wrist.set(0);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ExtenderConstants;
public class ExtenderSubsystem extends SubsystemBase {
    
    CANSparkMax m_Extender = new CANSparkMax(ExtenderConstants.extenderMotorID, ExtenderConstants.extenderMotorType);


    public ExtenderSubsystem() {}


    public void setExtenderPower(double extenderPower)
    {
        m_Extender.set(extenderPower);
    }
    
    public void wristOff()
    {
        m_Extender.set(0);
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
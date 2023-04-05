package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;
public class ArmSubsystem extends SubsystemBase {
    
    CANSparkMax m_arm1 = new CANSparkMax(ArmConstants.armMotor1ID, ArmConstants.armMotor1Type);
    CANSparkMax m_arm2 = new CANSparkMax(ArmConstants.armMotor2ID, ArmConstants.armMotor2Type);



    public ArmSubsystem() {}


    public void armUp()
    {
        m_arm1.set(ArmConstants.armSpeed);
        m_arm2.set(ArmConstants.armSpeed);
    }  

    public void armDown()
    {
        m_arm1.set(-ArmConstants.armSpeed);
        m_arm2.set(-ArmConstants.armSpeed);
    }

    public void armOff()
    {
        m_arm1.set(0);
        m_arm2.set(0);

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
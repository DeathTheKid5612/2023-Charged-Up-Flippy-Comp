package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GripperConstants;
import frc.robot.Constants.WristConstants;
public class GripperSubsystem extends SubsystemBase {
    
    //CANSparkMax m_RightRoller = new CANSparkMax(GripperConstants.rightRollerMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax m_LeftRoller = new CANSparkMax(GripperConstants.leftRollerMotorID, GripperConstants.leftRollerMotorType);
    
    public GripperSubsystem() 
    {
        m_LeftRoller.setSmartCurrentLimit(40, 40);
    }

    public void setSpeed(double desiredMotorSpeed)
    {
        m_LeftRoller.set(desiredMotorSpeed);
        //m_LeftRoller.setVoltage(12*desiredMotorSpeed);
        //m_RightRoller.set(-desiredMotorSpeed);
    }

    public void rollersOff()
    {
        m_LeftRoller.set(0);
        //m_RightRoller.set(0);
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
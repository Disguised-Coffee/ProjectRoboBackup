// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /** motor obj */
  TalonSRX m_motor = new TalonSRX(2); 
  /** joystick obj */
  Joystick r_joystick = new Joystick(0);
  Faults _faults = new Faults(); // for faults from the motor (I never knew you can do this from code!!)

  @Override
  public void robotInit() {
    m_motor.configFactoryDefault(); //Might not be needed

    m_motor.setInverted(false); //inverts if needed

    m_motor.setSensorPhase(false); // inverts if needed
    m_motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder); //we are using quadrature coder: https://docs.wpilib.org/en/stable/docs/hardware/sensors/encoders-hardware.html#quadrature-encoders
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    m_motor.setSelectedSensorPosition(0); // resets motor to 0
    m_motor.setNeutralMode(NeutralMode.Brake); // automatically brake the motor when we start driving.
  }

  @Override
  public void teleopPeriodic() {
    // drive a motor
    m_motor.set(TalonSRXControlMode.PercentOutput, r_joystick.getY());

    // update position and velocity on SmartDash
    SmartDashboard.setDefaultNumber("pos",m_motor.getSelectedSensorPosition());
    SmartDashboard.setDefaultNumber("Velocity", m_motor.getSelectedSensorVelocity());

    // Update if motor is out of phase
    m_motor.getFaults(_faults);
    SmartDashboard.setDefaultBoolean("Out of Phase: ", _faults.SensorOutOfPhase);

    //Update voltage
    SmartDashboard.setDefaultNumber("Voltage ", m_motor.getMotorOutputVoltage());;
  }

  @Override
  public void disabledInit() {
    m_motor.setNeutralMode(NeutralMode.Coast); // Move freely when disabled. Helpful for when you want to move the bot.
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

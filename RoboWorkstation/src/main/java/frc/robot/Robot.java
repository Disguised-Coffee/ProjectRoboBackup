// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.Faults;
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
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  TalonSRX m_motor = new TalonSRX(2);
  Joystick r_joystick = new Joystick(0);
  Faults _faults = new Faults();
  // private int timeoutMs = 30;

  @Override
  public void robotInit() {
    m_motor.configFactoryDefault();

    m_motor.setInverted(true);

    m_motor.setSensorPhase(false); // TODO
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.setDefaultNumber("Pos", m_motor.getSelectedSensorPosition());
    SmartDashboard.setDefaultNumber("Velocity", m_motor.getSelectedSensorVelocity());
    SmartDashboard.setDefaultBoolean("Out of Phase: ", _faults.SensorOutOfPhase);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // TODO: experiment with these
    // m_motor.configClearPositionOnLimitF(true, timeoutMs);
    // m_motor.configClearPositionOnLimitR(true, timeoutMs);
    // m_motor.configClearPositionOnQuadIdx(true, timeoutMs);
    m_motor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void teleopPeriodic() {
    m_motor.set(TalonSRXControlMode.PercentOutput, r_joystick.getY());
  }

  @Override
  public void disabledInit() {
    m_motor.setNeutralMode(NeutralMode.Coast);
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

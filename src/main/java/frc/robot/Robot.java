// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of

// the WPILib BSD license file in the root directory of this project.



package frc.robot;



import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.music.Orchestra;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.*;



/**

 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with

 * arcade steering.

 */ 

public class Robot extends TimedRobot {

  private final WPI_TalonFX falconRL = new WPI_TalonFX(2,"rio");

  private final WPI_TalonFX falconRF = new WPI_TalonFX(3,"rio");

  private final WPI_TalonFX falconLL = new WPI_TalonFX(5,"rio");

  private final WPI_TalonFX falconLF = new WPI_TalonFX(4,"rio");

  private final MotorControllerGroup right = new MotorControllerGroup(falconRL, falconRF);

  private final MotorControllerGroup left = new MotorControllerGroup(falconLL, falconLF);

  private final DifferentialDrive robotDrive= new DifferentialDrive(left,right);

  private final XboxController controller = new XboxController(0);
 // private final ArcadeController A1 = new ArcadeController(1);
 // private final ArcadeController A2 = new ArcadeController(2);

 // private final WPI_TalonSRX Csped = new WPI_TalonSRX(11);
  private boolean LBPressed = false;
  private boolean BPressed = false;
  private boolean RBPressed = false;
  private boolean APressed = false;
  private boolean YPressed = false;
  private double Presicion = 1.8;
  private boolean SD_Precision = false;
  private double SD_Throttle = 0.0;
  private double Turn = 0.0;
  private final Orchestra fOrchestra = new Orchestra();   

  @Override

  public void robotInit() {

    // We need to invert one side of the drivetrain so that positive voltages

    // result in both sides moving forward. Depending on how your robot's

    // gearbox is constructed, you might have to invert the left side instead.

    falconRF.follow(falconRL);

    falconLF.follow(falconLL);

    falconRL.setInverted(true);
    falconRF.setInverted(true);

    falconRL.setNeutralMode(NeutralMode.Coast);

    falconLL.setNeutralMode(NeutralMode.Coast);
  

    falconRL.neutralOutput();

    falconLL.neutralOutput();

  }


  public void teleopPeriodic (){
    updateSmartDashboard();
    // Drive with arcade drive.

    // That means that the Y axis drives forward

    // and backward, and the X turns left and right.
    if(controller.getLeftBumperPressed()){ 
      LBPressed = !LBPressed;
      SD_Precision=!SD_Precision;
    }
    if(LBPressed){
      //Method goes from 1.0 to -1.0, don't try and subtract values EVER
      SD_Throttle = -(controller.getRightTriggerAxis()-controller.getLeftTriggerAxis())/Presicion;
      Turn = controller.getLeftX()/Presicion;
    }else{  
      SD_Throttle = -(controller.getRightTriggerAxis()-controller.getLeftTriggerAxis());
      Turn = controller.getLeftX();
    }
    robotDrive.arcadeDrive(SD_Throttle,Turn);
    if(controller.getBButtonPressed()){
      RBPressed=false;
      BPressed=true;
    }
    if(BPressed){
      Orchestra();
      fOrchestra.play();
      BPressed=false;
    }
    if(controller.getRightBumperPressed()){
      RBPressed=true;
     BPressed=false;
    }
    if(RBPressed){
      Orchestra();
      fOrchestra.pause();
      RBPressed=false;
    }
    if(controller.getAButtonPressed()){
      APressed=!APressed;
    }
  if(APressed){
    Presicion-=0.1;
    APressed=!APressed;
  }
  if(controller.getYButtonPressed()){
    YPressed=!YPressed;
  }
  if(YPressed){
    Presicion+=0.1;
    YPressed=!YPressed;
  }
    //RIP C-Speedy T-T

    //robotDrive.arcadeDrive(-controller.getLeftTriggerAxis(),controller.getLeftX());
    /*if(controller.getAButtonPressed() && !APressed){

      APressed=!APressed;

      Csped.set(ControlMode.PercentOutput, 1.0);

    }else if(controller.getAButtonPressed() && APressed){

      APressed=!APressed;

      Csped.set(ControlMode.PercentOutput,0.0);

    }*/

   /* if(controller.getAButtonPressed()){
     Csped.set(ControlMode.PercentOutput,1.0);
    }
    if(controller.getYButtonReleased()){
      Csped.neutralOutput();
    }*/

  }

  public void Orchestra(){
    fOrchestra.loadMusic("RickRoll.chrp");
    fOrchestra.addInstrument(falconRL);
    fOrchestra.addInstrument(falconRF);
    fOrchestra.addInstrument(falconLL);
    fOrchestra.addInstrument(falconLF);

  }
  public void updateSmartDashboard(){
    SmartDashboard.putBoolean("Precision", SD_Precision);
    SmartDashboard.putNumber("Throttle (Percent)", SD_Throttle*100);
    SmartDashboard.putNumber("Precision Value", Presicion );
  }
}

//to beam or not to bean
//Would you rather have unlimited bacon but no games, or, games, unlimited games, but no games?
//pasta salad is a real thing
package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.GenericHID;

public class ArcadeController extends GenericHID{
    //Enum for the Button variables
    public enum Button{
        B1(1),
        B2(2),
        B3(3),
        B4(4),
        B5(5),
        B6(6),
        B7(7),
        B8(8),
        B9(9),
        B10(10);
        @SuppressWarnings("MemberName") //Used to make sure we don't get that error
        public final int value;

        Button(int value){
            this.value=value;
        }
    }
    //Enum for the Axis variables
    public enum Axis{
        X(0),
        Y(1);
        @SuppressWarnings("MemberName") //Used to make sure we don't get that error
        public final int value;
        
        Axis(int value){
            this.value=value;
        }
    }

    //Constructor
    public ArcadeController(int port){
        super(port);
        HAL.report(tResourceType.kResourceType_Controller,port+1);
    }
    //X and Y Axis from the joystick
    public double getXAxis(){
        return getRawAxis(Axis.X.value);
    }
    public double getYAxis(){
        return getRawAxis(Axis.Y.value);
    }
    //Button pressed functions
    public boolean getB1Pressed(){
        return getRawButtonPressed(Button.B1.value);
    }
    public boolean getB2Pressed(){
        return getRawButtonPressed(Button.B2.value);
    }
    public boolean getB3Pressed(){
        return getRawButtonPressed(Button.B3.value);
    }
    public boolean getB4Pressed(){
        return getRawButtonPressed(Button.B4.value);
    }
    public boolean getB5Pressed(){
        return getRawButtonPressed(Button.B5.value);
    }
    public boolean getB6Pressed(){
        return getRawButtonPressed(Button.B6.value);
    }
    public boolean getB7Pressed(){
        return getRawButtonPressed(Button.B7.value);
    }
    public boolean getB8Pressed(){
        return getRawButtonPressed(Button.B8.value);
    }
    public boolean getB9Pressed(){
        return getRawButtonPressed(Button.B9.value);
    }
    public boolean getB10Pressed(){
        return getRawButtonPressed(Button.B10.value);
    }
    //Button released functions
    public boolean getB1Released(){
        return getRawButtonReleased(Button.B1.value);
    }
    public boolean getB2Released(){
        return getRawButtonReleased(Button.B2.value);
    }
    public boolean getB3Released(){
        return getRawButtonReleased(Button.B3.value);
    }
    public boolean getB4Released(){
        return getRawButtonReleased(Button.B4.value);
    }
    public boolean getB5Released(){
        return getRawButtonReleased(Button.B5.value);
    }
    public boolean getB6Released(){
        return getRawButtonReleased(Button.B6.value);
    }
    public boolean getB7Released(){
        return getRawButtonReleased(Button.B7.value);
    }
    public boolean getB8Released(){
        return getRawButtonReleased(Button.B8.value);
    }
    public boolean getB9Released(){
        return getRawButtonReleased(Button.B9.value);
    }
    public boolean getB10Released(){
        return getRawButtonReleased(Button.B10.value);
    }
}

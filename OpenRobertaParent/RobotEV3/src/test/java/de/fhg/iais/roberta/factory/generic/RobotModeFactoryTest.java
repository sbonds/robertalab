package de.fhg.iais.roberta.factory.generic;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.factory.BlocklyDropdown2EnumHelper;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;
import de.fhg.iais.roberta.factory.Ev3LejosV0Factory;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.BrickLedColor;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.mode.action.LightMode;
import de.fhg.iais.roberta.mode.action.MotorMoveMode;
import de.fhg.iais.roberta.mode.action.MotorSide;
import de.fhg.iais.roberta.mode.action.MotorStopMode;
import de.fhg.iais.roberta.mode.action.TurnDirection;
import de.fhg.iais.roberta.mode.action.ev3.ShowPicture;
import de.fhg.iais.roberta.mode.general.IndexLocation;
import de.fhg.iais.roberta.mode.general.ListElementOperations;
import de.fhg.iais.roberta.mode.sensor.ColorSensorMode;
import de.fhg.iais.roberta.mode.sensor.GyroSensorMode;
import de.fhg.iais.roberta.mode.sensor.InfraredSensorMode;
import de.fhg.iais.roberta.mode.sensor.EncoderSensorMode;

import de.fhg.iais.roberta.mode.sensor.TimerSensorMode;
import de.fhg.iais.roberta.mode.sensor.UltrasonicSensorMode;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.Util1;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class RobotModeFactoryTest {
    Ev3LejosV0Factory factory = new Ev3LejosV0Factory(new PluginProperties("ev3lejosv0", "", "", Util1.loadProperties("classpath:ev3lejosv0.properties")));
    BlocklyDropdownFactory dropdownFactory = this.factory.getBlocklyDropdownFactory();

    @Test
    public void getIndexLocationFromString() {
        Assert.assertEquals(this.dropdownFactory.getIndexLocation("FIRST"), IndexLocation.FIRST);
    }

    @Test
    public void getIndexLocationByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getIndexLocation("FROMSTART"), IndexLocation.FROM_START);
    }

    @Test(expected = DbcException.class)
    public void invalidIndexLocation() {
        this.dropdownFactory.getIndexLocation("FROMSTART1");
    }

    @Test
    public void getListElementOperationFromString() {
        Assert.assertEquals(this.dropdownFactory.getListElementOpertaion("GET"), ListElementOperations.GET);
    }

    @Test(expected = DbcException.class)
    public void invalidListElementOperation() {
        this.dropdownFactory.getListElementOpertaion("FROMSTART1");
    }

    @Test
    public void getPickColorFromString() {
        Assert.assertEquals(this.dropdownFactory.getPickColor("#B30006").getFirst(), "RED");
    }

    @Test(expected = DbcException.class)
    public void invalidPickColor() {
        this.dropdownFactory.getPickColor("18");
    }

    @Test
    public void getActorPortFromString() {
        Assert.assertEquals(this.dropdownFactory.getActorPort("A"), new ActorPort("A", "MA"));
    }

    @Test
    public void getActorPortByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getActorPort("B"), new ActorPort("B", "MB"));
    }

    @Test(expected = DbcException.class)
    public void invalidActorPort() {
        this.dropdownFactory.getActorPort("Q");
    }

    @Test
    public void getBlinkModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getBlinkMode("FLASH"), LightMode.FLASH);
    }

    @Test(expected = DbcException.class)
    public void invalidBlinkMode() {
        this.dropdownFactory.getBlinkMode("18");
    }

    @Test
    public void getBrickLedColorFromString() {
        Assert.assertEquals(this.dropdownFactory.getBrickLedColor("ORANGE"), BrickLedColor.ORANGE);
    }

    @Test(expected = DbcException.class)
    public void invalidBrickLedColor() {
        this.dropdownFactory.getBrickLedColor("Q");
    }

    @Test
    public void getShowPictureFromString() {
        Assert.assertEquals(this.factory.getShowPicture("EYESCLOSED"), ShowPicture.EYESCLOSED);
    }

    @Test
    public void getShowPictureByAlternativeName() {
        Assert.assertEquals(this.factory.getShowPicture("BRILLE"), ShowPicture.OLDGLASSES);
    }

    @Test(expected = DbcException.class)
    public void invalidShowPicture() {
        this.factory.getShowPicture("Q");
    }

    @Test
    public void getTurnDirectionFromString() {
        Assert.assertEquals(this.dropdownFactory.getTurnDirection("LEFT"), TurnDirection.LEFT);
    }

    @Test(expected = DbcException.class)
    public void invalidTurnDirection() {
        this.dropdownFactory.getTurnDirection("Q");
    }

    @Test
    public void getMotorMoveModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getMotorMoveMode("ROTATIONS"), MotorMoveMode.ROTATIONS);
    }

    @Test(expected = DbcException.class)
    public void invalidMotorMoveMode() {
        this.dropdownFactory.getMotorMoveMode("Q");
    }

    @Test
    public void getMotorStopModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getMotorStopMode("FLOAT"), MotorStopMode.FLOAT);
    }

    @Test(expected = DbcException.class)
    public void invalidMotorStopMode() {
        this.dropdownFactory.getMotorStopMode("Q");
    }

    @Test
    public void getMotorSideFromString() {
        Assert.assertEquals(this.dropdownFactory.getMotorSide("LEFT"), MotorSide.LEFT);
    }

    @Test
    public void getMotorSideByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getMotorSide("right"), MotorSide.RIGHT);
    }

    @Test(expected = DbcException.class)
    public void invalidMotorSide() {
        this.dropdownFactory.getMotorSide("Q");
    }

    @Test
    public void getDriveDirectionFromString() {
        Assert.assertEquals(this.dropdownFactory.getDriveDirection("BACKWARD"), DriveDirection.BACKWARD);
    }

    @Test
    public void getDriveDirectionByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getDriveDirection("OFF"), DriveDirection.FOREWARD);
    }

    @Test(expected = DbcException.class)
    public void invalidDriveDirection() {
        this.dropdownFactory.getDriveDirection("Q");
    }

    @Test
    public void getBrickKeyFromString() {
        Assert.assertEquals(this.dropdownFactory.getSensorPort("DOWN"), new SensorPort("DOWN", "DOWN"));
    }

    @Test(expected = DbcException.class)
    public void invalidBrickKey() {
        this.dropdownFactory.getSensorPort("Q");
    }

    @Test
    public void getColorSensorModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getColorSensorMode("AMBIENTLIGHT"), ColorSensorMode.AMBIENTLIGHT);
    }

    @Test
    public void getColorSensorModeByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getColorSensorMode("Colour"), ColorSensorMode.COLOUR);
    }

    @Test(expected = DbcException.class)
    public void invalidColorSensorMode() {
        this.dropdownFactory.getColorSensorMode("Q");
    }

    @Test
    public void getGyroSensorModeFromString() {
        Assert.assertEquals(BlocklyDropdown2EnumHelper.getModeValue("ANGLE", GyroSensorMode.class), GyroSensorMode.ANGLE);
    }

    @Test
    public void getGyroSensorModeByAlternativeName() {
        Assert.assertEquals(BlocklyDropdown2EnumHelper.getModeValue("Rate", GyroSensorMode.class), GyroSensorMode.RATE);
    }

    @Test(expected = DbcException.class)
    public void invalidGyroSensorMode() {
        BlocklyDropdown2EnumHelper.getModeValue("Q", GyroSensorMode.class);
    }

    @Test
    public void getInfraredSensorModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getInfraredSensorMode("DISTANCE"), InfraredSensorMode.DISTANCE);
    }

    @Test
    public void getInfraredSensorModeByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getInfraredSensorMode("PRESENCE"), InfraredSensorMode.PRESENCE);
    }

    @Test(expected = DbcException.class)
    public void invalidInfraredSensorMode() {
        this.dropdownFactory.getInfraredSensorMode("Q");
    }

    @Test
    public void getTimerSensorModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getTimerSensorMode("VALUE"), TimerSensorMode.VALUE);
    }

    @Test(expected = DbcException.class)
    public void invalidTimerSensorMode() {
        this.dropdownFactory.getTimerSensorMode("Q");
    }

    @Test
    public void getMotorTachoModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getMotorTachoMode("DISTANCE"), EncoderSensorMode.DISTANCE);
    }

    @Test(expected = DbcException.class)
    public void invalidMotorTachoMode() {
        this.dropdownFactory.getMotorTachoMode("Q");
    }

    @Test
    public void getUltrasonicSensorModeFromString() {
        Assert.assertEquals(this.dropdownFactory.getUltrasonicSensorMode("DISTANCE"), UltrasonicSensorMode.DISTANCE);
    }

    @Test
    public void getUltrasonicSensorModeByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getUltrasonicSensorMode("Listen"), UltrasonicSensorMode.PRESENCE);
    }

    @Test(expected = DbcException.class)
    public void invalidUltrasonicSensorMode() {
        this.dropdownFactory.getUltrasonicSensorMode("Q");
    }

    @Test
    public void getSensorPortFromString() {
        Assert.assertEquals(this.dropdownFactory.getSensorPort("S1").getCodeName(), "S1");
    }

    @Test
    public void getSensorPortByAlternativeName() {
        Assert.assertEquals(this.dropdownFactory.getSensorPort("4").getCodeName(), "S4");
    }

    @Test(expected = DbcException.class)
    public void invalidSensorPort() {
        this.dropdownFactory.getSensorPort("Q");
    }
}

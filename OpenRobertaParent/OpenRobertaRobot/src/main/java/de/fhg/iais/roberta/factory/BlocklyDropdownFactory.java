package de.fhg.iais.roberta.factory;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.iais.roberta.inter.mode.action.IActorPort;
import de.fhg.iais.roberta.inter.mode.action.IBrickLedColor;
import de.fhg.iais.roberta.inter.mode.action.IDriveDirection;
import de.fhg.iais.roberta.inter.mode.action.ILanguage;
import de.fhg.iais.roberta.inter.mode.action.ILightMode;
import de.fhg.iais.roberta.inter.mode.action.IMotorMoveMode;
import de.fhg.iais.roberta.inter.mode.action.IMotorSide;
import de.fhg.iais.roberta.inter.mode.action.IMotorStopMode;
import de.fhg.iais.roberta.inter.mode.action.IRelayMode;
import de.fhg.iais.roberta.inter.mode.action.ITurnDirection;
import de.fhg.iais.roberta.inter.mode.general.IDirection;
import de.fhg.iais.roberta.inter.mode.general.IIndexLocation;
import de.fhg.iais.roberta.inter.mode.general.IListElementOperations;
import de.fhg.iais.roberta.inter.mode.general.IMode;
import de.fhg.iais.roberta.inter.mode.general.IWorkingState;
import de.fhg.iais.roberta.inter.mode.sensor.IBirckKeyPressMode;
import de.fhg.iais.roberta.inter.mode.sensor.IBrickKey;
import de.fhg.iais.roberta.inter.mode.sensor.IColorSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ICompassSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ICoordinatesMode;
import de.fhg.iais.roberta.inter.mode.sensor.IDropSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IEncoderSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IGestureSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IGyroSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IHumiditySensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IIRSeekerSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IInfraredSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IJoystickMode;
import de.fhg.iais.roberta.inter.mode.sensor.ILightSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IMoistureSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IMotionSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IPinPull;
import de.fhg.iais.roberta.inter.mode.sensor.IPinValue;
import de.fhg.iais.roberta.inter.mode.sensor.IPulseSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IRSeekerSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IRfidSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.inter.mode.sensor.ISlot;
import de.fhg.iais.roberta.inter.mode.sensor.ISoundSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ITemperatureSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ITimerSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.ITouchSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IUltrasonicSensorMode;
import de.fhg.iais.roberta.inter.mode.sensor.IVoltageSensorMode;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.BrickLedColor;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.mode.action.Language;
import de.fhg.iais.roberta.mode.action.LightMode;
import de.fhg.iais.roberta.mode.action.MotorMoveMode;
import de.fhg.iais.roberta.mode.action.MotorSide;
import de.fhg.iais.roberta.mode.action.MotorStopMode;
import de.fhg.iais.roberta.mode.action.RelayMode;
import de.fhg.iais.roberta.mode.action.TurnDirection;
import de.fhg.iais.roberta.mode.general.Direction;
import de.fhg.iais.roberta.mode.general.IndexLocation;
import de.fhg.iais.roberta.mode.general.ListElementOperations;
import de.fhg.iais.roberta.mode.general.PlaceholderSensorMode;
import de.fhg.iais.roberta.mode.general.WorkingState;
import de.fhg.iais.roberta.mode.sensor.Axis;
import de.fhg.iais.roberta.mode.sensor.ColorSensorMode;
import de.fhg.iais.roberta.mode.sensor.CompassSensorMode;
import de.fhg.iais.roberta.mode.sensor.DropSensorMode;
import de.fhg.iais.roberta.mode.sensor.EncoderSensorMode;
import de.fhg.iais.roberta.mode.sensor.GestureSensorMode;
import de.fhg.iais.roberta.mode.sensor.GyroSensorMode;
import de.fhg.iais.roberta.mode.sensor.HumiditySensorMode;
import de.fhg.iais.roberta.mode.sensor.InfraredSensorMode;
import de.fhg.iais.roberta.mode.sensor.KeysSensorMode;
import de.fhg.iais.roberta.mode.sensor.LightSensorMode;
import de.fhg.iais.roberta.mode.sensor.MoistureSensorMode;
import de.fhg.iais.roberta.mode.sensor.MotionSensorMode;
import de.fhg.iais.roberta.mode.sensor.PinPull;
import de.fhg.iais.roberta.mode.sensor.PinValue;
import de.fhg.iais.roberta.mode.sensor.PulseSensorMode;
import de.fhg.iais.roberta.mode.sensor.RfidSensorMode;
import de.fhg.iais.roberta.mode.sensor.SensorPort;
import de.fhg.iais.roberta.mode.sensor.Slot;
import de.fhg.iais.roberta.mode.sensor.SoundSensorMode;
import de.fhg.iais.roberta.mode.sensor.TemperatureSensorMode;
import de.fhg.iais.roberta.mode.sensor.TimerSensorMode;
import de.fhg.iais.roberta.mode.sensor.TouchSensorMode;
import de.fhg.iais.roberta.mode.sensor.UltrasonicSensorMode;
import de.fhg.iais.roberta.mode.sensor.VoltageSensorMode;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.sensor.Sensor;
import de.fhg.iais.roberta.syntax.sensor.SensorMetaDataBean;
import de.fhg.iais.roberta.util.DropDowns;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class BlocklyDropdownFactory {
    private static final Logger LOG = LoggerFactory.getLogger(BlocklyDropdownFactory.class);
    private final Map<String, SensorPort> sensorToPorts;
    private final Map<String, ActorPort> actorToPorts;
    private final Map<String, WaitUntilSensorBean> waMap;
    private final DropDowns blocklyDropdownItems;

    public BlocklyDropdownFactory(Properties properties) {
        this.sensorToPorts = BlocklyDropdown2EnumHelper.getSensorPortsFromProperties(properties);
        this.actorToPorts = BlocklyDropdown2EnumHelper.getActorPortsFromProperties(properties);
        this.blocklyDropdownItems = BlocklyDropdown2EnumHelper.getDropdownFromProperties(properties);
        this.waMap = BlocklyDropdown2EnumHelper.getWaitUntilFromProperties(properties);
    }

    /**
     * Get a sensor port from {@link ISensorPort} given string parameter and mapping from a port to SensorPort. It is possible for one sensor port to have
     * multiple string mappings. Throws exception if the sensor port does not exists. <br>
     * It can only be used by subclasses of IRobotFactory.
     *
     * @param name of the sensor port
     * @param port-SensorPort map
     * @return SensorPort {@link ISensorPort}
     */
    public ISensorPort getSensorPort(String port) {
        if ( port == null ) {
            throw new DbcException("Null sensor port!");
        }
        if ( port.isEmpty() ) {
            return this.sensorToPorts.get("NO_PORT");
        }
        final String sUpper = port.trim().toUpperCase(Locale.GERMAN);
        SensorPort sensorPort = this.sensorToPorts.get(sUpper);
        if ( sensorPort != null ) {
            return sensorPort;
        }
        sensorPort = this.sensorToPorts.get(port);
        if ( sensorPort != null ) {
            return sensorPort;
        }
        for ( Map.Entry<String, SensorPort> portName : this.sensorToPorts.entrySet() ) {
            if ( portName.getValue().getCodeName().equals(port) ) {
                return portName.getValue();
            }
        }
        throw new DbcException("Invalid sensor port: " + port);
    }

    /**
     * Get a actor port from {@link IActorPort} given string parameter and mapping from a port to ActorPort. It is possible for one sensor port to have multiple
     * string mappings. Throws exception if the sensor port does not exists. <br>
     * It can only be used by subclasses of IRobotFactory.
     *
     * @param name of the sensor port
     * @param port-ActorPort map
     * @return ActorPort {@link IActorPort}
     */
    public IActorPort getActorPort(String port) {
        if ( port == null ) {
            throw new DbcException("Null actor port!");
        }
        if ( port.isEmpty() ) {
            return this.actorToPorts.get("NO_PORT");
        }
        final String sUpper = port.trim().toUpperCase(Locale.GERMAN);
        ActorPort actorPort = this.actorToPorts.get(sUpper);
        if ( actorPort != null ) {
            return actorPort;
        }
        for ( Map.Entry<String, ActorPort> portName : this.actorToPorts.entrySet() ) {
            if ( portName.getValue().getCodeName().equals(port) ) {
                return portName.getValue();
            }
        }
        throw new DbcException("Invalid actor port: " + port);
    }

    /**
     * Get index location enumeration from {@link IIndexLocation} given string parameter. It is possible for one index location to have multiple string
     * mappings. Throws exception if the operator does not exists.
     *
     * @param indexLocation of the function
     * @return index location from the enum {@link IIndexLocation}
     */
    public IIndexLocation getIndexLocation(String indexLocation) {
        return BlocklyDropdown2EnumHelper.getModeValue(indexLocation, IndexLocation.class);
    }

    /**
     * Direction in space enumeration from {@link IDirection} given string parameter. It is possible for one direction to have multiple string mappings. Throws
     * exception if the operator does not exists.
     *
     * @param direction of the function
     * @return direction location from the enum {@link IDirection}
     */
    public IDirection getDirection(String direction) {
        return BlocklyDropdown2EnumHelper.getModeValue(direction, Direction.class);
    }

    /**
     * Get array element operation enumeration from {@link IListElementOperations} given string parameter. It is possible for one operation to have multiple
     * string mappings. Throws exception if the operator does not exists.
     *
     * @param operation string name
     * @return operation from the enum {@link IListElementOperations}
     */
    public IListElementOperations getListElementOpertaion(String operation) {
        return BlocklyDropdown2EnumHelper.getModeValue(operation, ListElementOperations.class);
    }

    /**
     * Get a {@link Pair<String, String>} given string parameter. Throws exception if
     * the color cannot be found.
     *
     * @param name of the color
     * @return {@link Pair<String, String>}
     */
    public Pair<String, String> getPickColor(String color) {
        return this.blocklyDropdownItems.get("color").getPairBySecond(color.toUpperCase());
    }

    /**
     * Get a {@link ILightMode} enumeration given string parameter. It is possible for one mode to have multiple string mappings. Throws exception if the mode
     * does not exists.
     *
     * @param name of the mode
     * @return mode from the enum {@link LightMode}
     */
    public ILightMode getBlinkMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, LightMode.class);
    }

    /**
     * Get a {@link IBrickLedColor} enumeration given string parameter. It is possible for one mode to have multiple string mappings. Throws exception if the
     * mode does not exists.
     *
     * @param name of the mode
     * @return mode from the enum {@link ILightMode}
     */
    public IBrickLedColor getBrickLedColor(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, BrickLedColor.class);
    }

    /**
     * Get a {@link ISensorMode} enumeration given string parameter. It is possible for one color to have multiple string mappings. Throws exception if the
     * color cannot be found.
     *
     * @param name of the color
     * @return enum {@link ISensorMode}
     */
    public ISensorMode getPlaceholderSensorMode(String color) {
        return BlocklyDropdown2EnumHelper.getModeValue(color, PlaceholderSensorMode.class);
    }

    /**
     * Get a {@link ILightSensorMode} enumeration given string parameter. It is possible for one mode to have multiple string mappings. Throws exception if the
     * mode does not exists.
     */
    public ILightSensorMode getLightColor(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, LightSensorMode.class);
    }

    public IWorkingState getWorkingState(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, WorkingState.class);
    }

    /**
     * Get a {@link ITurnDirection} enumeration given string parameter. It is possible for one turn direction to have multiple string mappings. Throws exception
     * if the mode does not exists.
     *
     * @param name of the turn direction
     * @return turn direction from the enum {@link ITurnDirection}
     */
    public ITurnDirection getTurnDirection(String direction) {
        return BlocklyDropdown2EnumHelper.getModeValue(direction, TurnDirection.class);
    }

    /**
     * Get a {@link IMotorMoveMode} enumeration given string parameter. It is possible for one motor move mode to have multiple string mappings. Throws
     * exception if the mode does not exists.
     *
     * @param name of the motor move mode
     * @return motor move mode from the enum {@link IMotorMoveMode}
     */
    public IMotorMoveMode getMotorMoveMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, MotorMoveMode.class);
    }

    /**
     * Get stopping mode from {@link IMotorStopMode} from string parameter. It is possible for one stopping mode to have multiple string mappings. Throws
     * exception if the stopping mode does not exists.
     *
     * @param name of the stopping mode
     * @return name of the stopping mode from the enum {@link IMotorStopMode}
     */
    public IMotorStopMode getMotorStopMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, MotorStopMode.class);
    }

    /**
     * Get motor side from {@link IMotorSide} given string parameter. It is possible for one motor side to have multiple string mappings. Throws exception if
     * the motor side does not exists.
     *
     * @param name of the motor side
     * @return the motor side from the enum {@link IMotorSide}
     */
    public IMotorSide getMotorSide(String motorSide) {
        return BlocklyDropdown2EnumHelper.getModeValue(motorSide, MotorSide.class);
    }

    /**
     * Get drive direction from {@link IDriveDirection} given string parameter. It is possible for one drive direction to have multiple string mappings. Throws
     * exception if the motor side does not exists.
     *
     * @param name of the drive direction
     * @return the drive direction from the enum {@link IDriveDirection}
     */
    public IDriveDirection getDriveDirection(String driveDirection) {
        return BlocklyDropdown2EnumHelper.getModeValue(driveDirection, DriveDirection.class);
    }

    /**
     * Get relay mode {@link IRelayMode} given string parameter. Throws exception if the mode does not exists.
     *
     * @param name of the mode
     * @return the drelay mode from the enum {@link IRelayMode}
     */
    public IRelayMode getRelayMode(String relayMode) {
        return BlocklyDropdown2EnumHelper.getModeValue(relayMode, RelayMode.class);
    }

    /**
     * Get a robot key from {@link IBrickKey} given string parameter. It is possible for one robot key to have multiple string mappings. Throws exception if the
     * robot key does not exists.
     *
     * @param name of the robot key
     * @return the robot keys from the enum {@link IBrickKey}
     */
    public IBirckKeyPressMode getBrickKeyPressMode(String brickKey) {
        return BlocklyDropdown2EnumHelper.getModeValue(brickKey, KeysSensorMode.class);
    }

    /**
     * Get a color sensor mode from {@link IColorSensorMode} given string parameter. It is possible for one color sensor mode to have multiple string mappings.
     * Throws exception if the color sensor mode does not exists.
     *
     * @param name of the color sensor mode
     * @return the color sensor mode from the enum {@link IColorSensorMode}
     */
    public IColorSensorMode getColorSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, ColorSensorMode.class);
    }

    public IJoystickMode getJoystickMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, Axis.class);
    }

    public ILightSensorMode getLightSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, LightSensorMode.class);
    }

    public ICompassSensorMode getCompassSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, CompassSensorMode.class);
    }

    public IPinValue getPinGetValueSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, PinValue.class);
    }

    public IPinPull getPinPullMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, PinPull.class);
    }

    public ITemperatureSensorMode getTemperatureSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, TemperatureSensorMode.class);
    }

    public ICoordinatesMode getAxis(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, Axis.class);
    }

    public ISoundSensorMode getSoundSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, SoundSensorMode.class);
    }

    public ILanguage getLanguageMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, Language.class);
    }

    /**
     * Get a gyro sensor mode from {@link IGyroSensorMode} given string parameter. It is possible for one gyro sensor mode to have multiple string mappings.
     * Throws exception if the gyro sensor mode does not exists.
     *
     * @param name of the gyro sensor mode
     * @return the gyro sensor mode from the enum {@link IGyroSensorMode}
     */
    public IGyroSensorMode getGyroSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, GyroSensorMode.class);
    }

    /**
     * Get a infrared sensor mode from {@link IInfraredSensorMode} given string parameter. It is possible for one infrared sensor mode to have multiple string
     * mappings. Throws exception if the infrared sensor mode does not exists.
     *
     * @param name of the infrared sensor mode
     * @return the infrared sensor mode from the enum {@link IInfraredSensorMode}
     */
    public IInfraredSensorMode getInfraredSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, InfraredSensorMode.class);
    }

    /**
     * Get a IRSeeker sensor mode from {@link IIRSeekerSensorMode} given string parameter. It is possible for one IRSeeker sensor mode to have multiple string
     * mappings. Throws exception if the IRSeeker sensor mode does not exists.
     *
     * @param name of the IRSeeker sensor mode
     * @return the IRSeeker sensor mode from the enum {@link IRSeekerSensorMode}
     */
    public IRSeekerSensorMode getIRSeekerSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, IRSeekerSensorMode.class);
    }

    /**
     * Get a timer sensor mode from {@link ITimerSensorMode} given string parameter. It is possible for one timer sensor mode to have multiple string mappings.
     * Throws exception if the timer sensor mode does not exists.
     *
     * @param name of the timer sensor mode
     * @return the timer sensor mode from the enum {@link ITimerSensorMode}
     */
    public ITimerSensorMode getTimerSensorMode(String modeName) {
        return BlocklyDropdown2EnumHelper.getModeValue(modeName, TimerSensorMode.class);
    }

    /**
     * Get a motor tacho sensor mode from {@link IEncoderSensorMode} given string parameter. It is possible for one motor tacho sensor mode to have multiple
     * string
     * mappings. Throws exception if the motor tacho sensor mode does not exists.
     *
     * @param name of the motor tacho sensor mode
     * @return the motor tacho sensor mode from the enum {@link IEncoderSensorMode}
     */
    public IEncoderSensorMode getMotorTachoMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, EncoderSensorMode.class);
    }

    /**
     * Get a ultrasonic sensor mode from {@link IUltrasonicSensorMode} given string parameter. It is possible for one ultrasonic sensor mode to have multiple
     * string mappings. Throws exception if the ultrasonic sensor mode does not exists.
     *
     * @param name of the ultrasonic sensor mode
     * @return the ultrasonic sensor mode from the enum {@link IUltrasonicSensorMode}
     */
    public IUltrasonicSensorMode getUltrasonicSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, UltrasonicSensorMode.class);
    }

    /**
     * Get a touch sensor mode from {@link ITouchSensorMode} given string parameter. It is possible for one touch sensor mode to have multiple string mappings.
     * Throws exception if the touch sensor mode does not exists.
     *
     * @param name of the touch sensor mode
     * @return the touch sensor mode from the enum {@link ITouchSensorMode}
     */
    public ITouchSensorMode getTouchSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, TouchSensorMode.class);
    }

    /**
     * Get a voltage sensor mode from {@link IVoltageSensorMode} given string parameter. It is possible for one voltage sensor mode to have multiple string
     * mappings. Throws exception if the voltage sensor mode does not exists.
     *
     * @param name of the voltage sensor mode
     * @return the volatage sensor mode from the enum {@link IVoltageSensorMode}
     */
    public IVoltageSensorMode getVoltageSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, VoltageSensorMode.class);
    }

    /**
     * Get a moisture sensor mode from {@link IMoistureSensorMode} given string parameter. It is possible for one motion sensor mode to have multiple string
     * mappings. Throws exception if the moisture sensor mode does not exists.
     *
     * @param name of the moisture sensor mode
     * @return the moisture sensor mode from the enum {@link IMoistureSensorMode}
     */
    public IMoistureSensorMode getMoistureSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, MoistureSensorMode.class);
    }

    /**
     * Get a motion sensor mode from {@link IMoistureSensorMode} given string parameter. It is possible for one motion sensor mode to have multiple string
     * mappings. Throws exception if the motion sensor mode does not exists.
     *
     * @param name of the motion sensor mode
     * @return the motion sensor mode from the enum {@link IMoistureSensorMode}
     */
    public IMotionSensorMode getMotionSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, MotionSensorMode.class);
    }

    /**
     * Get a motion sensor mode from {@link IHumiditySensorMode} given string parameter. It is possible for one humidity sensor mode to have multiple string
     * mappings. Throws exception if the humidity sensor mode does not exists.
     *
     * @param name of the humidity sensor mode
     * @return the motion sensor mode from the enum {@link IMoistureSensorMode}
     */
    public IHumiditySensorMode getHumiditySensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, HumiditySensorMode.class);
    }

    /**
     * Get a drop sensor mode from {@link IHumiditySensorMode} given string parameter. It is possible for one drop sensor mode to have multiple string mappings.
     * Throws exception if the drop sensor mode does not exists.
     *
     * @param name of the drop sensor mode
     * @return the drop sensor mode from the enum {@link IHumiditySensorMode}
     */
    public IDropSensorMode getDropSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, DropSensorMode.class);
    }

    /**
     * Get a pulse sensor mode from {@link IPulseSensorMode} given string parameter. It is possible for one pulse sensor mode to have multiple string mappings.
     * Throws exception if the pulse sensor mode does not exists.
     *
     * @param name of the pulse sensor mode
     * @return the pulse sensor mode from the enum {@link IPulseSensorMode}
     */
    public IPulseSensorMode getPulseSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, PulseSensorMode.class);
    }

    /**
     * Get a RFID sensor mode from {@link IRfidSensorMode} given string parameter. It is possible for one RFID sensor mode to have multiple string mappings.
     * Throws exception if the RFID sensor mode does not exists.
     *
     * @param name of the RFID sensor mode
     * @return the RFID sensor mode from the enum {@link IDropSensorMode}
     */
    public IRfidSensorMode getRfidSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, RfidSensorMode.class);
    }

    /**
     * Get a gesture sensor mode from {@link IGestureSensorMode} given string parameter. It is possible for one gesture sensor mode to have multiple string
     * mappings. Throws exception if the gesture sensor mode does not exists.
     *
     * @param name of the gesture sensor mode
     * @return the volatage sensor mode from the enum {@link IGestureSensorMode}
     */
    public IGestureSensorMode getGestureSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, GestureSensorMode.class);
    }

    public IMode getFlameSensorMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, InfraredSensorMode.class);
    }

    /**
     * Get a sensor port from {@link ISensorPort} given string parameter. It is possible for one sensor port to have multiple string mappings. Throws exception
     * if the sensor port does not exists.
     *
     * @param name of the sensor port
     * @return the sensor port from the enum {@link ISensorPort}
     */
    public ISlot getSlot(String slot) {
        return BlocklyDropdown2EnumHelper.getModeValue(slot, Slot.class);
    }

    /**
     * Creates an AST object representing sensor of specific type. If the type of the sensor does not exists it trows an exception.
     * @param sensorKey see {@link GetSampleType}
     * @param port on which the sensor is connected
     * @param slot on which the sensor is connected
     * @param properties of the block
     * @param comment of the block
     * @return returns instance of the specific sensor {@link Sensor}
     * @throws
     */
    @SuppressWarnings("unchecked")
    public Sensor<?> createSensor(
        String sensorKey,
        String port,
        String slot,
        boolean isPortInMutation,
        BlocklyBlockProperties properties,
        BlocklyComment comment) {

        WaitUntilSensorBean waBean = this.waMap.get(sensorKey);
        String implementingClass = waBean.getImplementingClass();
        String modeClassName = implementingClass.substring(implementingClass.lastIndexOf(".") + 1);

        try {
            Class<IMode> modeEnumClass =
                (Class<IMode>) BlocklyDropdownFactory.class.getClassLoader().loadClass("de.fhg.iais.roberta.mode.sensor." + modeClassName + "Mode");
            Class<Sensor<?>> sensorClass = (Class<Sensor<?>>) BlocklyDropdownFactory.class.getClassLoader().loadClass(implementingClass);
            IMode mode = BlocklyDropdown2EnumHelper.getModeValue(waBean.getMode(), modeEnumClass);
            SensorMetaDataBean sensorMetaDataBean = new SensorMetaDataBean(getSensorPort(port), mode, getSlot(slot), isPortInMutation);
            Method makeMethod = sensorClass.getDeclaredMethod("make", SensorMetaDataBean.class, BlocklyBlockProperties.class, BlocklyComment.class);
            return (Sensor<?>) makeMethod.invoke(null, sensorMetaDataBean, properties, comment);
        } catch ( Exception e ) {
            LOG.error("Sensor " + sensorKey + " could not be created", e);
            throw new DbcException("Sensor " + sensorKey + " could not be created", e);
        }

    }

}

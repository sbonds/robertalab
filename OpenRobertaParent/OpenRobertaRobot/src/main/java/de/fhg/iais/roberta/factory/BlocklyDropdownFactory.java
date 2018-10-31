package de.fhg.iais.roberta.factory;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import de.fhg.iais.roberta.inter.mode.general.IWorkingState;
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
import de.fhg.iais.roberta.mode.general.WorkingState;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.syntax.sensor.Sensor;
import de.fhg.iais.roberta.syntax.sensor.SensorMetaDataBean;
import de.fhg.iais.roberta.util.DropDowns;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.dbc.Assert;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class BlocklyDropdownFactory {
    private static final Logger LOG = LoggerFactory.getLogger(BlocklyDropdownFactory.class);
    private final Map<String, String> sensorToPorts;
    private final Map<String, String> actorToPorts;
    private final Map<String, String> slots;

    private final Map<String, WaitUntilSensorBean> waMap;
    private final Map<String, String> sensorModes;
    private final DropDowns blocklyDropdownItems;
    private final Map<String, String> configurationComponentTypes;

    public BlocklyDropdownFactory(Properties properties) {
        this.sensorToPorts = BlocklyDropdown2EnumHelper.getSensorPortsFromProperties(properties);
        this.actorToPorts = BlocklyDropdown2EnumHelper.getActorPortsFromProperties(properties);
        this.slots = BlocklyDropdown2EnumHelper.getSlotFromProperties(properties);
        this.blocklyDropdownItems = BlocklyDropdown2EnumHelper.getDropdownFromProperties(properties);
        this.waMap = BlocklyDropdown2EnumHelper.getWaitUntilFromProperties(properties);
        this.sensorModes = BlocklyDropdown2EnumHelper.getSensorModesFromProperties(properties);
        this.configurationComponentTypes = BlocklyDropdown2EnumHelper.getConfigurationComponentTypesFromProperties(properties);
    }

    public String getConfigurationComponentType(String key) {
        Assert.nonEmptyString(key, "Invalid component key");
        String configurationComponentType = this.configurationComponentTypes.get(key);
        Assert.notNull(configurationComponentType, "No associated component type for %s in the properties", key);
        return configurationComponentType;
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
    public String getSensorPort(String port) {
        Assert.nonEmptyString(port);
        String portInternal = this.sensorToPorts.get(port);
        Assert.notNull(portInternal, "Invalid sensor port: %s", port);
        return portInternal;
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
    public String getActorPort(String port) {
        Assert.notNull(port, "Null actor port!");
        if ( port.isEmpty() ) {
            return BlocklyConstants.NO_PORT;
        }
        final String sUpper = port.trim().toUpperCase(Locale.GERMAN);
        String actorPort = this.actorToPorts.get(sUpper);
        Assert.notNull(actorPort, "Undefined actor port %s", port);
        return actorPort;
    }

    /**
     * Get a sensor port from {@link ISensorPort} given string parameter. It is possible for one sensor port to have multiple string mappings. Throws exception
     * if the sensor port does not exists.
     *
     * @param name of the sensor port
     * @return the sensor port from the enum {@link ISensorPort}
     */
    public String getSlot(String slot) {
        Assert.notNull(slot, "Null slot port!");
        if ( slot.isEmpty() ) {
            return BlocklyConstants.NO_PORT;
        }
        final String sUpper = slot.trim().toUpperCase(Locale.GERMAN);
        String internalSlot = this.slots.get(sUpper);
        Assert.notNull(internalSlot, "Undefined slot! %s", slot);
        return internalSlot;
    }

    public String getSensorMode(String mode) {
        Assert.nonEmptyString(mode, "Invalid sensor mode");

        final String sUpper = mode.trim().toUpperCase(Locale.GERMAN);
        String internalSensorMode = this.sensorModes.get(sUpper);
        Assert.notNull(internalSensorMode, "Undefined sensor mode %s", mode);
        return internalSensorMode;
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

    public ILanguage getLanguageMode(String mode) {
        return BlocklyDropdown2EnumHelper.getModeValue(mode, Language.class);
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

        try {
            Class<Sensor<?>> sensorClass = (Class<Sensor<?>>) BlocklyDropdownFactory.class.getClassLoader().loadClass(implementingClass);
            String mode = waBean.getMode();
            SensorMetaDataBean sensorMetaDataBean = new SensorMetaDataBean(getSensorPort(port), getSensorMode(mode), getSlot(slot), isPortInMutation);
            Method makeMethod = sensorClass.getDeclaredMethod("make", SensorMetaDataBean.class, BlocklyBlockProperties.class, BlocklyComment.class);
            return (Sensor<?>) makeMethod.invoke(null, sensorMetaDataBean, properties, comment);
        } catch ( Exception e ) {
            LOG.error("Sensor " + sensorKey + " could not be created", e);
            throw new DbcException("Sensor " + sensorKey + " could not be created", e);
        }

    }

}

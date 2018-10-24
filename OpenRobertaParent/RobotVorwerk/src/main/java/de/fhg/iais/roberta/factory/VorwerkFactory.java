package de.fhg.iais.roberta.factory;

import java.util.ArrayList;

import de.fhg.iais.roberta.codegen.ICompilerWorkflow;
import de.fhg.iais.roberta.codegen.VorwerkCompilerWorkflow;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.mode.sensor.UltrasonicSensorMode;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.sensor.GetSampleType;
import de.fhg.iais.roberta.syntax.sensor.Sensor;
import de.fhg.iais.roberta.syntax.sensor.SensorMetaDataBean;
import de.fhg.iais.roberta.syntax.sensor.vorwerk.DropOffSensor;
import de.fhg.iais.roberta.syntax.sensor.vorwerk.WallSensor;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.visitor.validate.AbstractProgramValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractSimValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.VorwerkBrickValidatorVisitor;

public class VorwerkFactory extends AbstractRobotFactory {

    public VorwerkFactory(PluginProperties pluginProperties) {
        super(pluginProperties);
    }

    @Override
    public ICompilerWorkflow getRobotCompilerWorkflow() {
        return new VorwerkCompilerWorkflow(this.pluginProperties);
    }

    @Override
    public String getFileExtension() {
        return "py";
    }

    @Override
    public AbstractSimValidatorVisitor getSimProgramCheckVisitor(Configuration brickConfiguration) {
        return null;
    }

    @Override
    public AbstractProgramValidatorVisitor getRobotProgramCheckVisitor(Configuration brickConfiguration) {
        return new VorwerkBrickValidatorVisitor(brickConfiguration);
    }

    @Override
    public String generateCode(Configuration brickConfiguration, ArrayList<ArrayList<Phrase<Void>>> phrasesSet, boolean withWrapping) {
        return null;
    }

    @Override
    public ICompilerWorkflow getSimCompilerWorkflow() {
        return null;
    }

    public Sensor<?> createSensor(
        GetSampleType sensorType,
        String port,
        String slot,
        boolean isPortInMutation,
        BlocklyBlockProperties properties,
        BlocklyComment comment) {
        SensorMetaDataBean sensorMetaDataBean;
        switch ( sensorType.getSensorType() ) {
            case BlocklyConstants.WALL:
                sensorMetaDataBean =
                    new SensorMetaDataBean(
                        this.blocklyDropdown2EnumFactory.getSensorPort(port),
                        UltrasonicSensorMode.DISTANCE,
                        this.blocklyDropdown2EnumFactory.getSlot(slot),
                        isPortInMutation);
                return WallSensor.make(sensorMetaDataBean, properties, comment);
            case BlocklyConstants.DROP_OFF:
                sensorMetaDataBean =
                    new SensorMetaDataBean(
                        this.blocklyDropdown2EnumFactory.getSensorPort(port),
                        UltrasonicSensorMode.DISTANCE,
                        this.blocklyDropdown2EnumFactory.getSlot(slot),
                        isPortInMutation);
                return DropOffSensor.make(sensorMetaDataBean, properties, comment);
            default:
                return this.blocklyDropdown2EnumFactory.createSensor(sensorType, port, slot, isPortInMutation, properties, comment);
        }
    }
}

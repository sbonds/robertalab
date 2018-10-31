package de.fhg.iais.roberta.visitor.collect;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.fhg.iais.roberta.components.ActorType;
import de.fhg.iais.roberta.components.ConfigurationBlock;
import de.fhg.iais.roberta.components.SensorType;
import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.components.arduino.ArduinoConfiguration;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;
import de.fhg.iais.roberta.syntax.actors.arduino.PinWriteValueAction;
import de.fhg.iais.roberta.syntax.actors.arduino.RelayAction;
import de.fhg.iais.roberta.syntax.actors.arduino.SerialWriteAction;
import de.fhg.iais.roberta.syntax.sensor.generic.EncoderSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.PinGetValueSensor;
import de.fhg.iais.roberta.visitor.hardware.IArduinoVisitor;

/**
 * This visitor collects information for used actors and sensors in blockly program.
 *
 * @author eovchinnikova
 */
public final class ArduinoUsedHardwareCollectorVisitor extends AbstractUsedHardwareCollectorVisitor implements IArduinoVisitor<Void> {

    protected final Set<ConfigurationBlock> usedConfigurationBlocks = new LinkedHashSet<>();

    ArduinoConfiguration configuration;

    public ArduinoUsedHardwareCollectorVisitor(ArrayList<ArrayList<Phrase<Void>>> phrasesSet, ArduinoConfiguration configuration) {
        super(configuration);
        this.configuration = configuration;
        check(phrasesSet);
    }

    public Set<UsedSensor> getTimer() {
        return this.usedSensors;
    }

    public Set<ConfigurationBlock> getUsedConfigurationBlocks() {
        Map<String, ConfigurationBlock> configurationBlocks = this.configuration.getConfigurationBlocks();
        configurationBlocks.entrySet().stream().forEach(
            configurationBlock -> this.usedConfigurationBlocks.add(
                new ConfigurationBlock(
                    configurationBlock.getValue().getConfType(),
                    configurationBlock.getValue().getConfName(),
                    configurationBlock.getValue().getConfPorts())));

        return this.usedConfigurationBlocks;
    }

    @Override
    public Void visitMotorOnAction(MotorOnAction<Void> motorOnAction) {
        motorOnAction.getParam().getSpeed().visit(this);
        if ( motorOnAction.getParam().getDuration() != null ) {
            motorOnAction.getDurationValue().visit(this);
        }
        if ( this.robotConfiguration != null ) {
            //Actor actor = this.brickConfiguration.getActors().get(motorOnAction.getPort());
            //if ( actor != null ) {
            //    this.usedActors.add(new UsedActor(motorOnAction.getPort(), actor.getName()));
            //}
        }
        return null;
    }

    @Override
    public Void visitEncoderSensor(EncoderSensor<Void> encoderSensor) {
        return null;
    }

    @Override
    public Void visitRelayAction(RelayAction<Void> relayAction) {
        return null;
    }

    @Override
    public Void visitPinGetValueSensor(PinGetValueSensor<Void> pinGetValueSensor) {
        this.usedSensors.add(new UsedSensor((ISensorPort) pinGetValueSensor.getPort(), SensorType.PIN_VALUE, pinGetValueSensor.getMode()));
        return null;
    }

    @Override
    public Void visitPinWriteValueAction(PinWriteValueAction<Void> pinWriteValueSensor) {
        pinWriteValueSensor.getValue().visit(this);
        this.usedActors.add(new UsedActor(pinWriteValueSensor.getPort(), ActorType.ANALOG_PIN));
        return null;
    }

    @Override
    public Void visitSerialWriteAction(SerialWriteAction<Void> serialWriteAction) {
        serialWriteAction.getValue().visit(this);
        return null;
    }

}

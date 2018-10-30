package de.fhg.iais.roberta.factory;

import java.util.ArrayList;
import java.util.Map;

import de.fhg.iais.roberta.codegen.Ev3LejosCompilerWorkflow;
import de.fhg.iais.roberta.codegen.Ev3SimCompilerWorkflow;
import de.fhg.iais.roberta.codegen.ICompilerWorkflow;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.inter.mode.action.IActorPort;
import de.fhg.iais.roberta.inter.mode.action.IShowPicture;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.ev3.ShowPicture;
import de.fhg.iais.roberta.mode.sensor.SensorPort;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.Util1;
import de.fhg.iais.roberta.visitor.validate.AbstractProgramValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractSimValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.Ev3BrickValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.Ev3SimValidatorVisitor;

public abstract class AbstractEV3Factory extends AbstractRobotFactory {
    protected String name;
    Map<String, SensorPort> sensorToPorts = BlocklyDropdown2EnumHelper.getSensorPortsFromProperties(Util1.loadProperties("classpath:EV3ports.properties"));
    Map<String, ActorPort> actorToPorts = BlocklyDropdown2EnumHelper.getActorPortsFromProperties(Util1.loadProperties("classpath:EV3ports.properties"));

    public AbstractEV3Factory(PluginProperties pluginProperties) {
        super(pluginProperties);
    }

    @Override
    public ISensorPort getSensorPort(String port) {
        return getSensorPortValue(port, this.sensorToPorts);
    }

    @Override
    public IActorPort getActorPort(String port) {
        return getActorPortValue(port, this.actorToPorts);
    }

    @Override
    public IShowPicture getShowPicture(String picture) {
        return BlocklyDropdown2EnumHelper.getModeValue(picture, ShowPicture.class);
    }

    @Override
    public ICompilerWorkflow getSimCompilerWorkflow() {
        return new Ev3SimCompilerWorkflow(this.pluginProperties);
    }

    @Override
    public ICompilerWorkflow getRobotCompilerWorkflow() {
        return new Ev3LejosCompilerWorkflow(this.pluginProperties);
    }

    @Override
    public String getFileExtension() {
        return "java";
    }

    @Override
    public AbstractSimValidatorVisitor getSimProgramCheckVisitor(Configuration brickConfiguration) {
        return new Ev3SimValidatorVisitor(brickConfiguration);
    }

    @Override
    public AbstractProgramValidatorVisitor getRobotProgramCheckVisitor(Configuration brickConfiguration) {
        return new Ev3BrickValidatorVisitor(brickConfiguration);
    }

    @Override
    public String generateCode(Configuration brickConfiguration, ArrayList<ArrayList<Phrase<Void>>> phrasesSet, boolean withWrapping) {
        return null;
    }
}

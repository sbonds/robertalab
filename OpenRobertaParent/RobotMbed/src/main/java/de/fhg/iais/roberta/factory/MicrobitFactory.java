package de.fhg.iais.roberta.factory;

import java.util.ArrayList;
import java.util.Map;

import de.fhg.iais.roberta.codegen.ICompilerWorkflow;
import de.fhg.iais.roberta.codegen.MbedSimCompilerWorkflow;
import de.fhg.iais.roberta.codegen.MicrobitCompilerWorkflow;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.inter.mode.action.IShowPicture;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.sensor.SensorPort;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.Util1;
import de.fhg.iais.roberta.visitor.validate.AbstractProgramValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.AbstractSimValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.MbedBoardValidatorVisitor;
import de.fhg.iais.roberta.visitor.validate.MicrobitSimValidatorVisitor;

public class MicrobitFactory extends AbstractMbedFactory {
    Map<String, SensorPort> sensorToPorts = BlocklyDropdown2EnumHelper.getSensorPortsFromProperties(Util1.loadProperties("classpath:Microbitports.properties"));
    Map<String, ActorPort> actorToPorts = BlocklyDropdown2EnumHelper.getActorPortsFromProperties(Util1.loadProperties("classpath:Microbitports.properties"));

    public MicrobitFactory(PluginProperties pluginProperties) {
        super(pluginProperties);
    }

    @Override
    public IShowPicture getShowPicture(String picture) {
        return null;
    }

    @Override
    public String getFileExtension() {
        return "py";
    }

    @Override
    public ICompilerWorkflow getRobotCompilerWorkflow() {
        return new MicrobitCompilerWorkflow(this.pluginProperties);
    }

    @Override
    public ICompilerWorkflow getSimCompilerWorkflow() {
        return new MbedSimCompilerWorkflow(this.pluginProperties);
    }

    @Override
    public AbstractSimValidatorVisitor getSimProgramCheckVisitor(Configuration brickConfiguration) {
        return new MicrobitSimValidatorVisitor(brickConfiguration);
    }

    @Override
    public AbstractProgramValidatorVisitor getRobotProgramCheckVisitor(Configuration brickConfiguration) {
        return new MbedBoardValidatorVisitor(brickConfiguration);
    }

    @Override
    public String generateCode(Configuration brickConfiguration, ArrayList<ArrayList<Phrase<Void>>> phrasesSet, boolean withWrapping) {
        // TODO Auto-generated method stub
        return null;
    }
}

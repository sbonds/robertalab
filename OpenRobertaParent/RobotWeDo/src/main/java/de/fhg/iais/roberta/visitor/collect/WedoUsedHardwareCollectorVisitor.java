package de.fhg.iais.roberta.visitor.collect;

import java.util.ArrayList;
import java.util.Set;

import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.components.wedo.WeDoConfiguration;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;

/**
 * This visitor collects information for used actors and sensors in blockly program.
 *
 * @author eovchinnikova
 */
public class WedoUsedHardwareCollectorVisitor extends AbstractUsedHardwareCollectorVisitor {
    WeDoConfiguration configuration;

    public WedoUsedHardwareCollectorVisitor(ArrayList<ArrayList<Phrase<Void>>> phrasesSet, WeDoConfiguration configuration) {
        super(configuration);
        this.configuration = configuration;
        check(phrasesSet);
    }

    public Set<UsedSensor> getTimer() {
        return this.usedSensors;
    }

    @Override
    public Void visitMotorOnAction(MotorOnAction<Void> motorOnAction) {
        motorOnAction.getParam().getSpeed().visit(this);
        if ( motorOnAction.getParam().getDuration() != null ) {
            motorOnAction.getDurationValue().visit(this);
        }
        if ( this.robotConfiguration != null ) {
            this.usedActors.add(new UsedActor(motorOnAction.getPort()));
        }
        return null;
    }
}

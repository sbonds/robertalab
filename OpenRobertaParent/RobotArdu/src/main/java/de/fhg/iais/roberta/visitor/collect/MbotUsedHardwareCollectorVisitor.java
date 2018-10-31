package de.fhg.iais.roberta.visitor.collect;

import java.util.ArrayList;

import de.fhg.iais.roberta.components.ActorType;
import de.fhg.iais.roberta.components.SensorType;
import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.components.arduino.MbotConfiguration;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.differential.TurnAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOffAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOnAction;
import de.fhg.iais.roberta.syntax.expressions.arduino.LedMatrix;
import de.fhg.iais.roberta.syntax.sensors.arduino.mbot.FlameSensor;
import de.fhg.iais.roberta.syntax.sensors.arduino.mbot.Joystick;
import de.fhg.iais.roberta.visitor.hardware.IMbotVisitor;

/**
 * This visitor collects information for used actors and sensors in blockly program.
 *
 * @author kcvejoski
 */
public final class MbotUsedHardwareCollectorVisitor extends AbstractUsedHardwareCollectorVisitor implements IMbotVisitor<Void> {
    public MbotUsedHardwareCollectorVisitor(ArrayList<ArrayList<Phrase<Void>>> phrasesSet, MbotConfiguration configuration) {
        super(configuration);
        check(phrasesSet);
    }

    @Override
    public Void visitJoystick(Joystick<Void> joystick) {
        this.usedSensors.add(new UsedSensor((ISensorPort) joystick.getPort(), SensorType.JOYSTICK, null));
        return null;
    }

    @Override
    public Void visitFlameSensor(FlameSensor<Void> flameSensor) {
        this.usedSensors.add(new UsedSensor(flameSensor.getPort(), SensorType.FLAMESENSOR, null));
        return null;
    }

    @Override
    public Void visitToneAction(ToneAction<Void> toneAction) {
        super.visitToneAction(toneAction);
        this.usedActors.add(new UsedActor(null, ActorType.BUZZER));
        return null;
    }

    @Override
    public Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
        super.visitPlayNoteAction(playNoteAction);
        this.usedActors.add(new UsedActor(null, ActorType.BUZZER));
        return null;
    }

    @Override
    public Void visitDriveAction(DriveAction<Void> driveAction) {
        driveAction.getParam().getSpeed().visit(this);
        if ( driveAction.getParam().getDuration() != null ) {
            driveAction.getParam().getDuration().getValue().visit(this);
        }
        if ( this.robotConfiguration != null ) {
            this.usedActors.add(new UsedActor(this.robotConfiguration.getLeftMotorPort(), ActorType.DIFFERENTIAL_DRIVE));
        }
        return null;
    }

    @Override
    public Void visitCurveAction(CurveAction<Void> curveAction) {
        curveAction.getParamLeft().getSpeed().visit(this);
        curveAction.getParamRight().getSpeed().visit(this);
        if ( curveAction.getParamLeft().getDuration() != null ) {
            curveAction.getParamLeft().getDuration().getValue().visit(this);
        }
        if ( this.robotConfiguration != null ) {
            this.usedActors.add(new UsedActor(this.robotConfiguration.getLeftMotorPort(), ActorType.DIFFERENTIAL_DRIVE));
        }
        return null;
    }

    @Override
    public Void visitTurnAction(TurnAction<Void> turnAction) {
        turnAction.getParam().getSpeed().visit(this);
        if ( turnAction.getParam().getDuration() != null ) {
            turnAction.getParam().getDuration().getValue().visit(this);
        }
        if ( this.robotConfiguration != null ) {
            this.usedActors.add(new UsedActor(this.robotConfiguration.getLeftMotorPort(), ActorType.DIFFERENTIAL_DRIVE));
        }
        return null;
    }

    @Override
    public Void visitImage(LedMatrix<Void> ledMatrix) {
        return null;
    }

    @Override
    public Void visitMotorDriveStopAction(MotorDriveStopAction<Void> stopAction) {
        if ( this.robotConfiguration != null ) {
            if ( (this.robotConfiguration.getLeftMotorPort() != null) && (this.robotConfiguration.getRightMotorPort() != null) ) {
                this.usedActors.add(new UsedActor(this.robotConfiguration.getLeftMotorPort(), ActorType.GEARED_MOTOR));
                this.usedActors.add(new UsedActor(this.robotConfiguration.getRightMotorPort(), ActorType.GEARED_MOTOR));
            }
        }
        return null;
    }

    @Override
    public Void visitLightAction(LightAction<Void> lightAction) {
        this.usedActors.add(new UsedActor(null, ActorType.LED_ON_BOARD));
        return null;
    }

    @Override
    public Void visitLedOffAction(LedOffAction<Void> ledOffAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitLedOnAction(LedOnAction<Void> ledOnAction) {
        // TODO Auto-generated method stub
        return null;
    }

}

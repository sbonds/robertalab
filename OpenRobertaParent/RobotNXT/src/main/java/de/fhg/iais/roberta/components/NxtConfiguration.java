package de.fhg.iais.roberta.components;

import java.util.ArrayList;
import java.util.List;

import de.fhg.iais.roberta.inter.mode.action.IActorPort;
import de.fhg.iais.roberta.inter.mode.action.IMotorSide;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.MotorSide;
import de.fhg.iais.roberta.mode.sensor.SensorPort;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.dbc.Assert;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class NxtConfiguration extends Configuration {

    private final List<AbstractActor> actors;
    private final List<AbstractSensor> sensors;

    private final float wheelDiameterCM;
    private final float trackWidthCM;

    public NxtConfiguration(List<AbstractActor> actors, List<AbstractSensor> sensors, float wheelDiameterCM, float trackWidthCM) {
        //super(actors, sensors, wheelDiameterCM, trackWidthCM);
        super(null, null, -1, -1);
        this.actors = actors;
        this.sensors = sensors;
        this.wheelDiameterCM = wheelDiameterCM;
        this.trackWidthCM = trackWidthCM;
    }

    public List<AbstractActor> getNxtActors() {
        return this.actors;
    }

    public List<AbstractSensor> getNxtSensors() {
        return this.sensors;
    }

    public float getNxtWheelDiameterCM() {
        return this.wheelDiameterCM;
    }

    public float getNxtTrackWidthCM() {
        return this.trackWidthCM;
    }

    public NxtMotorActor getNxtLeftMotor() {
        return getNxtMotor(MotorSide.LEFT);
    }

    public NxtMotorActor getNxtRightMotor() {
        return getNxtMotor(MotorSide.RIGHT);
    }

    private NxtMotorActor getNxtMotor(IMotorSide side) {
        Assert.isTrue(this.actors != null, "There is no actors set to the configuration!");
        for ( AbstractActor actor : this.actors ) {
            if ( actor instanceof NxtMotorActor ) {
                NxtMotorActor motorActor = (NxtMotorActor) actor;
                if ( motorActor.getMotorSide() == side ) {
                    return motorActor;
                }
            }
        }
        throw new DbcException("Motor on side " + side + " not found!");
    }

    /**
     * This class is a builder of {@link Configuration}
     */
    public static class Builder extends Configuration.Builder<Builder> {
        private final List<AbstractActor> actors = new ArrayList<>();
        private final List<AbstractSensor> sensors = new ArrayList<>();

        private double wheelDiameter;
        private double trackWidth;

        /**
         * Add actor to the {@link Configuration}
         *
         * @param port on which the component is connected
         * @param actor we want to connect
         * @return
         */
        @Override
        public Builder addActor(IActorPort port, Actor actor) {
            this.actors.add(new NxtMotorActor(port.getOraName(), port.getCodeName(), actor.isRegulated(), actor.getRotationDirection(), actor.getMotorSide()));
            return this;
        }

        /**
         * Client must provide list of {@link Pair} ({@link ActorPort} and {@link NxtMotorActor})
         *
         * @param actors we want to connect to the brick configuration
         * @return
         */
        @Override
        public Builder addActors(List<Pair<IActorPort, Actor>> actors) {

            for ( Pair<IActorPort, Actor> pair : actors ) {
                IActorPort port = pair.getFirst();
                Actor actor = pair.getSecond();
                addActor(port, actor);
            }
            return this;
        }

        /**
         * Add sensor to the {@link Configuration}
         *
         * @param port on which the component is connected
         * @param component we want to connect
         * @return
         */

        @Override
        public Builder addSensor(ISensorPort port, Sensor sensor) {
            this.sensors.add(new NxtSensor(port.getOraName(), port.getCodeName(), BlocklyConstants.NO_SLOT));
            return this;
        }

        /**
         * Client must provide list of {@link Pair} ({@link SensorPort} and {@link Sensor})
         *
         * @param sensors we want to connect to the brick configuration
         * @return
         */
        @Override
        public Builder addSensors(List<Pair<ISensorPort, Sensor>> sensors) {
            for ( Pair<ISensorPort, Sensor> pair : sensors ) {
                ISensorPort port = pair.getFirst();
                Sensor sensor = pair.getSecond();
                addSensor(port, sensor);
            }
            return this;
        }

        /**
         * Set the wheel diameter
         *
         * @param wheelDiameter in cm
         * @return
         */
        @Override
        public Builder setWheelDiameter(double wheelDiameter) {
            this.wheelDiameter = wheelDiameter;
            return this;
        }

        /**
         * Set the track width
         *
         * @param trackWidth in cm
         * @return
         */
        @Override
        public Builder setTrackWidth(double trackWidth) {
            this.trackWidth = trackWidth;
            return this;
        }

        @Override
        public Configuration build() {
            return new NxtConfiguration(this.actors, this.sensors, (float) this.wheelDiameter, (float) this.trackWidth);
        }

        @Override
        public String toString() {
            return "Builder [actors="
                + this.actors
                + ", sensors="
                + this.sensors
                + ", wheelDiameter="
                + this.wheelDiameter
                + ", trackWidth="
                + this.trackWidth
                + "]";
        }

    }

}

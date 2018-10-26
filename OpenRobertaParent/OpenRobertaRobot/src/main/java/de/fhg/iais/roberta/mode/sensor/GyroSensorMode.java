package de.fhg.iais.roberta.mode.sensor;

import de.fhg.iais.roberta.inter.mode.sensor.IGyroSensorMode;

public enum GyroSensorMode implements IGyroSensorMode {
    DEFAULT, RATE( "Rate" ), ANGLE( "Angle" ), TILTED( "Tilted" ), RESET( "resetGyroSensor" ), X( "X" ), Y( "Y" ), Z( "Z" ), STRENGTH( "Strength" ), VALUE;

    private final String[] values;

    private GyroSensorMode(String... values) {
        this.values = values;
    }

    @Override
    public String[] getValues() {
        return this.values;
    }

}
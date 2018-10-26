package de.fhg.iais.roberta.mode.sensor;

import de.fhg.iais.roberta.inter.mode.sensor.IEncoderSensorMode;

public enum EncoderSensorMode implements IEncoderSensorMode {
    DEFAULT, ROTATION(), DEGREE(), RESET(), DISTANCE();

    private final String[] values;

    private EncoderSensorMode(String... values) {
        this.values = values;
    }

    @Override
    public String[] getValues() {
        return this.values;
    }

}
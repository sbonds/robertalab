package de.fhg.iais.roberta.mode.sensor;

import de.fhg.iais.roberta.inter.mode.general.IMode;

public enum AccelerometerSensorMode implements IMode {
    DEFAULT(), VALUE();

    private final String[] values;

    private AccelerometerSensorMode(String... values) {
        this.values = values;
    }

    @Override
    public String[] getValues() {
        return this.values;
    }
}

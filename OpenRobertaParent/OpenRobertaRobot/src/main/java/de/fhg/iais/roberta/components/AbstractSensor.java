package de.fhg.iais.roberta.components;

import de.fhg.iais.roberta.util.dbc.Assert;

public abstract class AbstractSensor {
    private final String userDefinedName;
    private final String portName;
    private final String slotName;

    public AbstractSensor(String userDefinedName, String portName, String slotName) {
        Assert.isTrue(userDefinedName != null && portName != null && slotName != null);
        Assert.nonEmptyString(userDefinedName);
        Assert.nonEmptyString(portName);
        Assert.nonEmptyString(slotName);
        this.userDefinedName = userDefinedName;
        this.portName = portName;
        this.slotName = slotName;
    }

    public String getUserDefinedName() {
        return this.userDefinedName;
    }

    public String getPortName() {
        return this.portName;
    }

    public String getSlotName() {
        return this.slotName;
    }
}

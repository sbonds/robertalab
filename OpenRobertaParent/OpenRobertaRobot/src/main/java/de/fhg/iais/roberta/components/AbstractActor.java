package de.fhg.iais.roberta.components;

import de.fhg.iais.roberta.util.dbc.Assert;

public abstract class AbstractActor {
    private final String userDefinedName;
    private final String portName;

    public AbstractActor(String userDefinedName, String portName) {
        Assert.isTrue(userDefinedName != null && portName != null);
        Assert.nonEmptyString(userDefinedName);
        Assert.nonEmptyString(portName);
        this.userDefinedName = userDefinedName;
        this.portName = portName;
    }

    public String getUserDefinedName() {
        return this.userDefinedName;
    }

    public String getPortName() {
        return this.portName;
    }
}

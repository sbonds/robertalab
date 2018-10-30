package de.fhg.iais.roberta.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.fhg.iais.roberta.util.dbc.Assert;

public final class ConfigurationComponent {
    private final String blocklyName;
    private final boolean isActor;
    private final String userDefinedName;
    private final String portName;
    private final String slotName;
    private final Map<String, String> componentProperties;

    public ConfigurationComponent(
        String blocklyName,
        boolean isActor,
        String portName,
        String slotName,
        String userDefinedName,
        Map<String, String> componentProperties) {
        this.blocklyName = blocklyName;
        this.isActor = isActor;
        this.portName = portName;
        this.slotName = slotName;
        this.userDefinedName = userDefinedName;
        this.componentProperties = Collections.unmodifiableMap(new HashMap<>(componentProperties));
    }

    public String getBlocklyName() {
        return this.blocklyName;
    }

    public boolean isActor() {
        return this.isActor;
    }

    public boolean isSensor() {
        return !this.isActor;
    }

    public String getPortName() {
        return this.portName;
    }

    public String getSlotName() {
        return this.slotName;
    }

    public String getUserDefinedName() {
        return this.userDefinedName;
    }

    public Map<String, String> getComponentProperties() {
        return this.componentProperties;
    }

    public String getProperty(String propertyName) {
        Assert.nonEmptyString(propertyName, "No valid property name %s", propertyName);
        String propertyValue = this.componentProperties.get(propertyName);
        Assert.notNull(propertyValue, "No property with name %s", propertyName);

        return propertyValue;
    }

    @Override
    public String toString() {
        return "ConfigurationComponent ["
            + "isActor="
            + this.isActor
            + ", userDefinedName="
            + this.userDefinedName
            + ", portName="
            + this.portName
            + ", slotName="
            + this.slotName
            + ", componentProperties="
            + this.componentProperties
            + "]";
    }

}

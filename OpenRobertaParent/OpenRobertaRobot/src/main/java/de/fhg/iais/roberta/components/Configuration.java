package de.fhg.iais.roberta.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhg.iais.roberta.util.dbc.Assert;

/**
 * This class represents model of the hardware configuration of a robot (assume we have "left" and "right" motor). It is used in the code generation. <br>
 * <br>
 * The {@link Configuration} contains four sensor ports and four actor ports. Client cannot connect more than that.
 */
public class Configuration {
    protected final Map<String, ConfigurationComponent> configurationComponents;

    private String robotName;
    private final float wheelDiameterCM;
    private final float trackWidthCM;

    public Configuration(Collection<ConfigurationComponent> configurationComponents, float wheelDiameterCM, float trackWidthCM) {
        this.configurationComponents = buildConfigurationComponentMap(configurationComponents);
        this.wheelDiameterCM = wheelDiameterCM;
        this.trackWidthCM = trackWidthCM;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public Collection<ConfigurationComponent> getConfigurationComponents() {
        return this.configurationComponents.values();
    }

    public ConfigurationComponent getConfigurationComponent(String userDefinedName) {
        ConfigurationComponent configurationComponent = this.configurationComponents.get(userDefinedName);
        Assert.notNull(configurationComponent, "co configuration component for user defined name " + userDefinedName);
        return configurationComponent;
    }

    public ConfigurationComponent optConfigurationComponent(String userDefinedName) {
        ConfigurationComponent configurationComponent = this.configurationComponents.get(userDefinedName);
        return configurationComponent;
    }

    public float getNxtWheelDiameterCM() {
        return this.wheelDiameterCM;
    }

    public float getNxtTrackWidthCM() {
        return this.trackWidthCM;
    }

    public String getFirstMotorPort(String side) {
        return getFirstMotor(side).getPortName();
    }

    public ConfigurationComponent getFirstMotor(String side) {
        List<ConfigurationComponent> found = getMotors(side);
        if ( found.size() == 0 ) {
            return null;
        } else {
            return found.get(0);
        }
    }

    public List<ConfigurationComponent> getMotors(String side) {
        List<ConfigurationComponent> found = new ArrayList<>();
        for ( ConfigurationComponent component : this.configurationComponents.values() ) {
            if ( component.getProperty("MOTOR_DRIVE").equals(side) ) {
                found.add(component);
            }
        }
        return found;
    }

    private Map<String, ConfigurationComponent> buildConfigurationComponentMap(Collection<ConfigurationComponent> configurationComponents) {
        Map<String, ConfigurationComponent> map = new HashMap<>();
        for ( ConfigurationComponent configurationComponent : configurationComponents ) {
            map.put(configurationComponent.getUserDefinedPortName(), configurationComponent);
        }
        return map;
    }

    /**
     * This class is a builder of {@link Configuration}
     */
    public static class Builder {
        private List<ConfigurationComponent> configurationComponents;

        private float wheelDiameter;
        private float trackWidth;

        /**
         * Client must provide list of hardware components ({@link ConfigurationComponent})
         *
         * @param sensors we want to connect to the brick configuration
         * @return
         */
        public Builder addComponents(List<ConfigurationComponent> components) {
            this.configurationComponents = components;
            return this;
        }

        /**
         * Set the wheel diameter
         *
         * @param wheelDiameter in cm
         * @return
         */
        public Builder setWheelDiameter(float wheelDiameter) {
            this.wheelDiameter = wheelDiameter;
            return this;
        }

        /**
         * Set the track width
         *
         * @param trackWidth in cm
         * @return
         */

        public Builder setTrackWidth(float trackWidth) {
            this.trackWidth = trackWidth;
            return this;
        }

        public Configuration build() {
            return new Configuration(this.configurationComponents, this.wheelDiameter, this.trackWidth);
        }
    }
}

package de.fhg.iais.roberta.components;

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
    private final Map<String, ConfigurationComponent> configurationComponents;

    private final float wheelDiameterCM;
    private final float trackWidthCM;

    public Configuration(List<ConfigurationComponent> configurationComponents, float wheelDiameterCM, float trackWidthCM) {
        this.configurationComponents = buildConfigurationComponentMap(configurationComponents);
        this.wheelDiameterCM = wheelDiameterCM;
        this.trackWidthCM = trackWidthCM;
    }

    private Map<String, ConfigurationComponent> buildConfigurationComponentMap(List<ConfigurationComponent> configurationComponents) {
        Map<String, ConfigurationComponent> map = new HashMap<>();
        for ( ConfigurationComponent configurationComponent : configurationComponents ) {
            map.put(configurationComponent.getUserDefinedName(), configurationComponent);
        }
        return map;
    }

    public float getNxtWheelDiameterCM() {
        return this.wheelDiameterCM;
    }

    public float getNxtTrackWidthCM() {
        return this.trackWidthCM;
    }

    public ConfigurationComponent getLeftMotor() {
        return getMotor("LEFT");
    }

    public ConfigurationComponent getRightMotor() {
        return getMotor("RIGHT");
    }

    private ConfigurationComponent getMotor(String side) {
        ConfigurationComponent foundCc = null;
        for ( ConfigurationComponent component : this.configurationComponents.values() ) {
            if ( component.getProperty("MOTOR_DRIVE").equals(side) ) {
                Assert.isNull(foundCc, "More than one component found for %s motor", side);
                foundCc = component;
            }
        }
        Assert.notNull(foundCc, "No motor for side %s", side);
        return foundCc;
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

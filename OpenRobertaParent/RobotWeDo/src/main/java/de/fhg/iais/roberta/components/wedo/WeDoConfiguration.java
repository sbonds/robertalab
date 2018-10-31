package de.fhg.iais.roberta.components.wedo;

import java.util.Collection;

import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ConfigurationComponent;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class WeDoConfiguration extends Configuration {
    public WeDoConfiguration(Collection<ConfigurationComponent> configurationComponents) {
        super(configurationComponents, 0.0f, 0.0f);
    }

    /**
     * @return text which defines the brick configuration
     */
    public String generateText(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("board WeDo ").append(name).append(" {\n");
        generateConfigurationBlocks(sb);
        sb.append("}");
        return sb.toString();
    }

    private void generateConfigurationBlocks(StringBuilder sb) {
        Collection<ConfigurationComponent> components = this.configurationComponents.values();
        if ( components.size() > 1 ) {
            sb.append(" configuration blocks {\n");
            for ( ConfigurationComponent component : components ) {
                sb.append("    ").append(component.getBlocklyName()).append(", ").append("Name: ").append(component.getUserDefinedPortName());
                sb.append(", port: ").append(component.getPortName()).append(", slot: ").append(component.getSlotName()).append(";\n");
            }
            sb.append("  }\n");
        }
    }

    @Override
    public String toString() {
        return "WeDoConfiguration [configurationComponents=" + configurationComponents + "]";
    }

    public ConfigurationComponent getConfigurationForPort(String port) {
        Collection<ConfigurationComponent> components = this.configurationComponents.values();
        for ( ConfigurationComponent component : components ) {
            if ( component.getPortName().equals(port) ) {
                return component;
            }
        }
        throw new DbcException("Port " + port + " not found");
    }

}

package de.fhg.iais.roberta.transformer.wedo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.blockly.generated.Instance;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ConfigurationComponent;
import de.fhg.iais.roberta.components.wedo.WeDoConfiguration;
import de.fhg.iais.roberta.factory.IRobotFactory;

/**
 * JAXB to brick configuration. Client should provide a tree of jaxb objects. Generates a BrickConfiguration object.
 */
public class Jaxb2WeDoConfigurationTransformer {
    IRobotFactory factory;

    public Jaxb2WeDoConfigurationTransformer(IRobotFactory factory) {
        this.factory = factory;
    }

    public WeDoConfiguration transform(BlockSet blockSet) {
        List<Instance> instances = blockSet.getInstance();
        List<List<Block>> blocks = new ArrayList<>();
        for ( int i = 0; i < instances.size(); i++ ) {
            blocks.add(instances.get(i).getBlock());
        }
        return blockToBrickConfiguration(blocks);
    }

    private WeDoConfiguration blockToBrickConfiguration(List<List<Block>> blocks) {
        List<ConfigurationComponent> allComponents = new ArrayList<>();
        for ( List<Block> block : blocks ) {
            allComponents.add(extractConfigurationBlockComponents(block));
        }
        return new Configuration.Builder().setTrackWidth(0.0f).setWheelDiameter(0.0f).addComponents(allComponents).build(WeDoConfiguration.class);
    }

    private ConfigurationComponent extractConfigurationBlockComponents(List<Block> block) {
        String userDefinedName = block.get(0).getField().get(0).getValue();
        Map<String, String> m = new HashMap<>();
        for ( int i = 1; i < block.get(0).getField().size(); i++ ) {
            m.put(block.get(0).getField().get(i).getName(), block.get(0).getField().get(i).getValue());
        }
        return new ConfigurationComponent("b", true, "?1", "?2", userDefinedName, m);
    }
}

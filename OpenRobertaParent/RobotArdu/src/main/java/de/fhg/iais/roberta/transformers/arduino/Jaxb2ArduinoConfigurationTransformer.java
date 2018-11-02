package de.fhg.iais.roberta.transformers.arduino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.blockly.generated.Instance;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ConfigurationBlockType;
import de.fhg.iais.roberta.components.arduino.ArduinoConfiguration;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;

/**
 * JAXB to brick configuration. Client should provide a tree of jaxb objects. Generates a BrickConfiguration object.
 */
public class Jaxb2ArduinoConfigurationTransformer {
    BlocklyDropdownFactory factory;

    public Jaxb2ArduinoConfigurationTransformer(BlocklyDropdownFactory factory) {
        this.factory = factory;
    }

    public Configuration transform(BlockSet blockSet) {
        List<Instance> instances = blockSet.getInstance();
        List<List<Block>> blocks = new ArrayList<>();
        for ( int i = 0; i < instances.size(); i++ ) {
            blocks.add(instances.get(i).getBlock());
        }
        return blockToBrickConfiguration(blocks);
    }

    private Configuration blockToBrickConfiguration(List<List<Block>> blocks) {
        Map<String, ConfigurationBlock> configurationBlocks = new HashMap<String, ConfigurationBlock>();
        for ( int i = 1; i < blocks.size(); i++ ) {
            configurationBlocks.put(blocks.get(i).get(0).getField().get(0).getValue(), extractConfigurationBlockComponents(blocks.get(i)));
        }
        return new ArduinoConfiguration(configurationBlocks);
    }

    private ConfigurationBlock extractConfigurationBlockComponents(List<Block> block) {
        ConfigurationBlockType confType = ConfigurationBlockType.get(block.get(0).getType());
        String name = block.get(0).getField().get(0).getValue();
        Map<String, String> confPorts = new HashMap<>();
        for ( int i = 1; i < block.get(0).getField().size(); i++ ) {
            confPorts.put(block.get(0).getField().get(i).getName(), block.get(0).getField().get(i).getValue());
        }
        return new ConfigurationBlock(confType, name, confPorts);
    }
}

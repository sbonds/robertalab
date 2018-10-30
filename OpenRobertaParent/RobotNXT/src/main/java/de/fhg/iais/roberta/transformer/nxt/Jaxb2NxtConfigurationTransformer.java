package de.fhg.iais.roberta.transformer.nxt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.blockly.generated.Instance;
import de.fhg.iais.roberta.blockly.generated.Value;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ConfigurationComponent;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;
import de.fhg.iais.roberta.factory.IRobotFactory;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.util.dbc.Assert;
import de.fhg.iais.roberta.util.dbc.DbcException;

/**
 * JAXB to brick configuration. Client should provide a tree of jaxb objects. Generates a BrickConfiguration object.
 */
public class Jaxb2NxtConfigurationTransformer {
    BlocklyDropdownFactory factory;

    public Jaxb2NxtConfigurationTransformer(IRobotFactory factory) {
        this.factory = factory.getBlocklyDropdownFactory();
    }

    public Configuration transform(BlockSet blockSet) {
        List<Instance> instances = blockSet.getInstance();
        List<Block> blocks = instances.get(0).getBlock();
        return blockToBrickConfiguration(blocks.get(0));
    }

    private Configuration blockToBrickConfiguration(Block block) {
        switch ( block.getType() ) {
            case "robBrick_EV3-Brick":
                List<Field> fields = extractFields(block, (short) 2);
                float wheelDiameter = Float.valueOf(extractField(fields, "WHEEL_DIAMETER", (short) 0)).floatValue();
                float trackWidth = Float.valueOf(extractField(fields, "TRACK_WIDTH", (short) 1)).floatValue();

                List<Value> values = extractValues(block, (short) 7);
                List<ConfigurationComponent> allComponents = extractHardwareComponent(values);

                return new Configuration.Builder().setTrackWidth(trackWidth).setWheelDiameter(wheelDiameter).addComponents(allComponents).build();
            default:
                throw new DbcException("There was no correct configuration block found!");
        }
    }

    private List<ConfigurationComponent> extractHardwareComponent(List<Value> values) {
        List<ConfigurationComponent> allComponents = new ArrayList<>();
        for ( Value value : values ) {
            String portName = value.getName();
            String userDefinedName = portName;
            boolean isActor = !portName.startsWith("S");
            String blocklyName = value.getBlock().getType();
            List<Field> fields = extractFields(value.getBlock(), (short) 3);
            Map<String, String> properties = new HashMap<>();
            for ( Field field : fields ) {
                String fKey = field.getName();
                String fValue = field.getValue();
                properties.put(fKey, fValue);
            }
            ConfigurationComponent cc = new ConfigurationComponent(blocklyName, isActor, portName, BlocklyConstants.NO_SLOT, userDefinedName, properties);
            allComponents.add(cc);
        }
        return allComponents;
    }

    private List<Value> extractValues(Block block, int numOfValues) {
        List<Value> values;
        values = block.getValue();
        Assert.isTrue(values.size() <= numOfValues, "Values size is not less or equal to " + numOfValues + "!");
        return values;
    }

    private List<Field> extractFields(Block block, int numOfFields) {
        List<Field> fields;
        fields = block.getField();
        Assert.isTrue(fields.size() == numOfFields, "Number of fields is not equal to " + numOfFields + "!");
        return fields;
    }

    private String extractField(List<Field> fields, String name, int fieldLocation) {
        Field field = fields.get(fieldLocation);
        Assert.isTrue(field.getName().equals(name), "Field name is not equal to " + name + "!");
        return field.getValue();
    }
}

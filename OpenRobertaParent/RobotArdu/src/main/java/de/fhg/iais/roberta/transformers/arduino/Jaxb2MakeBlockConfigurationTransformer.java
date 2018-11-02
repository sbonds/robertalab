package de.fhg.iais.roberta.transformers.arduino;

import java.util.ArrayList;
import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.blockly.generated.Instance;
import de.fhg.iais.roberta.blockly.generated.Value;
import de.fhg.iais.roberta.components.Actor;
import de.fhg.iais.roberta.components.ActorType;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.SensorType;
import de.fhg.iais.roberta.components.arduino.MbotConfiguration;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;
import de.fhg.iais.roberta.inter.mode.action.IActorPort;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.syntax.sensor.Sensor;
import de.fhg.iais.roberta.util.Pair;
import de.fhg.iais.roberta.util.dbc.Assert;
import de.fhg.iais.roberta.util.dbc.DbcException;

/**
 * JAXB to brick configuration. Client should provide a tree of jaxb objects. Generates a BrickConfiguration object.
 */
public class Jaxb2MakeBlockConfigurationTransformer {
    BlocklyDropdownFactory factory;

    public Jaxb2MakeBlockConfigurationTransformer(BlocklyDropdownFactory factory) {
        this.factory = factory;
    }

    public Configuration transform(BlockSet blockSet) {
        List<Instance> instances = blockSet.getInstance();
        List<Block> blocks = instances.get(0).getBlock();
        return blockToBrickConfiguration(blocks.get(0));
    }

    private Configuration blockToBrickConfiguration(Block block) {
        switch ( block.getType() ) {
            case "robBrick_makeBlock-Brick":
                List<Pair<ISensorPort, Sensor>> sensors = new ArrayList<>();
                List<Pair<IActorPort, Actor>> actors = new ArrayList<>();

                List<Value> values = extractValues(block, (short) 14);
                extractHardwareComponent(values, sensors, actors);

                return new MbotConfiguration.Builder().addActors(actors).addSensors(sensors).build();
            default:
                throw new DbcException("There was no correct configuration block found! " + block.getType());
        }
    }

    private void extractHardwareComponent(List<Value> values, List<Pair<ISensorPort, Sensor>> sensors, List<Pair<IActorPort, Actor>> actors) {
        List<Field> fields;
        for ( Value value : values ) {
            if ( value.getName().startsWith("P") ) {
                try {
                    // Extract sensor/actor on port
                    sensors.add(Pair.of(this.factory.sanitizePort(value.getName()), new Sensor(SensorType.get(value.getBlock().getType()))));
                } catch ( DbcException e ) {
                    System.out.println(e);
                    switch ( value.getBlock().getType() ) {
                        case "robBrick_led":
                            actors
                                .add(
                                    Pair
                                        .of(
                                            this.factory.sanitizePort(value.getName()),
                                            new Actor(ActorType.get(value.getBlock().getType()), false, DriveDirection.FOREWARD, null)));
                            break;
                        case "robBrick_led_matrix":
                            actors
                                .add(
                                    Pair
                                        .of(
                                            this.factory.sanitizePort(value.getName()),
                                            new Actor(ActorType.get(value.getBlock().getType()), false, DriveDirection.FOREWARD, null)));
                            break;
                        default:
                            throw new DbcException("Invalide actor type!" + value.getBlock().getType());
                    }
                }
            } else {
                // Extract actor
                switch ( value.getBlock().getType() ) {
                    case "robBrick_motor_geared":
                        fields = extractFields(value.getBlock(), (short) 1);
                        actors
                            .add(
                                Pair
                                    .of(
                                        this.factory.sanitizePort(value.getName()),
                                        new Actor(
                                            ActorType.get(value.getBlock().getType()),
                                            false,
                                            DriveDirection.FOREWARD,
                                            this.factory.getMotorSide(extractField(fields, "MOTOR_DRIVE", 0)))));
                        break;
                    default:
                        throw new DbcException("Invalide motor type!" + value.getBlock().getType());
                }
            }
        }
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

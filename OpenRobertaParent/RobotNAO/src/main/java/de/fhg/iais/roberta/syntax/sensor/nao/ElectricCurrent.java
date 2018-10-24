package de.fhg.iais.roberta.syntax.sensor.nao;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.syntax.BlockTypeContainer;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.sensor.ExternalSensor;
import de.fhg.iais.roberta.syntax.sensor.SensorMetaDataBean;
import de.fhg.iais.roberta.transformer.Jaxb2AstTransformer;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.hardware.INaoVisitor;

/**
 * This class represents the <b>naoActions_walk</b> block from Blockly into the AST (abstract syntax tree). Object from this class will generate code for making
 * the robot walk for a distance.<br/>
 * <br/>
 * The client must provide the {@link joint} and {@link degrees} (direction and distance to walk).
 */
public final class ElectricCurrent<V> extends ExternalSensor<V> {

    private ElectricCurrent(SensorMetaDataBean sensorMetaDataBean, BlocklyBlockProperties properties, BlocklyComment comment) {
        super(sensorMetaDataBean, BlockTypeContainer.getByName("ELECTRIC_CURRENT"), properties, comment);
        setReadOnly();
    }

    /**
     * Creates instance of {@link ElectricCurrent}. This instance is read only and can not be modified.
     *
     * @param joint {@link joint} the sensors data will be read from,
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment added from the user,
     * @return read only object of class {@link ElectricCurrent}
     */
    public static <V> ElectricCurrent<V> make(SensorMetaDataBean sensorMetaDataBean, BlocklyBlockProperties properties, BlocklyComment comment) {
        return new ElectricCurrent<V>(sensorMetaDataBean, properties, comment);
    }

    @Override
    protected V accept(IVisitor<V> visitor) {
        return ((INaoVisitor<V>) visitor).visitElectricCurrent(this);
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, Jaxb2AstTransformer<V> helper) {
        SensorMetaDataBean sensorData = extractSensorPortAndMode(block, helper, helper.getDropdownFactory()::getPlaceholderSensorMode);
        return ElectricCurrent.make(sensorData, helper.extractBlockProperties(block), helper.extractComment(block));
    }

}

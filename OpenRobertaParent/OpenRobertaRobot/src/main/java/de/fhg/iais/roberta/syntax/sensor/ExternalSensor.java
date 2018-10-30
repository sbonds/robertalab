package de.fhg.iais.roberta.syntax.sensor;

import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.blockly.generated.Field;
import de.fhg.iais.roberta.blockly.generated.Mutation;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;
import de.fhg.iais.roberta.inter.mode.general.IMode;
import de.fhg.iais.roberta.syntax.BlockType;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.transformer.Jaxb2AstTransformer;
import de.fhg.iais.roberta.transformer.JaxbTransformerHelper;
import de.fhg.iais.roberta.util.dbc.Assert;

public abstract class ExternalSensor<V> extends Sensor<V> {
    private final String port;
    private final String mode;
    private final String slot;
    private final boolean isPortInMutation;

    /**
     * This constructor set the kind of the sensor object used in the AST (abstract syntax tree). All possible kinds can be found in {@link BlockType}.
     *
     * @param kind of the the sensor object used in AST,
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment of the user for the specific block
     */
    public ExternalSensor(SensorMetaDataBean metaDataBean, BlockType kind, BlocklyBlockProperties properties, BlocklyComment comment) {
        super(kind, properties, comment);
        Assert.notNull(metaDataBean.getMode());
        Assert.notNull(metaDataBean.getPort());
        Assert.notNull(metaDataBean.getSlot());
        this.port = metaDataBean.getPort();
        this.mode = metaDataBean.getMode();
        this.slot = metaDataBean.getSlot();
        this.isPortInMutation = metaDataBean.isPortInMutation();
    }

    /**
     * @return get the port on which the sensor is connected. See enum {@link SensorPort} for all possible sensor ports
     */
    public String getPort() {
        return this.port;
    }

    /**
     * @return get the mode of sensor. See enum {@link IMode} for all possible modes that the sensor have
     */
    public String getMode() {
        return this.mode;
    }

    public String getSlot() {
        return this.slot;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + getPort() + ", " + getMode() + ", " + getSlot() + "]";
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> SensorMetaDataBean extractPortAndMode(Block block, Jaxb2AstTransformer<V> helper) {
        return extractPortAndModeAndSlot(block, helper);
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> SensorMetaDataBean extractPortAndModeAndSlot(Block block, Jaxb2AstTransformer<V> helper) {
        List<Field> fields = helper.extractFields(block, (short) 3);
        BlocklyDropdownFactory factory = helper.getDropdownFactory();
        String portName = helper.extractField(fields, BlocklyConstants.SENSORPORT, BlocklyConstants.NO_PORT);
        String modeName = helper.extractField(fields, BlocklyConstants.MODE, BlocklyConstants.DEFAULT);
        String slotName = helper.extractField(fields, BlocklyConstants.SLOT, BlocklyConstants.NO_SLOT);
        boolean isPortInMutation = (block.getMutation() != null) && (block.getMutation().getPort() != null);

        return new SensorMetaDataBean(factory.getSensorPort(portName), factory.getSensorMode(modeName), factory.getSlot(slotName), isPortInMutation);
    }

    public static <V> SensorMetaDataBean extractSensorPortAndMode(Block block, Jaxb2AstTransformer<V> helper) {
        return extractPortAndMode(block, helper);
    }

    public static <V> SensorMetaDataBean extractActorPortAndMode(Block block, Jaxb2AstTransformer<V> helper) {
        return extractPortAndMode(block, helper);
    }

    @Override
    public Block astToBlock() {
        Block jaxbDestination = new Block();
        JaxbTransformerHelper.setBasicProperties(this, jaxbDestination);
        boolean addMutation = false;
        Mutation mutation = new Mutation();
        if ( !this.mode.toString().equals(BlocklyConstants.DEFAULT) ) {
            mutation.setMode(getMode().toString());
            addMutation = true;
            JaxbTransformerHelper.addField(jaxbDestination, BlocklyConstants.MODE, getMode().toString());
            if ( this.isPortInMutation ) {
                mutation.setPort(getPort().toString());
            }
        }
        if ( !this.getPort().toString().equals(BlocklyConstants.NO_PORT) ) {
            String fieldValue = getPort();
            JaxbTransformerHelper.addField(jaxbDestination, BlocklyConstants.SENSORPORT, fieldValue);
        }
        if ( !this.getSlot().toString().equals(BlocklyConstants.NO_SLOT) ) {
            String fieldValue = getSlot();
            JaxbTransformerHelper.addField(jaxbDestination, BlocklyConstants.SLOT, fieldValue);
        }
        if ( addMutation ) {
            jaxbDestination.setMutation(mutation);
        }
        return jaxbDestination;
    }
}

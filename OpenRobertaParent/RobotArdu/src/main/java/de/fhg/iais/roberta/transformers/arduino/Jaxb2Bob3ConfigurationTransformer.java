package de.fhg.iais.roberta.transformers.arduino;

import de.fhg.iais.roberta.blockly.generated.BlockSet;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.arduino.Bob3Configuration;
import de.fhg.iais.roberta.factory.BlocklyDropdownFactory;

/**
 * JAXB to brick configuration. Client should provide a tree of jaxb objects. Generates a BrickConfiguration object.
 */
public class Jaxb2Bob3ConfigurationTransformer {

    public Jaxb2Bob3ConfigurationTransformer(BlocklyDropdownFactory factory) {
    }

    public Configuration transform(BlockSet blockSet) {
        return new Bob3Configuration();
    }
}

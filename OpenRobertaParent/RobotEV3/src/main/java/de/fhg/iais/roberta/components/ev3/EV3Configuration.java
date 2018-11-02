package de.fhg.iais.roberta.components.ev3;

import java.util.Collection;

import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ConfigurationComponent;

public class EV3Configuration extends Configuration {

    public EV3Configuration(Collection<ConfigurationComponent> configurationComponents, float wheelDiameterCM, float trackWidthCM) {
        super(configurationComponents, wheelDiameterCM, trackWidthCM);
    }
}

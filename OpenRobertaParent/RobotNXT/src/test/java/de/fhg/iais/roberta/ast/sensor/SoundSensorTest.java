package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.sensor.generic.SoundSensor;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.nxt.HelperNxtForXmlTest;

public class SoundSensorTest {
    private final HelperNxtForXmlTest h = new HelperNxtForXmlTest();

    @Test
    public void sensorSound() throws Exception {
        String a = "BlockAST [project=[[Location [x=137, y=338], SoundSensor [2, DEFAULT, NO_SLOT]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_Sound.xml"));
    }

    @Test
    public void getPort() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_Sound.xml");

        SoundSensor<Void> cs = (SoundSensor<Void>) transformer.getTree().get(0).get(1);

        Assert.assertEquals("2", cs.getPort());
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_Sound.xml");
    }
}

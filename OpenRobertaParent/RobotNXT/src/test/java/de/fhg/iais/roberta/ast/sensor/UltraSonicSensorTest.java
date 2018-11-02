package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.sensor.generic.UltrasonicSensor;
import de.fhg.iais.roberta.transformer.Jaxb2BlocklyProgramTransformer;
import de.fhg.iais.roberta.util.test.nxt.HelperNxtForXmlTest;

public class UltraSonicSensorTest {
    private final HelperNxtForXmlTest h = new HelperNxtForXmlTest();

    @Test
    public void sensorSetUltrasonic() throws Exception {
        String a =
            "BlockAST [project=[[Location [x=1, y=57], UltrasonicSensor [4, DISTANCE, NO_SLOT]], [Location [x=1, y=98], UltrasonicSensor [2, PRESENCE, NO_SLOT]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_setUltrasonic.xml"));
    }

    @Test
    public void getMode() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_setUltrasonic.xml");

        UltrasonicSensor<Void> cs = (UltrasonicSensor<Void>) transformer.getTree().get(0).get(1);
        UltrasonicSensor<Void> cs1 = (UltrasonicSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals(SC.DISTANCE, cs.getMode());
        Assert.assertEquals(SC.PRESENCE, cs1.getMode());
    }

    @Test
    public void getPort() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_setUltrasonic.xml");

        UltrasonicSensor<Void> cs = (UltrasonicSensor<Void>) transformer.getTree().get(0).get(1);
        UltrasonicSensor<Void> cs1 = (UltrasonicSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals("4", cs.getPort());
        Assert.assertEquals("2", cs1.getPort());
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_setUltrasonic.xml");
    }

}

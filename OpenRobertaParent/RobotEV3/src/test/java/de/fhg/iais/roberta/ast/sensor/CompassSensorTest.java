package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.sensor.generic.CompassSensor;
import de.fhg.iais.roberta.transformer.Jaxb2BlocklyProgramTransformer;
import de.fhg.iais.roberta.util.test.ev3.HelperEv3ForXmlTest;

public class CompassSensorTest {
    private final HelperEv3ForXmlTest h = new HelperEv3ForXmlTest();

    @Test
    public void sensorGetCompass() throws Exception {
        String a =
            "BlockAST [project=[[Location [x=384, y=50], CompassSensor [2, ANGLE, NO_SLOT]], "
                + "[Location [x=384, y=100], CompassSensor [4, COMPASS, NO_SLOT]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_getCompass.xml"));
    }

    @Test
    public void getMode() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_getCompass.xml");

        CompassSensor<Void> cs = (CompassSensor<Void>) transformer.getTree().get(0).get(1);
        CompassSensor<Void> cs1 = (CompassSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals(SC.ANGLE, cs.getMode());
        Assert.assertEquals(SC.COMPASS, cs1.getMode());
    }

    @Test
    public void getPort() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_getCompass.xml");

        CompassSensor<Void> cs = (CompassSensor<Void>) transformer.getTree().get(0).get(1);
        CompassSensor<Void> cs1 = (CompassSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals("2", cs.getPort());
        Assert.assertEquals("4", cs1.getPort());
    }

    @Test
    public void sensorCalibrateCompass() throws Exception {
        String a = "BlockAST [project=[[Location [x=384, y=50], CompassSensor [1, CALIBRATE, NO_SLOT]]]]";
        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_calibrateCompass.xml"));
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_getCompass.xml");
    }

    @Test
    public void reverseTransformation2() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_calibrateCompass.xml");
    }

}

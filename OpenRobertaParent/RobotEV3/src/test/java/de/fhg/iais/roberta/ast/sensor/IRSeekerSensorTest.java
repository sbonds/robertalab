package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.sensor.generic.IRSeekerSensor;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.ev3.HelperEv3ForXmlTest;

public class IRSeekerSensorTest {
    private final HelperEv3ForXmlTest h = new HelperEv3ForXmlTest();

    @Test
    public void sensorSetIRSeeker() throws Exception {
        String a =
            "BlockAST [project=[[Location [x=258, y=138], IRSeekerSensor [1, MODULATED, NO_SLOT]], "
                + "[Location [x=262, y=196], IRSeekerSensor [1, UNMODULATED, NO_SLOT]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_getIRSeeker.xml"));
    }

    @Test
    public void getMode() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_getIRSeeker.xml");

        IRSeekerSensor<Void> cs = (IRSeekerSensor<Void>) transformer.getTree().get(0).get(1);
        IRSeekerSensor<Void> cs1 = (IRSeekerSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals(SC.MODULATED, cs.getMode());
        Assert.assertEquals(SC.UNMODULATED, cs1.getMode());
    }

    @Test
    public void getPort() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_getIRSeeker.xml");

        IRSeekerSensor<Void> cs = (IRSeekerSensor<Void>) transformer.getTree().get(0).get(1);
        IRSeekerSensor<Void> cs1 = (IRSeekerSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals("1", cs.getPort());
        Assert.assertEquals("1", cs1.getPort());
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_getIRSeeker.xml");
    }
}

package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.sensor.generic.LightSensor;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.nxt.HelperNxtForXmlTest;

public class LightSensorTest {
    private final HelperNxtForXmlTest h = new HelperNxtForXmlTest();

    @Test
    public void sensorSetLight() throws Exception {
        final String a =
            "BlockAST [project=[[Location [x=162, y=238], LightSensor [3, LIGHT, NO_SLOT]], "
                + "[Location [x=163, y=263], LightSensor [4, AMBIENTLIGHT, NO_SLOT]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_setLight.xml"));
    }

    @Test
    public void getMode() throws Exception {
        final Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_setLight.xml");

        final LightSensor<Void> cs = (LightSensor<Void>) transformer.getTree().get(0).get(1);

        Assert.assertEquals(SC.LIGHT, cs.getMode());

    }

    @Test
    public void getPort() throws Exception {
        final Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_setLight.xml");

        final LightSensor<Void> cs = (LightSensor<Void>) transformer.getTree().get(0).get(1);
        final LightSensor<Void> cs1 = (LightSensor<Void>) transformer.getTree().get(1).get(1);

        Assert.assertEquals("3", cs.getPort());
        Assert.assertEquals("4", cs1.getPort());
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_setLight.xml");
    }
}

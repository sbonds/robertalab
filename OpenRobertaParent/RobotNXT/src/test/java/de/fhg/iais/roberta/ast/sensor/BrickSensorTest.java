package de.fhg.iais.roberta.ast.sensor;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.SC;
import de.fhg.iais.roberta.syntax.sensor.generic.KeysSensor;
import de.fhg.iais.roberta.transformer.Jaxb2BlocklyProgramTransformer;
import de.fhg.iais.roberta.util.test.nxt.HelperNxtForXmlTest;

public class BrickSensorTest {
    private final HelperNxtForXmlTest h = new HelperNxtForXmlTest();

    @Test
    public void main() throws Exception {
        String a = "BlockAST [project=[[Location [x=-19, y=1], KeysSensor [ENTER, PRESSED, NO_SLOT]]]]";
        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_brick1.xml"));
    }

    @Test
    public void getKey() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_brick1.xml");
        KeysSensor<Void> bs = (KeysSensor<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals("ENTER", bs.getPort());
    }

    @Test
    public void getMode() throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = this.h.generateTransformer("/ast/sensors/sensor_brick1.xml");
        KeysSensor<Void> bs = (KeysSensor<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(SC.PRESSED, bs.getMode());
    }

    @Test
    public void sensorBrick() throws Exception {
        String a =
            "BlockAST [project=[[Location [x=-96, y=73], \n"
                + "if SensorExpr [TouchSensor [1, DEFAULT, NO_SLOT]]\n"
                + ",then\n"
                + "Var [item] := SensorExpr [KeysSensor [ENTER, PRESSED, NO_SLOT]]\n\n"
                + "]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/sensors/sensor_brick.xml"));
    }

    @Test
    public void reverseTransformation() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_brick1.xml");
    }

    @Test
    public void reverseTransformation1() throws Exception {
        this.h.assertTransformationIsOk("/ast/sensors/sensor_brick.xml");
    }
}

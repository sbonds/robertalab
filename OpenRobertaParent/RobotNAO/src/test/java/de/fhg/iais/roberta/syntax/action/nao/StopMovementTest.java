package de.fhg.iais.roberta.syntax.action.nao;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.util.RobertaProperties;
import de.fhg.iais.roberta.util.Util1;
import de.fhg.iais.roberta.util.test.nao.HelperNaoForXmlTest;

public class StopMovementTest {
    private final HelperNaoForXmlTest h = new HelperNaoForXmlTest();

    @Test
    public void make_ByDefault_ReturnInstanceOfWalkClass() throws Exception {
        String expectedResult = "BlockAST [project=[[Location [x=138, y=88], " + "MainTask [], " + "Stop []]]]";

        String result = this.h.generateTransformerString("/action/stop.xml");

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void astToBlock_XMLtoJAXBtoASTtoXML_ReturnsSameXML() throws Exception {

        this.h.assertTransformationIsOk("/action/stop.xml");
    }
}
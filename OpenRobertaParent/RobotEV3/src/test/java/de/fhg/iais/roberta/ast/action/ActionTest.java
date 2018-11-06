package de.fhg.iais.roberta.ast.action;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.dbc.DbcException;
import de.fhg.iais.roberta.util.test.ev3.HelperEv3ForXmlTest;

public class ActionTest {
    private final HelperEv3ForXmlTest h = new HelperEv3ForXmlTest();

    @Test
    public void clearDisplay() throws Exception {
        String a = "BlockAST [project=[[Location [x=-69, y=10], MainTask [], ClearDisplayAction []]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/actions/action_ClearDisplay.xml"));
    }

    @Test
    public void reverseTransformatinclearDisplay() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_ClearDisplay.xml");
    }

    @Test
    public void reverseTransformatinclearDisplay1() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_ClearDisplay1.xml");
    }

    @Test
    public void stop() throws Exception {
        String a = "BlockAST [project=[[Location [x=1, y=135], StopAction []]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/actions/action_Stop.xml"));
    }

    @Test
    public void reverseTransformatinStop() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_Stop.xml");
    }

    @Test
    public void reverseTransformatinStop1() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_Stop1.xml");
    }

    @Test
    public void reverseTransformatinStop2() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_Stop2.xml");
    }

    @Test(expected = DbcException.class)
    public void blockException() throws Exception {
        this.h.assertTransformationIsOk("/ast/actions/action_Exception.xml");
    }

    @Test
    public void disabledComment() throws Exception {
        Jaxb2ProgramAst<Void> t = this.h.generateTransformer("/ast/actions/action_DisabledComment.xml");

        Assert.assertEquals(true, t.getTree().get(0).get(2).getProperty().isDisabled());
        Assert.assertEquals("h#,,", t.getTree().get(0).get(1).getComment().getComment());
    }
}

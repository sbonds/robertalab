package de.fhg.iais.roberta.ast.expr;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.lang.expr.Assoc;
import de.fhg.iais.roberta.syntax.lang.expr.BoolConst;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.GenericHelperForXmlTest;
import de.fhg.iais.roberta.util.test.AbstractHelperForXmlTest;

public class BoolConstTest {
    AbstractHelperForXmlTest h = new GenericHelperForXmlTest();

    @Test
    public void make() throws Exception {
        String a = "BlockAST [project=[[Location [x=1, y=171], BoolConst [true]]]]";

        Assert.assertEquals(a, this.h.generateTransformerString("/ast/logic/logic_boolConst.xml"));
    }

    @Test
    public void isValue() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/logic/logic_boolConst.xml");
        BoolConst<Void> boolConst = (BoolConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(true, boolConst.getValue());
    }

    @Test
    public void getPresedance() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/logic/logic_boolConst.xml");
        BoolConst<Void> boolConst = (BoolConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(999, boolConst.getPrecedence());
    }

    @Test
    public void getAssoc() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/logic/logic_boolConst.xml");
        BoolConst<Void> boolConst = (BoolConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(Assoc.NONE, boolConst.getAssoc());
    }

    @Test
    public void reverseTransformatin() throws Exception {
        this.h.assertTransformationIsOk("/ast/logic/logic_boolConst.xml");
    }

    @Test
    public void reverseTransformatin1() throws Exception {
        this.h.assertTransformationIsOk("/ast/logic/logic_boolConst1.xml");
    }

    @Test
    public void reverseTransformatin2() throws Exception {
        this.h.assertTransformationIsOk("/ast/logic/logic_boolConst2.xml");
    }
}

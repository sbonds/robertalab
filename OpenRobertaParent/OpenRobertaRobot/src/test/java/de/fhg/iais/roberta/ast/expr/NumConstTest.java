package de.fhg.iais.roberta.ast.expr;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.lang.expr.Assoc;
import de.fhg.iais.roberta.syntax.lang.expr.NumConst;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.GenericHelperForXmlTest;
import de.fhg.iais.roberta.util.test.AbstractHelperForXmlTest;

public class NumConstTest {
    AbstractHelperForXmlTest h = new GenericHelperForXmlTest();

    @Test
    public void make() throws Exception {
        String a = "BlockAST [project=[[Location [x=-15, y=-845], NumConst [0]]]]";
        Assert.assertEquals(a, this.h.generateTransformerString("/ast/math/math_num_constant.xml"));
    }

    @Test
    public void getValue() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/math/math_num_constant.xml");
        NumConst<Void> numConst = (NumConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals("0", numConst.getValue());
    }

    @Test
    public void getPresedance() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/math/math_num_constant.xml");
        NumConst<Void> numConst = (NumConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(999, numConst.getPrecedence());
    }

    @Test
    public void getAssoc() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/math/math_num_constant.xml");
        NumConst<Void> numConst = (NumConst<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(Assoc.NONE, numConst.getAssoc());
    }

    @Test
    public void reverseTransformatin() throws Exception {
        this.h.assertTransformationIsOk("/ast/math/math_num_constant.xml");
    }

    @Test
    public void reverseTransformatin1() throws Exception {
        this.h.assertTransformationIsOk("/ast/math/math_num_constant1.xml");
    }

    @Test
    public void reverseTransformatin2() throws Exception {
        this.h.assertTransformationIsOk("/ast/math/math_num_constant2.xml");
    }
}

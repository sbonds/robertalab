package de.fhg.iais.roberta.ast.expr;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import de.fhg.iais.roberta.syntax.lang.expr.Assoc;
import de.fhg.iais.roberta.syntax.lang.expr.Var;
import de.fhg.iais.roberta.transformer.Jaxb2ProgramAst;
import de.fhg.iais.roberta.util.test.GenericHelperForXmlTest;
import de.fhg.iais.roberta.util.test.AbstractHelperForXmlTest;

public class VariableTest {
    AbstractHelperForXmlTest h = new GenericHelperForXmlTest();

    @Test
    public void variableSet() throws Exception {
        String a = "BlockAST [project=[[Location [x=-23, y=-797], Var [item]]]]";
        Assert.assertEquals(a, this.h.generateTransformerString("/ast/variables/variable_set1.xml"));
    }

    @Test
    public void getValue() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/variables/variable_set1.xml");
        Var<Void> var = (Var<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals("item", var.getValue());
    }

    @Test
    public void getPresedance() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/variables/variable_set1.xml");
        Var<Void> var = (Var<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(999, var.getPrecedence());
    }

    @Test
    public void getAssoc() throws Exception {
        Jaxb2ProgramAst<Void> transformer = this.h.generateTransformer("/ast/variables/variable_set1.xml");
        Var<Void> var = (Var<Void>) transformer.getTree().get(0).get(1);
        Assert.assertEquals(Assoc.NONE, var.getAssoc());
    }

    @Ignore
    public void variableSet4() throws Exception {
        String a = "BlockAST [project=[[Location [x=-23, y=-797], Var [item]]]]";
        Assert.assertEquals(a, this.h.generateTransformerString("/ast/variables/variable_set4.xml"));
    }

    @Test
    public void reverseTransformatin() throws Exception {
        this.h.assertTransformationIsOk("/ast/variables/variable_set.xml");
    }

    @Test
    public void reverseTransformatin1() throws Exception {
        this.h.assertTransformationIsOk("/ast/variables/variable_set1.xml");
    }

    @Test
    public void reverseTransformatin2() throws Exception {
        this.h.assertTransformationIsOk("/ast/variables/variable_set2.xml");
    }

    @Test
    public void reverseTransformatin3() throws Exception {
        this.h.assertTransformationIsOk("/ast/variables/variable_set3.xml");
    }

}

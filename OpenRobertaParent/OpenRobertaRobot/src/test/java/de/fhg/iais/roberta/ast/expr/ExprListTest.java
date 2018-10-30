package de.fhg.iais.roberta.ast.expr;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.fhg.iais.roberta.factory.AbstractRobotFactory;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.lang.expr.ExprList;
import de.fhg.iais.roberta.syntax.lang.expr.NumConst;
import de.fhg.iais.roberta.util.Util1;

public class ExprListTest {

    @Before
    public void addBlockProperties() {
        Properties robotProperties = Util1.loadProperties("classpath:Robot.properties");
        AbstractRobotFactory.addBlockTypesFromProperties(robotProperties);
    }

    @Test
    public void make() throws Exception {
        NumConst<Void> numConst = NumConst.make("0", BlocklyBlockProperties.make("1", "1", false, false, false, false, false, true, false), null);
        ExprList<Void> exprList = ExprList.make();
        exprList.addExpr(numConst);

        String a = "NumConst [0]";

        Assert.assertEquals(a, exprList.toString());
    }

    @Test
    public void get() throws Exception {
        NumConst<Void> numConst = NumConst.make("0", BlocklyBlockProperties.make("1", "1", false, false, false, false, false, true, false), null);
        ExprList<Void> exprList = ExprList.make();
        exprList.addExpr(numConst);
        exprList.setReadOnly();
        String a = "[NumConst [0]]";

        Assert.assertEquals(a, exprList.get().toString());
    }

    @Test
    public void getPresedance() throws Exception {
        NumConst<Void> numConst = NumConst.make("0", BlocklyBlockProperties.make("1", "1", false, false, false, false, false, true, false), null);
        ExprList<Void> exprList = ExprList.make();
        exprList.addExpr(numConst);
        exprList.setReadOnly();
        try {
            exprList.getPrecedence();
        } catch ( Exception e ) {
            Assert.assertEquals("not supported", e.getMessage());
        }

    }

    @Test
    public void getAssoc() throws Exception {
        NumConst<Void> numConst = NumConst.make("0", BlocklyBlockProperties.make("1", "1", false, false, false, false, false, true, false), null);
        ExprList<Void> exprList = ExprList.make();
        exprList.addExpr(numConst);
        exprList.setReadOnly();
        try {
            exprList.getAssoc();
        } catch ( Exception e ) {
            Assert.assertEquals("not supported", e.getMessage());
        }

    }
}

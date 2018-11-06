package de.fhg.iais.roberta.syntax.lang.functions;

import java.util.ArrayList;
import java.util.List;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.syntax.BlockTypeContainer;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.BlocklyConstants;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.lang.expr.Assoc;
import de.fhg.iais.roberta.syntax.lang.expr.Expr;
import de.fhg.iais.roberta.transformer.ExprParam;
import de.fhg.iais.roberta.transformer.AbstractJaxb2Ast;
import de.fhg.iais.roberta.transformer.Ast2JaxbHelper;
import de.fhg.iais.roberta.typecheck.BlocklyType;
import de.fhg.iais.roberta.util.dbc.Assert;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.lang.ILanguageVisitor;

/**
 * This class represents the <b>math_trig</b>, <b>math_round</b> and <b>math_single</b> blocks from Blockly into the AST (abstract syntax tree).<br>
 * <br>
 * The user must provide name of the function and list of parameters. <br>
 * To create an instance from this class use the method {@link #make(FunctionNames, List, BlocklyBlockProperties, BlocklyComment)}.<br>
 * The enumeration {@link FunctionNames} contains all allowed functions.
 */
public class MathSingleFunct<V> extends Function<V> {
    private final FunctionNames functName;
    private final List<Expr<V>> param;

    private MathSingleFunct(FunctionNames name, List<Expr<V>> param, BlocklyBlockProperties properties, BlocklyComment comment) {
        super(BlockTypeContainer.getByName("MATH_SINGLE_FUNCT"), properties, comment);
        Assert.isTrue(name != null && param != null);
        this.functName = name;
        this.param = param;
        setReadOnly();
    }

    /**
     * Creates instance of {@link MathSingleFunct}. This instance is read only and can not be modified.
     *
     * @param name of the function; must be <b>not</b> null,
     * @param param list of parameters for the function; must be <b>not</b> null,,
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment that user has added to the block,
     * @return read only object of class {@link MathSingleFunct}
     */
    public static <V> MathSingleFunct<V> make(FunctionNames name, List<Expr<V>> param, BlocklyBlockProperties properties, BlocklyComment comment) {
        return new MathSingleFunct<V>(name, param, properties, comment);
    }

    /**
     * @return name of the function
     */
    public FunctionNames getFunctName() {
        return this.functName;
    }

    /**
     * @return list of parameters for the function
     */
    public List<Expr<V>> getParam() {
        return this.param;
    }

    @Override
    public int getPrecedence() {
        return this.functName.getPrecedence();
    }

    @Override
    public Assoc getAssoc() {
        return this.functName.getAssoc();
    }

    @Override
    public BlocklyType getReturnType() {
        return BlocklyType.NUMBER;
    }

    @Override
    protected V accept(IVisitor<V> visitor) {
        return ((ILanguageVisitor<V>) visitor).visitMathSingleFunct(this);
    }

    @Override
    public String toString() {
        return "MathSingleFunct [" + this.functName + ", " + this.param + "]";
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, AbstractJaxb2Ast<V> helper) {
        if ( helper.getOperation(block, BlocklyConstants.OP).equals(BlocklyConstants.NEG) ) {
            return helper.blockToUnaryExpr(block, new ExprParam(BlocklyConstants.NUM, BlocklyType.NUMBER_INT), BlocklyConstants.OP);
        }
        List<ExprParam> exprParams = new ArrayList<ExprParam>();
        exprParams.add(new ExprParam(BlocklyConstants.NUM, BlocklyType.NUMBER_INT));
        String op = helper.getOperation(block, BlocklyConstants.OP);
        List<Expr<V>> params = helper.extractExprParameters(block, exprParams);
        return MathSingleFunct.make(FunctionNames.get(op), params, helper.extractBlockProperties(block), helper.extractComment(block));
    }

    @Override
    public Block astToBlock() {
        Block jaxbDestination = new Block();
        Ast2JaxbHelper.setBasicProperties(this, jaxbDestination);

        Ast2JaxbHelper.addField(jaxbDestination, BlocklyConstants.OP, getFunctName().name());
        Ast2JaxbHelper.addValue(jaxbDestination, BlocklyConstants.NUM, getParam().get(0));
        return jaxbDestination;
    }

}

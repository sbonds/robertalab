package de.fhg.iais.roberta.syntax.action.vorwerk;

import de.fhg.iais.roberta.blockly.generated.Block;
import de.fhg.iais.roberta.syntax.BlockTypeContainer;
import de.fhg.iais.roberta.syntax.BlocklyBlockProperties;
import de.fhg.iais.roberta.syntax.BlocklyComment;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.Action;
import de.fhg.iais.roberta.syntax.lang.expr.Expr;
import de.fhg.iais.roberta.transformer.AbstractJaxb2Ast;
import de.fhg.iais.roberta.transformer.Ast2JaxbHelper;
import de.fhg.iais.roberta.visitor.IVisitor;
import de.fhg.iais.roberta.visitor.hardware.IVorwerkVisitor;

/**
 * This class represents the <b>vorwerkActions_vacuum_off</b> block from Blockly into the AST (abstract syntax tree). Object from this class will generate code
 * for applying a posture<br/>
 * <br/>
 * The client must provide the {@link Expr} speed.
 */
public final class VacuumOff<V> extends Action<V> {

    private VacuumOff(BlocklyBlockProperties properties, BlocklyComment comment) {
        super(BlockTypeContainer.getByName("VACUUM_OFF"), properties, comment);
        setReadOnly();
    }

    /**
     * Creates instance of {@link VacuumOff}. This instance is read only and can not be modified.
     *
     * @param speed {@link Expr} speed of the brush,
     * @param properties of the block (see {@link BlocklyBlockProperties}),
     * @param comment added from the user,
     * @return read only object of class {@link VacuumOff}
     */
    private static <V> VacuumOff<V> make(BlocklyBlockProperties properties, BlocklyComment comment) {
        return new VacuumOff<V>(properties, comment);
    }

    @Override
    public String toString() {
        return "VacuumOff []";
    }

    @Override
    protected V accept(IVisitor<V> visitor) {
        return ((IVorwerkVisitor<V>) visitor).visitVacuumOff(this);
    }

    /**
     * Transformation from JAXB object to corresponding AST object.
     *
     * @param block for transformation
     * @param helper class for making the transformation
     * @return corresponding AST object
     */
    public static <V> Phrase<V> jaxbToAst(Block block, AbstractJaxb2Ast<V> helper) {
        return VacuumOff.make(helper.extractBlockProperties(block), helper.extractComment(block));
    }

    @Override
    public Block astToBlock() {
        Block jaxbDestination = new Block();
        Ast2JaxbHelper.setBasicProperties(this, jaxbDestination);

        return jaxbDestination;
    }
}

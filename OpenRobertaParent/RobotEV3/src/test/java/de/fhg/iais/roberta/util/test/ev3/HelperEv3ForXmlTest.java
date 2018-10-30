package de.fhg.iais.roberta.util.test.ev3;

import java.util.Properties;

import org.junit.Assert;

import de.fhg.iais.roberta.components.Actor;
import de.fhg.iais.roberta.components.ActorType;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.ev3.EV3Configuration;
import de.fhg.iais.roberta.factory.AbstractRobotFactory;
import de.fhg.iais.roberta.factory.Ev3LejosV0Factory;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.mode.action.Language;
import de.fhg.iais.roberta.mode.action.MotorSide;
import de.fhg.iais.roberta.transformer.Jaxb2BlocklyProgramTransformer;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.Util1;
import de.fhg.iais.roberta.util.test.AbstractHelperForXmlTest;
import de.fhg.iais.roberta.visitor.codegen.Ev3JavaVisitor;
import de.fhg.iais.roberta.visitor.codegen.Ev3PythonVisitor;
import de.fhg.iais.roberta.visitor.codegen.Ev3SimVisitor;

public class HelperEv3ForXmlTest extends AbstractHelperForXmlTest {

    public HelperEv3ForXmlTest() {
        super(
            new Ev3LejosV0Factory(new PluginProperties("ev3lejosv0", "", "", Util1.loadProperties("classpath:ev3lejosv0.properties"))),
            new EV3Configuration.Builder()
                .addActor(new ActorPort("A", "MA"), new Actor(ActorType.LARGE, true, DriveDirection.FOREWARD, MotorSide.LEFT))
                .addActor(new ActorPort("B", "MB"), new Actor(ActorType.MEDIUM, true, DriveDirection.FOREWARD, MotorSide.RIGHT))
                .addActor(new ActorPort("C", "MC"), new Actor(ActorType.LARGE, false, DriveDirection.FOREWARD, MotorSide.LEFT))
                .addActor(new ActorPort("D", "MD"), new Actor(ActorType.MEDIUM, false, DriveDirection.FOREWARD, MotorSide.RIGHT))
                .build());
        Properties robotProperties = Util1.loadProperties("classpath:Robot.properties");
        AbstractRobotFactory.addBlockTypesFromProperties(robotProperties);
    }

    /**
     * Generate java code as string from a given program fragment. Do not prepend and append wrappings.
     *
     * @param pathToProgramXml path to a XML file, usable for {@link Class#getResourceAsStream(String)}
     * @return the code fragment as string
     * @throws Exception
     */
    private String generateStringWithoutWrapping(String pathToProgramXml) throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = generateTransformer(pathToProgramXml);
        String javaCode = Ev3JavaVisitor.generate("Test", (EV3Configuration) getRobotConfiguration(), transformer.getTree(), false, Language.ENGLISH);
        return javaCode;
    }

    /**
     * Assert that Java code generated from Blockly XML program is correct.<br>
     * All white space are ignored!
     *
     * @param correctJavaCode correct java code
     * @param fileName of the program we want to generate java code
     * @throws Exception
     */
    public void assertCodeIsOk(String correctJavaCode, String fileName) throws Exception {
        Assert.assertEquals(correctJavaCode.replaceAll("\\s+", ""), generateStringWithoutWrapping(fileName).replaceAll("\\s+", ""));
    }

    /**
     * Generate java code as string from a given program . Prepend and append wrappings.
     *
     * @param pathToProgramXml path to a XML file, usable for {@link Class#getResourceAsStream(String)}
     * @return the code as string
     * @throws Exception
     */
    public String generateJava(String pathToProgramXml, Configuration brickConfiguration) throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = generateTransformer(pathToProgramXml);
        String code = Ev3JavaVisitor.generate("Test", (EV3Configuration) brickConfiguration, transformer.getTree(), true, Language.ENGLISH);
        // System.out.println(code); // only needed for EXTREME debugging
        return code;
    }

    /**
     * this.robotConfiguration Generate python code as string from a given program . Prepend and append wrappings.
     *
     * @param pathToProgramXml path to a XML file, usable for {@link Class#getResourceAsStream(String)}
     * @return the code as string
     * @throws Exception
     */
    public String generatePython(String pathToProgramXml, Configuration brickConfiguration) throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = generateTransformer(pathToProgramXml);
        String code = Ev3PythonVisitor.generate((EV3Configuration) brickConfiguration, transformer.getTree(), true, Language.ENGLISH);
        // System.out.println(code); // only needed for EXTREME debugging
        return code;
    }

    /**
     * Generate java script code as string from a given program .
     *
     * @param pathToProgramXml path to a XML file, usable for {@link Class#getResourceAsStream(String)}
     * @return the code as string
     * @throws Exception
     */
    public String generateJavaScript(String pathToProgramXml) throws Exception {
        Jaxb2BlocklyProgramTransformer<Void> transformer = generateTransformer(pathToProgramXml);
        String code = Ev3SimVisitor.generate(getRobotConfiguration(), transformer.getTree(), Language.ENGLISH);
        // System.out.println(code); // only needed for EXTREME debugging
        return code;
    }

}

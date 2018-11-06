package de.fhg.iais.roberta.syntax.check.hardware.nao;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.util.test.nao.HelperNaoForXmlTest;
import de.fhg.iais.roberta.visitor.collect.NaoUsedHardwareCollectorVisitor;

public class UsedHardwareCollectorVisitorTest {
    HelperNaoForXmlTest h = new HelperNaoForXmlTest();

    private Configuration makeConfiguration() {
        return new Configuration.Builder().build();
    }

    @Test
    public void testLearnFace_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/learnface.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [null, NAOFACE, null]]", checkVisitor.getUsedSensors().toString());

    }

    @Test
    public void testForgetFace_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/forgetface.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [null, NAOFACE, null]]", checkVisitor.getUsedSensors().toString());

    }

    @Test
    public void testGetNaoMarkInfo_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/getnaomarkinfo.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [null, DETECT_MARK, null]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testDetectNaoMark_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/detectnaomark.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [null, DETECT_MARK, null]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testGetRecogniezdWordFromList_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/getrecognizedwordfromlist.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [null, NAOSPEECH, null]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testAllMoveBlocks_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/moveblocks.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testWalkDistance_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/walkdistance.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testTurnAction_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/turnaction.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testWalkToX_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/walktoXYTheta1.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testWalkToY_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/walktoXYTheta2.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }

    @Test
    public void testWalkToTheta_returnsListWithOneUsedSensor() throws Exception {
        ArrayList<ArrayList<Phrase<Void>>> phrases = this.h.generateASTs("/hardwarecheck/walktoXYTheta3.xml");

        NaoUsedHardwareCollectorVisitor checkVisitor = new NaoUsedHardwareCollectorVisitor(phrases, makeConfiguration());

        Assert.assertEquals("[UsedSensor [NO_PORT, ULTRASONIC, DISTANCE]]", checkVisitor.getUsedSensors().toString());
    }
}

package de.fhg.iais.roberta.util.test.ardu;

import java.util.Properties;

import de.fhg.iais.roberta.components.Actor;
import de.fhg.iais.roberta.components.ActorType;
import de.fhg.iais.roberta.components.arduino.MbotConfiguration;
import de.fhg.iais.roberta.factory.AbstractRobotFactory;
import de.fhg.iais.roberta.factory.MbotFactory;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.action.DriveDirection;
import de.fhg.iais.roberta.mode.action.MotorSide;
import de.fhg.iais.roberta.util.PluginProperties;
import de.fhg.iais.roberta.util.Util1;

public class HelperMBotForXmlTest extends de.fhg.iais.roberta.util.test.AbstractHelperForXmlTest {

    public HelperMBotForXmlTest() {
        super(
            new MbotFactory(new PluginProperties("mbot", "", "", Util1.loadProperties("classpath:mbot.properties"))),
            new MbotConfiguration.Builder()
                .addActor(new ActorPort("M2", "motor2"), new Actor(ActorType.GEARED_MOTOR, true, DriveDirection.FOREWARD, MotorSide.LEFT))
                .addActor(new ActorPort("M1", "motor1"), new Actor(ActorType.GEARED_MOTOR, false, DriveDirection.FOREWARD, MotorSide.RIGHT))
                .build());
        Properties robotProperties = Util1.loadProperties("classpath:Robot.properties");
        AbstractRobotFactory.addBlockTypesFromProperties(robotProperties);
    }
}

package de.fhg.iais.roberta.components;

import de.fhg.iais.roberta.inter.mode.action.IDriveDirection;
import de.fhg.iais.roberta.inter.mode.action.IMotorSide;
import de.fhg.iais.roberta.util.dbc.Assert;

public class NxtMotorActor extends AbstractActor {
    private final boolean regulated;
    private final IDriveDirection rotationDirection;
    private final IMotorSide motorSide;

    public NxtMotorActor(String userDefinedName, String portName, boolean regulated, IDriveDirection rotationDirection, IMotorSide motorSide) {
        super(userDefinedName, portName);
        Assert.isTrue(rotationDirection != null && motorSide != null);
        this.regulated = regulated;
        this.rotationDirection = rotationDirection;
        this.motorSide = motorSide;
    }

    /**
     * @return side on which the motor is connected
     */
    public IMotorSide getMotorSide() {
        return this.motorSide;
    }

    /**
     * @return rotation direction for the motor
     */
    public IDriveDirection getRotationDirection() {
        return this.rotationDirection;
    }

    /**
     * @return true if the motor is regulated
     */
    public boolean isRegulated() {
        return this.regulated;
    }
}

package de.fhg.iais.roberta.sound;

import org.junit.Test;

import de.fhg.iais.roberta.inter.mode.general.IMode;
import de.fhg.iais.roberta.mode.sensor.AccelerometerSensorMode;
import de.fhg.iais.roberta.mode.sensor.Axis;
import de.fhg.iais.roberta.mode.sensor.ColorSensorMode;
import de.fhg.iais.roberta.mode.sensor.CompassSensorMode;
import de.fhg.iais.roberta.mode.sensor.DropSensorMode;
import de.fhg.iais.roberta.mode.sensor.EncoderSensorMode;
import de.fhg.iais.roberta.mode.sensor.GestureSensorMode;
import de.fhg.iais.roberta.mode.sensor.GyroSensorMode;
import de.fhg.iais.roberta.mode.sensor.HumiditySensorMode;
import de.fhg.iais.roberta.mode.sensor.InfraredSensorMode;
import de.fhg.iais.roberta.mode.sensor.KeysSensorMode;
import de.fhg.iais.roberta.mode.sensor.LightSensorMode;
import de.fhg.iais.roberta.mode.sensor.MoistureSensorMode;
import de.fhg.iais.roberta.mode.sensor.MotionSensorMode;
import de.fhg.iais.roberta.mode.sensor.PinPull;
import de.fhg.iais.roberta.mode.sensor.PinValue;
import de.fhg.iais.roberta.mode.sensor.PulseSensorMode;
import de.fhg.iais.roberta.mode.sensor.RfidSensorMode;

import de.fhg.iais.roberta.mode.sensor.Slot;
import de.fhg.iais.roberta.mode.sensor.SoundSensorMode;
import de.fhg.iais.roberta.mode.sensor.TemperatureSensorMode;
import de.fhg.iais.roberta.mode.sensor.TimerSensorMode;
import de.fhg.iais.roberta.mode.sensor.TouchSensorMode;
import de.fhg.iais.roberta.mode.sensor.UltrasonicSensorMode;
import de.fhg.iais.roberta.mode.sensor.VoltageSensorMode;

public class GenTest {

    @Test
    public void make_ByDefault_ReturnInstanceOfPlayNoteActionClass() throws Exception {

        printEnumerationFromClass(AccelerometerSensorMode.class);
        printEnumerationFromClass(Axis.class);
        printEnumerationFromClass(ColorSensorMode.class);
        printEnumerationFromClass(CompassSensorMode.class);
        printEnumerationFromClass(DropSensorMode.class);
        printEnumerationFromClass(EncoderSensorMode.class);
        printEnumerationFromClass(GestureSensorMode.class);
        printEnumerationFromClass(GyroSensorMode.class);
        printEnumerationFromClass(HumiditySensorMode.class);
        printEnumerationFromClass(InfraredSensorMode.class);
        printEnumerationFromClass(KeysSensorMode.class);
        printEnumerationFromClass(LightSensorMode.class);
        printEnumerationFromClass(MoistureSensorMode.class);
        printEnumerationFromClass(MotionSensorMode.class);
        printEnumerationFromClass(PinPull.class);
        printEnumerationFromClass(PinValue.class);
        printEnumerationFromClass(PulseSensorMode.class);
        printEnumerationFromClass(RfidSensorMode.class);
        printEnumerationFromClass(SensorPort.class);
        printEnumerationFromClass(Slot.class);
        printEnumerationFromClass(SoundSensorMode.class);
        printEnumerationFromClass(TemperatureSensorMode.class);
        printEnumerationFromClass(TimerSensorMode.class);
        printEnumerationFromClass(TouchSensorMode.class);
        printEnumerationFromClass(UltrasonicSensorMode.class);
        printEnumerationFromClass(VoltageSensorMode.class);
    }

    private <E extends IMode> void printEnumerationFromClass(Class<E> clazz) {
        for ( E e : clazz.getEnumConstants() ) {
            System.out.println("robot.dropdown.mode." + clazz.getSimpleName().toLowerCase() + "." + e.toString() + " = " + e.toString());
        }
    }

}

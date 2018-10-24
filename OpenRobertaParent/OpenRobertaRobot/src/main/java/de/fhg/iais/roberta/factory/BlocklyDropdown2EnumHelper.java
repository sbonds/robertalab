package de.fhg.iais.roberta.factory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import de.fhg.iais.roberta.inter.mode.general.IMode;
import de.fhg.iais.roberta.mode.action.ActorPort;
import de.fhg.iais.roberta.mode.sensor.SensorPort;
import de.fhg.iais.roberta.util.dbc.DbcException;

public class BlocklyDropdown2EnumHelper {

    public static <E extends IMode> E getModeValue(String modeName, Class<E> modes) {
        if ( modeName == null ) {
            throw new DbcException("Invalid " + modes.getName() + ": " + modeName);
        }
        final String sUpper = modeName.trim().toUpperCase(Locale.GERMAN);
        for ( final E mode : modes.getEnumConstants() ) {
            if ( mode.toString().equals(sUpper) ) {
                return mode;
            }
            for ( final String value : mode.getValues() ) {
                if ( sUpper.equals(value.toUpperCase()) ) {
                    return mode;
                }
            }
        }
        throw new DbcException("Invalid " + modes.getName() + ": " + modeName);
    }

    public static Map<String, SensorPort> getSensorPortsFromProperties(Properties properties) {
        Map<String, SensorPort> sensorToPorts = new HashMap<>();
        String filter = "robot.port.sensor.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            if ( key.startsWith(filter) ) {
                key = key.substring(filter.length());
                SensorPort sensorPort = new SensorPort(key, value);
                sensorToPorts.put(key, sensorPort);
            }
        }
        return sensorToPorts;
    }

    public static Map<String, ActorPort> getActorPortsFromProperties(Properties properties) {
        Map<String, ActorPort> actorToPorts = new HashMap<>();
        String filter = "robot.port.actor.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            if ( key.startsWith(filter) ) {
                key = key.substring(filter.length());
                ActorPort actorPort = new ActorPort(key, value);
                actorToPorts.put(key, actorPort);
            }
        }
        return actorToPorts;
    }

    public static Map<String, String> getDropdownFromProperties(Properties properties) {
        Map<String, String> dropdownItems = new HashMap<>();
        String filter = "robot.dropdown.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            if ( key.startsWith(filter) ) {
                key = key.substring(filter.length());
                dropdownItems.put(value, key);
            }
        }
        return dropdownItems;
    }

}

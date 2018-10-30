package de.fhg.iais.roberta.factory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import de.fhg.iais.roberta.inter.mode.general.IMode;
import de.fhg.iais.roberta.util.DropDowns;
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

    public static Map<String, String> getSensorPortsFromProperties(Properties properties) {
        Map<String, String> sensorToPorts = new HashMap<>();
        String filter = "robot.port.sensor.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            if ( key.startsWith(filter) ) {
                key = key.substring(filter.length());
                sensorToPorts.put(key, value);
            }
        }
        return sensorToPorts;
    }

    public static Map<String, String> getActorPortsFromProperties(Properties properties) {
        Map<String, String> actorToPorts = new HashMap<>();
        String filter = "robot.port.actor.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            if ( key.startsWith(filter) ) {
                String value = (String) e.getValue();
                key = key.substring(filter.length());
                actorToPorts.put(key, value);
            }
        }
        return actorToPorts;
    }

    public static Map<String, String> getSlotFromProperties(Properties properties) {
        Map<String, String> m = new HashMap<>();
        String filter = "robot.port.slot.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            if ( key.startsWith(filter) ) {
                String value = (String) e.getValue();
                key = key.substring(filter.length());
                m.put(key, value);
            }
        }
        return m;
    }

    public static DropDowns getDropdownFromProperties(Properties properties) {
        DropDowns dropdownItems = new DropDowns();
        String filter = "robot.dropdown.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String propertyKey = (String) e.getKey();
            if ( propertyKey.startsWith(filter) ) {
                String value = (String) e.getValue();
                String[] typeAndKey = propertyKey.substring(filter.length()).split("\\.");
                String dropdownType = typeAndKey[0];
                String key = typeAndKey[1];
                dropdownItems.add(dropdownType, key, value);
            }
        }
        return dropdownItems;
    }

    public static Map<String, String> getSensorModesFromProperties(Properties properties) {
        Map<String, String> m = new HashMap<>();
        String filter = "robot.dropdown.mode.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String propertyKey = (String) e.getKey();
            if ( propertyKey.startsWith(filter) ) {
                String value = (String) e.getValue();
                String[] typeAndKey = propertyKey.substring(filter.length()).split("\\.");
                String key = typeAndKey[1];
                m.put(key, value);
            }
        }
        return m;
    }

    public static Map<String, WaitUntilSensorBean> getWaitUntilFromProperties(Properties properties) {
        Map<String, WaitUntilSensorBean> map = new HashMap<>();
        String filter = "robot.wait_until.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String key = (String) e.getKey();
            if ( key.startsWith(filter) ) {
                key = key.substring(filter.length());
                String value = (String) e.getValue();
                String[] vs = value.split("\\s*,\\s*");
                map.put(key, new WaitUntilSensorBean(vs[0], vs[1], vs[2]));
            }
        }
        return map;
    }

    public static Map<String, String> getConfigurationComponentTypesFromProperties(Properties properties) {
        Map<String, String> configurationComponentTypes = new HashMap<>();
        String filter = "robot.configuration.";
        for ( Entry<Object, Object> e : properties.entrySet() ) {
            String propertyKey = (String) e.getKey();
            if ( propertyKey.startsWith(filter) ) {
                String value = (String) e.getValue();
                String key = propertyKey.substring(filter.length());
                configurationComponentTypes.put(key, value);
            }
        }
        return configurationComponentTypes;
    }

}

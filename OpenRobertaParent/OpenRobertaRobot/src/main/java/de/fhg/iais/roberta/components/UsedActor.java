package de.fhg.iais.roberta.components;

/**
 * Stores information for port and mode of used actor in a blockly program. This information is used for program validation.
 *
 * @author ensonic
 */

public class UsedActor {
    private final String port;

    public UsedActor(String port) {
        this.port = port;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return this.port;
    }

    @Override
    public String toString() {
        return "UsedActor [" + this.port + "]";
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        try {
            result = prime * result + this.port.hashCode();
        } catch ( NullPointerException e ) {
            result = 31;
        }
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        UsedActor other = (UsedActor) obj;
        if ( this.port == null ) {
            if ( other.port != null ) {
                return false;
            }
        } else if ( !this.port.equals(other.port) ) {
            return false;
        }
        return true;
    }
}

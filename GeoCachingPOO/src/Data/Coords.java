package Data;

import java.io.Serializable;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Coords implements Serializable {

    private float latitude;
    private float longitude;

    /**
     *
     * @param latitude
     * @param longitude
     */
    public Coords(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public Coords clone() {
        return new Coords(this.getLatitude(), this.getLongitude());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coords other = (Coords) obj;
        if (Float.floatToIntBits(this.latitude) != Float.floatToIntBits(other.getLatitude())) {
            return false;
        }
        if (Float.floatToIntBits(this.longitude) != Float.floatToIntBits(other.getLongitude())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Float.floatToIntBits(this.latitude);
        hash = 59 * hash + Float.floatToIntBits(this.longitude);
        return hash;
    }

    @Override
    public String toString() {
        return "Latitude: " + this.latitude + "\nLongitude: " + this.getLongitude();
    }

}

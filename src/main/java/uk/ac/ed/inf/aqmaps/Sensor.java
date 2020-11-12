package uk.ac.ed.inf.aqmaps;

/** Contains information about a sensor. */
public class Sensor {
  private final W3W location;
  private final float battery;
  private final String reading;

  public Sensor(W3W location, float battery, String reading) {
    this.location = location;
    this.battery = battery;
    this.reading = reading;
  }

  public W3W getLocation() {
    return location;
  }

  public float getBattery() {
    return battery;
  }

  @Override
  public String toString() {
    return "Sensor{"
        + "location="
        + location
        + ", battery="
        + battery
        + ", reading='"
        + reading
        + '\''
        + '}';
  }

  public String getReading() {
    return reading;
  }
}

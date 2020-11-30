package uk.ac.ed.inf.aqmaps.flightplanning;

import uk.ac.ed.inf.aqmaps.geometry.Coords;

import java.util.Objects;

/**
 * A class which holds data about the input values of the flight planning algorithm from a position
 * to a sensor, potentially with a next sensor. This is for use in the cache, so stores hashed
 * values directly in order to save memory. Note that the 3 fields could be combined into 1 in order
 * to save more memory, however with potentially hundreds of thousands of entries the chance that
 * two different inputs would have the same hash is too high.
 */
public class FlightCacheKeys {
  /** A hash of the start position. */
  private final int startPosition;
  /** A hash of the current target. */
  private final int currentTarget;
  /** If there is no target, hash is 0 */
  private final int nextTarget;

  /**
   * @param startPosition the start position of the drone.
   * @param currentTarget the current target
   * @param nextTarget the next target if there is one, or null otherwise
   */
  public FlightCacheKeys(Coords startPosition, Coords currentTarget, Coords nextTarget) {
    this.startPosition = startPosition.hashCode();
    this.currentTarget = currentTarget.hashCode();
    this.nextTarget = nextTarget == null ? 0 : nextTarget.hashCode();
  }

  /** equals() method, needed to work with a HashMap, automatically generated by IntelliJ. */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    FlightCacheKeys that = (FlightCacheKeys) o;
    return startPosition == that.startPosition
        && currentTarget == that.currentTarget
        && Objects.equals(nextTarget, that.nextTarget);
  }

  /** hashCode() method, needed to work with a HashMap, automatically generated by IntelliJ. */
  @Override
  public int hashCode() {
    return Objects.hash(startPosition, currentTarget, nextTarget);
  }
}

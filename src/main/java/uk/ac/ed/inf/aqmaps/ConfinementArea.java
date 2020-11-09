package uk.ac.ed.inf.aqmaps;

/** Holds information about the confinement area and checking whether a point is inside it. */
public class ConfinementArea {
  /** A Point representing the northwest corner of the confinement area. */
  private static final Coords TOP_LEFT = new Coords(-3.192473, 55.946233);
  /** A Point representing the southeast corner of the confinement area. */
  private static final Coords BOTTOM_RIGHT = new Coords(-3.184319, 55.942617);

  /**
   * Determines whether or not a point is inside the confinement area
   *
   * @param point the point to examine
   * @return true if the point is inside the confinement area, false otherwise
   */
  public static boolean isInConfinement(Coords point) {
    return TOP_LEFT.lng < point.lng
        && point.lng < BOTTOM_RIGHT.lng
        && BOTTOM_RIGHT.lat < point.lat
        && point.lat < TOP_LEFT.lat;
  }
}

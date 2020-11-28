package uk.ac.ed.inf.aqmaps;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ed.inf.aqmaps.geometry.Coords;
import uk.ac.ed.inf.aqmaps.io.ServerInputController;
import uk.ac.ed.inf.aqmaps.pathfinding.Obstacles;

import static org.junit.Assert.*;

public class ObstaclesTest {
  private Obstacles obstacles;

  @Before
  public void setup() {
    var testServer = ServerInputControllerTest.getFakeServer();
    var input = new ServerInputController(testServer, 1, 1, 2020, 80);
    obstacles = new Obstacles(input.getNoFlyZones());
  }

  @Test
  public void numberOfPointsCorrect() {
    var points = obstacles.getOutlinePoints();
    assertEquals("The obstacle polygons should have a total of 36 vertices", 32, points.size());
  }

  @Test
  public void meetsCornersLineNoCollision() {
    var start = obstacles.getOutlinePoints().get(0);
    var end = obstacles.getOutlinePoints().get(1);
    assertFalse(obstacles.lineCollision(start, end));
  }

  @Test
  public void middleOfNowhereLineNoCollision() {
    var line = TestPaths.MIDDLE_OF_NOWHERE;
    assertFalse(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void nearBuildingsLineNoCollision() {
    var line = TestPaths.NEAR_BUILDINGS;
    assertFalse(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void leavesConfinementLineCollides() {
    var line = TestPaths.LEAVES_CONFINEMENT;
    assertTrue(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void collidesWith1BuildingLineCollides() {
    var line = TestPaths.COLLIDES_1_BUILDING;
    assertTrue(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void trickyPathThroughBuildingLineCollides() {
    var line = TestPaths.TRICKY_PATH_THROUGH_BUILDINGS;
    assertTrue(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void collidesWith3BuildingsLineCollides() {
    var line = TestPaths.COLLIDES_3_BUILDINGS;
    assertTrue(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void shortestRouteLeavesConfinementLineCollides() {
    var line = TestPaths.SHORTEST_ROUTE_LEAVES_CONFINEMENT;
    assertTrue(obstacles.lineCollision(line.start, line.end));
  }

  @Test
  public void pointNotInObstacleNoCollision() {
    assertFalse(obstacles.pointCollision(TestPaths.MIDDLE_OF_NOWHERE.start));
  }

  @Test
  public void pointOutsideConfinementCollision() {
    assertTrue(obstacles.pointCollision(TestPaths.LEAVES_CONFINEMENT.end));
  }

  @Test
  public void pointInsideObstacleCollision() {
    assertTrue(obstacles.pointCollision(new Coords(-3.186743, 55.944321)));
  }
}

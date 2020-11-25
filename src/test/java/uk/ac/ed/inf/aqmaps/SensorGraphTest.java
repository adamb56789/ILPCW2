package uk.ac.ed.inf.aqmaps;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ed.inf.aqmaps.geometry.Coords;
import uk.ac.ed.inf.aqmaps.io.ServerInputController;
import uk.ac.ed.inf.aqmaps.pathfinding.ObstacleEvader;
import uk.ac.ed.inf.aqmaps.pathfinding.Obstacles;
import uk.ac.ed.inf.aqmaps.pathfinding.SensorGraph;

import static org.junit.Assert.assertEquals;

public class SensorGraphTest {
  SensorGraph sensorGraph;

  @Before
  public void setup() {
    var testServer = ServerInputControllerTest.getFakeServer();
    var input = new ServerInputController(testServer, 1, 1, 2020, 80);
    var obstacleGraph = new ObstacleEvader(new Obstacles(input.getNoFlyZones()));
    sensorGraph = new SensorGraph(input.getSensorLocations(), obstacleGraph, 0);
  }

  @Test
  public void tourLengthCorrect() {
    var testCoords = new Coords(-3.1878, 55.9444);
    assertEquals(34, sensorGraph.getTour(testCoords).size());
  }

  @Test
  public void tourStartingPositionCorrect() {
    var testCoords = new Coords(-3.1878, 55.9444);
    assertEquals(testCoords.x, sensorGraph.getTour(testCoords).get(0).get(0).x, 0.00000001);
  }
}

package life

import org.scalatest.Suite


class CoordinateTests extends Suite {
  def testCoordinateNeighbours {
    val coordinate = new Coordinate(5,3)
    
    assert(coordinate.x == 5)
  }
}

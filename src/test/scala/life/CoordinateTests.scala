package life

import org.scalatest.Suite

class CoordinateTests extends Suite {
  val coordinate = new Coordinate(3, 5)

  def testCoordsAreDecomposable() {
    assert(coordinate.x == 3)
    assert(coordinate.y == 5)
  }
  
  def testSimpleCoordinateNeighbours() {
    val neighbours = coordinate.getNeighbours(10,10)
    
    assert(neighbours.length == 8)
    
    val expectedNeighbours = Array((2, 4), (2, 5), (2, 6), (3, 4), (3, 6), (4, 4), (4, 5), (4, 6))
    
    for ((x, y) <- expectedNeighbours;
         n = new Coordinate(x, y)) {
      assert(neighbours.contains(n) )
    }
  }
}

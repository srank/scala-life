package life

class Coordinate(val x: Int, val y: Int) {
  require(x >= 0);
  require(y >= 0);
  
  override def toString = "(" + x + ", " + y + ")"
  
  override def hashCode = 11 + x*101 + y;

  override def equals(other: Any) = {
    other match {
      case that: Coordinate => this.x == that.x && this.y == that.y
      case _ => false
    }
  }
  
  def getNeighbours(width:Int, height:Int) = {
    for {xOffset <- -1 to 1
         yOffset <- -1 to 1
         if (xOffset != 0 || yOffset != 0)}
    yield Coordinate((x + xOffset + width) % width, (y + yOffset + width) % height)
  }
}

object Coordinate {
  def apply(x: Int, y: Int) = new Coordinate(x,y)
}

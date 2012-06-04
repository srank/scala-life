package life
import scala.collection.mutable.HashSet

object Life {

  def main(args: Array[String]): Unit = {
    val initialCells = new HashSet[Coordinate]
    initialCells.add(new Coordinate(5, 5))
    val g0 = new Generation(10, 10, initialCells)
    println(g0.toString())
    
    println(new Coordinate(0,0).getNeighbours(5, 5))
    println(new Coordinate(2,2).getNeighbours(5, 5))
  }

}

class Coordinate(val x: Int, val y: Int) {
  override def toString = {"(" + x + ", " + y + ")"}
  override def hashCode = 11 + x*101 + y;
  override def equals(other: Any) = {
    other match {
      case that: Coordinate => this.x == that.x && this.y == that.y
      case _ => false
    }
  }
  
  def getNeighbours(width:Int, height:Int) = {
    for (xOffset <- -1 to 1;
        yOffset <- -1 to 1 )
    yield new Coordinate((x + xOffset + width) % width, (y + yOffset + width) % height)
  }
}



class Generation(width: Int, height: Int, cells: HashSet[Coordinate]) {
  
  override def toString = {
    var output: String = ""
    for (x <- 0 to width - 1;
         y <- 0 to height - 1;
         coord = new Coordinate(x, y)) {
      if (cells.contains(coord)) {
        output += "*" 
      } else {
        output += "-"
      }
      if (y == width - 1) {
        output += "\n"
      }
    }
    output;
  }
  
  def nextGeneration(): Generation = {
    new Generation(width, height, cells)
  }
}
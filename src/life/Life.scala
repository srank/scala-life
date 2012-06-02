package life
import scala.collection.mutable.HashSet

object Life {

  def main(args: Array[String]): Unit = {
    val initialCells = new HashSet[Coordinate]
    initialCells.add(new Coordinate(5, 5))
    val g0 = new Generation(10, 10, initialCells)
    println(g0.toString())
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
}


class Generation(width: Int, height: Int, cells: HashSet[Coordinate]) {
  
  override def toString = {
    var output: String = ""
    for (x <- 0 to width;
         y <- 0 to height;
         coord = new Coordinate(x, y)) {
      if (cells.contains(coord)) {
        output += "*" 
      } else {
        output += "-"
      }
      if (y == width) {
        output += "\n"
      }
    }
    output;
  }
  
  def nextGeneration(): Generation = {
    new Generation(width, height, cells)
  }
}
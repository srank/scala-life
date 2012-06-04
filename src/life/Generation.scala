package life
import scala.collection.mutable.HashSet

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
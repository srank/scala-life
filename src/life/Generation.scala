package life
import scala.collection.mutable.HashSet

class Generation(width: Int, height: Int, cells: HashSet[Coordinate]) {
  
  override def toString = {
    var output: String = ""
    for (y <- 0 to height - 1;
         x <- 0 to width - 1;
         coord = new Coordinate(x, y)) {
      if (cells.contains(coord)) {
        output += "*" 
      } else {
        output += "-"
      }
      if (x >= width - 1) {
        output += "\n"
      }
    }
    output;
  }
  
  def cellsToConsider = {
    // there's probably a more idiomatic way of doing this
    var result = new HashSet[Coordinate]()
    for (cell <- cells)
      result = result ++ cell.getNeighbours(width, height)
    result
  }
  
  def nextGeneration(): Generation = {
    new Generation(width, height, cells)
  }
}
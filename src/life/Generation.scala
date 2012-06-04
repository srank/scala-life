package life
import scala.collection.mutable.HashSet
import scala.collection.Set

class Generation(width: Int, height: Int, cells: Set[Coordinate]) {
  
  override def toString = {
    // FIXME: this doesn't feel very functional in style
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
    // FIXME: there's probably a more idiomatic way of doing this
    var result = new HashSet[Coordinate]()
    for (cell <- cells)
      result = result ++ cell.getNeighbours(width, height)
    result
  }
  
  /* Rules:
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   */
  
  def cellIsAliveInNextGeneration(cell: Coordinate): Boolean = {
    val numberOfNeighbours = cell.getNeighbours(width, height).toIndexedSeq.intersect(cells.toIndexedSeq).length
    if (cells.contains(cell) && numberOfNeighbours < 2) {
      false
    } else if (cells.contains(cell) && (numberOfNeighbours == 2 || numberOfNeighbours == 3)){
      true
    } else if (cells.contains(cell) && numberOfNeighbours > 3) {
      false
    } else if (!cells.contains(cell) && numberOfNeighbours == 3){
      true
    } else {
      false
    }
  }
  def nextGeneration(): Generation = {
    new Generation(width, height, cells)
  }
}
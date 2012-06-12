package life
import scala.collection.mutable.HashSet
import scala.collection.Set

class Generation(width: Int, height: Int, cells: Set[Coordinate]) {
  val w = width
  val h = height
  val c = cells
  
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
  
  def canEqual(other: Any):Boolean = {
    other.isInstanceOf[Generation]
  }
  
  override def equals(other: Any):Boolean = {
    other match {
      case that: Generation => (that canEqual this) && 
          (this.width == that.w) && (this.height == that.h) &&
          (this.cells == that.c)
          
      case _ => false
    }
  }
  
  def cellsToConsider = {
    // FIXME: there's probably a more idiomatic way of doing this
    // maybe using flatten
    var result = new HashSet[Coordinate]()
    for (cell <- cells)
      result = result ++ cell.getNeighbours(width, height) + cell
    result
  }
  
  /* Rules:
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   */
  def cellIsAliveInNextGeneration(cell: Coordinate): Boolean = {
    // FIXME: this line looks too verbose to be correct...
    val numberOfNeighbours = cell.getNeighbours(width, height).toIndexedSeq.intersect(cells.toIndexedSeq).length
    val cellIsAlive  = cells.contains(cell)
    // FIXME: can we use pattern matching here?
    if (cellIsAlive && numberOfNeighbours < 2) {
      false
    } else if (cellIsAlive && (numberOfNeighbours == 2 || numberOfNeighbours == 3)){
      true
    } else if (cellIsAlive && numberOfNeighbours > 3) {
      false
    } else if (!cellIsAlive && numberOfNeighbours == 3){
      true
    } else {
      false
    }
  }
  
  def nextGenerationsLivingCells = {
      for (cell <- cellsToConsider;
      if cellIsAliveInNextGeneration(cell)) 
        yield cell
  }
  
  def nextGeneration(): Generation = {
    new Generation(width, height, nextGenerationsLivingCells)
  }
}
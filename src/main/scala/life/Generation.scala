package life
import scala.collection.mutable.HashSet
import scala.collection.Set

class Generation(width: Int, height: Int, cells: Set[Coordinate]) {
  private val w = width
  private val h = height
  private val c = cells
  
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
                               (this.width == that.w) && 
                               (this.height == that.h) &&
                               (this.cells == that.c)
      case _ => false
    }
  }
  
  def cellsToConsider = {
    val allNeighbours = for (cell <- cells)
      yield cell.getNeighbours(width, height)
      
    (allNeighbours + cells.toIndexedSeq).flatten
  }
  
  /* Rules:
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   */
  def cellIsAliveInNextGeneration(cell: Coordinate): Boolean = {
    // FIXME: this line looks too verbose to be correct...
    val numberOfNeighbours = cell.getNeighbours(width, height).intersect(cells.toIndexedSeq).length
    val cellIsAlive  = cells.contains(cell)
    
    (cellIsAlive, numberOfNeighbours) match {
      case (true, 2) => true
      case (_, 3) => true
      case _ => false
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
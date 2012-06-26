package life

class Generation(width: Int, height: Int, livingCells: Set[Coordinate]) {
  require(width > 0)
  require(height > 0)
  
  private val w = width
  private val h = height
  private val c = livingCells
  
  override lazy val toString = {
    // FIXME: this doesn't feel very functional in style
    var output: String = ""
    for {y <- 0 until height
         x <- 0 until width
         coord = Coordinate(x, y)} {
      if (livingCells(coord)) {
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
                               (this.livingCells == that.c)
      case _ => false
    }
  }
  
  lazy val cellsToConsider = {
    val allNeighbours = for (cell <- livingCells)
      yield cell.getNeighbours(width, height)
      
    (allNeighbours + livingCells.toIndexedSeq).flatten
  }
  
  /* Rules:
   * Any live cell with fewer than two live neighbours dies, as if caused by under-population.
   * Any live cell with two or three live neighbours lives on to the next generation.
   * Any live cell with more than three live neighbours dies, as if by overcrowding.
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   */
  def cellIsAliveInNextGeneration(cell: Coordinate): Boolean = {
    // FIXME: this line looks too verbose to be correct...
    val numberOfNeighbours = cell.getNeighbours(width, height).intersect(livingCells.toIndexedSeq).length
    val cellIsAlive = livingCells(cell)
    
    (cellIsAlive, numberOfNeighbours) match {
      case (true, 2) => true
      case (_, 3) => true
      case _ => false
    } 
  }
  
  lazy val nextGenerationsLivingCells = {
      for (cell <- cellsToConsider;
      if cellIsAliveInNextGeneration(cell)) 
        yield cell
  }
  
  lazy val nextGeneration: Generation = {
    new Generation(width, height, nextGenerationsLivingCells)
  }
  
  // FIXME: this method probably shouldn't exist
  def liveCells(x: Int, y: Int) = livingCells(Coordinate(x, y))
}
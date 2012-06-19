package life
import scala.collection.mutable.HashSet
import scala.collection.mutable.Set

object Life {

  def main(args: Array[String]): Unit = {
    val initialCells = Set[Coordinate]()
    initialCells.add(Coordinate(4, 5))
    initialCells.add(Coordinate(5, 5))
    initialCells.add(Coordinate(6, 5))
    
    initialCells.add(Coordinate(8, 0))
    initialCells.add(Coordinate(9, 0))
    initialCells.add(Coordinate(0, 0))
    
    val g0 = new Generation(10, 10, initialCells.toSet)
    println("Generation 0")
    println(g0.toString())
    
    println(Coordinate(0,0).getNeighbours(5, 5))
    println(Coordinate(2,2).getNeighbours(5, 5))
    
    println(g0.cellsToConsider);
    println(g0.nextGenerationsLivingCells)
    
    println("Generation 1")
    println(g0.nextGeneration)
    println("Generation 2")
    println(g0.nextGeneration.nextGeneration)
  }
}

package life

import org.scalatest.FunSuite
import scala.collection.mutable.HashSet
import scala.collection.Set

class GenerationTests extends FunSuite {
  test("cellsToConsider gets all neighbours of a cell") {

  }
  
  test("cellsToConsider includes all cells that are currently live") {
    
  }
  
  test("") {
    val initialCells = new HashSet[Coordinate] with Set[Coordinate]
    initialCells.add(new Coordinate(4, 5))
    initialCells.add(new Coordinate(5, 5))
    initialCells.add(new Coordinate(6, 5))
    
    initialCells.add(new Coordinate(8, 0))
    initialCells.add(new Coordinate(9, 0))
    initialCells.add(new Coordinate(0, 0))
    
    val g0 = new Generation(10, 10, initialCells)
    val g1 = g0.nextGeneration
    assert(g1 != g0)
    val g2 = g1.nextGeneration
    assert(g0 === g2)

  }
}
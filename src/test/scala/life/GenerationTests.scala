package life

import org.scalatest.FunSuite
import scala.collection.mutable.HashSet
import scala.collection.Set

class GenerationTests extends FunSuite {
  test("cellsToConsider gets all neighbours of a cell") {
    val cells = new HashSet[Coordinate] with Set[Coordinate]
    cells.add(new Coordinate(2,2))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 9)
    val expectedCells = Set(new Coordinate(1,1), new Coordinate(1,2), new Coordinate(1,3),
                            new Coordinate(2,1), new Coordinate(2,2), new Coordinate(2,3), 
                            new Coordinate(3,1), new Coordinate(3,2), new Coordinate(3,3))
    assert(expectedCells === cellsToConsider)
  }
  
  test("cellsToConsider includes neighbours of cells that are on the edge") {
    val cells = new HashSet[Coordinate] with Set[Coordinate]
    cells.add(new Coordinate(0,0))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 9)
    val expectedCells = Set(new Coordinate(7,7), new Coordinate(7,0), new Coordinate(7,1),
                            new Coordinate(0,7), new Coordinate(0,0), new Coordinate(0,1), 
                            new Coordinate(1,7), new Coordinate(1,0), new Coordinate(1,1))
    assert(expectedCells === cellsToConsider)


  }
  
  test("Grid with two blinkers has period two") {
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
    val g3 = g2.nextGeneration
    assert(g3 === g1)

  }
}
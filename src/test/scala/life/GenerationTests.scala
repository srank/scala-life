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
  
  test("cellsToConsider gets all neighbours of all living cells") {
    val cells = Set(new Coordinate(2,2), new Coordinate(5,5))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 18)
    val expectedCells = Set(new Coordinate(1,1), new Coordinate(1,2), new Coordinate(1,3),
                            new Coordinate(2,1), new Coordinate(2,2), new Coordinate(2,3), 
                            new Coordinate(3,1), new Coordinate(3,2), new Coordinate(3,3),
                            new Coordinate(4,4), new Coordinate(4,5), new Coordinate(4,6),
                            new Coordinate(5,4), new Coordinate(5,5), new Coordinate(5,6), 
                            new Coordinate(6,4), new Coordinate(6,5), new Coordinate(6,6))
    assert(expectedCells === cellsToConsider)
  }
  
  test("cellsToConsider gets all neighbours of all living cells and handles overlap") {
    val cells = Set(new Coordinate(2,2), new Coordinate(3,3))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 14)
    val expectedCells = Set(new Coordinate(1,1), new Coordinate(1,2), new Coordinate(1,3),
                            new Coordinate(2,1), new Coordinate(2,2), new Coordinate(2,3), 
                            new Coordinate(3,1), new Coordinate(3,2), new Coordinate(3,3),
                            /*new Coordinate(2,2), new Coordinate(2,3),*/ new Coordinate(2,4),
                            /*new Coordinate(3,2), new Coordinate(3,3),*/ new Coordinate(3,4), 
                            new Coordinate(4,2), new Coordinate(4,3), new Coordinate(4,4))
    assert(expectedCells === cellsToConsider)
  }
  
  test("Grid with two blinkers has period two") {
    var initialCells = Set[Coordinate]()
    initialCells += (new Coordinate(4, 5))
    initialCells += (new Coordinate(5, 5))
    initialCells += (new Coordinate(6, 5))
    
    initialCells += (new Coordinate(8, 0))
    initialCells += (new Coordinate(9, 0))
    initialCells += (new Coordinate(0, 0))
    
    val g0 = new Generation(10, 10, initialCells)
    val g1 = g0.nextGeneration
    assert(g1 != g0)
    val g2 = g1.nextGeneration
    assert(g0 === g2)
    val g3 = g2.nextGeneration
    assert(g3 === g1)
  }
  
  test("Blocks are solid and unmoving") {
    var initialCells = Set[Coordinate]()
    initialCells += (new Coordinate(4, 4))
    initialCells += (new Coordinate(4, 5))
    initialCells += (new Coordinate(5, 4))
    initialCells += (new Coordinate(5, 5))

    val g0 = new Generation(10, 10, initialCells)
    val g1 = g0.nextGeneration
    assert(g1 === g0)

    val g2 = g1.nextGeneration
    assert(g2 === g0)
  }
}
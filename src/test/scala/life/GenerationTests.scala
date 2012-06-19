package life

import org.scalatest.FunSuite

class GenerationTests extends FunSuite {
  test("Generations must be of positive dimensions") {
    intercept[IllegalArgumentException] {
      val badGeneration = new Generation(-1, 2, Set());
    }
    
    intercept[IllegalArgumentException] {
      val badGeneration = new Generation(0, 2, Set());
    }
    
    intercept[IllegalArgumentException] {
      val badGeneration = new Generation(1, 0, Set());
    }
    
    intercept[IllegalArgumentException] {
      val badGeneration = new Generation(2, -2, Set());
    }
  }
  
  test("cellsToConsider gets all neighbours of a cell") {
    val cells = Set(Coordinate(2,2))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 9)
    val expectedCells = Set(Coordinate(1,1), Coordinate(1,2), Coordinate(1,3),
                            Coordinate(2,1), Coordinate(2,2), Coordinate(2,3), 
                            Coordinate(3,1), Coordinate(3,2), Coordinate(3,3))
    assert(expectedCells === cellsToConsider)
  }
  
  test("cellsToConsider includes neighbours of cells that are on the edge") {
    val cells = Set(Coordinate(0,0))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 9)
    val expectedCells = Set(Coordinate(7,7), Coordinate(7,0), Coordinate(7,1),
                            Coordinate(0,7), Coordinate(0,0), Coordinate(0,1), 
                            Coordinate(1,7), Coordinate(1,0), Coordinate(1,1))
    assert(expectedCells === cellsToConsider)
  }
  
  test("cellsToConsider gets all neighbours of all living cells") {
    val cells = Set(Coordinate(2,2), Coordinate(5,5))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 18)
    val expectedCells = Set(Coordinate(1,1), Coordinate(1,2), Coordinate(1,3),
                            Coordinate(2,1), Coordinate(2,2), Coordinate(2,3), 
                            Coordinate(3,1), Coordinate(3,2), Coordinate(3,3),
                            Coordinate(4,4), Coordinate(4,5), Coordinate(4,6),
                            Coordinate(5,4), Coordinate(5,5), Coordinate(5,6), 
                            Coordinate(6,4), Coordinate(6,5), Coordinate(6,6))
    assert(expectedCells === cellsToConsider)
  }
  
  test("cellsToConsider gets all neighbours of all living cells and handles overlap") {
    val cells = Set(Coordinate(2,2), Coordinate(3,3))
    val generation = new Generation(8, 8, cells)
    val cellsToConsider = generation.cellsToConsider
    assert(cellsToConsider.size === 14)
    val expectedCells = Set(Coordinate(1,1), Coordinate(1,2), Coordinate(1,3),
                            Coordinate(2,1), Coordinate(2,2), Coordinate(2,3), 
                            Coordinate(3,1), Coordinate(3,2), Coordinate(3,3),
                            /*Coordinate(2,2), Coordinate(2,3),*/ Coordinate(2,4),
                            /*Coordinate(3,2), Coordinate(3,3),*/ Coordinate(3,4), 
                            Coordinate(4,2), Coordinate(4,3), Coordinate(4,4))
    assert(expectedCells === cellsToConsider)
  }
  
  test("Grid with two blinkers has period two") {
    var initialCells = Set[Coordinate]()
    initialCells += Coordinate(4, 5)
    initialCells += Coordinate(5, 5)
    initialCells += Coordinate(6, 5)
    
    initialCells += Coordinate(8, 0)
    initialCells += Coordinate(9, 0)
    initialCells += Coordinate(0, 0)
    
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
    initialCells += Coordinate(4, 4)
    initialCells += Coordinate(4, 5)
    initialCells += Coordinate(5, 4)
    initialCells += Coordinate(5, 5)

    val g0 = new Generation(10, 10, initialCells)
    val g1 = g0.nextGeneration
    assert(g1 === g0)

    val g2 = g1.nextGeneration
    assert(g2 === g0)
  }
}
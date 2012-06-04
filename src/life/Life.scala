package life
import scala.collection.mutable.HashSet

object Life {

  def main(args: Array[String]): Unit = {
    val initialCells = new HashSet[Coordinate]
    initialCells.add(new Coordinate(5, 5))
    val g0 = new Generation(10, 10, initialCells)
    println(g0.toString())
    
    println(new Coordinate(0,0).getNeighbours(5, 5))
    println(new Coordinate(2,2).getNeighbours(5, 5))
  }

}

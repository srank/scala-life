package life

import scala.swing._

object FirstSwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    val cells = Set(Coordinate(2,2))
    val g = new Generation(10, 10, cells)
    title = "Scala life"
    contents = new LifeGUI(10,10, g)
    
  }
}

class LifeGUI(val height: Int, val width: Int, val g: Generation) extends ScrollPane {
  val table = new Table(height, width) {
    rowHeight = 10
    
    autoResizeMode = Table.AutoResizeMode.Off
    showGrid = true
    gridColor = java.awt.Color.lightGray
    
    override def rendererComponent(isSelected: Boolean, focused: Boolean, row: Int, column: Int): Component = {
      if (g.liveCells(row, column)) {
        new Label("x")
      } else {
        new Label(" ")
      }
    }
  }
  
  viewportView = table
}
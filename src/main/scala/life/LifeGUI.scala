package life

import scala.swing._

object FirstSwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    val cells = Set(Coordinate(2,2), Coordinate(2,3), Coordinate(2,4))
        val g = new Generation(10, 10, cells)
    title = "Scala life"
    contents = new BoxPanel(Orientation.Vertical) {
      val gui = new LifeGUI(10,10, g)
      contents += gui
      val button = new Button {
        text = "next"
      }
      contents += button
    }
  }
}

class LifeGUI(val height: Int, val width: Int, val g: Generation) extends ScrollPane {
  val table = new Table(height, width) {
    rowHeight = 10
    
    autoResizeMode = Table.AutoResizeMode.Off
    showGrid = true
    gridColor = java.awt.Color.lightGray
    
    override def rendererComponent(isSelected: Boolean, focused: Boolean, row: Int, column: Int): Component = {
      // FIXME: this is too much coupling
      if (g.liveCells(row, column)) {
        new Label("x")
      } else {
        new Label(" ")
      }
    }
  }
  
  viewportView = table
}
package life

import scala.swing._
import scala.swing.event.ButtonClicked

object FirstSwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    val cells = Set(Coordinate(2,2), Coordinate(2,3), Coordinate(2,4))
    val g = new Generation(10, 10, cells)
    var generationNumber = 0
    title = "Scala life"
    val button = new Button {
      text = "next"
    }
    val label = new Label {
      text = generationNumber.toString()
    }
    listenTo(button)
    val gui = new LifeGUI(10,10, g)
    contents = new BoxPanel(Orientation.Vertical) {
      contents += button
      contents += label
      contents += gui
    }
    reactions += {
      case ButtonClicked(b) =>
        generationNumber += 1
        label.text = generationNumber.toString()
        gui.g = gui.g.nextGeneration
        gui.repaint()
    }
  }
}

class LifeGUI(val height: Int, val width: Int, var g: Generation) extends ScrollPane {
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
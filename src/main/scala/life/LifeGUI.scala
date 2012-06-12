package life

import scala.swing._

object FirstSwingApp extends SimpleGUIApplication {
  def top = new MainFrame {
    title = "Scala life"
        contents = new Button {
      text = "Click me"
    }
  }
}

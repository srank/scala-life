package life

import scala.swing._

object FirstSwingApp extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Scala life"
        contents = new Button {
      text = "Click me"
    }
  }
}

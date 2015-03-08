package main

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing._

/**
 * Created by kasonchan on 3/7/15.
 */
class GUI extends JFrame {

  def init: Unit = {

    //    Exit button
    val exitButton: JButton = new JButton("Exit");

    exitButton.addActionListener(new ActionListener() {
      override def actionPerformed(e: ActionEvent) {
        //    Exit program
        System.exit(0);
      }
    })

    //    Panel configurations
    setSize(300, 200)
    setTitle("First GUI")
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    def contentPane = getContentPane
    contentPane.add(exitButton)
  }
}

object GUI {

  def main(args: Array[String]) {
    val gui: GUI = new GUI

    gui.init
    gui.setVisible(true)
  }
}

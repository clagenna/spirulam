package prova;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PlotPoints extends JPanel {

  private static final long serialVersionUID = 6592840311496158527L;
  private Random            m_rnd;

  @Override
  protected void paintComponent(Graphics p_g) {
    super.paintComponent(p_g);
    m_rnd = new Random();
    Graphics2D graph = (Graphics2D) p_g;
    graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int width = getWidth();
    int heigt = getHeight();
    disegnaAssi(graph, width, heigt);
    graph.setColor(Color.black);
    for (int i = 0; i < 1800; i++)
      plotPoint(graph, width, heigt);
    plotCircle(graph, width, heigt);
  }

  private void plotCircle(Graphics2D p_graph, int p_width, int p_heigt) {
    final int qta = 200;
    double dpi = 2 * Math.PI / qta;
    double wih = p_width / 2;
    double heh = p_heigt / 2;
    p_graph.setColor(Color.red);
    for (int i = 0; i < qta; i++) {
      double ix = Math.sin(dpi * i) * (wih - 10);
      double iy = Math.cos(dpi * i) * (heh - 10);
      p_graph.fillRect((int) (wih + ix), (int) (heh - iy), 2, 2);
    }
  }

  private void plotPoint(Graphics2D p_graph, int p_width, int p_heigt) {
    int wih = p_width / 2;
    int heh = p_heigt / 2;
    int rndx = m_rnd.nextInt(wih);
    int rndy = m_rnd.nextInt(heh);
    p_graph.fillRect(wih + rndx, rndy, 2, 2);
  }

  private void disegnaAssi(Graphics2D graph, int wi, int he) {
    graph.setColor(new Color(5, 120, 5));
    int wih = wi / 2;
    int heh = he / 2;
    graph.draw(new Line2D.Double(wih, 0, wih, he));
    graph.draw(new Line2D.Double(0, heh, wi, heh));
  }

  public static void main(String[] args) {
    JFrame frm = new JFrame();
    frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frm.add(new PlotPoints());
    frm.setSize(600, 400);
    frm.setLocation(100, 100);
    frm.setTitle("Punti randomizzati nel primo quadrante");
    frm.setVisible(true);
  }
}

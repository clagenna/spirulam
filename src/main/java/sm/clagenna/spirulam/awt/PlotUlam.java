package sm.clagenna.spirulam.awt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.commons.lang3.time.StopWatch;

import sm.clagenna.spirulam.PrimiFactory;
import sm.clagenna.spirulam.util.CerchioSpir;
import sm.clagenna.spirulam.util.Coord;

public class PlotUlam extends JPanel {
  private static final long serialVersionUID = -3739208735302094549L;
  private static final long MAX_PRIMI        = 150_000;
  private JFrame            jframe;
  private int               m_width;
  private int               m_heigt;

  private PrimiFactory      m_fact;
  private List<Coord>       m_coord;
  private NumberFormat      m_fmt;

  public PlotUlam() {
    initData();
  }

  public PlotUlam(JFrame p_frm) {
    setJframe(p_frm);
    initData();
    p_frm.setSize(600, 800);
    p_frm.setLocation(100, 100);
    p_frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    p_frm.setLocationRelativeTo(null);
    p_frm.setTitle("Plot della spirale di Ulam");
  }

  private void initData() {
    StopWatch wtch = new StopWatch();
    m_fmt = NumberFormat.getIntegerInstance();
    wtch.start();
    m_fact = new PrimiFactory();
    List<Long> liPrim = m_fact.creaPrimi((int) MAX_PRIMI);
    m_coord = new ArrayList<>();
    for (long ii : liPrim) {
      CerchioSpir cc = CerchioSpir.getSpirale((int) ii);
      Coord coo = CerchioSpir.coordinate(cc);
      m_coord.add(coo);
    }
    wtch.stop();
    System.out.printf("%s \tin %10s ms\n", m_fmt.format(m_coord.size()), m_fmt.format(wtch.getTime(TimeUnit.MILLISECONDS)));
  }

  @Override
  public void paintComponent(Graphics p_g) {
    super.paintComponents(p_g);
    Graphics2D graph = (Graphics2D) p_g;
    graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    m_width = getWidth();
    m_heigt = getHeight();
    disegnaAssi(graph);
    graph.setColor(Color.black);
    disegnaSpirale(graph);
  }

  private void disegnaAssi(Graphics2D graph) {
    graph.setColor(new Color(5, 120, 5));
    int wih = m_width / 2;
    int heh = m_heigt / 2;
    graph.draw(new Line2D.Double(wih, 0, wih, m_heigt));
    graph.draw(new Line2D.Double(0, heh, m_width, heh));
    graph.setColor(Color.magenta);
    int wi12, wi22, he12, he22;
    if (m_width >= m_heigt) {
      wi12 = wih - heh;
      wi22 = wih + heh;
      he12 = 0;
      he22 = m_heigt;
    } else {
      wi12 = 0;
      wi22 = m_width;
      he12 = heh - wih;
      he22 = heh + wih;
    }
    graph.draw(new Line2D.Double(wi12, he12, wi22, he22));
    graph.draw(new Line2D.Double(wi22, he12, wi12, he22));
  }

  private void disegnaSpirale(Graphics2D p_graph) {
    double wih = m_width / 2;
    double heh = m_heigt / 2;
    final boolean bTimer = false;
    StopWatch wtch;
    if (bTimer) {
      wtch = new StopWatch();
      wtch.start();
    }
    for (Coord co : m_coord) {
      int lx = (int) (co.x() + wih);
      int ly = (int) (heh - co.y());
      p_graph.fillRect(lx, ly, 1, 1);
    }
    if (bTimer) {
      wtch.stop();
      System.out.printf("Plot Spir:%s \tin %10s ms\n", m_fmt.format(m_coord.size()),
          m_fmt.format(wtch.getTime(TimeUnit.MILLISECONDS)));
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        JFrame frm = new JFrame();
        PlotUlam pan = new PlotUlam(frm);
        frm.add(pan);

        frm.setVisible(true);
      }
    });
  }

  public JFrame getJframe() {
    return jframe;
  }

  public void setJframe(JFrame p_jframe) {
    jframe = p_jframe;
  }

}

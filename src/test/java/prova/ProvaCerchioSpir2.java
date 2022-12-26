package prova;

import org.junit.Test;

import sm.clagenna.spirulam.util.CerchioSpir2;
import sm.clagenna.spirulam.util.Coord;
import sm.clagenna.spirulam.util.RapprCoord;

public class ProvaCerchioSpir2 {
  private RapprCoord m_rp;

  @Test
  public void doit() {
    final int qta = 128;
    m_rp = new RapprCoord(qta);
    m_rp.setShowAxes(true);

    for (int i = 1; i <= qta; i++) {
      CerchioSpir2 cc = CerchioSpir2.getSpirale(i);
      Coord coo = CerchioSpir2.coordinate(cc);
      m_rp.show(coo);
    }
    System.out.println("ProvaCerchioSpir.doit()");
  }
}

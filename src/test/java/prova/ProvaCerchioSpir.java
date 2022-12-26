package prova;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import sm.clagenna.spirulam.util.CerchioSpir;
import sm.clagenna.spirulam.util.Coord;
import sm.clagenna.spirulam.util.RapprCoord;

public class ProvaCerchioSpir {
  private static Map<Integer, Integer> m_map;

  private RapprCoord                   m_rp;

  static {
    m_map = new HashMap<>();
    m_map.put(3, 1);
    m_map.put(8, 1);
    m_map.put(13, 2);
    m_map.put(23, 2);
    m_map.put(26, 3);
    m_map.put(48, 3);
    m_map.put(49, 4);
  }

  @Test
  public void doit() {
    m_rp = new RapprCoord(200);
    
    for (int i = 0; i < 200; i++) {
      CerchioSpir cc = CerchioSpir.getSpirale(i);
      Coord coo = CerchioSpir.coordinate(cc);
      m_rp.show(coo);
    }
    System.out.println("ProvaCerchioSpir.doit()");
  }

  // @ Test
  public void provalo() {
    for (Integer ii : m_map.keySet()) {
      int vv = m_map.get(ii);
      CerchioSpir cc = CerchioSpir.getSpirale(ii);
      Assert.assertTrue(cc.cerchio() == vv);
      Coord coo = CerchioSpir.coordinate(cc);
      m_rp.show(coo);
    }
  }

}

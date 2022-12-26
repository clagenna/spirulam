package prova;

import org.junit.Test;

import sm.clagenna.spirulam.util.Coord;
import sm.clagenna.spirulam.util.RapprCoord;

public class ProvaRappr {

  @Test
  public void provalo() {
    RapprCoord rp;
    rp = new RapprCoord(100);

    Coord coo = new Coord(2, 1, 0);
    rp.show(coo);
    coo = new Coord(3, 1, 1);
    rp.show(coo);
    coo = new Coord(9, 1, -1);
    rp.show(coo);
    System.out.println("ProvaRappr.provalo()");
  }

}

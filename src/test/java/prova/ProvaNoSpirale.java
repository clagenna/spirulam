package prova;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

record Cerchio(int val, int cerchio, int resto) {

}

public class ProvaNoSpirale {

  final int           SZX = 20;
  final int           SZY = 20;

  List<StringBuilder> rappr;

  @Before
  public void init() {
    rappr = new ArrayList<>();
    String sz = " ".repeat(4);
    sz = sz.repeat(SZX);
    for (int i = 0; i < SZY; i++)
      rappr.add(new StringBuilder(sz));
  }

  @Test
  public void provalo() {
    int sp = getSpirale(3);
    Assert.assertTrue(sp == 1);
    sp = getSpirale(8);
    Assert.assertTrue(sp == 1);
    sp = getSpirale(13);
    Assert.assertTrue(sp == 2);
    sp = getSpirale(23);
    Assert.assertTrue(sp == 2);
    sp = getSpirale(26);
    Assert.assertTrue(sp == 3);
    sp = getSpirale(48);
    Assert.assertTrue(sp == 3);
    sp = getSpirale(49);
    Assert.assertTrue(sp == 4);
  }

  private int getSpirale(int p_i) {
    int noCerchio = 0;
    if (p_i <= 1)
      return noCerchio;

    int qtaInCerchio = 1;
    int qtTotale = 1;
    int cellEccesso = 0;
    for (int i = 1; i < Integer.MAX_VALUE; i++) {
      noCerchio = i;
      qtaInCerchio = 8 * i;
      int totNextCerchio = qtTotale + qtaInCerchio;
      // superato il max del cerchio?
      if (totNextCerchio > p_i) {
        cellEccesso = p_i - qtTotale + 1;
        break;
      }
      qtTotale = totNextCerchio;
    }
    Cerchio cer = new Cerchio(p_i, noCerchio, cellEccesso);
    System.out.println(cer.toString());
    return noCerchio;
  }

}

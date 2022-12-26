package prova;

import java.text.NumberFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import sm.clagenna.spirulam.PrimiFactory;
import sm.clagenna.spirulam.util.CerchioSpir;
import sm.clagenna.spirulam.util.Coord;
import sm.clagenna.spirulam.util.RapprCoord;

public class ProvaPrimeSpir {
  private StopWatch  wtch;
  private RapprCoord m_rp;

  @Test
  public void provalo() {
    PrimiFactory fact = new PrimiFactory();
    NumberFormat fmt = NumberFormat.getIntegerInstance();
    wtch = new StopWatch();
    List<Long> li = fact.creaPrimi(1_000);

    wtch.start();
    for (long ii : li) {
      CerchioSpir cc = CerchioSpir.getSpirale((int) ii);
      Coord coo = CerchioSpir.coordinate(cc);
      m_rp.show(coo);
    }
    wtch.stop();
    System.out.printf("%s \tin %10s ms\n", fmt.format(li.size()), fmt.format(wtch.getTime(TimeUnit.MILLISECONDS)));
  }

  @Override
  public String toString() {
    return m_rp.toString();
  }
}

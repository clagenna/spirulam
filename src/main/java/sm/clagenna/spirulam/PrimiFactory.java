package sm.clagenna.spirulam;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

public class PrimiFactory {
  private static PrimiFactory s_inst;
  // private static final long   MAXV = 2_000_000_000L;

  private List<Long>          liPrimi;
  private long                qtaMax;
  private long                m_ll;
  private long                m_lx;
  private boolean             m_done;

  public PrimiFactory() {
    if ( !Beans.isDesignTime() && s_inst != null)
      throw new UnsupportedOperationException("PrimiFactory è già stata istanziata !");
    s_inst = this;
  }

  public static PrimiFactory getInst() {
    return s_inst;
  }

  public List<Long> creaPrimi(int qt) {
    setQtaMax(qt);
    return creaPrimi();
  }
  public List<Long> creaPrimi() {
    // long LLP = 521;
    liPrimi = new ArrayList<Long>();
    liPrimi.add(Long.valueOf(2));
    liPrimi.add(Long.valueOf(3));
    liPrimi.add(Long.valueOf(5));
    liPrimi.add(Long.valueOf(7));
    m_ll = 2;
    m_lx = 0;
    
    do {
      m_lx = m_ll++ * 6 - 1;
      if (isPrimo(m_lx))
        liPrimi.add(m_lx);
      // if (( ll % LLP) == 0 ) System.out.println(fmt.format(ll));
      m_lx += 2;
      if (isPrimo(m_lx))
        liPrimi.add(m_lx);
    } while (liPrimi.size() < getQtaMax());
    return liPrimi;
  }

  public boolean isPrimo(long vv) {
    // 1)  i primi 2,3,5,7 sono dispari oppure = 2
    boolean bRet = vv == 2 || (vv & 1) != 0;
    if (vv <= 7 || !bRet)
      return bRet;

    // 2)  i prossimi primi saranno distribuiti *SOLO* su (6n-1,6n+1)
    if ( (vv + 1) % 6 != 0 && (vv - 1) % 6 != 0)
      return false;

    // 3) il tetto dei fattori non supera SQRT(n)
    long maxFact = (long) Math.sqrt(vv) + 1;
    // itero fino a che lastPr < maxFact
    long lastPr = 3;
    // verifico la lista
    int maxLiPrimi = -1;
    if (liPrimi != null || liPrimi.size() > 0)
      maxLiPrimi = liPrimi.size();
    // salto 2 e 3 visto il test al punto 2)
    int iLiPrimo = 2;
    // 4) se esiste cerco se è primo con ogni della lista
    for (; iLiPrimo < maxLiPrimi && lastPr <= maxFact; iLiPrimo++) {
      lastPr = liPrimi.get(iLiPrimo);
      if (vv % lastPr == 0)
        return false;
    }
    // 5) ho finito la lista, proseguo con lastPr
    for (; lastPr <= maxFact; lastPr += 2) {
      // System.out.printf(" %d su %d (%d)\n", lastPr, vv, maxFact);
      if (vv % lastPr == 0)
        return false;
    }
//    if ( bRet ) {
//      if ( ! PrimeNumberProgram.checkForPrime(vv))
//        System.err.println("Non è primo"+vv);
//    }
    return bRet;
  }

  public long getQtaPrimi() {
    if (liPrimi == null)
      return 0;
    return liPrimi.size();
  }

  public void reset() {
    m_done = false;
    if (liPrimi != null)
      liPrimi.clear();
    liPrimi = null;
  }

  public boolean isDone() {
    return m_done;
  }

  public long getQtaMax() {
    return qtaMax;
  }

  public void setQtaMax(long p_qtaMax) {
    qtaMax = p_qtaMax;
  }

}

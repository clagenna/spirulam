package sm.clagenna.spirulam.util;

public record CerchioSpir(int val, int cerchio, int resto) {

  public static CerchioSpir getSpirale(int p_i) {
    CerchioSpir csp = new CerchioSpir(p_i, 0, 0);
    if (p_i < 1)
      return csp;
    int noCerchio = 1;
    int qtInCerchio = 1;
    int qtTotale = 1;
    int qtEccesso = 0;
    for (int i = 1; i < Integer.MAX_VALUE; i++) {
      noCerchio = i;
      qtInCerchio = 8 * i;
      // centro + cerchi esterni 1 + 8 = 9
      int totNextCerchio = qtTotale + qtInCerchio;
      // superato il max del cerchio?
      if (totNextCerchio > p_i) {
        qtEccesso = p_i - qtTotale + 1;
        break;
      }
      qtTotale = totNextCerchio;
    }
    csp = new CerchioSpir(p_i, noCerchio, qtEccesso);
    return csp;
  }

  public static Coord coordinate(CerchioSpir p_cc) {
    Coord coo = new Coord(0, 0, 0);
    if (p_cc.val() <= 0)
      return coo;
    int qtaFirst = (2 * (p_cc.cerchio - 1) + 1);
    qtaFirst *= qtaFirst;
    int lato = p_cc.cerchio * 8 / 4;
    int iLato = lato / 2;
    coo = new Coord(qtaFirst, p_cc.cerchio, 0);
    for (int k = 1; k < p_cc.resto; k++) {
      int quadr = iLato++ / lato;
      Coord incr = Coord.quadrante(quadr);
      coo = coo.add(incr);
    }
    return coo;
  }
}

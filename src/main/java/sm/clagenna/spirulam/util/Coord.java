package sm.clagenna.spirulam.util;

public record Coord(int a, int x, int y) {
  @Override
  public boolean equals(Object other) {
    boolean bRet = false;
    if ( ! (other instanceof Coord))
      return bRet;
    Coord oth = (Coord) other;
    bRet = oth.x == x && oth.y == y;
    return bRet;
  }

  public Coord add(Coord co) {
    return new Coord(co.a + a(), x + co.x(), y + co.y());
  }

  public static Coord quadrante(int quadrante) {
    Coord quadr;
    switch (quadrante) {
      case 0:
        quadr = new Coord(1, 0, 1);
        break;
      case 1:
        quadr = new Coord(1, -1, 0);
        break;
      case 2:
        quadr = new Coord(1, 0, -1);
        break;
      case 3:
        quadr = new Coord(1, 1, 0);
        break;
      case 4:
        quadr = new Coord(1, 0, 1);
        break;
      default:
        quadr = new Coord(1, 1, 0);
        break;
    }
    return quadr;
  }

}

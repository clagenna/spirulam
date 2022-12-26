package sm.clagenna.spirulam.util;

import java.util.ArrayList;
import java.util.List;

public class RapprCoord {
  private int         SZmax = 200;
  private int         SZX   = 50;
  private int         SZY   = 50;
  private boolean     showAxes;
  List<StringBuilder> rappr;

  public RapprCoord(int max) {
    SZmax = max;
    init();
  }

  private void init() {
    rappr = new ArrayList<>();
    String sz = " ".repeat(4);
    SZY = (int) Math.sqrt(SZmax);
    SZX = SZY * 4;
    sz = sz.repeat(SZY);
    for (int i = 0; i < SZY; i++)
      rappr.add(new StringBuilder(sz));
    if (isShowAxes()) {
      showAxes();
    }
  }

  private void showAxes() {
    int posx = (SZY / 2) * 4;
    // asse verticale
    StringBuilder ver;
    String sz = "  ! ";
    for (int y = 0; y < SZY; y++) {
      ver = rappr.get(y);
      ver = ver.replace(posx, posx + 4, sz);
    }
    StringBuilder ori = rappr.get(SZY / 2);
    for (int y = 0; y < SZY; y++) {
      int px = y * 4;
      ori = ori.replace(px, px + 4, "----");
    }
    ver = rappr.get(SZY / 2);
    ver = ver.replace(posx, posx + 4, "--+-");
  }

  public void show(Coord coo) {
    int posx = SZX / 2 + coo.x() * 4;
    int posy = SZY / 2 - coo.y();
    String sz = String.format("%4d", coo.a());
    StringBuilder rig = rappr.get(posy);
    rig = rig.replace(posx, posx + 4, sz);
  }

  public void setShowAxes(boolean p_showAxes) {
    showAxes = p_showAxes;
    if (p_showAxes)
      showAxes();
  }

  @Override
  public String toString() {
    StringBuilder sz = new StringBuilder("");
    for (StringBuilder bl : rappr)
      sz.append(bl.toString()).append("\n");
    return sz.toString();
  }

  public boolean isShowAxes() {
    return showAxes;
  }
}

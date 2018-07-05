package com.amitesh.skiing;

import java.uti.Comparator;

class SkiHill {
  int elevation;
  int posX;
  int posY;

  SkiHill (int elevation, int x, int y){
    this.elevation = elevation;
    this.posX = x;
    this.posY = y;
  }

  public int getElevation(){
    return this.elevation;
  }

  public int getX(){
    return this.posX;
  }

  public int getY(){
    return this.posY;
  }

  static Comparator<SkiHill> comparator = new Comparator<SkiHill>() {
			public int compare(SkiHill s1, SkiHill s2) {
				return ((s1.getElevation() - s2.getElevation() < 0) ? -1 : (s1.getElevation() - s2.getElevation() == 0 ? 0 : 1));
			}
		};
}

package com.amitesh.skiing;

import com.amitesh.skiing.SkiHill;

class MinimaWeight {
  SkiHill minHill;
  int length;

  MinimaWeight(SkiHill sH, int l){
    this.minHill = sH;
    this.length = l;
  }

  SkiHill getMinHill() {
    return this.minHill;
  }

  int getRouteLength() {
    return this.length;
  }

  void setMinHill(SkiHill sH) {
    this.minHill = sH;
  }

  void setRouteLength(int l) {
    this.length = l;
  }

  static MinimaWeight maxMinima(SkiHill refHill, MinimaWeight mw, MinimaWeight mw2) {
    if(mw == null) {
      return mw2;
    } else {
      if(mw.getRouteLength() - mw2.getRouteLength() > 0) {
        return mw;
      } else if(mw.getRouteLength() - mw2.getRouteLength() == 0) {
        if(refHill.getElevation() - mw.getMinHill().getElevation() > refHill.getElevation() - mw2.getMinHill().getElevation()) {
          return mw;
        } else {
          return mw2;
        }
      } else {
        return mw2;
      }
    }
  }
}

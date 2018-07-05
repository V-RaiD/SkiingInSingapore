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
}

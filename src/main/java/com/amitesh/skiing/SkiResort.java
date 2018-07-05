package com.amitesh.skiing;

//importing required classes
import java.util.Scanner;
import java.io.File;
import java.lang.Exception;
import java.lang.Integer;
import java.util.Arrays;

//import project classes
import com.amitesh.skiing.SkiHill;
import com.amitesh.skiing.MinimaWeight;

class SkiResort {
  SkiHill[][] skiMap;
  SkiHill[] skiList;
  SkiHill maxHill;
  SkiHill minHill;
  int maxRouteLength;
  int maxX;
  int maxY;
  boolean flag;
  int skiiableHills;

  SkiResort(String path, String length, String width){
    int len = Integer.parseInt(length);
    int wid = Integer.parseInt(width);
    if(len != wid){
      flag = false;
    }else{
      flag = true;
      this.skiMap = new SkiHill[len][wid];
      this.skiList = new SkiHill[len*wid];
      this.maxX = len;
      this.maxY = wid;
      this.minHill = null;
      this.maxHill = null;
      this.addDataToSkiMap(path);
      this.createSortedSkiElevationList();
      this.calculateLongestSteepestRoute();
    }
  }

  private void addDataToSkiMap(String path) {
    try {
      Scanner scan = new Scanner(new File(path));
      int x = 0;
      int y = 0;
      while(scan.hasNextLine()){
        String line = scan.nextLine();
        String[] arrElevation = line.split(" ");
        for(int i = 0; i < arrElevation.length;i++){
          skiMap[x][y] = new SkiHill(Integer.parseInt(arrElevation[i]),x,y);
          y=y+1;
        };
        x=x+1;
        y=0;
      }
      scan.close();
    } catch(Exception ex){
      ex.printStackTrace();
    }
  }

  private void createSortedSkiElevationList(){
    int k=0;
    for(int i = 0; i < this.maxX; i++){
      for(int j = 0; j < this.maxY; j++){
        if(this.isSkiPossible(this.skiMap[i][j]))
          this.skiList[k++] = this.skiMap[i][j];
      }
    }
    this.setSkiiableHills(k);

    Arrays.sort(this.skiList,0, this.getSkiiableHills(), SkiHill.comparator);
  }

  private void setSkiiableHills(int nSkiHills) {
    this.skiiableHills = nSkiHills;
  }

  private int getSkiiableHills() {
    return this.skiiableHills;
  }

  private void calculateLongestSteepestRoute(){
    this.maxRouteLength = 0;
    this.maxHill = this.skiList[0];

    for(int i = 0; i < this.skiiableHills; i++){
      MinimaWeight mw = this.findLongRouteAndMinima(this.skiList[i]);
      if(mw.getRouteLength() > this.maxRouteLength) {
        this.maxRouteLength = mw.getRouteLength();
        this.maxHill = this.skiList[i];
        this.minHill = mw.getMinHill();
      } else if(mw.getRouteLength() == this.maxRouteLength) {
        if(this.minHill == null) {
          this.maxHill = this.skiList[i];
          this.minHill = mw.getMinHill();
        } else {
          if((this.maxHill.getElevation() - this.minHill.getElevation()) < (this.skiList[i].getElevation() - mw.getMinHill().getElevation())){
            this.maxHill = this.skiList[i];
            this.minHill = mw.getMinHill();
          }
        }
      }
    }
  }

  private MinimaWeight findLongRouteAndMinima(SkiHill seedHill) {
    if(this.isSkiPossible(seedHill)) {
      MinimaWeight mw = null;
      if(this.getNeighbouringHill(seedHill, SkiHill.NORTH) != null && (this.getNeighbouringHill(seedHill, SkiHill.NORTH).getElevation() < seedHill.getElevation())){
        mw = MinimaWeight.maxMinima(seedHill, mw, this.findLongRouteAndMinima(this.getNeighbouringHill(seedHill, SkiHill.NORTH)));
      }
      if(this.getNeighbouringHill(seedHill, SkiHill.SOUTH) != null && (this.getNeighbouringHill(seedHill, SkiHill.SOUTH).getElevation() < seedHill.getElevation())){
        mw = MinimaWeight.maxMinima(seedHill, mw, this.findLongRouteAndMinima(this.getNeighbouringHill(seedHill, SkiHill.SOUTH)));
      }
      if(this.getNeighbouringHill(seedHill, SkiHill.EAST) != null && (this.getNeighbouringHill(seedHill, SkiHill.EAST).getElevation() < seedHill.getElevation())){
        mw = MinimaWeight.maxMinima(seedHill, mw, this.findLongRouteAndMinima(this.getNeighbouringHill(seedHill, SkiHill.EAST)));
      }
      if(this.getNeighbouringHill(seedHill, SkiHill.WEST) != null && (this.getNeighbouringHill(seedHill, SkiHill.WEST).getElevation() < seedHill.getElevation())){
        mw = MinimaWeight.maxMinima(seedHill, mw, this.findLongRouteAndMinima(this.getNeighbouringHill(seedHill, SkiHill.WEST)));
      }
      if(mw == null) {
        return new MinimaWeight(seedHill, 1);
      } else {
        mw.length = mw.length+1;
        return mw;
      }
    } else {
      return new MinimaWeight(seedHill, 1);
    }
  }

  private boolean isSkiPossible(SkiHill refHill) {
    if(
      (this.getNeighbouringHill(refHill, SkiHill.NORTH) != null && (this.getNeighbouringHill(refHill, SkiHill.NORTH).getElevation() < refHill.getElevation())) ||
      (this.getNeighbouringHill(refHill, SkiHill.SOUTH) != null && (this.getNeighbouringHill(refHill, SkiHill.SOUTH).getElevation() < refHill.getElevation())) ||
      (this.getNeighbouringHill(refHill, SkiHill.EAST) != null && (this.getNeighbouringHill(refHill, SkiHill.EAST).getElevation() < refHill.getElevation())) ||
      (this.getNeighbouringHill(refHill, SkiHill.WEST) != null && (this.getNeighbouringHill(refHill, SkiHill.WEST).getElevation() < refHill.getElevation())) ) {
      return true;
    }
    return false;
  }

  private SkiHill getNeighbouringHill(SkiHill refHill, int zone) {
    switch(zone) {
      case SkiHill.NORTH : {
        return this.getSkiHillXY(refHill.getX()-1,refHill.getY());
      }
      case SkiHill.SOUTH : {
        return this.getSkiHillXY(refHill.getX()+1,refHill.getY());
      }
      case SkiHill.EAST : {
        return this.getSkiHillXY(refHill.getX(),refHill.getY()-1);
      }
      case SkiHill.WEST : {
        return this.getSkiHillXY(refHill.getX(),refHill.getY()+1);
      }
      default : {
        return null;
      }
    }
  }

  private SkiHill getSkiHillXY(int X, int Y) {
    if(X >= 0 && X < this.maxX) {
      if(Y >= 0 && Y < this.maxY) {
        return this.skiMap[X][Y];
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  public String toString() {
    return "MaxHill - " + this.maxHill.toString() + "\nMinHill - " + this.minHill.toString() + "\nMax Route Length - " + this.maxRouteLength + "\nMax Elevation - " + (this.maxHill.getElevation() - this.minHill.getElevation());
  }
}

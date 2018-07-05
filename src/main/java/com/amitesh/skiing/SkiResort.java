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
      this.minHill == null;
      this.maxHill = null;
      this.addDataToSkiMap(path);
      this.createSortedSkiElevationList();
      this.calculateLongestSteepestRoute();
    }
  }

  private void addDataToSkiMap(String path) {
    try{
      Scanner scan = new Scanner(new File(path));
      int x = 0;
      int y = 0;
      while(scan.hasNextLine()){
        String line = scan.nextLine();
        String[] arrElevation = line.split(" ");
        for(int i = 0; i < arrElevation.length;i++){
          skiMap[x][y++] = new SkiHill(Integer.parseInt(arrElevation[i]),x,y);
        };
        x=x+1;
        y=0;
      }
    }catch(Exception ex){
      ex.printStackTrace();
    }
  }

  private void createSortedSkiElevationList(){
    int k=0;
    for(int i = 0; i < this.maxX; i++){
      for(int j = 0; j < this.maxY; j++){
        this.skiList[k++] = this.skiMap[i][j];
      }
    }

    Arrays.sort(this.skiList, SkiHill.comparator);
  }

  private void calculateLongestSteepestRoute(){
    this.maxRouteLength = 0;
    this.maxHill = this.skiList[0];

    for(int i = 0; i < this.skiList.length; i++){
      MinimaWeight mw = this.findLongRouteAndMinima(this.skiList[i]);
      if(mw.length > this.maxRouteLength) {
        this.maxRouteLength = mw.length;
        this.maxHill = this.skiList[i];
        this.minHill = mw.minHill;
      } else if(mw.length == this.maxRouteLength) {
        if(this.minHill == null) {
          this.maxHill = this.skiList[i];
          this.minHill = mw.minHill;
        } else {
          if((this.maxHill.getElevation() - this.minHill.getElevation()) < (this.skiList[i].getElevation() - mw.minHill.getElevation())){
            this.maxHill = this.skiList[i];
            this.minHill = mw.minHill;
          }
        }
      }
    }
  }

  private MinimaWeight findLongRouteAndMinima(SkiHill seedHill) {
    if(this.isSkiPossible(seedHill)) {
      SkiHill[] skiiableHillList = this.getSkiiableHills(seedHill);

    } else {
      return new MinimaWeight(seedHill, 1);
    }
  }
}

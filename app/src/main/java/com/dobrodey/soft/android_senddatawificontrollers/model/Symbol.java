/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dobrodey.soft.android_senddatawificontrollers.model;

/**
 *
 * @author Arhian
 */
public class Symbol {
    
  private static int symbolHeight = 16;
  private static String priceAlignSpace = "";
  private static String projectFont;
  public String caracterSpacing = "";
  private static String startLineSpacing = "";

  public String[] code;

  
  
  public static  void setStartLineSpacing(int count) {
	  startLineSpacing = "";
	  for(int i = 0; i < count; i++) {
		  startLineSpacing += "0";
	  }
  }

  public Symbol(Character c, String projectFontInp, int caracterSpace, int startLineCount){

    projectFont = projectFontInp;
    StringBuilder sb = new StringBuilder();
    for(int i=0;i<caracterSpace;i++) {
      sb.append('0');
    }
    caracterSpacing = sb.toString();
    setStartLineSpacing(startLineCount);
  }
  
  
  
    
    protected static void setSymbolHeight(Integer set) {
        symbolHeight = set;
    }
    
    public static int getSymbolHeight()
    {
        return symbolHeight;
    }
    
    
    /*
    protected static String getCaracterSpacing()
    {
        
        return caracterSpacing;
        
    }*/

  public static String getStartLineSpacing() {
    return startLineSpacing;
  }
  
  public static String getPriceAlignSpace() {
    return priceAlignSpace;
  }
}

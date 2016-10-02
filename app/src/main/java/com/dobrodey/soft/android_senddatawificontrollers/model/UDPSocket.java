package com.dobrodey.soft.android_senddatawificontrollers.model;

import android.util.Log;

import com.dobrodey.soft.android_senddatawificontrollers.activities.MainActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPSocket {
	  public static DatagramSocket socket = null ;
	  private static InetAddress ipAddress = null;
	  private static int sourcePort;
	  private static int destPort;
	  public static String ip;
	  
	  private static String  pathToPortConfigurations = "sstm/dcether";
	  
	  public UDPSocket(){

	  }
	  
	  public void openUDPSocket() throws SocketException, UnknownHostException {
	    if(socket == null) {
	      socket = new DatagramSocket(sourcePort);
	      ipAddress = InetAddress.getByName(ip);
	      socket.setSoTimeout(5000);//Time wait receive data(seconds)
	    }
	  }
	  
	  public void closeUDPSocket() {
	    if(!socket.isClosed())
	      socket.close();
	    socket = null;
	  }
	  
	  public boolean sendUDPSocket(String dataInHEX) throws IOException {
	    if(dataInHEX.length()%2 != 0) { //If getting bytes is not pair
	      return false;
	    }
	    
	    byte[] sendData = new byte[dataInHEX.length()/2];
	    byte[] receiveData = new byte[1024];
	    StringBuilder sb = new StringBuilder();
	    
	    //_______________________From HEX to byte input_____________________________
	    for(int i=0, counter=0; i<dataInHEX.length(); i+=2,counter++) {
	      sendData[counter]=(byte)Integer.parseInt(dataInHEX.substring(i, i+2), 16);
	    }
	    //__________________________________________________________________________
	    
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, destPort);
	    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		  for(int i=0;i<3;i++) {
			Log.d(MainActivity.TAG, "SEND PACKET" + i);

	      socket.send(sendPacket);
	      try {
	      socket.receive(receivePacket);
	      } catch(SocketTimeoutException ex) {
			  Log.d(MainActivity.TAG, ex.toString() + ", " + ex.getMessage() + "," + ex.getLocalizedMessage());
	        if(i==2)
	          return false;// If no data receive
	        continue;
	      }
	      break;
	    }
	    
	    for(int i=0; i<receivePacket.getLength(); i++) {
	      if(Integer.toHexString(receiveData[i]).length() == 1)
	        sb.append("0");
	      sb.append(Integer.toHexString(receiveData[i]));
	    }

	    sendData = null;
	    receiveData = null;
	    sendPacket = null;
	    receivePacket = null;
	    return true;
	  }
	  
	  public void setIp(String ip) {
		  this.ip = ip;
	  }
	  
	  protected static void getPortFromFile(String fileName) throws FileNotFoundException, IOException {

	  }
}

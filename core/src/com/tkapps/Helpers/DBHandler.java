package com.tkapps.Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;

public class DBHandler {
	private static final String url = "moore06.cs.purdue.edu";
	private static final int port = 3006;
	

	
	public static String[] getHighScores(){
		String result = "";
		String[] toReturn;
		Socket dbSocket = null;
		try {
			dbSocket = Gdx.net.newClientSocket(Protocol.TCP, url, port, null);
			
			//setup input and output for socket
			PrintWriter out = new PrintWriter(dbSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(dbSocket.getInputStream()));
			
			out.println("GET-ALL-SCORES|root|philiaga");
			String temp = in.readLine();
			while (temp != null) {
				result += temp + "\n";
				temp = in.readLine();
			}
			toReturn = result.split("\n");
		} catch (Exception e) {
			toReturn = new String[1];
			toReturn[0] = "No Connection|...|"; 
		} finally {
			if(dbSocket != null)
				dbSocket.dispose();
		}
		
		return toReturn;
	}
	
	public static void submitScore(String inits, int time){
		Socket dbSocket = null;
		try {
			dbSocket = Gdx.net.newClientSocket(Protocol.TCP, url, port, null);
			
			//setup input and output for socket
			PrintWriter out = new PrintWriter(dbSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(dbSocket.getInputStream()));
			
			out.println("PUT-SCORE-INFO|root|philiaga|"+inits+"|"+time);

		} catch (Exception e) {
		} finally {
			if(dbSocket != null)
				dbSocket.dispose();
		}
		return;
	}
}

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
		try {
			Socket dbSocket = Gdx.net.newClientSocket(Protocol.TCP, url, port, null);
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		toReturn = result.split("\n");
		return toReturn;
	}
	
	public static void submitScore(String inits, int time){

		try {
			Socket dbSocket = Gdx.net.newClientSocket(Protocol.TCP, url, port, null);
			
			//setup input and output for socket
			PrintWriter out = new PrintWriter(dbSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(dbSocket.getInputStream()));
			
			out.println("PUT-SCORE-INFO|root|philiaga|"+inits+"|"+time);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

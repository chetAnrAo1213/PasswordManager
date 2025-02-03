package com.boot.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.stereotype.Service;

@Service
public class InternetConnectivityService 
{

	public boolean validateInternetConnection()
	{
		boolean internetAccess = false;
		try (Socket socket = new Socket()) 
		{
			socket.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
			internetAccess = true;
		}
		catch (IOException e) 
		{
			internetAccess = false;
		}
		
		return internetAccess;
	}
}

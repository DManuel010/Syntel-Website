package org.syntinel.utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

abstract public class Utilities 
{
	private final static String key = "saltandpepper";
	
	public final static String getToday()
	{
		SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		String today = fDate.format(now);
		return today;
	}
	
	public final static String createUniqueId()
	{
		int randomID = (int) Math.round((Math.random() * System.nanoTime()));
		randomID = Math.abs(randomID);
		return Integer.toString(randomID);
	}
	
	public final static String encryptPassword(String password)
	{
		String encrypted_password = null;
		try
		{
			SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = null;
			cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			encrypted_password = new String(encrypted);
		}catch(Exception e)
		{
			
		}
		
		return encrypted_password;
	}
	
}

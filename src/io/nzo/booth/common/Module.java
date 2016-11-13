
/**
 * Common Function
 * 
 * @author		gemzone (gemzone@naver.com)
 * @version	1.4
 * @date		2011-08-26
 * 				2011-11-10
 * 				2011-11-22
 * 				2016-11-08
 */
package io.nzo.booth.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Module
{
	public static final Logger logger = LoggerFactory.getLogger(Module.class);

	public static int negativeNumber(int number)
	{
		return number * (-1);
	}

	public static int positiveNumber(int number)
	{
		return number * (1);
	}
	

	/**
	 * Random 숫자
	 */
	public static int random(int max)
	{
		return (int)(Math.random() * (max+1));
	}
	
	public static int random(int start, int end)
	{
		return (int)(Math.random() * (end - start + 1)) + start;
	}
	
	public static boolean chance(float prob)
	{
		return chance(prob, 100.0f);
	}

	public static boolean chance(float prob, float max)
	{
		float randNum = randomChance(max);
		if( randNum <= prob)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	public static float chance()
	{
		float randNum = (float)((int)(Math.random() * 10000 + 1) * 0.01d);
		return randNum;
	}
	
	// random chance 우연
	public static float randomChance(float max)
	{
		float randNum = (float)((int)(Math.random() * (max * 100) + 1) * 0.01d);
		return randNum;
	}
	
	public static float percent( int currentValue, int maxValue )
	{
		if (maxValue == 0)
		{
			return 100.0f;
		}
		
		float per = (float)((int)(currentValue * 10000) / (int)(maxValue) * 0.01d);
		return per;
	}

	
	public static String SHA256(String str) 
	{
		String SHA = "";
		try 
		{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++) 
			{
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}

	public static String MD5(String str) 
	{
		String MD5 = "";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++)
			{
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			MD5 = null;
		}
		return MD5;
	}
	
}

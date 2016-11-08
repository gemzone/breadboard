
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

public class ResultValidation
{
	public static final Logger logger = LoggerFactory.getLogger(ResultValidation.class);
	
	public static boolean check(ModelMap map)
	{
		if( map.containsKey("reason") == false || map.get("reason") == null ) 
		{
			map.addAttribute("reason", "");
		}
		
		if( map.containsKey("success") == false || map.get("success") == null ) 
		{
			map.addAttribute("success", false);
		}
		
		if( map.containsKey("error") == false || map.get("error") == null ) 
		{
			map.addAttribute("error", 0);
		}
		
		return (((boolean)map.get("success")) && ((int)map.get("error")) == 0);
	}
	
}

package io.nzo.breadboard.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import io.nzo.breadboard.service.BoardService;
import io.nzo.breadboard.service.UserService;

@Configuration
@Controller
@SessionAttributes("user")
public class TestController
{
	@Value("${breadboard.data.root}")
	private String DATA_ROOT;
    
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	BoardService boardService;
	
	// /install
	@RequestMapping(path =  "/test", method = { RequestMethod.GET }, produces = MediaType.TEXT_HTML )
	public String test(Model model, HttpServletRequest request)
	{
		return "test";
	}
	
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file)
	{
		if (!file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				File dir = new File(DATA_ROOT + "/temp" + File.separator );
				if (!dir.exists()) { dir.mkdirs(); }
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} 
			catch (Exception e)
			{
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} 
		else
		{
			return "You failed to upload " + name + " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipleFileHandler(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files) 
	{

		if (files.length != names.length)
		{
			return "Mandatory information missing";
		}

		String message = "";
		for (int i = 0; i < files.length; i++)
		{
			MultipartFile file = files[i];
			String name = names[i];
			try
			{
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				// System.out.println(rootPath);
				File dir = new File(DATA_ROOT + File.separator + "tmpFiles");
				if (!dir.exists()) { dir.mkdirs(); }

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name;
			} 
			catch (Exception e)
			{
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}

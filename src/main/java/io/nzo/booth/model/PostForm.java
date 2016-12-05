package io.nzo.booth.model;

import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
 
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class PostForm implements Serializable
{
	@Size(min = 1, message = "First name cannot be blank")
	private String title;
	private String text;
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
    @Override
    public String toString() {
        return "PostForm [title=" + title + ", text=" + text
                + "]";
    }
	
}
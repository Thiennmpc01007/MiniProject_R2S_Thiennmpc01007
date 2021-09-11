package com.poly;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		exposeDir("photo", registry);
	}
	public void exposeDir(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);
		String uploadPath = uploadDir.toFile().getAbsolutePath();
		if (dirName.startsWith("../")) {
			dirName = dirName.replace("../", "");
		}
		registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:/"+uploadPath+"/");
	}	
}

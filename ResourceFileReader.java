package com.kafkasparkstream.project.KafkaProducer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import com.producer.properties.KafkaProperties;
import java.lang.reflect.*;

public class ResourceFileReader
{ 	
	String Str="";
	String Str2 ="";
	String[] Arr;
	
	public File getFileResource(String fileName)
	{
		 ClassLoader classLoader = getClass().getClassLoader();
	     URL resource = classLoader.getResource(fileName);
	        if (resource == null) 
	        {
	            throw new IllegalArgumentException("file is not found!");
	        } 
	        else 
	        {
	            return new File(resource.getFile());
	        }		
    }
	
	public void setProperties(File file,KafkaProperties kafkaProp)
	{
		try
	 	{
	 		int i=0;
	 		BufferedReader bfr = new BufferedReader(new FileReader(file));
	 		while ((Str = bfr.readLine()) != null)
	 		{	 		
	 		 Arr= Str.split("=");
	 		 if (Arr[0].contains("Server"))
	 		 {
	 			 try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setBootstrapServer",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 }
	 		  }
	 		else if (Arr[0].contains("KeySerializer"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setKeySerializer",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 }
	 		 }
	 		 else if (Arr[0].contains("ValueSerializer"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setValueSerializer",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 } 
	 		 }
	 		 else if (Arr[0].contains("MaxRetries"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setMaxRetries",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 } 
	 		 }
	 		 else if (Arr[0].contains("FilePath"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setFilePath",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 }  
	 		 }
	 		 else if (Arr[0].contains("FileName"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setFileName",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 } 
	 		 }
	 		else if (Arr[0].contains("TopicName"))
	 		 {
	 			try
	 			 {
	 			   Method met = KafkaProperties.class.getDeclaredMethod("setTopicName",String.class);
	 			   met.setAccessible(true);
	 			   met.invoke(kafkaProp, Arr[1]);
	 			   
	 			 }
	 			 catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException ex)
	 			 {
	 				System.out.println("Method does not exist"+ ex); 
	 			 } 
	 		 }
	 		}		
	 		
	 		bfr.close();
	 	}
	 	catch(IOException exp )
	 	{
	 		System.out.println("IO Exception while setting the kafka Properties" + exp);
	 	}
	}	
	
}	

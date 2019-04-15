package com.kafkasparkstream.project.KafkaProducer;

import java.io.File;
import java.util.Properties;
import java.util.Scanner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import com.producer.properties.KafkaProperties;

public class KProducer {

	public static void main(String[] args) 
			throws Exception
	{
		 
	    Properties props= new Properties();
	    KafkaProperties kafkaProp = new KafkaProperties();
	    
	    ResourceFileReader Obj = new ResourceFileReader();
	    File fr = Obj.getFileResource("kafkaProperties.txt");	    
	    
	    //Accessing the properties file content and setting the kafka properties.
	    Obj.setProperties(fr,kafkaProp);   
	
	    String topicName = kafkaProp.getTopicName();   
	    String absFilePath=kafkaProp.getFilePath()+kafkaProp.getFileName();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProp.getBootstrapServer());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,kafkaProp.getKeySerializer());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,kafkaProp.getValueSerializer());
		props.put(ProducerConfig.RETRIES_CONFIG,Integer.parseInt(kafkaProp.getMaxRetries()));
	   	    
        Producer <String,String> producer = new KafkaProducer <>(props);		
	    
	    //Read Apache Log File
		File file = new File(absFilePath);
		
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())			
		{
			String line = sc.nextLine();
			ProducerRecord<String,String> record = new ProducerRecord<>(topicName,line);			
			producer.send(record);
		}
		
		//Close the File Stream		
		sc.close();
		
		//Close Producer Stream
		producer.close();	
	}
}

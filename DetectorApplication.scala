package com.kafkasparkstream.project

import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.clients.consumer.ConsumerRecords

object DetectorApplication {
  
  def main(args: Array[String])
  {
      val sparkConf = new SparkConf().setAppName("FromKafkatoSparkStreaming").setMaster("local[3]")
      val ssc = new StreamingContext(sparkConf, Seconds(20))
      ssc.checkpoint("checkpoint")      
      val topic = Array("ApacheLogTopic10")
      
       //setting up kafka parameters    
      val kafkaParams = Map[String, Object]("bootstrap.servers" -> "localhost:9092,localhost:9093",
                       "key.deserializer" -> classOf[StringDeserializer],
                       "value.deserializer" -> classOf[StringDeserializer],
                       "group.id" -> "test-consumer-group",
                       "enable.auto.commit" -> (false: java.lang.Boolean),
                       "auto.offset.reset" -> "earliest")
                       
      //creating direct stream
      val stream = KafkaUtils.createDirectStream[String,String](ssc,PreferConsistent,Subscribe[String,String](topic,kafkaParams))
      
      //analysis to find the DDOS attacks
      val ip = stream.transform{(rdd => rdd.flatMap(record => record.value().split(" ")))}
      val ippairs = ip.map(x => (x, 1))
      val ipCounts = ippairs.reduceByKey(_ + _).filter({case (x,y) => x.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")})
      val updated = ipCounts.filter({case(k,v)=> v >= 50})
      
      updated.print()      
      
      
      //saving the output in a text file
      updated.saveAsTextFiles("file:///home/cloudera/Desktop/DDOS_attack-3", "txt") 
      
      ssc.start()
      ssc.awaitTermination()   
  }  
}
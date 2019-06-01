package com.producer.properties;

public class KafkaProperties 
{
	private String TopicName;
	private String BootstrapServer;
	private String FileName;
	private String FilePath;
	private String MaxRetries;
	private String KeySerializer;
	private String ValueSerializer;
	
	public String getTopicName() {
		return TopicName;
	}
	private void setTopicName(String topicName) {
		TopicName = topicName;
	}
	public String getBootstrapServer() {
		return BootstrapServer;
	}
	private void setBootstrapServer(String bootstrapServer) {
		BootstrapServer = bootstrapServer;
	}
	public String getFileName() {
		return FileName;
	}
	private void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFilePath() {
		return FilePath;
	}
	private void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public String getMaxRetries() {
		return MaxRetries;
	}
	private void setMaxRetries(String maxRetires) {
		MaxRetries = maxRetires;
	}
	public String getKeySerializer() {
		return KeySerializer;
	}
	private void setKeySerializer(String keySerializer) {
		KeySerializer = keySerializer;
	}
	public String getValueSerializer() {
		return ValueSerializer;
	}
	private void setValueSerializer(String valueSerializer) {
		ValueSerializer = valueSerializer;
	}
	
	
	
}
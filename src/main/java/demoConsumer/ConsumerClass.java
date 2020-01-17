package demoConsumer;


import java.io.IOException;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



import utilsClass.Popertiesfetcher;

/**
 * Consumer class to consume records from kafka topic
 * @author swetavk
 *
 */


public class ConsumerClass {

	static Properties consumerConfig = new Properties();
	
	
	/**
	 * Method which has consumer subscribed to Kafka 
	 * Once subscription is done polls every 1000 to get the list of records and print it..
	 * @throws IOException
	 */
	

		public void Consumer() throws IOException {

		System.out.println("Entering consumer..");

		Popertiesfetcher fetcher_obj = new Popertiesfetcher();

		// Calling consumer configurations from properties files and setting it..
		consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, fetcher_obj.getbootstrap_servers());
		consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
		consumerConfig.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, fetcher_obj.getAUTO_OFFSET_RESET_CONFIG());
		consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				fetcher_obj.getVALUE_DESERIALIZER_CLASS_CONFIG());
		consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				fetcher_obj.getKEY_DESERIALIZER_CLASS_CONFIG());

//		consumerConfig.put("security.protocol", "SSL");
//		consumerConfig.put("ssl.truststore.location", "/home/ubuntu/MSK_Project/kafka.client.truststore.jks");
//		consumerConfig.put("ssl.keystore.location", "/home/ubuntu/MSK_Project/kafka.client.keystore.jks");
//		consumerConfig.put("ssl.keystore.password", "changeit");
//		consumerConfig.put("ssl.key.password", "changeit");
		
		KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<byte[], byte[]>(consumerConfig);

		// Subscribing consumer ...
		consumer.subscribe(Collections.singleton(fetcher_obj.getTopicName()));
		while (true) 
		{
			
			ConsumerRecords<byte[], byte[]> records = consumer.poll(1000);
			for (ConsumerRecord<byte[], byte[]> record : records) 
			{
				System.out.printf("Received Message topic =%s, partition =%s, offset = %d, key = %s, value = %s \n",
						record.topic(), record.partition(), record.offset(), record.key(), record.value());
				
			}
			consumer.commitSync();
			
		}
		
		
		

	}


}

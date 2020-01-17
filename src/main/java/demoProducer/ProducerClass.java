package demoProducer;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.PutMetricDataResult;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import utilsClass.DemoDataGeneration;
import utilsClass.Popertiesfetcher;
import utilsClass.RandomDataGenerator;


/**
 * 
 * Class which will be producing data to Kafka cluster by calling kafka producer..
 * @author swetavk
 *
 */


public class ProducerClass {

	public static Properties props = new Properties();

	public void ProduceSampleData() throws IOException {

		
		
		//fetching the property and setting it to cerate the Producer
		Popertiesfetcher fetcher_obj = new Popertiesfetcher();
		props.put("bootstrap.servers", fetcher_obj.getbootstrap_servers());
		props.put("acks", fetcher_obj.getacks());
		props.put("retries", fetcher_obj.getretries());
		props.put("batch.size", fetcher_obj.getbatch_size());
		props.put("linger.ms", fetcher_obj.getlinger_ms());
		props.put("buffer.memory", fetcher_obj.getbuffer_memory());
		props.put("key.serializer", fetcher_obj.getkey_serializer());
		props.put("value.serializer", fetcher_obj.getvalue_serializer());
//		props.put("security.protocol", "SSL");
//		
//		props.put("ssl.truststore.location", "/home/ubuntu/MSK_Project/kafka.client.truststore.jks");
//		props.put("ssl.keystore.location", "/home/ubuntu/MSK_Project/kafka.client.keystore.jks");
//		props.put("ssl.keystore.password", "changeit");
//		props.put("ssl.key.password", "changeit");

		String topic_name = fetcher_obj.getTopicName();

		
		
		//The number of records that is to publish and consume..
		int total_records = fetcher_obj.getNum_of_records();

		System.out.println("Data Being produces to Topic " + topic_name);

		// Initializing the Producer
		Producer<String, Integer> producer = new KafkaProducer<String, Integer>(props);

		
		
		
	
		
		
		
		/**
		 * This is an important menthod as this will generate a number between 1, 5
		 *  use the above generated number to get the company name from the treeMap craeted in DemoDataGeneraion
		 *  Finally call a method to generate a value between 1000 and 5000 as stock price and 
		 *  associate it with the company...
		 */
		
		
		final AmazonCloudWatch cw =
			    AmazonCloudWatchClientBuilder.defaultClient();
		
		Dimension dimension = new Dimension()
			    .withName("Producer_metrics_request_rate")
			    .withValue("request-rate-producer");
		
		for (int i = 0; i < total_records; i++) {
			int key_gen = new RandomDataGenerator().getRandomNumberInRange(1, 5);
			int val_gen = new RandomDataGenerator().getRandomNumberInRange(1000, 5000);

			String comp = new DemoDataGeneration().DataGeneration(key_gen);

			producer.send(new ProducerRecord<String, Integer>(topic_name, comp, val_gen));
			producer.flush();

			System.out.println("Input data ... " + comp + " " + val_gen + " ");
			
			
			/**
			 * Trying to use producer metrics ..
			 * 
			 */
			
			
//			for (Entry<MetricName, ? extends Metric> entry : producer.metrics().entrySet()) {
//
//				
//				{
//					System.out.println(entry.getKey().name()+ " : " + entry.getValue().metricValue());
//				
//				
//				}
//			}
//			
			
			for (Entry<MetricName, ? extends Metric> entry : producer.metrics().entrySet()) {

					if(entry.getKey().name().contains("request-rate"))
					{
						System.out.println(entry.getKey().name()+ " : " + entry.getValue().metricValue());
					
					double data =(Double) entry.getValue().metricValue();
					
				    MetricDatum datum = new MetricDatum()
				            .withMetricName("REQUEST_RATE_PRODUCER")
				            .withUnit(StandardUnit.None)
				            .withValue(data)
				            .withDimensions(dimension);
				    
				    PutMetricDataRequest request = new PutMetricDataRequest()
				            .withNamespace("MY_MSK/REQUEST_RATE_PRODUCER")
				            .withMetricData(datum);
				    
				    
				    PutMetricDataResult response = cw.putMetricData(request);
				    
				    System.out.printf("Successfully put data point %f", data);
				    
				    
					}
		
				
			}
		

		}
		
		
		
	

		producer.close();

	}

}

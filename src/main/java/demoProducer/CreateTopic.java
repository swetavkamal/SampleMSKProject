package demoProducer;


import java.io.IOException;

import java.util.Properties;




import kafka.admin.RackAwareMode;
import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;
import utilsClass.Popertiesfetcher;

/**
 * 
 *  This class has method that will create the topic
 *  Will take the topic name as argument from properties file and will check if the topic exist
 *  If topic does not exist create the topic..
 * @author swetavk
 *
 */



public class CreateTopic {

	public void create() throws IOException {


		Popertiesfetcher fetcher_obj = new Popertiesfetcher();
		String topic_name=fetcher_obj.getTopicName();

		
		KafkaZkClient zkClient = KafkaZkClient.apply(fetcher_obj.getzookeeperHost(), fetcher_obj.getisSucre(), fetcher_obj.getsessionTimeoutMs(), fetcher_obj.getconnectionTimeoutMs(),
				fetcher_obj.getmaxInFlightRequests(),fetcher_obj.gettime(),fetcher_obj.getmetricGroup(),fetcher_obj.getmetricType());


		AdminZkClient adminZkClient = new AdminZkClient(zkClient);
		
		Properties topicConfig = new Properties();

		
		System.out.println("Topicname "+topic_name);
		
		
		//Check whether the Topic Exists..
		
		if(zkClient.topicExists(topic_name))
		{
			System.out.println("The topic "+topic_name +" already exists \n So no need to create the topic ");
		}
		else
		{
			System.out.println("The Topic "+topic_name+" does not exits..\n So creating it");
			adminZkClient.createTopic(topic_name, fetcher_obj.getPartition(), fetcher_obj.getreplication(), topicConfig, RackAwareMode.Disabled$.MODULE$);
		}
		



	}
}

package utilsClass;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;

import DemoClusterOperations.ClusterOperations;
import demoConsumer.ConsumerClass;
import demoProducer.CreateTopic;
import demoProducer.ProducerClass;

public class RunnerClass {

	final static Logger logger = Logger.getLogger(utilsClass.RunnerClass.class);


	public static void main(String args[]) throws IOException
	{
	//	BasicConfigurator.configure();
		logger.setLevel(Level.INFO);
		
		
		//  Stack<Integer> sk= new Stack<Integer>();
		
	
		logger.info("LOG4J working");
		Popertiesfetcher obj = null;
		try {
			obj = new Popertiesfetcher();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(obj.getTopicName());
		
	
		// Calling the cluster Operations.. which would include List Operation and Describe cluster
		System.out.println("LISTING THE CLUSTERS IN THE ACCOUNT.. ");
		
		new ClusterOperations().ListClusterOperation();
		new ClusterOperations().DescribeClusters();
		
//		System.out.println("Calling the Zookeeper broker");
//		try {
//			new clusterOperations().GetBrokerDetails();
//		} catch (KeeperException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		} catch (InterruptedException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		//Calling the create topic 
		try {
			new CreateTopic().create();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Calling the producer..
		try {
			
		new	ProducerClass().ProduceSampleData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Calling the consumer..
		try {
			System.out.println("Calling the consumer");
			new ConsumerClass().Consumer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}

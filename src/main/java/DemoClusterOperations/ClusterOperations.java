package DemoClusterOperations;



import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kafka.AWSKafka;
import com.amazonaws.services.kafka.AWSKafkaClientBuilder;
import com.amazonaws.services.kafka.model.DescribeClusterRequest;
import com.amazonaws.services.kafka.model.DescribeClusterResult;
import com.amazonaws.services.kafka.model.GetBootstrapBrokersRequest;
import com.amazonaws.services.kafka.model.GetBootstrapBrokersResult;
import com.amazonaws.services.kafka.model.ListClustersRequest;
import com.amazonaws.services.kafka.model.ListClustersResult;



/**
 * 
 * Cluster Operations method 
 * 1. List Clusters
 * 2. Describe Clusters
 * 
 * @author swetavk
 *
 */

public class ClusterOperations {


	
	
//	BasicAWSCredentials awsCreds = new BasicAWSCredentials("XXXX", "XXXXXX");
//
//	
//	public  AWSKafka kf1 = AWSKafkaClientBuilder.standard().
//			withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.US_EAST_1).build();
//			
			
	
	public AWSCredentialsProviderChain credentialsChain = 
			new AWSCredentialsProviderChain(new DefaultAWSCredentialsProviderChain());
	     //   new AWSCredentialsProviderChain(new ProfileCredentialsProvider("default"),
	       //                                 new EnvironmentVariableCredentialsProvider());
	
	//Creating AWS kafka client
	public  AWSKafka kf1 = AWSKafkaClientBuilder.standard().withRegion(Regions.US_EAST_1)
			.withCredentials(credentialsChain).build();
	

	private final  ListClustersRequest list_cluster_request = new ListClustersRequest();
	private final  ListClustersResult list_cluster_result = kf1.listClusters(list_cluster_request);
	private int num_clusters =list_cluster_result.getClusterInfoList().size();;

	/**
	 * Getting the list of clusters on your account..
	 * 
	 * @return
	 */
	public  ListClustersResult ListClusterOperation() {
		num_clusters = list_cluster_result.getClusterInfoList().size();
		System.out.println("This Account has " + num_clusters + " Clusters\n\n");
		System.out.println("The Name and ARN of Clusters are");

		for (int i = 0; i < num_clusters; i++) {
			System.out.println((i + 1) + "  " + 
		list_cluster_result.getClusterInfoList().get(i).getClusterName() + "   "
					+ list_cluster_result.getClusterInfoList().get(i).getClusterArn());
			
			
			System.out.println("CALLING CUSTOMER CODE ");
			
			GetBootstrapBrokersRequest brokersRequest = new GetBootstrapBrokersRequest();
			brokersRequest.setClusterArn(list_cluster_result.getClusterInfoList().get(i).getClusterArn());
		
			GetBootstrapBrokersResult brokersResult = kf1.getBootstrapBrokers(brokersRequest);
			
			System.out.println("BROKER STRING "+i+" IS"+brokersResult.getBootstrapBrokerString());
		}

		return list_cluster_result;

	}

	
	
	
	
	public void GetBrokerDetails() throws IOException, KeeperException, InterruptedException
	{
		
		 ZooKeeper zk = new ZooKeeper("<your-zookeeper>", 10000, null);
		 List<String> ids = zk.getChildren("/brokers/ids", false);
		 for (String id : ids) {
	            String brokerInfo = new String(zk.getData("/brokers/ids/" + id, false, null));
	            System.out.println(id + ": " + brokerInfo);
	        }
	}
	
	

	
	
	
	
	
	/**
	 * Method which takes the Listcluster result And calls describe one cluster at a
	 * time..
	 */
	public void DescribeClusters() {
		System.out.println("\n\nDescribing Cluster one at a time "+num_clusters);

		for (int i = 0; i < num_clusters; i++) {
			// new clusterOperations();
			System.out.println("Inside Describe cluster API..");

			DescribeClusterRequest request  = new DescribeClusterRequest()
					.withClusterArn(list_cluster_result.getClusterInfoList().get(i).getClusterArn());
			DescribeClusterResult result = kf1.describeCluster(request);
			System.out.println("THE Cluster description for cluster " + (i + 1) + " is: "
					+ result.getClusterInfo().toString() + "\n");
		}
	}

}

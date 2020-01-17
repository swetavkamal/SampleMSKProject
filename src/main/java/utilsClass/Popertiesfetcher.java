package utilsClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.common.utils.Time;

/**
 * 
 * 
 * This is the fetcher class which will pull in Properties file and set it to
 * Private variables
 * 
 * 
 * @author swetavk
 *
 */

public class Popertiesfetcher {

	public static Properties props_t = new Properties();

	// Private Variable to hold the variables
	private String TopicName = null;
	private String zookeeperHost = null;
	private String bootstrap_servers = null;
	private int num_of_records;
	private Boolean isSucre;
	private int sessionTimeoutMs;
	private int connectionTimeoutMs;
	private int maxInFlightRequests;
	private int replication;
	private int Partition;
	private Time time = null;
	private String metricGroup = null;
	private String metricType = null;
	private String value_serializer = null;
	private int buffer_memory;
	private int retries;
	private int linger_ms;

	private String key_serializer;
	private int batch_size;
	private String acks = null;
	private String GROUP_ID_CONFIG = null;
	private String AUTO_OFFSET_RESET_CONFIG = null;
	private String KEY_DESERIALIZER_CLASS_CONFIG = null;
	private String VALUE_DESERIALIZER_CLASS_CONFIG = null;
	
	
	
	
	/**
	 * Calling the Properties file and using FileInputStream to get the properties and load that in a prop variable to use later in getters
	 * @throws IOException
	 */
	public Popertiesfetcher() throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream("/home/ec2-user/MSK_Project/config1.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		props_t.load(in);
		TopicName = props_t.getProperty("TopicName");
		bootstrap_servers = props_t.getProperty("bootstrap.servers");
		num_of_records = Integer.parseInt(props_t.getProperty("NUM_OF_RECORDS"));
		zookeeperHost = props_t.getProperty("zookeeperHost");
		isSucre = Boolean.parseBoolean(props_t.getProperty("isSucre"));
		sessionTimeoutMs = Integer.parseInt(props_t.getProperty("sessionTimeoutMs"));
		connectionTimeoutMs = Integer.parseInt(props_t.getProperty("connectionTimeoutMs"));
		maxInFlightRequests = Integer.parseInt(props_t.getProperty("maxInFlightRequests"));
		replication = Integer.parseInt(props_t.getProperty("replication"));
		Partition = Integer.parseInt(props_t.getProperty("Partition"));
		time = Time.SYSTEM;
		metricGroup = props_t.getProperty("metricGroup");
		metricType = props_t.getProperty("metricType");
		value_serializer = props_t.getProperty("value.serializer");
		buffer_memory = Integer.parseInt(props_t.getProperty("buffer.memory"));
		retries = Integer.parseInt(props_t.getProperty("retries"));
		linger_ms = Integer.parseInt(props_t.getProperty("linger.ms"));

		key_serializer = props_t.getProperty("key.serializer");
		batch_size = Integer.parseInt(props_t.getProperty("batch.size"));
		acks = props_t.getProperty("acks");
		GROUP_ID_CONFIG = props_t.getProperty("GROUP_ID_CONFIG");
		AUTO_OFFSET_RESET_CONFIG = props_t.getProperty("AUTO_OFFSET_RESET_CONFIG");
		KEY_DESERIALIZER_CLASS_CONFIG = props_t.getProperty("KEY_DESERIALIZER_CLASS_CONFIG");
		VALUE_DESERIALIZER_CLASS_CONFIG = props_t.getProperty("VALUE_DESERIALIZER_CLASS_CONFIG");

	}
	
	
	
	/**
	 * Public getters which will be called by other classes to get the properties details and use in 
	 * Create topic, Producers and consumers
	 * @return
	 * @throws FileNotFoundException
	 */
	public String getTopicName() throws FileNotFoundException {
		return TopicName;

	}

	public int getNum_of_records() {
		return num_of_records;
	}

	public String getbootstrap_servers() {
		return bootstrap_servers;
	}

	public String getzookeeperHost() {
		return zookeeperHost;
	}

	public int getconnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public int getsessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public boolean getisSucre() {
		return isSucre;
	}

	public int getmaxInFlightRequests() {
		return maxInFlightRequests;
	}

	public Time gettime() {
		return time;
	}

	public String getmetricGroup() {
		return metricGroup;
	}

	public String getmetricType() {
		return metricType;
	}

	public String getvalue_serializer() {
		return value_serializer;
	}

	public long getbuffer_memory() {
		return buffer_memory;
	}

	public int getretries() {
		return retries;
	}

	public int getlinger_ms() {
		return linger_ms;
	}

	public String getkey_serializer() {
		return key_serializer;
	}

	public int getbatch_size() {
		return batch_size;
	}

	public String getacks() {
		return acks;
	}

	public String getGROUP_ID_CONFIG() {
		return GROUP_ID_CONFIG;
	}

	public String getAUTO_OFFSET_RESET_CONFIG() {
		return AUTO_OFFSET_RESET_CONFIG;
	}

	public String getKEY_DESERIALIZER_CLASS_CONFIG() {
		return KEY_DESERIALIZER_CLASS_CONFIG;
	}

	public String getVALUE_DESERIALIZER_CLASS_CONFIG() {
		return VALUE_DESERIALIZER_CLASS_CONFIG;
	}

	public int getPartition() {
		return Partition;
	}

	public int getreplication() {
		return replication;
	}

}

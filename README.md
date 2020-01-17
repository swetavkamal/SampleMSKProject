# SampleMSKProject
Shows sample API to produce and consume records


This Project declares how to use MSK(Managed Streaming Kafka) APIs and Kafka API to produce sample data and consume those
ONCE RUNNING THIS PROJECT WILL LIST CLUSTERS, PRODUCE SAMPLE RECORDS TO MSK CLUSTER AND CONSUME THEM.


HOW TO USE IT :

    Launch an Ec2 cluster in the same VPC as MSK
    Copy the project from here to your eclipse cluster
    Export as runnable jar and scp this project to Ec2 instance
    Create a folder Msk_Project and copy the jar in MSK
    Create a config file config1.properties which will have configuration details such as broker IPs, etc.
    
    
    

*** config1.Properties 
TopicName=MSK_SAMPLE_TOPIC 
NUM_OF_RECORDS=10 
bootstrap.servers=XXXXX
zookeeperHost=XXXXX 
isSucre=false 
sessionTimeoutMs=200000 
connectionTimeoutMs=15000 
maxInFlightRequests=2 
time=Time.SYSTEM 
metricGroup=myGroup 
metricType=myType 
value.serializer=org.apache.kafka.common.serialization.IntegerSerializer 
buffer.memory=33554432 
retries=0 
linger.ms=0 
key.serializer=org.apache.kafka.common.serialization.StringSerializer
batch.size=1634 
acks=0 
GROUP_ID_CONFIG=my-group 
AUTO_OFFSET_RESET_CONFIG=earliest 
VALUE_DESERIALIZER_CLASS_CONFIG=org.apache.kafka.common.serialization.IntegerDeserializer 
KEY_DESERIALIZER_CLASS_CONFIG=org.apache.kafka.common.serialization.StringDeserializer 
Partition=1 
replication=3




Finally run
java -jar MSK_Project.jar

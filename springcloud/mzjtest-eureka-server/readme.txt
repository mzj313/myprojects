java -jar mzjtest-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
java -jar mzjtest-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
http://localhost:1111
http://localhost:1112
http://localhost:1111/test

Spring cloud的服务注册及发现，不仅仅只有默认eureka，还支持Zookeeper和Consul。
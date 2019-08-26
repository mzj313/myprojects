title client-peers-lb1-8081

cd target
java -jar mzjtest-eureka-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=peers --server.port=8081 -Xmx512m

cd ..
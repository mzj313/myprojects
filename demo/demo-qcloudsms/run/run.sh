#CLASSPATH必须大写,centos区分大小写
export CLASSPATH=$CLASSPATH:smssdk.jar:json_jdk1.7.jar:demo.jar
java SmsSDKDemo -classpath $CLASSPATH
web: java -Xss512k -Xms512m -Xmx512m -XX:NativeMemoryTracking=detail -XX:+PrintNMTStatistics -XX:+UseCompressedOops -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps $JAVA_OPTS -jar target/music-all-back.jar -Dserver.port=$PORT -DMAIL_HOST=$MAIL_HOST -DMAIL_PORT=$MAIL_PORT -DMAIL_USERNAME=$MAIL_USERNAME -DMAIL_PASSWORD=$MAIL_PASSWORD -DMAIL_PTL=$MAIL_PTL -DMAIL_PTL_EBLE=$MAIL_TPL_EBLE -DMAIL_CNT_TIMEOUT=$MAIL_CNT_TIMEOUT $JAR_OPTS
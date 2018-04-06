# UndertowIssue1317
a simple project to reproduce undertow (issue 1317)[https://issues.jboss.org/browse/UNDERTOW-1317]


$ mvn package

  Running com.softinstigate.test.TestIssue1317
  Apr 06, 2018 12:46:42 PM org.xnio.Xnio <clinit>
  INFO: XNIO version 3.3.8.Final
  Apr 06, 2018 12:46:42 PM org.xnio.nio.NioXnio <clinit>
  INFO: XNIO NIO Implementation Version 3.3.8.Final

  ************* relative path: 
  ************* request path: /_logic/test
  Apr 06, 2018 12:46:43 PM io.undertow.server.Connectors executeRootHandler
  ERROR: UT005071: Undertow request failed HttpServerExchange{ GET /_logic/test request {Connection=[Keep-Alive], accept-encoding=[gzip], user-agent=[unirest-java/1.3.11], Host=[localhost:8080]} response {Connection=[keep-alive], Content-Length=[0], Date=[Fri, 06 Apr 2018 10:46:43 GMT]}}
  java.lang.IllegalStateException: wrong realitive path
  
  This clearly show that the relative path is wrong.

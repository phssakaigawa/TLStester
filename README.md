# TLStester

TODO:
プロキシをサポートしていない

TLSv1.0とTLSv1.2をクライアント側が選択するテストを行うツール


cmdline Option
httpsURL

JVM Debugging Option
```
-Dorg.apache.commons.logging.Log=org.apache.commons.logging.impl.SimpleLog
-Dorg.apache.commons.logging.simplelog.showdatetime=true
-Dorg.apache.commons.logging.simplelog.log.org.apache.http=DEBUG
-Dorg.apache.commons.logging.simplelog.log.org.apache.http.wire=ERROR
```

requirements:
Apache HTTP Components httpclient

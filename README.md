# wstest

## Building

    lein make-jumpstart
    cp target/wstest-0.1.0-SNAPSHOT.jar ./jumpstart.jar
    lein make-module

## Running

    java -jar lib/jetty-runner-9.4.9.v20180320.jar \
      --jar jumpstart.jar \
      --jar target/wstest-0.1.0-SNAPSHOT-standalone.jar
      <war-file>


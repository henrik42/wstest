# wstest

Tiny test driver for HTTP GET requests. I use this when we have touble
with HTTP access in production/test environments. Typical problems:

* HTTP proxy
* SSL certs

## Building

You'll need two JARs

* the `jumpstart.jar` which will jump-start an nREPL server in the
  target environment.

* the `wstest.jar` with `wstest.core`.

Note: you will build `jumpstart.jar` only once, since you probably
never going to touch that code.

    lein make-jumpstart
    cp target/wstest-0.1.0-SNAPSHOT.jar ./jumpstart.jar
    lein make-module

## Running

You can test-run your code with Jetty-Runner. But you'll need some
web-app (WAR) for this to work (not yet part of wstest).

    java -jar lib/jetty-runner-9.4.9.v20180320.jar \
      --jar jumpstart.jar \
      --jar target/wstest-0.1.0-SNAPSHOT-standalone.jar
      <war-file>

## Production

For _production_ (i.e. running within the target JEE/web-app in an
application sever) you just put both JARs into the classpath.

## Using

When wstest is jump-startet, you should see something like this:

    wstest.jumpstart.servlet_container_initializer/-onStartup
    wstest.jumpstart/jumpstart: starting up:
    Starting nREPL server on {:port 7888, :host "0.0.0.0"} ...
    Started nREPL server on {:port 7888, :host "0.0.0.0"}.
    wstest.jumpstart/jumpstart: done.

Now you can connect and use `wstest.core`:

    lein repl :connect 7888
    user=> (wstest.core/http-get-url "https://www.google.com/search?q=foo")


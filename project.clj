(defproject wstest "0.1.0-SNAPSHOT"
  
  ;; https://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/ReadDebug.html
  :jvm-opts ["-D__javax.net.debug=all"]

  ;; build: lein uberjar
  ;; run: java -jar target/wstest-0.1.0-SNAPSHOT-standalone.jar url <url>
  :main wstest.core
  :aot [wstest.core]
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.httpcomponents/httpclient "4.5.5"]
                 #_ [swank-clojure/swank-clojure "1.4.3"]
                 #_ [org.clojure/tools.nrepl "0.2.12"]]
  
  :plugins [[lein-swank "1.4.5"]]

  :aliases {;; executable JAR (includes Swank & nREPL)
            "make-standalone" ["with-profile" "+make-standalone" "uberjar"]
            
            ;; module which can be added to JEE/web-app (includes
            ;; httpclient JAR). You'll need nREPL and jumpstart (see
            ;; below) to jump-start the nREPL server. Otherwise you're
            ;; not able to do anything with your code.
            ;;
            ;; Note: httpclient pulls in the following:
            ;;
            ;; META-INF/maven/org.apache.httpcomponents/httpclient/pom.xml
            ;; META-INF/maven/org.apache.httpcomponents/httpcore/pom.xml
            ;; META-INF/maven/commons-logging/commons-logging/pom.xml
            ;; META-INF/maven/commons-codec/commons-codec/pom.xml
            ;;
            ;; If your JEE/web-app already contains commons-logging
            ;; and commons-codec you probably want to exclude them
            ;; from the uberjar.
            ;;
            ;; This will leave you with:
            ;;
            ;; META-INF/maven/wstest/wstest/pom.xml
            ;; META-INF/maven/org.clojure/clojure/pom.xml
            ;; META-INF/maven/org.apache.httpcomponents/httpclient/pom.xml
            ;; META-INF/maven/org.apache.httpcomponents/httpcore/pom.xml
            "make-module" ["with-profile" "+make-module" "uberjar"]}
  
  :profiles {:make-module {:exclusions [[commons-logging]
                                        [commons-codec]]}
             
             :make-standalone {:dependencies [[swank-clojure/swank-clojure "1.4.3"]
                                              [org.clojure/tools.nrepl "0.2.12"]]}})




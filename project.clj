(defproject wstest "0.1.0-SNAPSHOT"
  
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.httpcomponents/httpclient "4.5.5"]
                 #_ [swank-clojure/swank-clojure "1.4.3"]
                 #_ [org.clojure/tools.nrepl "0.2.12"]]
  
  :plugins [[lein-swank "1.4.5"]]

  ;; https://docs.oracle.com/javase/7/docs/technotes/guides/security/jsse/ReadDebug.html
  :jvm-opts ["-D__javax.net.debug=all"]
  
  :main wstest.core)

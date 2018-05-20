(ns wstest.jumpstart
  (:require [wstest.core]
            [clojure.tools.nrepl :refer :all]
            [clojure.tools.nrepl.server :as server]))

(defn start-nrepl-server
  "Example: (start-server :port 7888 :host \"127.0.0.1\")"

  [& {:keys [port host]
      :or {port 7888 host "0.0.0.0"}}]
  (let [params {:port port :host host}
        _ (.println System/out (str "Starting nREPL server on " params " ..."))
        server (try
                 (server/start-server :port port :bind host)
                 (catch Throwable t
                   (throw (ex-info
                           (format "Could not start nREPL server: %s Cause: %s" params t)
                           params t))))]
    (.println System/out (str "Started nREPL server on " params "."))
    server))


(defn jumpstart []
  (println "wstest.jumpstart/jumpstart: starting up:")
  (start-nrepl-server)
  (println "wstest.jumpstart/jumpstart: done."))

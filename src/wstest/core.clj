(ns wstest.core
  (:gen-class))

(defn log [& xs]
  (.println System/out (apply str xs)))

;; https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/

(def head-size 200)

(defn http-get-url [url-str]
  (log "Trying '" url-str "'")
  (let [url (java.net.URL. url-str)
        con (.openConnection url)
        _ (.setRequestProperty con "User-Agent" "Mozilla/5.0")
        rc (.getResponseCode con)
        _ (log "getResponseCode : " rc)]
    (with-open [in (.getInputStream con)]
      (log "Content: '" (.substring (slurp in) 0, head-size) "'"))))

(defn http-get-httpclient [url-str]
  (let [client (org.apache.http.impl.client.DefaultHttpClient.)
        request (org.apache.http.client.methods.HttpGet. url-str)
        _ (.addHeader request "User-Agent" "Mozilla/5.0")
        response (.execute client request)
        rc (-> response .getStatusLine .getStatusCode)
        _ (log "getResponseCode : " rc)]
    (with-open [in (-> response .getEntity .getContent)]
      (log "Content: '" (.substring (slurp in) 0, head-size) "'"))))

;; lein run url https://www.mopo.de/
;; lein run apache 'https://www.google.com/search?q=foo'

(defn -main [& [client url]]
  (when-not url
    (do
      (log "Usage: wstest <client:url|apache> <url>")
      (System/exit 1)))
  (condp = client
    "url" (http-get-url url)
    "apache" (http-get-httpclient url)
    (do
      (log "Unknown client '" client "'")
      (System/exit 1))))

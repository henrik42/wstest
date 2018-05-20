(ns wstest.jumpstart.servlet_container_initializer
  (:gen-class
   :implements [javax.servlet.ServletContainerInitializer]))

(defn -onStartup [& args]
  (println "wstest.jumpstart.servlet_container_initializer/-onStartup")
  (try 
    (require 'wstest.jumpstart)
    (eval (read-string "(wstest.jumpstart/jumpstart)"))
    (catch Throwable t
      (println
       (str "wstest.jumpstart.servlet_container_initializer/-onStartup:"
            "Load/run (wstest.jumpstart/jumpstart) failed : "
            t)))))


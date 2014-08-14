(ns puppetlabs.trapperkeeper.services.webserver.jetty9-middleware
  (:import (org.eclipse.jetty.server.handler HandlerWrapper)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Protocols

(defprotocol WebserverServiceMiddleware
  (process [this request response]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Classes

(gen-class :name "puppetlabs.trapperkeeper.services.webserver.jetty9-middleware.MiddleWare"
           :extends "org.eclipse.jetty.server.handler.HandlerWrapper"
           :state "middleware"
           :init "init"
           :constructors { [puppetlabs.trapperkeeper.services.webserver.jetty9_middleware.WebserverServiceMiddleware] [] }
           :exposes-methods { handle superHandle }
           :prefix "mw-")

(defn mw-init [middleware]
  [ [] middleware])

(defn mw-handle [this target base-request request response]
  (let [mw (.middleware this)]
    (if (.process mw request response)
      (.superHandle this target base-request request response))))


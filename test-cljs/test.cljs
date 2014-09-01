(ns slides2.test
  (:require [slides2.test.render-test :as render-test]))

(def success 0)

(defn ^:export run []
  (.log js/console "tests started.")
  (render-test/run)
  success)

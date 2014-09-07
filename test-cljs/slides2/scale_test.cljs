(ns slides2.test.scale-test
  (:require [slides2.scale :as scale])
  (:use [slides2.test.test-util :only [expect]]))



;; not executed in phantom
(set! (.-onload js/window)
      (fn []
        (scale/scale-to-fit! "#svg-container")
        (scale/scale-to-fit! "#sqare-container")
        (scale/scale-to-fit! "#sqare-container-2")
        (scale/scale-to-fit! "#landscape-container")
        (scale/scale-to-fit! "#portrait-container")))

(expect [:div {:class "slide-7"}
         [:div {:class ["center" "inner-wrapper"]}
          [:div {:class ["center" "inner"]} "foo"]]]

         (scale/wrap 7 [:div "foo"]))

(defn run []
  )

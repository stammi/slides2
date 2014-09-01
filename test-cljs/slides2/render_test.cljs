(ns slides2.test.render-test
  (:require [dommy.core :as dommy]
            [slides2.render :as r])
  (:use-macros [dommy.macros :only [node]])
  (:use [slides2.test.test-util :only [expect]]))

(defn run []
  (expect [:h1 {:class "single"} "2"] (r/render [:h1 {:class "single"} "2"]))
  (expect [:object {:data "foo.svg"}] (r/render(r/SvgSlide. "foo.svg"))))


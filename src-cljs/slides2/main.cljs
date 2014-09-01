(ns slides2.main
  (:require
   [slides2.render :as r]
   [slides2.statemachine :as s]))

(def presentation
  [[:h1 "headline"]
   (r/SvgSlide. "svg/001_sample-slide.svg")
   (r/SvgSlide. "svg/002_sample-slide-with-image.svg")])

(s/init-presentation! presentation)

(ns slides2.scale
  (:require [dommy.core :as dommy]
            [slides2.render :as render])
  (:use-macros [dommy.macros :only [node sel1]]))

(defn set-transform! [node transform]
    (set! (.-MozTransform (.-style node)) transform)
    (set! (.-transform (.-style node)) transform))

(defn- translate-scale-translate! [node factor]
  (set-transform! node (str "translate(-50%, -50%) scale(" factor ") translate(50%, 50%)")))

(defn- scale! [node factor] (set-transform! node (str "scale(" factor ")")))

(defn- clientBox [node] [(.-clientWidth node) (.-clientHeight node)])

(defn- scrollBox [node] [(.-scrollWidth node) (.-scrollHeight node)])

(defn- scaled-box [box factor] [(* (box 0) factor) (* (box 1) factor)])

(defn- set-dimensions! [node box]
                 (set! (.-width (.-style node)) (box 0))
                 (set! (.-height (.-style node)) (box 1)))

(defn- scale-factor [[outer-width outer-height] [inner-width inner-height]]
      (min (/ outer-width inner-width) (/ outer-height inner-height)))

(defn- scale-up! [inner inner-wrapper factor scaled-box]
  (scale! inner factor)
  (set-dimensions! inner-wrapper scaled-box))

(defn- scale-down! [inner inner-wrapper factor scaled-box inner-box]
  (set-dimensions! inner-wrapper scaled-box)
  (set-dimensions! inner inner-box)
  (translate-scale-translate! inner factor))

(defn scale-to-fit! [container]
  (let [outer (sel1 container)
        inner (sel1 [container ".inner"])
        inner-wrapper (sel1 [container ".inner-wrapper"])
        outer-box (clientBox outer)
        inner-box (scrollBox inner)
        factor (scale-factor outer-box inner-box)
        scaled-box (scaled-box inner-box factor)]
    (if (< 1 factor)
      (scale-up! inner inner-wrapper factor scaled-box)
      (scale-down! inner inner-wrapper factor scaled-box inner-box))))

(defn- idx-class [n] (str "slide-" n))

(defn wrap [idx slide]
  [:div {:id idx :class "slide"}
   [:div {:class "inner-wrapper center"}
    (dommy/add-class! (dommy/add-class! slide "inner" ) "center")]])

(defn scalable-slide! [idx slide]
  (-> (node (wrap (idx-class idx) (render/render slide)))
      (dommy/add-class! :hidden)))

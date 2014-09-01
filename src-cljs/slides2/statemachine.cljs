(ns slides2.statemachine
  "A statemachine that creates and animates an in-browser html presentation."
  (:require
   [slides2.render :as s]
   [dommy.core :as dommy]
   [dommy.utils :as utils])
  (:use-macros
    [dommy.macros :only [node sel1]]))
(def state (atom {}))

;; control
(defn advance [state]
  (let [new-cursor (inc (:cursor state))]
    (if (<= new-cursor (:max state))
      (assoc state :cursor new-cursor)
      state)))

(defn back [state]
  (let [new-cursor (dec (:cursor state))]
    (if (>= new-cursor 0)
      (assoc state :cursor new-cursor)
      state)))

(defn advance! [] (swap! state advance))

(defn back! [] (swap! state back))

(def key-map {37 back!
              39 advance!})

(defn key-event! [event]
  (let [code (.-which event)]
    (.log js/console (str "keypressed: " code))
    (if-let [fun! (get key-map code)]
      (fun!))))

;; init
(defn idx-class [n] (str "slide-" n))

(defn indexed-slide! [idx slide]
  (swap! state #(assoc % :max idx)) ;; this is a little ugly
  (-> (node [:div {:id (idx-class idx) :class "center"}  (s/render slide)])
      (dommy/add-class! :hidden)))

(defn init-slides! [presentation]
  (doseq [slide (map-indexed indexed-slide! presentation)]
    (dommy/append! (sel1 :#presentation) slide)))

(defn sel-by-idx [n] (sel1 (str "#" (idx-class n))))

(defn idx-sel [n] (str "#" (idx-class n)))

(defn switch! [old new]
  (dommy/remove-class! (sel-by-idx (:cursor new)) :hidden)
  (dommy/add-class! (sel-by-idx (:cursor old)) :hidden))

(defn init! [presentation]
  (init-slides! presentation)
  (dommy/listen! js/window :keyup key-event!)
  (add-watch state :watch-change (fn  [_ _ o n] (if (not= o n)(switch! o n))))
  (swap! state #(assoc % :cursor 0)))

(defn init-presentation! [presentation] (set! (.-onload js/window) (fn [] (init! presentation))))

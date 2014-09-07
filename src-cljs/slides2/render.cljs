(ns slides2.render
  "The slide protocol is used to render slides
  in hiccup/dommy compatible html representation.")

(defprotocol Slide (render [this]))

(defrecord SvgSlide [svg-path])

(extend-type SvgSlide
  Slide
  (render [this] [:object {:data (:svg-path this)}]))

(extend-type PersistentVector
  Slide
  (render [this] this))



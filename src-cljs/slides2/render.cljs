(ns slides2.render
  "This namespace defines the slide protocol,
  which is use to render presentation slides as
  hiccup/dommy compatible html representation.")

(defprotocol Slide (render [this]))

(defrecord SvgSlide [svg-path])

(extend-type SvgSlide
  Slide
  (render [this] [:object {:data (:svg-path this)}]))

(extend-type PersistentVector
  Slide
  (render [this] this))



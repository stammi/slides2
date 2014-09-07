(defproject slides2 "0.1.1"
  :description "Library, that allows to create a slide based presentation in the browser."
  :url "https://github.com/stammi/slides2"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojurescript "0.0-2227"]
                 [prismatic/dommy "0.1.2"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
              :builds {:dev {:source-paths ["src-cljs"]
                             :jar true
                             :compiler {
                                   :output-to "resources/public/gen/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       :test {
                              :source-paths ["src-cljs" "test-cljs"]
                              :compiler {:output-to "resources/private/gen/cljs-test.js"
                                         :optimizations :whitespace
                                         :pretty-print true}}}
              :test-commands {"my-test" ["phantomjs" "resources/private/phantom/unit-test.js" "resources/private/cljs-test.html"]}})

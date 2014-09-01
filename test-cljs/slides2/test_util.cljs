(ns slides2.test.test-util)

(defn expect
  [expected actual]
  (if (not (= expected actual))
       (.log js/console (str "Assertion fails: " ".\n Expected: \"" (str expected) "\" but was: \"" (str actual) "\"." ))
       (.log js/console (str "OK. \"" (str expected) "\" is \"" (str actual) "\"." )))
  (assert (= expected actual)))

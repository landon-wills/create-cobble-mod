(ns create-cobble-mod.cli.input-form.input-validation
  (:require [clojure.string :refer [split]]
            [create-cobble-mod.constants :refer [reserved-words]]))

(defn has-non-alphanumeric? [str] (re-find #"[^a-zA-Z0-9_]" str))

(defn validate-mod-id
  [mod-id]
  (let [invalid-characters? (has-non-alphanumeric? mod-id)
        under-2-chars? (< (count mod-id) 2)
        over-64-chars? (> (count mod-id) 64)]
    (cond invalid-characters?
            "Mod ID can only have alphanumeric characters and underscores"
          under-2-chars? "Mod ID length cannot be under 2 characters"
          over-64-chars? "Mod ID length cannot exceed 64 characters"
          :else true)))

(defn has-invalid-namespace-chars? [str] (re-find #"[^a-z0-9\.]" str))

(defn starts-with-number? [str] (boolean (re-find #"\d" str)))

(defn package-starts-with-number?
  [str]
  (->> (split str #"\.")
       (map starts-with-number?)
       (reduce #(or %1 %2))))

(defn is-reserved-word? [str]
  (some #(= % str) reserved-words))

(defn package-contains-reserved-word?
  [str]
  (->> (split str #"\.")
       (map is-reserved-word?)
       (reduce #(or %1 %2))))

(defn validate-namespace
  [namespace]
  (let [invalid-characters? (has-invalid-namespace-chars? namespace)
        starts-with-number? (package-starts-with-number? namespace)
        contains-reserved-word? (package-contains-reserved-word? namespace)]
    (cond invalid-characters? "Namespace must be undercase, alphanumeric, with periods as separators"
          starts-with-number? "A package must not start with a number"
          contains-reserved-word? "A package must not be a Java reserved word"
          :else true)))
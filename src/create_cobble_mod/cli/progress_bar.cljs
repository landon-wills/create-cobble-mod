(ns create-cobble-mod.cli.progress-bar
  (:require ["cli-progress" :as progress-bar]))

(defn start-progress [progress-bar message]
  (.start progress-bar 100 0 (clj->js {:message message})))

(defn update-progress [progress-bar value message]
  (.update progress-bar value (clj->js {:message message})))

(defn stop-progress [progress-bar]
  (.stop progress-bar))

(defn create-progress-bar []
  (progress-bar/SingleBar. (clj->js {:format "{bar} | {percentage}% | {message}"}) (-> progress-bar .-Presets .-shades_classic)))
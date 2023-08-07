(ns create-cobble-mod.app
  (:require [create-cobble-mod.cli.input-form.input-questions :as questions]
            [create-cobble-mod.cli.progress-bar :refer [create-progress-bar start-progress update-progress stop-progress]]
            [create-cobble-mod.template-gen.git :as git]
            [promesa.core :as promise]))

(defn sleep [time]
  (js/Promise. (fn [resolve] (js/setTimeout (fn [] (resolve)) time))))

(defn create
  [cwd args]
  (promise/let [mod-name (questions/mod-name)
                mod-id (questions/mod-id)
                mod-namespace (questions/mod-namespace)
                language (questions/language)
                platform (questions/platform)
                dependencies (questions/dependencies)
                progress-bar (create-progress-bar)]
    (promise/do
      (start-progress progress-bar "Fetching template...")
      (git/pull-project "https://github.com/landonjw/mal.git" mod-name)
      (update-progress progress-bar 20 "Generating namespace...")
      (sleep 1000)
      (update-progress progress-bar 40 "Updating mod configuration...")
      (sleep 500)
      (update-progress progress-bar 60 "Updating gradle configuration...")
      (sleep 300)
      (update-progress progress-bar 80 "Initializing git repository...")
      (sleep 250)
      (update-progress progress-bar 100 "Done.")
      (sleep 500)
      (stop-progress progress-bar))))

(def exports #js {:create create})
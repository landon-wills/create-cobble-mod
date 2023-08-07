(ns create-cobble-mod.template-gen.git
  (:require ["simple-git" :as simple-git]))

(defn pull-project [link destination]
  (let [git (.simpleGit simple-git)] 
    (.clone git link destination)))
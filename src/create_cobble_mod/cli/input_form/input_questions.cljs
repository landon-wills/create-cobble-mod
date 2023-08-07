(ns create-cobble-mod.cli.input-form.input-questions
  (:require [create-cobble-mod.cli.input-form.input-validation :refer [validate-mod-id validate-namespace]]
            [create-cobble-mod.cli.prompts :refer [input select checkbox]]))

(defn mod-name [] (input {:message "Choose a mod name"}))

(defn mod-id
  []
  (input {:message "Choose a mod id", :validate validate-mod-id}))

(defn mod-namespace
  []
  (input {:message "Choose a namespace", :validate validate-namespace}))

(defn language
  []
  (select {:message "Choose a language to develop in",
           :choices [{:name "Kotlin", :value :kotlin}
                     {:name "Java", :value :java}]}))

(defn platform
  []
  (select
   {:message "Select a platform to develop for",
    :choices [{:name "Fabric", :value :fabric} 
              {:name "Forge", :value :forge}
              {:name "Multi-Platform",
               :value :multi-platform,
               :description "Support for both Forge and Fabric"}]}))

(defn dependencies
  []
  (checkbox {:message "Check any optional dependencies",
             :choices [{:name "Cloud", :value :cloud :description "Command framework for easier command management"}
                       {:name "Adventure" :value :adventure :description "Library for easier text & message handling"}
                       {:name "Impactor", :value :impactor :description "Library for managing economy, placeholders, and more"}
                       {:name "GooeyLibs", :value :gooeylibs :description "Library for creating user interfaces"}]}))
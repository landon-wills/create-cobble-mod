(ns create-cobble-mod.cli.prompts
  (:require ["@inquirer/prompts" :as prompts]))

(defn checkbox [opts] (.checkbox prompts (clj->js opts)))

(defn select [opts] (.select prompts (clj->js opts)))

(defn input [opts] (.input prompts (clj->js opts)))
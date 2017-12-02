(ns leiningen.new.aoc
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "aoc"))

(defn aoc
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (->files data ["project.clj" (render "project.clj" data)]
["src/{{sanitized}}/core.clj" (render "src/day3/core.clj" data)]
[".gitignore" (render ".gitignore")]
)))

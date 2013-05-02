(ns rn-all.core
  (:import java.io.File))

(require 'clojure.string)

(def file clojure.java.io/file)

(def global-patterns '([#"name1" "name2"]
                        [#"Name1" "Name2"]
                        [#"NAME1" "NAME2"]
                        [#"clj" "clojure"]))

(defn try-renaming [patterns old-name]
  (if (empty? patterns)
    old-name
    (let [from-pattern ((first patterns) 0)
          to-pattern ((first patterns) 1)
          new-name (clojure.string/replace old-name from-pattern to-pattern)]
      (recur (rest patterns) new-name))))

(defn get-path-only [f]
  (.getParent f))

(defn my-ls [d]
  (let [old-name (.getName d)
        path (get-path-only d)
        new-name (try-renaming global-patterns old-name)]
    (if (.isDirectory d)
      (let [inner-files (.listFiles d)]
        (concat (mapcat my-ls inner-files) (list [path new-name])))
      (list [path new-name]))))

(defn get-patterns [txt]
  (map #(clojure.string/split % #">") (clojure.string/split txt #"[\n:]")))

(defn -main [& args]
  (println (get-patterns (slurp "/home/marat/rn-all/resource/patterns.txt")))
  (map #(apply println %) (my-ls (File. "/home/marat/renamer"))))


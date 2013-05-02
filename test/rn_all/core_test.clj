(ns rn-all.core-test
  (:use clojure.test
        rn-all.core))

(deftest try-renaming-test
  (testing "try-renaming function"
    (is (= "PrefixName1Main.cpp" (try-renaming () "PrefixName1Main.cpp")))
    (is (= "PrefixName2Main.cpp" (try-renaming [[#"Name1" "Name2"]] "PrefixName1Main.cpp")))
    (is (= "NAME2_CONTS.h" (try-renaming [[#"name1" "name2"] [#"NAME1" "NAME2"]] "NAME1_CONTS.h")))
    (is (= "PrefixName2Main.cpp" (try-renaming
                                   [[#"name1" "name2"]
                                   [#"Name1" "Name2"]
                                   [#"NAME1" "NAME2"]
                                   [#"clj" "clojure"]] "PrefixName1Main.cpp")))
    (is (="name21NAME2.cpp" (try-renaming
                              [[#"name1" "name2"]
                              [#"Name1" "Name2"]
                              [#"NAME1" "NAME2"]
                              [#"clj" "clojure"]] "name11NAME1.cpp")))))

(deftest get-patterns-test
  (testing "get-patterns function"
    (is (= '(["test1" "test2"] ["TEST1" "TEST2"]) (get-patterns "test1>test2\nTEST1>TEST2")))
    (is (= '(["test1" "test2"] ["TEST1" "TEST2"]) (get-patterns "test1>test2:TEST1>TEST2")))))

(deftest rename-file-tree-test
  (testing "rename-files "))
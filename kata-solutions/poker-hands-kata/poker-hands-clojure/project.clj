(defproject kata/poker-hands "0.1.0-SNAPSHOT"
  :description "Poker hands kata sample solution"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :plugins [[dev.weavejester/lein-cljfmt "0.11.2"]]            
  :main ^:skip-aot kata.poker-hands
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
(defproject re-frisk "0.3.1"
  :description "Visualize re-frame pattern data in your re-frame apps as a tree structure."
  :url "https://github.com/flexsurfer/re-frisk"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}

  :min-lein-version "2.6.1"

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.89"]
                 [reagent "0.6.0"]
                 [re-frame "0.8.0"]
                 [data-frisk-reagent "0.3.5"]
                 [com.cognitect/transit-cljs "0.8.239"]]

  :plugins [[lein-cljsbuild "1.1.3" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["devresources/public/js/compiled" "target"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src" "dev"]
                :figwheel {:on-jsload "re-frisk.core/on-js-reload"}

                :compiler {:main re-frisk.demo
                           :asset-path "js/compiled/out"
                           :output-to "devresources/public/js/compiled/re_frisk.js"
                           :output-dir "devresources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]
                           :external-config {:re-frisk {:enabled true}}}}

               {:id "reagent"
                :source-paths ["src" "dev"]
                :figwheel {:on-jsload "re-frisk.core/on-js-reload"}
                :compiler {:main re-frisk.reagent-demo
                           :asset-path "js/compiled/out"
                           :output-to "devresources/public/js/compiled/re_frisk_reagent.js"
                           :output-dir "devresources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.7.2"]
                                  [figwheel-sidecar "0.5.4-7"]
                                  [com.cemerick/piggieback "0.2.1"]]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src" "dev"]
                   ;; for CIDER
                   ;; :plugins [[cider/cider-nrepl "0.12.0"]]
                   :repl-options {; for nREPL dev you really need to limit output
                                  :init (set! *print-length* 50)
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})



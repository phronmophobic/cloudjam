(ns com.phronemophobic.cloudjam
  (:require [amazonica.core :as amazonica]
            [amazonica.aws.s3 :as s3]
            [amazonica.aws.s3transfer :as s3-transfer]))

(def s3-creds
  (merge {}
         (when-let [profile (System/getProperty "AWS_PROFILE")]
           {:profile profile
            :endpoint "us-east-1"})))

(amazonica/defcredential s3-creds)

(defn testfn [& args]
  (let [some-bytes (.getBytes "Amazonica" "UTF-8")
        input-stream (java.io.ByteArrayInputStream. some-bytes)]
    (s3/put-object {:bucket-name "cloudjam-smith-rocks"
                    :key "testkey"
                    :input-stream input-stream})))


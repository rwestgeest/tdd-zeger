(ns game.core-test
  (:require [clojure.test :refer :all]
            [game.utilities :refer :all]
            [game.board :refer :all]
            [game.core :refer :all]))

(deftest equal-move-ratings-for-new-board
  (testing "Equal ratings for a new board."
    (is (= COLUMNS_SCORE_MAP (rate-moves GAME)))))

(deftest generate-random-ai-move
  (testing "AI player generates a random move on empty board")
    (is (and (>= (generate-ai-move GAME) 0) (< (generate-ai-move GAME) WIDTH))))

(deftest winning-move-ratings-for-connect-four
  (testing "Winning move ratings for a connect four."
    (is (= {0 DEFAULT_SCORE, 1 DEFAULT_SCORE, 2 DEFAULT_SCORE, 3 18, 4 DEFAULT_SCORE, 5 DEFAULT_SCORE, 6 DEFAULT_SCORE} (rate-moves (play-connect-4-with [0 6 0 5 1 4 1]))))))

(deftest ai-selects-highest-move
  (testing "AI player generates a random move on empty board")
  (is (= 3 (generate-ai-move (play-connect-4-with [0 6 0 5 1 4 1])))))

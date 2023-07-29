(ns kata.game-of-life
  (:gen-class))

(defn living-cell 
  [x y]
  [x y true])

(defn dead-cell 
  [x y]
  [x y false])

(defn dead?
  [cell]
  (= (last cell) false))

(defn alive?
  [cell]
  (= (last cell) true))

(defn to-dead-cell
  [cell-killable? cell]
  (if (cell-killable? cell)
    (dead-cell (get cell 0) (get cell 1))
    cell))

(defn to-living-cell
  [cell-viable? cell]
  (if (cell-viable? cell)
    (living-cell (get cell 0) (get cell 1))
    cell))

(defn- distance-between
  [cell other-cell]
  (list 
   (Math/abs (- (get cell 0) (get other-cell 0))) 
   (Math/abs (- (get cell 1) (get other-cell 1)))))

(defn- distance-less-than-two?
  [cell other-cell]
  (< (reduce max (distance-between cell other-cell)) 2))

(defn neighbour-of?
  [given-cell]
  (fn [cell] (and (not(= cell given-cell)) (distance-less-than-two? cell given-cell))
  ))

(defn living-neighbours-in
  [game]
  (fn [cell] (filter alive? (filter (neighbour-of? cell) game) )))


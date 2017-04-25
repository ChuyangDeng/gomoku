# gomuku

a gomuku game

Player --> Cell --> Pawn
Board --> Cell

## Game Restrictions:
- 30 seconds to decide a game
- Not allowed to have a starting size larger than 2MB
- Not allowed to exceed the size of 30 MB at any point during the game

## Algorithms implemented by Computer Player:

- MiniMax
	* Zero-Sum based game: Player1's gain of score is balanced by Player2's loss of score
	* Player maximize its own score while the other player minimize this player's score by maximizing its own score
	* MINIMAX(p) = score(p) <if it is last move>
				 = max[MINIMAX(RESULT(s, move))] <if p = MAX>
				 = min[MINIMAX(RESULT(s, move))] <if p = MIN>
	(1) nextMove():
	for every cell in availableCells:
		currrentScore = minScore()
		if currentScore > currentBest:
			currentBest = currentScore
			bestMove = currentCell
	return bestMove
	(2) minScore():
	if lastMove: return evaluateScore(player)
	else:
		score = MAX_VALUE
		for every cell in availableCells:
			score = maxScore()
			minScore = Math.min(minScore, score)
	return result
	(3) maxScore():
	if lastMove: return evaluateScore(player)
	else:
		score = MIN_VALUE
		for every cell in availableCells:
		score = minScore()
		maxScore = Math.max(maxScore, score)
	return maxScore

- Iterative Deepening

- Monte Carlo Tree Search


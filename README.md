# Connect 4 - ICS4U RST
Connect 4 made with Swing by Roman Daher.

## Concept
Connect 4 is a strategy game where players take turns placing red or yellow pieces in a 7x6 grid. The object of the game is to place 4 pieces of your own colour in an unobstructed vertical, horizontal, or diagonal line in the grid. The first player to do this wins.

## Controls
- Click - Place a piece in a column

## Game
### The Board
    The board is a 2-dimensional integer array that contains either 0, 1, or 2 for each tile in the 7 by 6 grid. 0 indicates no piece, while 1 indicates a red piece and 2 indicates a yellow piece. 
### Adding a piece
    Adding a piece takes a colour (1 or 2) and a column (0-6) as the parameters. After the player selects their column, the game iterates from the bottom of the column to the top looking for the first empty space, "dropping" it into the column. If the column is full, the function should return 1 indicating an error. If this occurs, the game would prompt the player with a dialog box to try again. After a piece is successfully placed, the game checks for a win.
### Checking for a win
    Checking for a win is comprised of 4 sub-checks, where the program iterates through the columns, rows, ascending diagonals, and descending diagonals. This check occurs after every turn and checks if the colour that played won. For example, if red played the previous turn, the win check would see if red got 4 in a row. For each row/column/diagonal, the program counts up by one for each piece it comes across that matches the colour provided, and resets the counter otherwise. If 4 in a row is found, the game will notify the player with a dialog box that someone won, and the game concludes. 
### Draw
    After 42 moves, the table will be filled. If the move counter hits 42 and there is no win, no player can place another piece, and there is a draw. A dialog box will appear and notify the player of this.
## Display
    The graphical user interface is built with Swing. There is a 7 by 6 grid representing the board, with a 7th row at the bottoms with buttons labelled 1-7. These buttons, upon being clicked, place a piece in their respective column. Each tile will be white by default, unless it has a piece placed in the column, in which case it will be coloured either red or yellow. Dialogue windows are used to notify the player of certain events, such as a draw, or informing a player that the column they'd like to place a piece in is full. 
## Problems that Occurred
    - There was a problem where the diagonal checks would carry the counter from the edge of one diagonal to the next diagonal, which would occasionally falsely count non-wins as wins. This was solved by reseting the piece counter at the end of every diagonal.
    - There was a problem where the tiles were not displayed in the correct order. This was because the for-loops responsible for displaying them were nested in the wrong order. 
    - The move counter was too low, resulting in early draws. This was fixed by correctly recounting the number of moves required to fill the board.
    - There was a problem where when adding a piece the game would still move on to the next player's turn even if adding a piece failed, causing repeat turns. This was fixed by making add_piece return a non-zero integer result when it fails.

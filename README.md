Chess game created with Object Oriented Programming principles in Java by Kent Woolner and Gianni Grosso for the SFA CSCI3331 Final Project.
Features:
Stalemate Detection
En Passant
Queen Promotion
Castling (both sides)
Move history tracking.


Known bugs:
Kings do not properly avoid squares watched by Pawns.
Kings will sometimes improperly avoid unwatched squares.
Pieces can sometimes move to a position that will open their King to check/mate.
Promoted Queens will sometimes fail to check.

Things Kent did:
Designed the UML and the structure of the project, 
Everything JavaFX related, 
  i.e. Coded to where everything displays when App.java runs, 
       Being able to click on the pieces to display the squares it can move to, 
       the ability to click and drag the piece to a square or an opposing piece, 
       If a piece was dragged onto an opposing piece, the event(s) that captures and kills the piece, 
       Created the chessboard layout, 
       Created the squares for the chessboard, 
       Created all of the pieces and the correct images for them attached to them, 
Implemented the turn system, 
       
Kent did majority of the external logic/ front-end of the project, 

Gianni worked on the unique valid move squares (en passant/castling), testing for check/mate, as well as the support for writing moves to a replay file.


Things we both worked on together:
  logic and methods for getting the valid move squares

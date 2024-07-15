# Puzzle game
 A puzzle game in Java, using Swing and AWT framework.
 
 
 ## Preview of the game
 
 ### Image
<img width="598" alt="Screenshot 2022-12-04 alle 02 44 16" src="https://user-images.githubusercontent.com/75211163/205470141-41b18545-832b-4467-84c1-ba154034f48a.png">


### Video



https://user-images.githubusercontent.com/75211163/205470503-fbe79878-f8c6-4e18-9afd-f30572866f7c.mp4


## Details of the Game

* Every piece of the Image is wrapped in `ImagePanel`  that extends the `JPanel` from [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) framework, and is stored in a Matrix of `ImagePanel` variable.
* A copy of this matrix is called `shuffledImages` and these are the images that are changing while the shuffle and movement of the click.
* The restart button will copy inside this shuffledImages variable the first Matrix where are stored the correct order of the images.
* The movement happens when the user click a panel **near** the blank (TOP, BOTTOM, LEFT, RIGHT) and the image of the one is setted to blank and vice versa.

## Author:

Francesco0610, only me.

## Others

If you have any question, feel free to contact me on destasiofrancesco2000@gmail.com or on Instagram: [@stu.sio](https://www.instagram.com/stu.sio/@stu.sio).

> Thanks! 🦧

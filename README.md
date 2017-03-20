# IGNCodeFoo7App


Question 1:

  This is my video intro...ign passion...thing. Sorry about the rough editing and wonky volume,
it was my first time editing a video like this. :)

https://www.youtube.com/watch?v=hY9-FxwavAo

Question 2:

  This question has its own .txt file in the repo. Not much to say about this one besides that I spent WAY more time then I should 
  
  have on this one. It was a fun and frustrating quesiton.



Question 3:

For Question 2 I implemented my solution recursively.

 The function getDynamicGrid() returns a randomly generated grid based on the constant dimensions of MAX_ROW and MAX_COL.

The function getSumsRecDriver(grid) kicks off the recursive function and updates the ArrayList validSequences with 

solutions for the given grid. getSumsRecDriver initializes a boolean array, visited, to keep track of what 

cells in the maze have been traversed. Then, for each cell in the grid it calls getSums();

getSums begins by marking the current cell as visited in the boolean array. It then assumes that the cell will be valid, 

and adds the number at the current cell to the temporary sequence array. Our base case will check to see if the sum of the 

current sequence is equal to the area of the grid as and also whether or not the sequence has already been used. 

If it passes these checks while also having a size that is greater then or equal to MAX-ROW length -1 the sequence will be 

passed to the global array validSequences, for it is a valid sequence! :D

Next, we have a nested for loop that will call getSums() on every adjacent sell to the current one.  

After the nested recursive loop the current cell is removed from the tempSequence and unmarked from the visited grid.


SCALEABILITY

This program is designed to solve a grid of any size. 

The highest grid it can solve in a reasonable amount of time on my MacBook is a 4x4. 

This is due to the exponential nature of the solutions relative to the size of the grid. 

The greater the grid is the more recursive calls and cell traversal the program has to make. Some things could be done to make 

it faster, the initial nested for loop could be changed to not even attempt to traverse cells to the left because those sequences will

never yield results that arn't repeats.


Question 4: (I recorded a quick video so I could show off this program on my digital portfolio that I will link here as well 

incase anything goes wrong on your end at the program doesn't work! https://youtu.be/J3Iv8ZRgFeE)


  I chose to program an Android app for this problem. I tested all of my designs on the Nexus 6 and forgot to build it in a way
  
that is "resolution indapendent". I'm entirely self-taught on android programming so I understand if some of my practices come of
  
as bad style or in-efficient, my goal in working with IGN is to really develop myself into a good programmer by picking
  
up good habits from some of the best in the industry. I worked really hard on the app, I even made my own thumbnail play

buttons in photoshop!



Thank you so much for giving me an oppurtinity and thank you so much for helping to create this company that I have such a 

passion for!




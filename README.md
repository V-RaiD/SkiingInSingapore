# Skiing In Singapore
## Instructions To Run SkiingInSingapore

- Build
  - `mvn package`

- Use script `builder.sh` to build if you don't want to use *mvn* but make sure *mvn* is installed on the system and you are inside the `SkiingInSingapore` folder

- Build and Run
  - `chmod +x builder.sh`
  - `builder.sh <filename> totalRows totalColumn`
  *rows and columns need to be same it can only be solved for square matrix, for ease of reading total rows/columns has not been taken from file so the file format should be without first line with row/column*

- Method Used to solve
  - SkiResort initialises the Map and a List of Hill tops from which Navigation to other hills is possible,
  - Optimal Subtructure is used on each member of the List to create a longest path, currently memoisation is not used to optimise the same subroutes but can be easily achieved.
  - formula for each route => LongestRoute(x,y) = 1+max(Longest(x-1,y),Longest(x+1,y),Longest(x,y-1),Longest(x,y+1));
  - LongestRoute is implemented using name `findLongRouteAndMinima` in SkiResort class
  - `findLongRouteAndMinima` not only checks the longest route but also considers the steepest route for equidistant routes.
  - SkiHill, MinimaWeight, and other functions are helper functions for ease of development, reading and optimising.
  - No Special Library or Collection* has been used to implement the solution so no plugins / addons required. (\*except `Comparator`)

- Solution Observed
  - MaxHill - E : 1422 X : 693 Y : 603
  - MinHill - E : 0 X : 689 Y : 607
  - `E : elevation`, `X : row`, `Y : column`
  - Max Route Length - 15
  - Max Elevation - 1422

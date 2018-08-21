
# Double Moon

The Double Moon experiment was first introduced on section 1.5 of Haykin's Neural Network book. It uses a Single Layer Perceptron with 2 inputs and 2 outputs.

## Plot

### Theory

This graph represents the lines that must contain all the points of each moon. Note that `w = 6`, `r = 4` and `d = 0` .

![](aux/doublemoon.jpg)

### Implementation

In order to get the points inside of each moon on Scala, I used `matplotlib` on Python to get the expressions, which are:
>![](http://latex.codecogs.com/gif.latex?%5C%5Ca%20%3D%20%5Cpi%5Ccdot%20random%20%5C%5C%20x_%7B1%7D%20%3D%20%5Csqrt%7Brandom%7D%5Ccdot%20%5Ccos%7Ba%7D%5Ccdot%203%20%5Cpm%207%20%5Ccdot%20%5Ccos%7Ba%7D%20%5C%5C%20y_%7B1%7D%20%3D%20%5Csqrt%7Brandom%7D%5Ccdot%20%5Csin%7Ba%7D%5Ccdot%206%20&plus;%203%5Ccdot%20%5Csin%7Ba%7D%20%5C%5C%5C%5C%20a%20%3D%20%5Cpi%5Ccdot%20random%20&plus;%20%5Cpi%20%5C%5C%20x_%7B2%7D%20%3D%207%20&plus;%20%5Csqrt%7Brandom%7D%20%5Ccdot%20%5Ccos%7Ba%7D%5Ccdot%203%20%5Cpm%207%20%5Ccos%7Ba%7D%20%5C%5C%20y_%7B2%7D%20%3D%20-%28%5Csqrt%7Brandom%7D%20%5Ccdot%20%5Csin%7Ba%7D%5Ccdot%20%28-6%29%20-%204%5Ccdot%20%5Csin%7Ba%7D%29)

Where `random` means a complete random number (between 0 and 1), not a same, and `a` stands for a random angle. Note that the first equation will give you random points on inside the blue-moon and the second equation will give you random points inside the red-moon. You can see it by running `python aux/plotter.py` .

Final result:

![](aux/points.jpg)

## Results
Using parameters `w = 6; r = 4; d = 0` (which was also used on the book) and `lr = 0.05`:
* 1,000 training samples:
	*	about 1900 right answers out of 2,000	(~95%)
	*	about 9600 right answers out of 10,000	(~96%)
* 5,000 training samples:
	* about 1950 right answers out of 2,000	(~97%)
	* about 9800 right answers out of 10,000	(~98%)
* 20,000 training samples:
	* about 1980 right answers out of 2000	(~99%)
	* about 9900 right answers out of 10,000 	(~99%)

Prime Artist
============

Number Visualization tool

![screenshot.png](https://github.com/hermanzdosilovic/prime-artist/blob/master/pictures/screenshot.png)

About
-----
*Prime Artist* is a number visualization tool. By adding colors to numbers you can see how beautiful those patterns can become.

Like this one:

![example1.png](https://github.com/hermanzdosilovic/prime-artist/blob/master/pictures/example1.png)


How Numbers Are Represented
---------------------------
Currently there is only one pattern that I am using to visualize numbers. That pattern is best described on this picture:

![example3.png](https://github.com/hermanzdosilovic/prime-artist/blob/master/pictures/example3.png)

As you can see, every number here is represented by square of *30x30px*. When prime number is found it gets a different color (red in this example).

Here number in upper left corner is called __*start number*__. In this example it is `0`. But it can be any number you want from range [`-999999999`, `999999999`].

For example (start number is `100`):

![example4.png](https://github.com/hermanzdosilovic/prime-artist/blob/master/pictures/example4.png)

Colors and Size
---------------
Numbers can change their color and size.

* Color can be whatever you want.
* Minimum width and height of number can be *1px*.
* Maximum width and height of number can be *30px*.

You can also change color of composite numbers (non prime numbers). Here, composite numbers are just "background" for more interesting prime numbers.

Combining some colors and number width and height, you can get something like this:

![example2.png](https://github.com/hermanzdosilovic/prime-artist/blob/master/pictures/example2.png)

Next Step
---------
* Adding some more patterns for more fun with primes.

How to Run
----------
You must have Java installed.

1. [Download](https://github.com/hermanzdosilovic/prime-artist/archive/master.zip) or clone project.
2. If you downloaded project, extract _.zip_ file that you got.
3. In project directory open `dist` folder.
4. In `dist` folder run _prime-artist.jar_.


License
-------
The MIT License (MIT)

Copyright (c) 2014 Herman Zvonimir Došilović

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

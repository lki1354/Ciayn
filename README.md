# Project Ciayn
Controller implementation as you need (Ciayn)

## how to use the module
A few examples are listed in the folder "./src/examples". This examples should help
to use the module.

The class SignalFloat is for log all values into a list at a connection. 
It also use the Unit singleton to add a defined Unit to a Signal.
The class ReadingFloat is the same as ValueFloat but includes the Unit of the value.
How to use the Signal class is shown in the example "useSignal.java".

## run an example
* download CiaynExamplePlot.jar form release v0.5.1 and run 
* java -jar CiaynExamplePlot.jar
* at this example a PI controller and a PT1 plant is used
 * PT1 element: https://de.wikipedia.org/wiki/PT1-Glied#Sprungantwort
 

## goals of the project
1. learn java programming
2. use the object oriented programming concept
3. the final project, resulting code has to implement a controller with following features:
    * the control algorithm has to be exchangeable
    * a P, PD, PI, PID controller must be possible to use
    * the controller has to read actual values from the plant and write the output to the plant
    * the controller has to run independent form the environmental

## documentation
A HTML documentation of the Project is deposited in "./documentation/html-docs/".
The online Link is: https://rawgit.com/lki1354/Ciayn/master/documentation/html-docs/index.html

## UML diagrams
In the folder "./documentation/UML diagrams" the UML diagrams form the current design is documented.
    
## report of the project
### design decisions and learn process
#### first part
The first work what I did before starting programming was to think about the controller 
structure. The first version of the model is drawn in the PDF "./documentation/OOP_Projekt_161216.pdf". 
The next step was to learn the java language. But I stated very fast to program the first working version
of the project (https://github.com/lki1354/Ciayn/releases/tag/v0.1).
During the implementation of v0.1 I read a lot about java peculiarities. So i learned
the language a bit by doing programming and with that I saw what is possible in java and 
what not. 
#### second part
After a steep learning curve I realise that I was dissatisfied with my actual solution. 
So I change my first design / structure of the code and a new version was finished. 
(https://github.com/lki1354/Ciayn/releases/tag/v0.2) Between the end of v0.1 and 
the start of the new design I read a lot about java.
#### third part
In the last part I did the finished the new design and implements only a few basic parts.

### lessons learned
What do I have learned form this project? First of all, some basic design thoughts
has to be done before starting programming although this could be change later. It's very helpful if you draw your thoughts
on a paper. I definitely learned a new programming language.
For me this project was good to build a object oriented module but maybe It makes more
sense to realize a controller in another language.

In the first draft of the module design I want to use generics that the user can decide 
if he want to use the controller with float, double or integer values. But in the first implementation 
I became clear to me that this won't work with java. Because basic arithmetic operations are not applicable 
if generics are used. That is why I created the abstract class "Value".

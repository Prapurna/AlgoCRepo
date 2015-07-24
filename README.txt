First the system should be installed with NetBeans 6.8(or higher) and TurboC++.
Open NetBeans and create a new project. 
Create NewClass, Ifclass, Variables and copy the contents of the code appropriately into the three classes.
 In the class "NewClass", go to the event handler written for "Execute"(in the function "actionPerformed()")
In the function Runtime.getRuntime(), give the name and path where the batch file is located.
Also set the path for "input.c" file as  \TCWIN\BIN.
For example if TurboC++ is installed in C:\Program Files, then the path for "input.c" should be
C:\Program Files\TCWIN\BIN
The pictures needed must be in the same folder as that of the NetBeans project. 

CONTENTS OF THE BATCH FILE(algos.bat):-
Here we must specify the path to TCWIN.
Suppose, C:/Program Files/TCWIN/BIN is the path, then
the batch file must contain:

@echo off
C:\
cd Program Files\TCWIN\BIN
tcw input.c

Save the batch file with *.bat extension
Now run the "NewClass" class.

After you run "NewClass" it can tested with the example algorithms writtes in "example_algorithm" or one can write oen algorithm and test it.




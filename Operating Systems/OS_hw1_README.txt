NOT IMPLEMENTED ALL POINTS BELOW
This assignment helps you learn about processes and basic process management in a shell. You are asked to write a simple shell program called minish. This shell must work as follows. You start the shell by running minish program. This will give a prompt of your shell as follows:

minish>
From here onwards, you should be able to execute and control any program/command just as you would in a normal shell. For instance
minish> ls
[ Output of ls shown here. Your shell waits for ls to finish. ]
minish>

Additionally, your shell should be able to do the following:

Execute commands with multiple arguments. For example:
		minish> Command arg1 arg2 arg3
		[ Output of Command shown here. Your shell waits for Command to finish. ]
		minish>
		
Execute commands in either foreground or background mode. In foreground mode, the shell just waits for the command to complete before displaying the shell prompt again (as in the above example). In background mode, a command is executed with an ampersand & suffix. The shell prompt appears immediately after typing a command name (say Command1) and shell becomes ready to accept and execute the next command (say Command2), even as Command1 continues executing in the background. For example:
		minish> Command1 &
		minish> Command2
		[Output of Command1 and Command2 may interleave here in arbitrary order. Your shell waits for Command 2 to finish.]
		minish>
		
Maintain multiple processes running in background mode simultaneously. For example:
		minish> Command1 &
		minish> Command2 &
		minish> Command3 &
		minish> 
		[Output of Command1, Command2, and Command3 may interleave here in arbitrary order. Shell does not wait for any of the commands to finish.]

		
List all currently running background jobs using "listjobs" command.
		minish> Command1 &
		minish> Command2 &
		minish> Command3 &
		minish> listjobs
		List of backgrounded processes:
		Command 1 with PID 1000 Status:RUNNING
		Command 2 with PID 1005 Status:RUNNING
		Command 3 with PID 1007 Status:FINISHED
		minish>
		
Bring a background process to foreground using the fg command with process ID as argument. For instance, continuing from the previous example:
		minish> fg 1005
		[ Your shell waits for Command2 to finish. ]
		minish>
		
Terminate a process by pressing [Ctrl-C]. Your shell must not get killed; only the process running inside your shell must terminate.
The exit command should terminate your shell. Take care to avoid orphan processes.
The cd command must actually change the directory of your shell and the pwd command must return the current directory of your shell. Note that normal fork-exec mechanism won't work here. Why?
Do Nots:
1. DO NOT use any special wrapper libraries or classes to borrow the basic functionality required in this assignment. If in doubt, ask the instructor first BEFORE doing so.
2. DO NOT use the system(...) syscall to execute the programs in your shell directly.
3. DO NOT write five or six different programs, one for each feature. Write one single program that includes all the above features.

Hints:
1. Build and test one functionality at a time.
2. Make backup copies of partially working versions of your code. This way, if you irreparably screw up your code, then you can at least roll back to your last backup copy.
3. First design your data structures and code-structure before you begin coding each feature. Anticipate specific problems you will face.
4. Check out man page for the following:
	fork()
	execv(), execl(), execlp(), execvp() (which one should you use?)
	waitpid()
	kill()
	chdir()
	getcwd()
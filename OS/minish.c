#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <readline/readline.h>
#include <readline/history.h>
int executeCommand(int bg, char **arguments){
	
	pid_t process_id;
	process_id = fork();
	int childStatus;
	int execvpReturnValue;
	if(process_id==0 && bg==0)
		{
				execvp(arguments[0],arguments);
				perror("minish");
		}
	
	if(process_id==0 && bg==1)
		{
				setpgid(process_id, 0);
				execvp(arguments[0],arguments);
				_exit(1);
		}
		
	if(bg!=1)
				waitpid(process_id, &childStatus, WUNTRACED);
	
	
	return 1;
	
}

char **split_line(char *shellLine){
	
	size_t buffersize = 64;
	char **arguments = malloc(buffersize*sizeof(char));
	char *ptr = strtok(shellLine," \t\r\n\a");
	int i=0;
	while(ptr!=NULL){
		//printf("%s\n",ptr);
		arguments[i] = ptr;
		i++;
		ptr=strtok(NULL," \t\r\n\a");
	}
	arguments[i]=NULL;
	
	return arguments;
	
}

void childHandler(){
	//
}

void main()
{
	
	char *shellLine;
	char **arguments;
	int flag;
	size_t buffersize = 1024;
	
	shellLine = (char *)malloc(buffersize*sizeof(char));
	
	do{
		
		flag=0;
		printf("minish>");
		int bg=0, chars=0;
		signal(SIGINT, childHandler);
		chars = getline(&shellLine, &buffersize, stdin);
		arguments = split_line(shellLine);
		if(arguments[0]==NULL || chars<=1){
											flag = 1;
											shellLine = NULL;
											arguments = NULL;		
											free(shellLine);
											free(arguments);
											continue;
				}
		
		else{
											if (strcmp(arguments[0], "pwd") == 0){
												char cwd[1024];
												if (getcwd(cwd,sizeof(cwd)) != NULL)	
													printf("%s\n",cwd);
												flag =1;
											}
											
											else if (strcmp(arguments[0], "exit") == 0)
														exit(0);
											else if (strcmp(arguments[0], "cd") == 0){
														if(arguments[1]==NULL)
															fprintf(stderr,"minish: arguments required for cd\n");
														else if(chdir(arguments[1]) != 0)
															perror("minish");
														
														flag =1;
											}
											else {
														if (shellLine[chars-2]=='&'){
																shellLine[chars-2] = '\0';
																arguments = split_line(shellLine);
																bg=1;
														}
														flag = executeCommand(bg, arguments);
														
														shellLine = NULL;
														free(shellLine);
														arguments = NULL;		
														free(arguments);
												}
					
				}
				
	}while(flag);
	
}
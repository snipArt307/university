#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
	int level = atoi(argv[1]); 
	printf("The process %d staring working on level %d.\n", getpid(), level);
	if (level == 5) {
		printf("end\n");
		exit(0);
	} else {
		pid_t pid1 = fork();
		level++;
		
		char level_str[12];
                sprintf(level_str, "%d", level);
                
		if (pid1 == 0) {
			execl("/home/artem/c_prog_university/hello", "hello", level_str, (char *) NULL);
			perror("execl failed");
                        exit(1);
		}
		
		pid_t pid2 = fork();
		
		if (pid2 == 0) {
			execl("/home/artem/c_prog_university/hello", "hello", level_str, (char *) NULL);
			perror("execl failed");
                        exit(1);
		}
		
		waitpid(pid1, NULL, 0);
		waitpid(pid2, NULL, 0);
		exit(0);
	}
}

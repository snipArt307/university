#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	if (argc != 2) {
		fprintf(stderr, "Usage: %s <level>\n", argv[0]);
		exit(1);
	}

	int level = atoi(argv[1]);

	if (level > 5 || level <= 0) {
		fprintf(stderr, "Input error\n");
		exit(1);
	}
	
	char *level_str = argv[1];
        execl("./hello", "hello", level_str, "0", (char *) NULL);
        perror("execl failed");
        exit(1);
}

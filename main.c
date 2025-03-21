#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    execl("/home/artem/c_prog_university/hello", "hello", "0", (char *) NULL);
    perror("execl failed");
    exit(1);
}

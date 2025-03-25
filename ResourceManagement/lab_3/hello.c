#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    int level = atoi(argv[1]);
    int index = atoi(argv[2]);
    printf("The process %d starting working on level %d.\n", getpid(), index);

    if (level == 0) {
        printf("end\n");
        exit(0);
    } else {
        // Создаём два канала: по одному для каждого дочернего процесса
        int pipefd1[2]; // Для первого дочернего процесса
        int pipefd2[2]; // Для второго дочернего процесса

        // Проверяем успешность создания каналов
        if (pipe(pipefd1) == -1 || pipe(pipefd2) == -1) {
            perror("pipe failed");
            exit(1);
        }

        pid_t pid1 = fork();
        index++;
        level--;

        char level_str[12];
        char index_str[12];
        sprintf(index_str, "%d", index);
        sprintf(level_str, "%d", level);

        if (pid1 == 0) { // Первый дочерний процесс
            // Закрываем ненужный конец канала (чтение)
            close(pipefd1[0]);
            // Выполняем программу
            execl("./hello", "hello", level_str, index_str, (char *) NULL);
            perror("execl failed");
            // Если execl не сработал, отправляем сигнал через канал и выходим
            write(pipefd1[1], "1", 1); // Отправляем байт, чтобы родитель знал, что процесс завершился
            close(pipefd1[1]);
            exit(1);
        }

        pid_t pid2 = fork();

        if (pid2 == 0) { // Второй дочерний процесс
            // Закрываем ненужный конец канала (чтение)
            close(pipefd2[0]);
            // Выполняем программу
            execl("./hello", "hello", level_str, index_str, (char *) NULL);
            perror("execl failed");
            // Если execl не сработал, отправляем сигнал через канал и выходим
            write(pipefd2[1], "1", 1); // Отправляем байт
            close(pipefd2[1]);
            exit(1);
        }

        // Родительский процесс
        // Закрываем концы каналов для записи, так как родитель только читает
        close(pipefd1[1]);
        close(pipefd2[1]);

        // Читаем из каналов, чтобы дождаться завершения дочерних процессов
        char buf;
        read(pipefd1[0], &buf, 1); // Ждём сигнал от первого процесса
        read(pipefd2[0], &buf, 1); // Ждём сигнал от второго процесса

        // Закрываем оставшиеся концы каналов
        close(pipefd1[0]);
        close(pipefd2[0]);

        exit(0);
    }
}

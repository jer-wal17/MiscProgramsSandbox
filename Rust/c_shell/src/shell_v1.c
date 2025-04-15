#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>

int main(int argc, char *argv[])
{
    char *program;
    char *arg_list[argc - 1];
    pid_t child_pid;

    if (argc < 2)
    {
        printf("Usage: ./shell_v1 <-execlp or -execvp> <command> <add'l args for command>\n");
        exit(1);
    }

    // strcmp returns 0 if the strings are the same
    if (strcmp(argv[1], "-execlp") == 0) // using execlp
    {
        program = argv[2];

        child_pid = fork();
        if (child_pid < 0) // error
        {
            fprintf(stderr, "Fork Failed");
            return 1;
        }
        else if (child_pid == 0) // child process
        {
            execlp(program, argv[2], argv[3], argv[4], argv[5], argv[6], NULL);
            fprintf(stderr, "Error with execlp(): %s\n", strerror(errno));
        }
        else // child_pid > 0; parent process
        {
            wait(NULL);
            printf("-- the end of shell_v1 --\n");
        }
    }
    else if (strcmp(argv[1], "-execvp") == 0) // using execvp
    {
        // set program name and build arg_list with NULL termination at the end of it
        program = argv[2];
        for (int i = 2; i < argc; i++)
        {
            arg_list[i - 2] = argv[i];
        }
        arg_list[argc - 2] = NULL;

        child_pid = fork();
        if (child_pid < 0) // error
        {
            fprintf(stderr, "Fork Failed");
            return 1;
        }
        else if (child_pid == 0) // child process
        {
            execvp(program, arg_list);
            fprintf(stderr, "Error with execvp(): %s\n", strerror(errno));
        }
        else // child_pid > 0; parent process
        {
            wait(NULL);
            printf("-- the end of shell_v1 --\n");
        }
    }
    else // no execlp or execvp
    {
        child_pid = fork();
        if (child_pid < 0) // error
        {
            fprintf(stderr, "Fork Failed");
            return 1;
        }
        else if (child_pid == 0) // child process
        {
            execvp("/bin/ls", NULL);
        }
        else // child_pid > 0; parent process
        {
            wait(NULL);
            printf("-- the end of shell_v1 --\n");
        }
    }

    return 0;
}

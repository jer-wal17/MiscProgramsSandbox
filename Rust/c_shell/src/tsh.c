#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <stdbool.h>

int main(int argc, char const *argv[])
{

    char error_message[] = "An error has occurred (from JW)\n";

    if (argc > 2)
    {
        printf("Usage: tsh [exec option]\n");
        exit(1);
    }

    bool useExecvp = false;
    if (argc == 2)
    {
        if (!strcmp(argv[1], "-execvp"))
        {
            useExecvp = true;
        }
        else if (strcmp(argv[1], "-execlp")) // if it does NOT match either execvp or execlp
        {
            write(STDERR_FILENO, error_message, strlen(error_message));
        }
    }

    char **userHistory = (char **)calloc(50, sizeof(char *));
    int histCount = 0;
    int histPos = 0;
    int histRollOver = 0;

    while (true)
    {
        bool redirectingOutput = false;
        char *userPath;

        char *buffer;
        size_t size = 100;
        buffer = (char *)malloc(size * sizeof(char));
        printf("tsh> ");
        int numChars = getline(&buffer, &size, stdin);

        // Used for output redirection
        char *fileOut;
        char *newBuffer;
        int fd;
        int savedStdOut = dup(STDOUT_FILENO);

        if (strstr(buffer, " > "))
        {
            redirectingOutput = true;
            char *t1;
            char *programAndFileOut[] = {"", ""};
            int count = 0;
            for (t1 = strtok(buffer, ">"); t1 != NULL; t1 = strtok(NULL, " > "))
            {
                programAndFileOut[count] = t1;
                count++;
            }
            newBuffer = programAndFileOut[0];
            fileOut = programAndFileOut[1];
            fileOut[strlen(fileOut) - 1] = '\0';

            fd = open(fileOut, O_WRONLY | O_CREAT, 0666);
            if (dup2(fd, STDOUT_FILENO) == -1)
            {
                write(STDERR_FILENO, error_message, strlen(error_message));
            }
        }

        if (!histRollOver)
        {
            userHistory[histPos] = malloc(numChars * sizeof(char));
        }
        else
        {
            memset(userHistory[histPos], 0, 100);
        }
        strncpy(userHistory[histPos], buffer, numChars - 1);
        histCount++;
        histPos++;
        if (histPos == 50)
        {
            histPos = 0;
            histRollOver++;
        }

        char *userArgs[] = {NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL};
        char *t1;
        int numArgs = 0;

        // TODO: use dup2 to actually redirect the file, then close at the end
        if (redirectingOutput)
        {
            for (t1 = strtok(newBuffer, " \t\n"); t1 != NULL; t1 = strtok(NULL, " \t\n"))
            {
                userArgs[numArgs] = t1;
                numArgs++;
            }
        }
        else
        {
            for (t1 = strtok(buffer, " \t\n"); t1 != NULL; t1 = strtok(NULL, " \t\n"))
            {
                userArgs[numArgs] = t1;
                numArgs++;
            }
        }

        if (!userArgs[0])
        {
            continue;
        }

        char *command = userArgs[0];

        if (!strcmp(command, "exit")) // exit
        {
            exit(0);
        }
        else if (!strcmp(command, "cd"))
        {
            if (numArgs == 1 || numArgs > 2)
            {
                write(STDERR_FILENO, error_message, strlen(error_message));
            }
            else
            {
                if (chdir(userArgs[1]) == -1)
                {
                    write(STDERR_FILENO, error_message, strlen(error_message));
                }
            }
        }
        else if (!strcmp(command, "path"))
        {
            if (numArgs == 1)
            {
                if (userPath)
                {
                    printf("%s\n", userPath);
                }
                else
                {
                    printf("\n");
                }
            }
            else if (numArgs == 2)
            {
                userPath = userArgs[1];
            }
            else
            {
                write(STDERR_FILENO, error_message, strlen(error_message));
            }
        }
        else if (!strcmp(command, "history"))
        {
            if (numArgs == 1)
            {
                if (histRollOver)
                {
                    int stopNum = histPos;
                    for (int i = stopNum; i < stopNum + 50; i++)
                    {
                        if (i % 50 == stopNum)
                        {
                            continue;
                        }
                        else
                        {
                            int lineNum;
                            if (i % 50 > stopNum)
                            {
                                lineNum = i % 50 + ((histRollOver - 1) * 50);
                            }
                            else if (i % 50 < stopNum)
                            {
                                lineNum = i % 50 + histRollOver * 50;
                            }
                            printf("%d %s\n", lineNum, userHistory[i % 50]);
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < histCount; i++)
                    {
                        printf("%d %s\n", i + 1, userHistory[i]);
                    }
                }
            }
            else if (numArgs == 2)
            {
                int userHistNum = atoi(userArgs[1]);
                if (histRollOver)
                {
                    int stopNum = histPos;
                    for (int i = stopNum; i < stopNum + userHistNum; i++)
                    {
                        if (i % userHistNum == stopNum)
                        {
                            continue;
                        }
                        else
                        {
                            int lineNum;
                            if (i % userHistNum > stopNum)
                            {
                                lineNum = i % userHistNum + ((histRollOver - 1) * 50);
                            }
                            else if (i % userHistNum < stopNum)
                            {
                                lineNum = i % userHistNum + histRollOver * 50;
                            }
                            printf("%d %s\n", lineNum, userHistory[i % userHistNum]);
                        }
                    }
                }
                else
                {
                    for (int i = 0; i < userHistNum; i++)
                    {
                        printf("%d %s\n", i + 1, userHistory[i]);
                    }
                }
            }
        }
        else // external command
        {
            pid_t child_pid;

            child_pid = fork();
            if (child_pid < 0) // error
            {
                write(STDERR_FILENO, error_message, strlen(error_message));
            }
            else if (child_pid == 0) // child process
            {
                char *currPath = userPath;
                char *currPathWithCommand = (char *)malloc(strlen(currPath));
                for (currPath = strtok(currPath, ":\n"); currPath != NULL; currPath = strtok(NULL, ":\n"))
                {
                    strcpy(currPathWithCommand, currPath);
                    currPathWithCommand = strcat(currPathWithCommand, "/");
                    currPathWithCommand = strcat(currPathWithCommand, command);

                    if (useExecvp)
                    {
                        // execvp call
                        execvp(currPathWithCommand, userArgs);
                    }
                    else
                    {
                        execlp(currPathWithCommand, currPathWithCommand, userArgs[1], userArgs[2], userArgs[3], userArgs[4], userArgs[5], userArgs[6], userArgs[7], userArgs[8], userArgs[9], NULL);
                    }
                }
                write(STDERR_FILENO, error_message, strlen(error_message));
                exit(1); // if we reach this point, something went wrong and we need to exit or else we stay in the child terminal
            }
            else // child_pid > 0; parent process
            {
                if (redirectingOutput)
                {
                    dup2(savedStdOut, STDOUT_FILENO);
                }
                wait(NULL);
            }
        }
    }

    return 0;
}

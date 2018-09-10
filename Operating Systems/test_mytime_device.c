#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#define N 3
#define MAX_LENGTH 1024
struct timeval gtodTimes[N];
int main()
{
int i;
char procClockTime[N][MAX_LENGTH];

/* allocate memory for character buffers HERE before you use them */

int fd = open("/dev/mytime", O_RDONLY);
/* check for errors HERE */
if(fd<0)
				printf("Error in reading mytime device");

else{
			for( i=0; i < N; i++)
			{
				gettimeofday(&gtodTimes[i], 0);
				int bytes_read = read(fd, procClockTime[i], MAX_LENGTH);
				if(bytes_read<0)
					printf("\nError in reading from MyTime device\n");
			}
			close(fd);
			for(i=0; i < N; i++)
			{
				printf("time_from_userProgram: %ld\t%ld\n%s\n\n", gtodTimes[i].tv_sec,gtodTimes[i].tv_usec, procClockTime[i]);
				/* fix the output format appropriately in the above line */
			}

	}
}

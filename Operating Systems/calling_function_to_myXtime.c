#include<stdio.h>
#include<unistd.h>
#include<linux/unistd.h>
#include<linux/time.h>
int main(){
//int y = 2;

struct timespec time;

my_xtime(&time);

time_t sec = time.tv_sec;

printf("Current time using my_xtime system call: %d %ld\n",asctime(localtime(&sec)), time->tv_nsec);

return 0;
}
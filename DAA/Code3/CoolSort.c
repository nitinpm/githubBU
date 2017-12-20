#include <stdio.h>
#define ARR_SIZE 100
#define H_SIZE 10
void coolSort(int [],int, int [], int); //takes input array, its size, H array, H array size respectively.
void display(int [],int);	//takes input as array and array size

int main()
{
 
    int n,h,k,arr[ARR_SIZE], H[H_SIZE];
    printf("----------------------------------------Cool Sort----------------------------------------\n\n");
    
	//Taking input array
	printf("Enter the number of elements to sort:");
    scanf("%d",&n);
    printf("\nEnter the elements:\n");
    for(int i=0;i<n;i++)
        scanf("%d",&arr[i]);
	
	//Taking input H
	printf("Enter the number of elements in H:");
    scanf("%d",&h);
    printf("\nEnter the elements of H:\n");
    for(int i=0;i<h;i++)
        scanf("%d",&H[i]);
	
	if((H[0]<=n) && (H[h-1] ==1))	//H value is only acceptable when H[0] < n and H[h-1] =1 
		coolSort(arr, n, H, h);
	else 
		printf("Entered elements in H are unacceptable as \nfirst element in H should be less than total Input Data elements and \nlast element in H should be 1\n");
	
	
return 0;

}




void coolSort(int input[ARR_SIZE], int n, int H[H_SIZE], int h){
	
	int count=0;
	for(int p=0;p<h;p++){									
		
		int step=H[p], x=0;
		int i;
		while(x<H[p]){		//For each H value x we need to iterate x times to consider all elements at each step. 
				for(int j=step+x;j<n;j+=step){			//Insertion sort for each H value as a step on the array of elements
				
					int key=input[j];
					int i=j-step;
					
					while(i>=0 && input[i]>key){
						input[i+step]=input[i];
						i=i-step;
						count++;
						}
					
					input[i+step]=key;
				}
			x++;				
		}
		//printf("While Count = %d\n",count);
		printf("H=%d: ",H[p]);
		display(input,n);

		
		
	}
	
}


void display(int arr[ARR_SIZE], int n){
		for(int i=0;i<n;i++)
			printf(" %d\t",arr[i]);
		printf("\n\n");
}
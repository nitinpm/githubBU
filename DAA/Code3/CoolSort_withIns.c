#include <stdio.h>
#define ARR_SIZE 100
#define H_SIZE 10
void coolSort(int [],int, int [], int); 
void display(int [],int);
void insertionSort(int [], int);

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
	
	insertionSort(arr,n);
	//coolSort(arr, n, H, h);
	
	
return 0;

}

void coolSort(int input[ARR_SIZE], int n, int H[H_SIZE], int h){
	
	int count=0;
	for(int p=0;p<h;p++){
		
		int step=H[p], x=0;
		int i;
		while(x<H[p]){
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
		printf("While Count = %d\n",count);
		printf("H=%d: ",H[p]);
		display(input,n);

		
		
	}
	
}

void insertionSort(int arr[ARR_SIZE], int n)
{
   int i, key, j, count=0;
   for (i = 1; i < n; i++)
   {
       key = arr[i];
       j = i-1;
 
       /* Move elements of arr[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
       while (j >= 0 && arr[j] > key)
       {
           arr[j+1] = arr[j];
           j = j-1;
		   count++;
       }
       arr[j+1] = key;
   }
   printf("While Count = %d\n",count);
   display(arr,n);
}

void display(int arr[ARR_SIZE], int n){
		for(int i=0;i<n;i++)
			printf(" %d\t",arr[i]);
		printf("\n\n");
}
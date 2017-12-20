#include <stdio.h>
#define SIZE 100
void selectionSort(int [], int, int); //param1 = input array, param2 = total number of elements, param3 = k from the question

int main()
{
 
    int n,k,arr[SIZE],choice;
    printf("----------------------------------------Selection Sort----------------------------------------\n\n");
    
	//Taking input	
	printf("Enter the number of elements to sort:");
    scanf("%d",&n);
    printf("\nEnter the elements:\n");
    for(int i=0;i<n;i++)
        scanf("%d",&arr[i]);
    
    do{
			
			printf("1. Sort all elements of the array\n");
			printf("2. Find smallest k elements of the array\n");
			printf("3. Find median of the input\n");
			printf("4. Enter 4 to exit\n");
			printf("Enter your choice: ");
			scanf("%d",&choice);
			
			switch(choice){
				case 1: 
							selectionSort(arr,n,n);					//sorting and printing all elements
							printf("\nSorted Array is:\n");
							for(int i=0;i<n;i++)
								printf("%d\t",arr[i]);
							printf("\n\n\n\n");
							break;
				case 2:
							printf("\nEnter the \"k\" number of elements to sort:");
							scanf("%d",&k);
							if(k<=n){
									selectionSort(arr,n,k+1);				//sorting and printing only k smallest elements of the array.
									printf("\nk smallest elements of the array are:\n");    
									for(int i=0;i<k;i++)
										printf("%d\t",arr[i]);
									printf("\n\n\n\n");
							}
							else
									printf("Value of k should be less than total number of elements.....try again\n\n\n\n");
							break;
				case 3:
							selectionSort(arr,n,n/2+2);			//finding the median value by taking first (n/2 +1) sorted elements, passing k as n/2+2 as the selectionSort first loop iterates till k-1 
							printf("\nMedian of the array is: ");
							if(n%2==0)								//when array is even numbered we take average of middle two elements
								printf("%.1f\n\n\n\n",(arr[n/2-1]+arr[n/2])/2.0);
							else
								printf("%.1f\n\n\n\n",arr[n/2]/1.0);			//array is odd numbered we take middle element of the sorted part
							break;
				case 4: 
							printf("\nEXITING......\n");
							break;
				default:
							printf("\n Incorrect choice...EXITING...\n");
			}
		
		}while(choice<4);
    
	
    return 0;
}


void selectionSort(int sort[SIZE],int n, int k){
int min=0;

	//select repeatedly the next smallest element and put it at first then second then third.....positions.
    for(int i=0;i<k-1;i++){
		min=i;
        for(int j=i+1;j<n;j++){
            if(sort[j]<sort[min])
				min=j;
			
		}
		
		//exchange the i-th with the minimum of (i+1) to k elements of the array
		if(min!=i){
			int temp;
            temp = sort[i];
            sort[i]=sort[min];
            sort[min]=temp;
		}
    }
}
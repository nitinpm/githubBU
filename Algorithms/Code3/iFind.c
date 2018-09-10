#include <stdio.h>
#define SIZE 100
void iFind(int [], int, int); //param1 = input array, param2 = low, param3 = high

int main()
{
 
    int n,k,arr[SIZE],i;
    printf("----------------------------------------iFind Algorithm----------------------------------------\n\n");
    
	//Taking input	
	printf("Enter the number of elements in the sorted array:");
    scanf("%d",&n);
    printf("\nEnter the distinct elements in ascending order:\n");
    for(int i=0;i<n;i++)
        scanf("%d",&arr[i]);
	
	int low=0,high=n-1;
	
	i = iFind(arr,low,high);
	
	if(i==-1)
		printf("\n Sorry, no index found where arr[i] = i\n");
	else
		printf("\nThe index where i=arr[i] is at: %d\n",i);
	
	return 0;
}

void iFind(int arr[SIZE],int low, int high){
	
	int mid;
	
	if(high<low)
		reuturn -1;								//index not found anywhere in the arr[].
	
	mid = (low+high)/2;
	
	if(arr[mid]==mid)
		return mid;								//found the index where i=arr[i]
	else if(arr[mid]>mid)
		return iFind(arr,low, mid-1);
	else
		return iFind(arr,mid+1, high);
	
}
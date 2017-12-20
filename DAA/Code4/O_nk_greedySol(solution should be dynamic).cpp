/**
Consider the problem of making change for n cents using the fewest number of coins. Assume
that each coin’s value is an integer. (40 points)

Give an O(nk) time algorithm that makes change for any set of k different coin
denominations, assuming that one of the coins is a penny.
*/

#include <iostream>

#define SIZE 50
void change(int,int[], int);

int main() {
    int n,c,k;
    std::cout << "Enter the value of n: ";
    std::cin>>n;

    //denomination base
    std::cout << "Enter the number of denominations you wish to enter: ";
    std::cin>>k;

    //denomination power
    std::cout << "Enter the denominations in ascending order starting with 1 as first denomination: ";

    int denominations[SIZE];

    for(int i=0;i<=k;i++)
        std::cin>>denominations[i];

    change(n,denominations,k);

    return 0;
}

void change(int n, int den[],int k){
    int total_count = 0;

    for(int i=k;i>=0;i--){
        int den_count = 0;
        while(n>= den[i]){
            den_count++;
            total_count++;
            n = n-den[i];
        }
        std::cout<<"\nnumber of "<<den[i] << " cent coins= "<<den_count;
    }
    std::cout<<"\nTotal coins= "<<total_count;

}
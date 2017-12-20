/**
Consider the problem of making change for n cents using the fewest number of coins. Assume
that each coin’s value is an integer. (40 points)

Suppose that the available coins are in the denominations that are powers of c, i.e., the
denominations are c0, c1, …, ck for some integers c > 1 and k ≥ 1. Show that the greedy
algorithm always yields an optimal solution.
*/

#include <iostream>
#include <math.h>

#define SIZE 50
void change(int,int[], int);

int main() {
    int n,c,k;
    std::cout << "Enter the value of n: ";
    std::cin>>n;

    //denomination base
    std::cout << "Enter the value of c: ";
    std::cin>>c;

    //denomination power
    std::cout << "Enter the value of k: ";
    std::cin>>k;

    int denominations[SIZE];

    for(int i=0;i<=k;i++){
        denominations[i] = pow(c,i);
    }

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
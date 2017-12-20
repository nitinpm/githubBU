/**
Consider the problem of making change for n cents using the fewest number of coins. Assume
that each coin’s value is an integer. (40 points)

Describe a greedy algorithm to make change consisting of quarters, dimes, nickels, and
pennies. Prove that your algorithm yields an optimal solution.
*/

#include <iostream>

void change(int,int[]);
int main() {
    int n;
    std::cout << "Enter the value of n: ";
    std::cin>>n;
    int denominations[4] = {1,5,10,25}; //denominations arranged in sorted increasing order.
    change(n,denominations);

    return 0;
}

void change(int n, int den[]){
    int total_count = 0;

    for(int i=3;i>=0;i--){
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
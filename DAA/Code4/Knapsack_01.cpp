/**
Implement the dynamic programming algorithm described in class for solving the 0-1 Knapsack
problem and show that it works correctly. You are required to also implement backtracking to make sure 
you list the set of items chosen and not just the final value.
*/

#include <iostream>
#define SIZE 100
int max(int, int);

int main() {
    int w[SIZE],v[SIZE],k,i,j,included[SIZE];
    int knap[SIZE][SIZE],W;

    std::cout << "Enter the number of items: ";
    std::cin>>k;
    std::cout << "Enter the WEIGHTS: ";
    w[0]=0;
    for(int l=1;l<=k;l++)
        std::cin>>w[l];

    std::cout << "Enter the VALUE respective to the weigths entered above: ";
    v[0]=0;
    for(int l=1;l<=k;l++)
        std::cin>>v[l];

    std::cout << "\nEntered weights and value";
    std::cout<<"\nItem\tWeight\tValue";
    for(int l=0;l<k;l++)
        std::cout<<"\n"<<l+1<<"\t"<<w[l+1]<<"\t"<<v[l+1];
    std::cout<<"\n";

    std::cout<<"Enter the W (knapsack capacity): ";
    std::cin>>W;

    //initialise the knap[][]
    for(int i=0;i<=k;i++)
        knap[i][0] = 0; //weight is 0
    for(int i=0;i<=W;i++)
        knap[0][W] = 0; //items are 0

    //Fill the table
    for(int j=1;j<=W;j++){
        int W_temp=j;
        for(int i=1;i<=k;i++){
            if(W_temp>=w[i]){
                knap[i][j]=max((knap[i-1][j-w[i]]+v[i]),knap[i-1][j]);
                //W_temp -=w[i];
            }
            else
                knap[i][j]=knap[i-1][j];
        }
    }

    //print the table
    std::cout<<"\n------------------Populated Knapsack Table------------------\n";
    for(int i=0;i<=k;i++){
        for(int j=0;j<=W;j++)
            std::cout<<knap[i][j]<<"\t";
        std::cout<<"\n";
    }

    int inc=0;
    //int p=k, q=W;
    int q=W,p=k;
    while(q>0 && p>0){
            if(knap[p][q]!=knap[p-1][q]){
                included[inc]=p;
                inc++;
                q=q-w[p];
                p--;
            }
            else
                p--;
        }

    std::cout<<"\n------------------Index of Included Items--------------\n";
    for(int i=inc-1;i>=0;i--)
        std::cout<<included[i]<<"\t";

std::cout<<"\n";

    return 0;
}

int max(int a, int b){
    return (a>b)?a:b;
}
/* Dynamic Programming implementation of LCS problem */
#include<iostream>
#include<cstring>
using namespace std;

void LongestCommonSubsequence( char *X, int lenX, char *Y,  int lenY ){
    int LCScount[lenX+1][lenY+1];

//Create the LCS 2D array to get the max length of the LCS between the two strings.

//Initialise
    for (int i=0; i<=lenX; i++)
        for (int j=0; j<=lenY; j++)
            if (i == 0 || j == 0)
                LCScount[i][j] = 0;


//Filling the table
    for (int i=1; i<=lenX; i++)
        for (int j=1; j<=lenY; j++){
            if (X[i-1] == Y[j-1])
                LCScount[i][j] = LCScount[i-1][j-1] + 1;
            else
                LCScount[i][j] = max(LCScount[i-1][j], LCScount[i][j-1]);
        }

//Printing table
    for (int i=0; i<=lenX; i++){
        for (int j=0; j<=lenY; j++){
            cout << LCScount[i][j]<<" ";
        }
        cout << "\n";
    }




int lastCount = LCScount[lenX][lenY];
// Initialise the lcsString to store the LCS of X and Y strings.
    char lcsString[lastCount+1];
    lcsString[lastCount] = '\0';

    int i = lenX, j = lenY;

//Creating the lcsString, backtracking the LCScount grid.
    while (i > 0 && j > 0){

        // If the last characters match then it is part of lcsString.
        if (X[i-1] == Y[j-1]){
            lcsString[lastCount-1] = X[i-1]; //Add it to lcsString
            i--;
            j--;
            lastCount--;
        }

        //If they don't match we move either above or left as per the max value.
        else if (LCScount[i-1][j] > LCScount[i][j-1])
            i--;
        else
            j--;
    }

    cout<<"X is: "<<X<<"\n";
    cout<<"Y is: "<<Y<<"\n";
    cout << "Longest Common Subsequence length is: "<<LCScount[lenX][lenY];
    cout << "\n";
    cout << "Longest Common Subsequence is: " << lcsString;
    cout << "\n";
}

int main()
{
    char X[100],Y[100];
    cout << "Enter first string X: ";
    cin>>X;

    cout << "Enter second string Y: ";
    cin>>Y;

    int lenX = strlen(X);
    int lenY = strlen(Y);
    LongestCommonSubsequence(X,lenX, Y, lenY);
    return 0;
}
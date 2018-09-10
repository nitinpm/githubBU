#include <iostream>
#include <vector>

#define SIZE 10
#define INF 999
#define SRC 1111

using namespace std;

//function declarations
void display(int [SIZE][SIZE], int);
void dijkstras(int [SIZE][SIZE], int, int, int);
int minOfD(int *, int, int []);


int main() {

    int v, src, dest;
    int C[SIZE][SIZE];

    cout << "How many vertices? " << endl;
    cin >> v;

    //INITIALISE
    // C, distance (D), predecessor(P) array to a large number 999
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            C[i][j] = INF;


    cout << "Enter the edge weight between (if there is no edge enter 999): \n";
    for(int i=0 ; i < v; i++)
        for(int j = i+1 ; j < v; j++){
            cout << "edge weight between " << i << " and " << j << ": ";
                cin >> C[i][j];
                C[j][i] = C[i][j];
    }

    //Display the adjacency matrix
    display(C, v);


    cout << "Enter the source and destination vertex (tab/enter separated): ";
    cin >> src >> dest;

    dijkstras(C, v, src, dest);

    return 0;
}


void display(int C[SIZE][SIZE], int v){

    for (int i = 0; i < v; i++) {
        for (int j = 0; j < v; j++)
            cout << C[i][j] << "\t";
        cout << "\n";
    }

}

int minOfD(int *rowOfD,int v, int VISITED[]){ //Remember to handle - INF case of the SRC node.
    int minIndex = SIZE-1;

    for(int i = 0; i < v; i++) {
        if (VISITED[i] != 1) {
            if (rowOfD[minIndex] > rowOfD[i])
                minIndex = i;
        }

    }

    return minIndex;
}

void dijkstras(int C[SIZE][SIZE], int v, int src, int dest){
    int D[SIZE][SIZE],P[SIZE][SIZE];
    int VISITED[v];

    //INITIALISE D and P arrays to INF
    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++) {
            D[i][j] = INF;
            P[i][j] = INF;
        }





    //INITIALISE D-array's 0th row to distance of other vertices from *src*
    for (int i = 0; i < v; i++) {
        if(src!=i) {
            D[0][i] = C[src][i];


            if (C[src][i] != INF)
                P[0][i] = src;
            else
                P[0][i] = INF;
        }
    }

    //Set SRC column to 1111 value to differentiate it from other columns.
    //Init VISITED array to INF
    for (int i = 0; i < v; i++){
        D[i][src] = SRC;
        P[i][src] = SRC;
        VISITED[i] = INF;
    }





    VISITED[src] = 1;
    int fill = 1, minPrev = 0, rowWiseD = 0;
    while(fill < v) {
        minPrev = minOfD(D[rowWiseD], v, VISITED);



        //Setting all cells below minPrev to current value.
        for (int i = fill; i < v; i++){
            D[i][minPrev] = D[rowWiseD][minPrev];
            P[i][minPrev] = P[rowWiseD][minPrev];
        }

        VISITED[minPrev] = 1;

        if(fill == v-1){
            break;
        }



        rowWiseD++; //D and P array: Starting now from ROW 1 as ROW 0 is filled above

        for (int i = 0; i < v; i++) {
            if (i != src && VISITED[i] != 1) { //EXCLUDE the source  and the VISITED cell

                //FILL D
                D[rowWiseD][i] = min(D[rowWiseD - 1][i], D[rowWiseD][minPrev] + C[minPrev][i]);


                //FILL P
                if (D[rowWiseD][i] == D[rowWiseD - 1][i])
                    P[rowWiseD][i] = P[rowWiseD - 1][i];
                else
                    P[rowWiseD][i] = minPrev;
            }

        }

        fill++;
    }


    cout<<"Table D: Current value of cost of path from source to destination V - Intermediate Result\n";
    display(D, v);
    cout << "\n\nTable P: Predecessor vertex table\n";
    display(P, v);
    cout << "\n";

    cout << "Shortest distance from " << src << " to " << dest << " is: "<<D[v-1][dest]<<"\n\n";

    cout << "Shortest distance from " << src << " to " << dest << " is: ";


    //Display Path
    vector <int> path;
    path.push_back(dest);

    int p = P[v-1][dest];
    while(p!=src){
        path.push_back(p);
        p = P[v-1][p];
    }

    path.push_back(src);
    cout<<path[path.size()-1];
    for(int i=path.size()-1;i>0;i--){
        cout<<"-->"<<path[i-1];
    }
    cout << "\n";


}
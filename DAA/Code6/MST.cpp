#include <iostream>

#define SIZE 10
#define INF 999
int v;

using namespace std;


//Returns the index of min distant vertex which is not yet in MST
int minDistantVertex(int key[], bool MST[]) {
    int min_value = INT_MAX, indexOfMin;

    for (int i = 0; i < SIZE; i++)
        if (MST[i] == false && key[i] < min_value) {
            min_value = key[i];
            indexOfMin = i;
        }
    return indexOfMin;
}

void display(int C[SIZE][SIZE], int v){
    for (int i = 0; i < v; i++) {   
        for (int j = 0; j < v; j++)
            cout << C[i][j] << "\t";
        cout << "\n";
    }

    cout << "\n";
}

//Prints the MST. 
int displayMST(int parentMST[], int v, int graph[SIZE][SIZE]){
    cout<< "Edge\t\tWeight\n";
    for (int i = 1; i < v; i++)
        cout << parentMST[i] << " - " << i << "\t" << graph[i][parentMST[i]] << "\n";
}

// Function to construct and print MST for a graph represented using adjacency
// matrix representation
void primMST(int graph[SIZE][SIZE])
{
    int parentMST[v]; // MST to be stored in this
    int key[v];   // Keys to pick min weight edge
    bool MST_Set[v];  // To represent set of vertices not yet included in MST

    //INITIALISE All Keys to INF and not included set also to false.
    for (int i = 0; i < SIZE; i++) {
        key[i] = INT_MAX;
        MST_Set[i] = false;
    }


    //INCLUDE first vertex in MST
    key[0] = 0;

    // Making first picked vertex as ROOT of the MST
    parentMST[0] = -1;


    for (int count = 0; count < SIZE-1; count++)
    {
        //SELECT the min not yet included in MST
        int u = minDistantVertex(key, MST_Set);

        //Add it to MST set
        MST_Set[u] = true;

        // Update key and parentMST index of the adjacent vertices of
        // the selected vertex. (Only considering vertices not included in MST)
        for (int v = 0; v < SIZE; v++)
            if (graph[u][v] && MST_Set[v] == false && graph[u][v] <  key[v])
                parentMST[v]  = u, key[v] = graph[u][v];
    }

    //Display the MST
    displayMST(parentMST, v, graph);
}


// driver program to test above function
int main()
{
    int graph[SIZE][SIZE];

    cout << "How many vertices? " << endl;
    cin >> v;

    for (int i = 0; i < SIZE; i++)
        for (int j = 0; j < SIZE; j++)
            graph[i][j] = INF;


    cout << "Enter the edge weight between (if there is no edge enter 0): \n";
    for(int i=0 ; i < v; i++)
        for(int j = i+1 ; j < v; j++){
            cout << "edge weight between " << i << " and " << j << ": ";
            cin >> graph[i][j];
            graph[j][i] = graph[i][j];
        }


    //Display the adjacency matrix
    display(graph, v);

    // Print the solution
    primMST(graph);

    return 0;
}
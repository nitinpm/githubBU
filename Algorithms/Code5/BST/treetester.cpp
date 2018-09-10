/*----- treetester.cpp -----------------------------------------------------
                Program for testing BST.
 ------------------------------------------------------------------------*/
#include <iostream>
using namespace std;

#include "BST.h"

int main()
{
   // Testing Constructor and empty()
   BST intBST;            // test the class constructor
   cout << "Constructing empty BST\n";
   cout << "BST " << (intBST.empty() ? "is" : "is not") << " empty\n";

   // Testing insert
   cout << "\nNow insert a bunch of integers into the BST."
           "\nTry items not in the BST and some that are in it:\n";
   int number;
   for (;;)
   {
      cout << "Item to insert (-999 to stop): ";
      cin >> number;
      if (number == -999) break;
      intBST.insert(number);
   }
   cout << "BST " << (intBST.empty() ? "is" : "is not") << " empty\n";

   // Testing search()
   cout << "\n\nNow testing the search() operation."
           "\nTry both items in the BST and some not in it:\n";
   for (;;)
   {
      cout << "Item to find (-999 to stop): ";
      cin >> number;
      if (number == -999) break;
      cout << (intBST.search(number) ? "Found" : "Not found") << endl;
   }

   cout << "\ninOrder traversal below:\n";
   intBST.inOrder(intBST.myRoot);

   cout << "\npreOrder traversal below:\n";
   intBST.preOrder(intBST.myRoot);

   intBST.nodeCount(intBST.myRoot);
   cout << "\nNumber of nodes in the BST are: "<< intBST.count;
   intBST.count = 0;

    for (;;)
    {
        cout << "\nItem to DELETE (-999 to stop): ";
        cin >> number;
        if (number == -999) break;
        intBST.deleteMember(number);
        cout<<"\n";
        intBST.inOrder(intBST.myRoot);
        cout<<"\n";
        intBST.inOrder(intBST.myRoot);
    }
}

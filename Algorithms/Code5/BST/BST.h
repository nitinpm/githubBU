#include <iostream>
using namespace std;  

#ifndef BINARY_SEARCH_TREE
#define BINARY_SEARCH_TREE

class BST
{
private:
    /***** Node class *****/
    class BinNode
    {
    public:
        int data;
        BinNode * left;
        BinNode * right;

        // BinNode constructors
        // Default -- data part is default int value; both links are null.
        BinNode()
                : left(0), right(0)
        {}

        // Explicit Value -- data part contains item; both links are null.
        BinNode(int item)
                : data(item), left(0), right(0)
        {}

    };// end of class BinNode declaration

 public:
  /***** Function Members *****/
  BST();
  bool empty() const;
  bool search(const int & item) const; 
  void insert(const int & item);
  void deleteMember(const int & item);
  void inOrder(BinNode * myRoot);
  void preOrder(BinNode * myRoot);
  void nodeCount(BinNode * myRoot);
  

  
  /***** Data Members *****/
  BinNode * myRoot;
  int count=0;

}; // end of class declaration

#endif

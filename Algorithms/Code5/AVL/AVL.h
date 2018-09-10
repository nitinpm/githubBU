#include <iostream>
using namespace std;  

#ifndef BINARY_SEARCH_TREE
#define BINARY_SEARCH_TREE

class AVL
{
private:
    /***** Node class *****/
    class BinNode
    {
    public:
        int data;
        BinNode * left;
        BinNode * right;
        BinNode * parent;
        int height;

        // BinNode constructors
        // Default -- data part is default int value; both links are null.
        BinNode()
                : left(0), right(0), parent(0), height(-1)
        {}

        // Explicit Value -- data part contains item; both links are null.
        BinNode(int item)
                : data(item), left(0), right(0), height(0), parent(0)
        {}

    };// end of class BinNode declaration

 public:
  /***** Function Members *****/
  AVL();
  bool empty() const;
  bool search(const int & item) const; 
  void insert(const int & item);
  void deleteMember(const int & item);
  void inOrder(BinNode * myRoot);
  void preOrder(BinNode * myRoot);
  void nodeCount(BinNode * myRoot);
  int maxLorH(BinNode * ptr1, BinNode * ptr2);
  int heightDifference(BinNode * ptr1, BinNode * ptr2);
  void rotateRight(BinNode * ptr);
  void rotateLeft(BinNode * ptr);
  void updateHeight(BinNode * ptr);
  void balanceAVLafterDELETE(BinNode * ptr);
  void balanceAVLafterINSERT(BinNode * ptr, const int & item);
  

  
  /***** Data Members *****/
  BinNode * myRoot;
  int count=0;

}; // end of class declaration

#endif

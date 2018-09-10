#include <iostream>
//#include <iomanip>

using namespace std;

#include "BST.h"

//--- Definition of constructor
BST::BST()
: myRoot(0)
{}

//--- Definition of destructor


bool BST::empty() const
{ return myRoot == 0; }


bool BST::search(const int & item) const
{
   BinNode * locptr = myRoot;
   bool found = false;
   while (!found && locptr != 0)
   {
      if (item < locptr->data)       // descend left
        locptr = locptr->left;
      else if (locptr->data < item)  // descend right
        locptr = locptr->right;
      else                           // item found
        found = true;
   }
   return found;
}


void BST::insert(const int & item)
{
   BinNode * locptr = myRoot;   // search pointer
   BinNode * parent = 0;        // pointer to parent of current node
   bool found = false;     // indicates if item already in BST
   while (!found && locptr != 0)
   {
      parent = locptr;
      if (item < locptr->data)       // descend left
         locptr = locptr->left;
      else if (locptr->data < item)  // descend right
         locptr = locptr->right;
      else                           // item found
         found = true;
   }
   if (!found)
   {                                 // construct node containing item
      locptr = new BinNode(item);  
      if (parent == 0)               // empty tree
         myRoot = locptr;
      else if (item < parent->data )  // insert to left of parent
         parent->left = locptr;
      else                           // insert to right of parent
         parent->right = locptr;
   }
   else
      cout << "Item already in the tree\n";
}

void BST::inOrder(BinNode * ptr)
{
        if(ptr != 0) {
            inOrder(ptr->left);
            cout << ptr->data << " ";
            inOrder(ptr->right);
        }
}

void BST::preOrder(BinNode * ptr)
{
    if(ptr != 0) {
        cout << ptr->data << " ";
        preOrder(ptr->left);
        preOrder(ptr->right);
    }
}

void BST::nodeCount(BinNode * ptr)
{
    if(ptr!=0){
        if(ptr->data) count++;
        nodeCount(ptr->left);
        nodeCount(ptr->right);
    }
}

void BST::deleteMember(const int & item) {
    if (myRoot == 0)
        cout << "\nTree is empty, nothing to delete\n";
    else {
        BinNode *locptr = myRoot;   // search pointer
        BinNode *parent = 0;        // pointer to parent of current node
        bool found = false;     // indicates if item already in BST
        while (!found && locptr != 0) {
            if(!found && locptr->data!=item)
                parent = locptr;
            if (item < locptr->data) {       // descend left
                locptr = locptr->left;
            }
            else if (locptr->data < item) { // descend right
                locptr = locptr->right;
            }
            else                           // item found
                found = true;
        }


        if (found) {
            //CASE 1: Node to be deleted has no children
            if (locptr->left == 0 && locptr->right == 0) {
                if(locptr==myRoot)
                    myRoot = 0;

                else if(parent->left==locptr)
                    parent->left = 0;
                else
                    parent->right = 0;
            }






            //CASE2: Node to be deleted has 1 children
            else if((locptr->left != 0 && locptr->right == 0) || (locptr->left == 0 && locptr->right != 0)){
                if(locptr==myRoot){
                    if(locptr->right != 0)
                        myRoot = locptr->right;
                    else
                        myRoot = locptr->left;
                }
                else if(parent->left==locptr){
                        if(locptr->left!=0)
                            parent->left=locptr->left;
                        else
                            parent->left=locptr->right;
                }

                else{
                    if(locptr->left!=0)
                        parent->right=locptr->left;
                    else
                        parent->right=locptr->right;
                }

            }




            //CASE3: Node to be deleted has two children. I am taking Min(RightSubTree) to replace the current node.
            else if(locptr->left != 0 && locptr->right != 0){
                BinNode * min_RightTree = locptr;
                BinNode * minParent = min_RightTree;
                min_RightTree = min_RightTree->right;
                while(min_RightTree->left!=0){
                    minParent = min_RightTree;
                    min_RightTree = min_RightTree->left;
                }


                if (min_RightTree->right == 0) {//Smallest item on right subtree has right child
                    if(minParent->left==min_RightTree) {
                        minParent->left = 0;
                        locptr->data = min_RightTree->data;
                    }
                    else{
                        minParent->right = 0;
                        locptr->data = min_RightTree->data;
                    }

                    }
                else {
                    if(minParent->left==min_RightTree) {
                        minParent->left = min_RightTree->right;
                        locptr->data = min_RightTree->data;
                    }
                    else{
                        minParent->right = min_RightTree->right;
                        locptr->data = min_RightTree->data;
                    }
                    }
            }
        }
        else
            cout << "\nItem to delete is not in the Tree\n";
    }
}
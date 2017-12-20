#include <iostream>
//#include <iomanip>

using namespace std;

#include "AVL.h"

//--- Definition of constructor
AVL::AVL()
: myRoot(0)
{}

//--- Definition of destructor


bool AVL::empty() const
{ return myRoot == 0; }

int htDiff = 0;

bool AVL::search(const int & item) const
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


void AVL::insert(const int & item)
{
   BinNode * locptr = myRoot;   // search pointer
   BinNode * parent = 0;        // pointer to parent of current node
   bool found = false;     // indicates if item already in AVL
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


   if (!found){      // construct node containing item
      locptr = new BinNode(item);
      if (parent == 0)               // empty tree
         myRoot = locptr;


      else if (item < parent->data){
          if(parent->right == 0) {
              parent->left = locptr;
              locptr->parent = parent;
          }
          else{
              parent->left = locptr;
              locptr->parent = parent;
          }
      }
      else{
          if(parent->left == 0) {
              parent->right = locptr;
              locptr->parent = parent;
          }
          else{
              parent->right = locptr;
              locptr->parent = parent;
          }
      }


       /*After INSERT check the height difference from the added node till root
         And rotate as per the below conditions */
       if(locptr!=myRoot){
           updateHeight(parent);
           balanceAVLafterINSERT(locptr->parent, item);
       }
   }

   else
      cout << "Item already in the tree\n";
}



void AVL::inOrder(BinNode * ptr)
{
        if(ptr != 0) {
            inOrder(ptr->left);
            cout <<"data: "<< ptr->data <<"\t"<<"height: "<<ptr->height << "\n";
            inOrder(ptr->right);
        }
}

void AVL::preOrder(BinNode * ptr)
{
    if(ptr != 0) {
        cout <<"data: "<< ptr->data <<"\t"<<"height: "<<ptr->height << "\n";
        preOrder(ptr->left);
        preOrder(ptr->right);
    }
}

void AVL::nodeCount(BinNode * ptr)
{
    if(ptr!=0){
        if(ptr->data) count++;
        nodeCount(ptr->left);
        nodeCount(ptr->right);
    }
}

void AVL::deleteMember(const int & item) {
    if (myRoot == 0)
        cout << "\nTree is empty, nothing to delete\n";
    else {
        BinNode *locptr = myRoot;   // search pointer
        BinNode *parent = 0;        // pointer to parent of current node
        bool found = false;     // indicates if item already in AVL
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
                if(parent!=0) {
                    if (parent->left == locptr)
                        parent->left = 0;
                    else
                        parent->right = 0;

                    updateHeight(parent);
                    balanceAVLafterDELETE(parent);
                }
                else
                    myRoot = 0;
            }






            //CASE2: Node to be deleted has 1 children
            else if((locptr->left != 0 && locptr->right == 0) || (locptr->left == 0 && locptr->right != 0)){
                if(parent!=0) {
                    if (parent->left == locptr) {
                        if (locptr->left != 0) {
                            parent->left = locptr->left;
                            locptr->left->parent = parent;
                        } else {
                            parent->left = locptr->right;
                            locptr->right->parent = parent;
                        }

                        updateHeight(parent->left);
                        balanceAVLafterDELETE(parent->left);
                    }
                    else {
                        if (locptr->left != 0) {
                            parent->right = locptr->left;
                            locptr->left->parent = parent;
                        } else {
                            parent->right = locptr->right;
                            locptr->right->parent = parent;
                        }
                        updateHeight(parent->right);
                        balanceAVLafterDELETE(parent->right);
                    }
                }

                // ROOT to be deleted case when root has only 1 child
                else{
                    if(locptr->left != 0){
                        myRoot = locptr->left;
                        myRoot->parent = 0;
                    }
                    else{
                        myRoot = locptr->right;
                        myRoot->parent = 0;
                    }
                }

            }




            //CASE3: Node to be deleted has two children. I am taking Min(RightSubTree) to replace the current node.
            else if(locptr->left != 0 && locptr->right != 0){
                BinNode * min_RightTree = locptr;
                BinNode * minParent = locptr;
                min_RightTree = min_RightTree->right;
                while(min_RightTree->left!=0){
                    minParent = min_RightTree;
                    min_RightTree = min_RightTree->left;
                }


                if (min_RightTree->right == 0) {
                    if (minParent->left == min_RightTree) {
                        minParent->left = 0;
                        locptr->data = min_RightTree->data;
                    } else {
                        minParent->right = 0;
                        locptr->data = min_RightTree->data;
                    }
                    updateHeight(minParent);
                    balanceAVLafterDELETE(minParent);
                }
                else {
                    if (minParent->left == min_RightTree) {
                        minParent->left = min_RightTree->right;
                        min_RightTree->right->parent = minParent;
                        locptr->data = min_RightTree->data;
                    } else {
                        minParent->right = min_RightTree->right;
                        min_RightTree->right->parent = minParent;
                        locptr->data = min_RightTree->data;
                    }
                    updateHeight(minParent);
                    balanceAVLafterDELETE(minParent);
                }
            }
        }
        else
            cout << "\nItem to delete is not in the Tree\n";
    }
}



void AVL::rotateRight(BinNode *Y) {
//rotate about Y to right, now X becomes the root.
    BinNode *X = Y->left;
    BinNode *temp = X->right;

    X->right = Y;
    Y->left = temp;
    if(temp!=0) temp->parent = Y;


    if(Y->parent!=0){
        X->parent = Y->parent;
        if(Y==Y->parent->left)
            Y->parent->left = X;
        else
            Y->parent->right = X;
    }
    else{ //Y is root
        myRoot = X;
        X->parent = 0;
    }

    Y->parent = X;
    Y->height = maxLorH(Y->left,Y->right)+1;
    X->height = maxLorH(X->left,X->right)+1;


}

void AVL::rotateLeft(BinNode *Y) {
    //rotate about Y to right, now X becomes the root.
    BinNode *X = Y->right;
    BinNode *temp = X->left;

    X->left = Y;
    Y->right = temp;
    if(temp!=0) temp->parent = Y;


    if(Y->parent!=0){
        X->parent = Y->parent;
        if(Y==Y->parent->left)
            Y->parent->left = X;
        else
            Y->parent->right = X;
    }
    else{ //Y is root
        myRoot = X;
        X->parent = 0;
    }

    Y->parent = X;
    Y->height = maxLorH(Y->left,Y->right)+1;
    X->height = maxLorH(X->left,X->right)+1;

}

void AVL::updateHeight(BinNode *reversePtr) {
    while (reversePtr != 0) {
        reversePtr->height = maxLorH(reversePtr->left, reversePtr->right)+1;
        reversePtr = reversePtr->parent;
    }
}

int AVL::maxLorH(BinNode * leftTree, BinNode * rightTree){
    if(leftTree == 0 && rightTree != 0)
        return rightTree->height;
    else if(leftTree !=0 && rightTree == 0)
        return leftTree->height;
    else if(leftTree !=0 && rightTree != 0)
        return (leftTree->height>rightTree->height) ? leftTree->height : rightTree->height;
    else
        return -1;
}

int AVL::heightDifference(BinNode * leftTree, BinNode * rightTree){
    if(leftTree != 0 && rightTree != 0)
        return (leftTree->height - rightTree->height);
    else if(leftTree != 0 && rightTree == 0)
        return leftTree->height+1;
    else if(leftTree == 0 && rightTree != 0)
        return -rightTree->height-1;
    else
        return 0;
}

void AVL::balanceAVLafterDELETE(BinNode *revHtCheck) {

    while (revHtCheck != 0) {
        //Checking the difference in the heights of L and R subtree and break if > 1
        htDiff = heightDifference(revHtCheck->left, revHtCheck->right);
        if (htDiff > 1 || htDiff < -1) {
            //Case 1: Left Left
            if (htDiff > 1 && heightDifference(revHtCheck->left->left, revHtCheck->left->right) >= 0)
                rotateRight(revHtCheck);

                //Case 2: Right Right
            else if (htDiff < -1 && heightDifference( revHtCheck->right->left, revHtCheck->right->right) <= 0)
                rotateLeft(revHtCheck);

                //Case 3: Left Right
            else if (htDiff > 1 && heightDifference(revHtCheck->left->left, revHtCheck->left->right) < 0) {
                rotateLeft(revHtCheck->left);
                rotateRight(revHtCheck);
            }

                //Case 4: Right Left
            else if (htDiff < -1 && heightDifference(revHtCheck->right->left, revHtCheck->right->right) > 0) {
                rotateRight(revHtCheck->right);
                rotateLeft(revHtCheck);
            }
        }
        //update height after rotation and before next iteration
        updateHeight(revHtCheck);

        revHtCheck = revHtCheck->parent;

    }
}

void AVL::balanceAVLafterINSERT(BinNode *revHtCheck, const int & item) {

    while (revHtCheck != 0) {

        //Checking the difference in the heights of L and R subtree and rotate accordingly.
        htDiff = heightDifference(revHtCheck->left,revHtCheck->right);

        if (htDiff > 1 || htDiff < -1){
            //Case 1: Left Left
            if (htDiff > 1 && item < revHtCheck->left->data)
                rotateRight(revHtCheck);

                //Case 2: Right Right
            else if (htDiff < -1 && item > revHtCheck->right->data)
                rotateLeft(revHtCheck);

                //Case 3: Left Right
            else if (htDiff > 1 && item > revHtCheck->left->data){
                rotateLeft(revHtCheck->left);
                rotateRight(revHtCheck);
            }

                //Case 4: Right Left
            else if(htDiff < -1 && item < revHtCheck->right->data){
                rotateRight(revHtCheck->right);
                rotateLeft(revHtCheck);
            }
        }

        //update height after rotation and before next iteration
        updateHeight(revHtCheck);
        revHtCheck = revHtCheck->parent;
    }
}
#include <iostream>
#include <stddef.h>

#define FACTOR 10000000
#define mem_alloc(t) (t*)malloc(sizeof(t)*FACTOR)


int BLOCK_COUNT = 1;

struct Deque_int_Iterator{
    int* value;
    int frnt;
    int rear;
    int current_index;
    int* data;
    bool (*int_compare)(const int& o1, const int& o2);

    int& deref(Deque_int_Iterator* it){
        return *it->value;
    }

    void inc(Deque_int_Iterator* it){
        if(it->current_index == it->rear) {
            it->current_index = -1;
            it->value = nullptr;
        }

        else{
            it->current_index = (it->current_index + 1) % (FACTOR*BLOCK_COUNT);
            it->value = &it->data[it->current_index];
        }
    }

    void dec(Deque_int_Iterator* it){
        if(it->current_index == it->frnt) {
            it->current_index = -1;
            it->value = nullptr;
        }

        else if(it->current_index == -1){
            it->current_index = it->rear;
            it->value = &it->data[it->rear];
        }
        else{
            it->current_index = (it->current_index - 1) % (FACTOR*BLOCK_COUNT);
            it->value = &it->data[it->current_index];
        }
    }


};

Deque_int_Iterator begin_it_int, end_it_int;

struct Deque_int{
    const char type_name[sizeof("Deque_int")] = "Deque_int";
    int frnt;
    int rear;
    int* data;
    int num_elements;
    bool (*int_compare)(const int& o1,const int& o2);

//FN: 1
    void push_front(Deque_int *deq, int value){
        if(full(deq)) {
            //Allocate and shift if required
            int old_block_count = BLOCK_COUNT;
            deq->data = (int*)realloc(deq->data, (size_t)++BLOCK_COUNT*FACTOR);
            int i;

            if(deq->frnt == deq->rear + 1) { //shift the elements from rear+1 to the end of the new block. Also set the new front.
                for(i = old_block_count*FACTOR-1; i >= deq->frnt; i--)
                    deq->data[i + FACTOR] = deq->data[i];

                deq->frnt = deq->frnt + FACTOR;
            }
        }

        if(empty(deq)){
            deq->rear=0;
            deq->frnt = 0;
        }

        else if (deq->frnt == 0)
            deq->frnt = FACTOR*BLOCK_COUNT - 1;

        else
            deq->frnt = deq->frnt - 1;

        deq->data[deq->frnt] = value;
        deq->num_elements++;
    }

//FN: 2
    void push_back(Deque_int *deq,int value){
        if(full(deq)) {
            std::cout << "Overflow: more memory allocated";
            int old_block_count = BLOCK_COUNT;
            deq->data = (int*)realloc(deq->data, (size_t)++BLOCK_COUNT*FACTOR);
            int i;

            if(deq->frnt == deq->rear + 1) { //shift the elements from rear+1 to the end of the new block. Also set the new front.
               for(i = old_block_count*FACTOR-1; i >= deq->frnt; i--)
                   deq->data[i + FACTOR] = deq->data[i];

               deq->frnt = deq->frnt + FACTOR;
            }
        }

        if(empty(deq)){
            deq->rear=0;
            deq->frnt = 0;
        }

        else if(deq->rear == FACTOR*BLOCK_COUNT - 1)
            deq->rear = 0;
        else
            deq->rear = deq->rear + 1;

        deq->data[deq->rear] = value;
        deq->num_elements++;
    }

//FN: 3
    void pop_back(Deque_int *deq){
        if(empty(deq))
            return;

        if(deq->frnt == deq->rear){    //last element in the deque
            deq->frnt = -1;
            deq->rear = -1;
            deq->num_elements = 0;
            return;
        }
        else if(deq->rear == 0)
            deq->rear = (FACTOR*BLOCK_COUNT - 1);

        else
            deq->rear = deq->rear - 1;

        deq->num_elements--;
    }

//FN: 4
    void pop_front(Deque_int *deq){
        if(empty(deq))
            return;

        if(deq->frnt == deq->rear){    //last element in the deque
            deq->frnt = -1;
            deq->rear = -1;
            deq->num_elements = 0;
            return;
        }
        else if(deq->frnt == (FACTOR*BLOCK_COUNT - 1))
            deq->frnt = 0;

        else
            deq->frnt = deq->frnt + 1;

        deq->num_elements--;
    }

//FN: 5
    bool empty(Deque_int *deq){
        return ((deq->frnt == -1) && (deq->rear == -1));
    }

//FN: 6
    bool full(Deque_int *deq){
        return ((deq->frnt == 0 && deq->rear == FACTOR*BLOCK_COUNT - 1) || deq->frnt == deq->rear + 1);
    }

//FN: 7
    int& front(Deque_int *deq){
        return deq->data[deq->frnt];
    }


//FN: 8
    int& back(Deque_int *deq){
        return deq->data[deq->rear];
    }

//FN: 9
    //Resets the queue and its pointers and frees all the memory and allocates fresh memory.
    //Also resets BLOCK_COUNT to 1. Hence queue can be used again.
    void clear(Deque_int *deq){
        free(deq->data);
        deq->frnt = -1;
        deq->rear = -1;
        deq->num_elements = 0;
        deq->data = mem_alloc(int);
        BLOCK_COUNT = 1;
    }

//FN: 10
    int size(Deque_int *deq){
        return deq->num_elements;
    }

//FN: 11
    int& at(Deque_int *deq, int index){
        return deq->data[(deq->frnt+index)%(FACTOR*BLOCK_COUNT)];
    }

//FN: 12
    Deque_int_Iterator& begin(Deque_int *deq){

        begin_it_int.frnt = deq->frnt;
        begin_it_int.rear = deq->rear;
        begin_it_int.current_index = deq->frnt;
        begin_it_int.data = deq->data;
        begin_it_int.value = &front(deq);
        begin_it_int.int_compare = deq->int_compare;
        return begin_it_int;
    }

//FN: 13    - Only change is current_index = -1 i.e. one past the last element.
    Deque_int_Iterator& end(Deque_int *deq){
        end_it_int.frnt = deq->frnt;
        end_it_int.rear = deq->rear;
        end_it_int.current_index = -1;
        end_it_int.data = deq->data;
        end_it_int.value = &front(deq);
        end_it_int.int_compare = deq->int_compare;
        return end_it_int;
    }

//FN: 14
    void sort(Deque_int *deq, Deque_int_Iterator it1, Deque_int_Iterator it2){
        int i,j;
        for(i = 0; i < deq->num_elements - 1; i++) {
            for (j = i; j < deq->num_elements; j++) {
                int &el1 = deq->at(deq, i);
                int &el2 = deq->at(deq, j);
                bool el1_vs_el2 = deq->int_compare(el1, el2);

                if(!el1_vs_el2){
                    //swap
                    int temp;
                    temp = el1;
                    el1 = el2;
                    el2 = temp;
                }
            }
        }
    }


};

bool Deque_int_Iterator_equal(Deque_int_Iterator& it1, Deque_int_Iterator& it2) {
    return it1.current_index == it2.current_index;
}

bool Deque_int_equal(Deque_int& deq1, Deque_int& deq2){
    if(deq1.num_elements != deq2.num_elements)
        return false;

    else{
        int j;
        for(j = 0; j < deq1.num_elements; j++){
            //compare each element in deq1 and deq2
            int& el1 = deq1.at(&deq1,j);
            int& el2 = deq2.at(&deq2,j);
            //If either of the below two comparisons returns true that means the elements are different and we return false and exit.
            bool el1_vs_el2 = deq1.int_compare(el1, el2);
            bool el2_vs_el1 = deq1.int_compare(el2, el1);

            if(el1_vs_el2 || el2_vs_el1)
                return false;
        }
        //If till end of the loop all are equal then return true.
        return true;
    }
}

void Deque_int_dtor(Deque_int *deq){
    free(deq->data);
}


void Deque_int_ctor(Deque_int *deq, bool (*fptr)(const int& o1, const int& o2)){
    deq->data = mem_alloc(int);
    deq->frnt = -1;
    deq->rear = -1;
    deq->num_elements = 0;
    deq->int_compare = fptr;
}


bool
int_less(const int &o1, const int &o2) {
    return o1 < o2;
}




int main() {


    Deque_int deq;
    Deque_int_ctor(&deq, int_less);

    deq.push_back(&deq, 5);
    deq.push_back(&deq, 2);
    deq.push_back(&deq, 4);
    deq.push_front(&deq, 0);
    deq.push_front(&deq, 9);

    int j;
    for(j = 0; j < deq.num_elements; j++)
        printf("%d\t", deq.at(&deq,j));

    deq.sort(&deq, deq.begin(&deq), deq.end(&deq));

    int k;
    printf("\n");
    for(k = 0; k < deq.num_elements; k++)
        printf("%d\t", deq.at(&deq,k));


    Deque_int_dtor(&deq);

    return 0;
}
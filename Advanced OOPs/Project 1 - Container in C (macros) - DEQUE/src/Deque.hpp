#include <stddef.h>

#ifndef DEQUE_H

#define FACTOR 10000000
#define mem_alloc(type) (type*)malloc(sizeof(type)*FACTOR)

#define Deque_DEFINE(t)\
	struct Deque_##t;\
	struct Deque_##t##_Iterator;\
	int BLOCK_COUNT_##t = 1;\
\
struct Deque_##t##_Iterator{\
    t* value;\
    int frnt;\
    int rear;\
    int current_index;\
    t* data;\
    bool (*t##_compare)(const t& o1, const t& o2);\
\
    t& deref(Deque_##t##_Iterator* it){\
        return *it->value;\
    }\
\
    void inc(Deque_##t##_Iterator* it){\
        if(it->current_index == it->rear) {\
            it->current_index = -1;\
            it->value = nullptr;\
        }\
\
        else{\
            it->current_index = (it->current_index + 1) % (FACTOR*BLOCK_COUNT_##t);\
            it->value = &it->data[it->current_index];\
        }\
    }\
\
    void dec(Deque_##t##_Iterator* it){\
        if(it->current_index == it->frnt) {\
            it->current_index = -1;\
            it->value = nullptr;\
        }\
\
        else if(it->current_index == -1){\
            it->current_index = it->rear;\
            it->value = &it->data[it->rear];\
        }\
        else{\
            it->current_index = (it->current_index - 1) % (FACTOR*BLOCK_COUNT_##t);\
            it->value = &it->data[it->current_index];\
        }\
    }\
\
\
};\
\
Deque_##t##_Iterator begin_##t, end_##t;\
\
struct Deque_##t{\
    const char type_name[sizeof("Deque_"#t)] = "Deque_"#t;\
    int frnt;\
    int rear;\
    t* data;\
    int num_elements;\
    bool (*t##_compare)(const t& o1,const t& o2);\
\
    void push_front(Deque_##t *deq, t value){\
        if(full(deq)) {\
            int old_BLOCK_COUNT_##t = BLOCK_COUNT_##t;\
            deq->data = (t*)realloc(deq->data, (size_t)++BLOCK_COUNT_##t*FACTOR);\
            int i;\
\
            if(deq->frnt == deq->rear + 1) { \
                for(i = old_BLOCK_COUNT_##t*FACTOR-1; i >= deq->frnt; i--)\
                    deq->data[i + FACTOR] = deq->data[i];\
\
                deq->frnt = deq->frnt + FACTOR;\
            }\
        }\
\
        if(empty(deq)){\
            deq->rear=0;\
            deq->frnt = 0;\
        }\
\
        else if (deq->frnt == 0)\
            deq->frnt = FACTOR*BLOCK_COUNT_##t - 1;\
\
        else\
            deq->frnt = deq->frnt - 1;\
\
        deq->data[deq->frnt] = value;\
        deq->num_elements++;\
    }\
\
    void push_back(Deque_##t *deq,t value){\
        if(full(deq)) {\
            int old_BLOCK_COUNT_##t = BLOCK_COUNT_##t;\
            deq->data = (t*)realloc(deq->data, (size_t)++BLOCK_COUNT_##t*FACTOR);\
            int i;\
\
            if(deq->frnt == deq->rear + 1) { \
               for(i = old_BLOCK_COUNT_##t*FACTOR-1; i >= deq->frnt; i--)\
                   deq->data[i + FACTOR] = deq->data[i];\
\
               deq->frnt = deq->frnt + FACTOR;\
            }\
        }\
\
        if(empty(deq)){\
            deq->rear=0;\
            deq->frnt = 0;\
        }\
\
        else if(deq->rear == FACTOR*BLOCK_COUNT_##t - 1)\
            deq->rear = 0;\
        else\
            deq->rear = deq->rear + 1;\
\
        deq->data[deq->rear] = value;\
        deq->num_elements++;\
    }\
\
    void pop_back(Deque_##t *deq){\
        if(empty(deq))\
            return;\
\
        if(deq->frnt == deq->rear){    \
            deq->frnt = -1;\
            deq->rear = -1;\
            deq->num_elements = 0;\
            return;\
        }\
        else if(deq->rear == 0)\
            deq->rear = (FACTOR*BLOCK_COUNT_##t - 1);\
\
        else\
            deq->rear = deq->rear - 1;\
\
        deq->num_elements--;\
    }\
\
    void pop_front(Deque_##t *deq){\
        if(empty(deq))\
            return;\
\
        if(deq->frnt == deq->rear){    \
            deq->frnt = -1;\
            deq->rear = -1;\
            deq->num_elements = 0;\
            return;\
        }\
        else if(deq->frnt == (FACTOR*BLOCK_COUNT_##t - 1))\
            deq->frnt = 0;\
\
        else\
            deq->frnt = deq->frnt + 1;\
\
        deq->num_elements--;\
    }\
\
    bool empty(Deque_##t *deq){\
        return ((deq->frnt == -1) && (deq->rear == -1));\
    }\
\
    bool full(Deque_##t *deq){\
        return ((deq->frnt == 0 && deq->rear == FACTOR*BLOCK_COUNT_##t - 1) || deq->frnt == deq->rear + 1);\
    }\
\
\
    t& front(Deque_##t *deq){\
        return deq->data[deq->frnt];\
    }\
\
\
    t& back(Deque_##t *deq){\
        return deq->data[deq->rear];\
    }\
\
    void clear(Deque_##t *deq){\
        free(deq->data);\
        deq->frnt = -1;\
        deq->rear = -1;\
        deq->num_elements = 0;\
        deq->data = mem_alloc(t);\
        BLOCK_COUNT_##t = 1;\
    }\
\
    int size(Deque_##t *deq){\
        return deq->num_elements;\
    }\
\
    t& at(Deque_##t *deq, int index){\
        return deq->data[(deq->frnt+index)%(FACTOR*BLOCK_COUNT_##t)];\
    }\
\
    Deque_##t##_Iterator& begin(Deque_##t *deq){\
\
        begin_##t.frnt = deq->frnt;\
        begin_##t.rear = deq->rear;\
        begin_##t.current_index = deq->frnt;\
        begin_##t.data = deq->data;\
        begin_##t.value = &front(deq);\
        begin_##t.t##_compare = deq->t##_compare;\
        return begin_##t;\
    }\
\
    Deque_##t##_Iterator& end(Deque_##t *deq){\
        end_##t.frnt = deq->frnt;\
        end_##t.rear = deq->rear;\
        end_##t.current_index = -1;\
        end_##t.data = deq->data;\
        end_##t.value = &front(deq);\
        end_##t.t##_compare = deq->t##_compare;\
        return end_##t;\
    }\
\
    void sort(Deque_##t *deq, Deque_##t##_Iterator it1, Deque_##t##_Iterator it2){\
        int i,j;\
        for(i = 0; i < deq->num_elements - 1; i++) {\
            for (j = i; j < deq->num_elements; j++) {\
                t &el1 = deq->at(deq, i);\
                t &el2 = deq->at(deq, j);\
                bool el1_vs_el2 = deq->t##_compare(el1, el2);\
\
                if(!el1_vs_el2){\
                    t temp;\
                    temp = el1;\
                    el1 = el2;\
                    el2 = temp;\
                }\
            }\
        }\
    }\
	void dtor(Deque_##t *deq){\
    free(deq->data);\
}\
\
\
\
};\
\
bool Deque_##t##_Iterator_equal(Deque_##t##_Iterator& it1, Deque_##t##_Iterator& it2) {\
    return it1.current_index == it2.current_index;\
}\
\
bool Deque_##t##_equal(Deque_##t& deq1, Deque_##t& deq2){\
    if(deq1.num_elements != deq2.num_elements)\
        return false;\
\
    else{\
        int j;\
        for(j = 0; j < deq1.num_elements; j++){\
            t& el1 = deq1.at(&deq1,j);\
            t& el2 = deq2.at(&deq2,j);\
            bool el1_vs_el2 = deq1.t##_compare(el1, el2);\
            bool el2_vs_el1 = deq1.t##_compare(el2, el1);\
\
            if(el1_vs_el2 || el2_vs_el1)\
                return false;\
        }\
        return true;\
    }\
}\
\
\
void Deque_##t##_ctor(Deque_##t *deq, bool (*fptr)(const t& o1, const t& o2)){\
    deq->data = mem_alloc(t);\
    deq->frnt = -1;\
    deq->rear = -1;\
    deq->num_elements = 0;\
    deq->t##_compare = fptr;\
}\

#endif
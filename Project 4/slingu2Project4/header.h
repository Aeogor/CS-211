//
//  header.h
//  main.c
//
//  Created by Srinivas Lingutla on 10/13/16.
//  Copyright © 2016 Srinivas Lingutla. All rights reserved.
//
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>


#ifndef header_h
#define header_h


#endif /* header_h */
//DEFINING TRUE = 1 AND  FALSE = 0
#define FALSE 0
#define TRUE 1

//************************************************************************************************************

// A linked list for the queue
struct Node
{
    int group_size;
    char Name[40];
    char status[15];

    struct Node *next;
};


//************************************************************************************************************

//pointer to the queue
struct Queue
{
    struct Node *front, *back;
};

//************************************************************************************************************

struct Node* new_Node(int k, char *name, char *status);
struct Queue *createQueue();
struct Node *pop(struct Queue *q);

void clearToEoln();
int getNextNWSChar ();
int getPosInt ();
char *getName();
void clearToEoln();
void printCommands();


void doAdd (struct Queue *q);
void doCallAhead (struct Queue *q);
void doWaiting (struct Queue *q);
void doRetrieve (struct Queue *q);
void doList (struct Queue *q);
void doDisplay (struct Queue *q);


void addToList(struct Queue **q, int size, char *name , char *status);
int doesNameExist(struct Queue *q, char *name);
void updateStatus(struct Queue *q, char *name);
char *retrieveAndRemove(struct Queue *q, int size);
int countGroupsAhead(struct Queue *q, char *name);
void displayGroupSizeAhead(struct Queue *q, char *name);
void displayListInformation(struct Queue *q);

//
//  main.c
//
//  Created by Srinivas Lingutla on 10/13/16.
//  Copyright © 2016 Srinivas Lingutla. All rights reserved.
//




#include "header.h"

extern bool debug;

//************************************************************************************************************

//Create a new Node for the queue with all the required details
struct Node* new_Node(int size, char *name, char *status)
{
    struct Node *temp = (struct Node*)malloc(sizeof(struct Node));
    temp->group_size = size;
    strcpy(temp->Name, name);
    strcpy(temp->status, status);
    temp->next = NULL;
    return temp;

}


//************************************************************************************************************

//Pop the front node from the queue
struct Node *pop(struct Queue *queue)
{
    //return if the list is empty
    if (queue->front == NULL)  return NULL;

    //pop the node from the front
    struct Node *temp = queue->front;
    queue->front = queue->front->next;

    //if the list becomes empty after popping the node, equate both front and back to null
    if (queue->front == NULL)  queue->back = NULL;

    return temp;
}

//************************************************************************************************************

// function to add a new node at the end of the list
void addToList(struct Queue **queue, int size, char *name , char *status)
{
    //debug information
    if(debug == TRUE){
        printf("   ADDING NEW GROUP AT THE END OF THE LINE...(addToList)\n");
    }

    //creating a new node using the function and passing the required parameters
    struct Node *new = new_Node(size, name, status );

    //if the queue is empty, then point the head and end pointer to the same location temp
    if ((*queue)->back == NULL)
    {
        (*queue)->front = (*queue)->back = new;
        (*queue)->front->next = (*queue)->back->next = NULL;
        return;
    }

    //insert the new node at the end of the list
    (*queue)->back->next = new;
    (*queue)->back = new;

}

//************************************************************************************************************
//function to verify whether a name exists or not
int doesNameExist(struct Queue *q, char *name)
{
    //debug info
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   VERIFYING WHETHER THE NAME EXISTS...(doesNameExist)\n");
    }
    //a tmep pointer to iterate through the list
    struct Queue temp = *q;
    while (temp.front != NULL) {

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", temp.front->Name, temp.front->group_size, temp.front->status);

        //if found return true
        if(strcmp(temp.front->Name, name) == 0)
            return TRUE; //true
        else
            temp.front = temp.front->next;
    }

    return FALSE; //false

}
//************************************************************************************************************

//function to change from waiting to call ahead
void updateStatus(struct Queue *q, char *name)
{
    //debug info
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   UPDATING THE STATUS OF '%s'...(updateStatus)\n", name);
    }
    //list is empty if front is null
    if(q->front == NULL){
        printf("**List is Empty\n");
        return;
      }
    //getting a new variable to iterate through the linked list and saving the front pointer
    struct Queue *temp = q;
    struct Node *front = temp->front;

    while (temp->front != NULL) {

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", temp->front->Name, temp->front->group_size, temp->front->status);

        //if name is found change the status to waiting
        if(strcmp(temp->front->Name, name) == 0){

            if(strcmp(temp->front->status, "Waiting") == 0){
                printf("\nGroup <%s> already in restaurant\n", name);
                q->front = front;
                return; //false
            }
            else{
                strcpy(temp->front->status, "Waiting");
                q->front = front;
                return;  //true
            }
        }
        else
            temp->front = temp->front->next;

    }
    q->front = front;
    printf("\nGroup <%s> not in the list\n", name);
    return; //false
}
//************************************************************************************************************

//function to remove the group from the list
char *retrieveAndRemove(struct Queue *q, int size)
{
    //debug info
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   FINDING A SUITABLE GROUP...  (retrieveAndRemove)\n");
    }

    //if the list is empty
    if(q->front == NULL){
        printf("**List is Empty\n");
        return "";
      }

    //run this if statement if its the first group
    if(size >= q->front->group_size && strcmp(q->front->status, "Waiting") == 0){

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", q->front->Name, q->front->group_size, q->front->status);

        struct Node *removed = pop(q); //remove from front
        return removed->Name;
    }
    //variable to iterate through the list
    struct Queue temp = *q;
    struct Queue prev = *q;
    struct Node *front = temp.front;
    //while loop till the name is found or the list is empty
    while (temp.front != NULL) {

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", temp.front->Name, temp.front->group_size, temp.front->status);

        if(size >= temp.front->group_size && strcmp(temp.front->status, "Waiting") == 0 ) {
            prev.front->next = temp.front->next;
            q->front = front;
            return temp.front->Name; //true
        }
        else {
            prev.front = temp.front;
            temp.front = temp.front->next;
        }
    }
    //reposition the front of the linked list
    q->front = front;
    return "";
}

//************************************************************************************************************
//function to count the nodes in front of the group
int countGroupsAhead(struct Queue *q, char *name)
{
    //debug info
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   COUNTING NUMBER OF GROUPS AHEAD...  (countGroupsAhead)\n");
    }

    //variable to iterate through the list
    struct Queue temp = *q;
    int number_of_groups = 0;
    while (temp.front != NULL) {

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", temp.front->Name, temp.front->group_size, temp.front->status);

        //if fount end the loop and return
        if(strcmp(temp.front->Name, name) == 0)
            return number_of_groups;
        //keep going if not found
        else{
            number_of_groups++;
            temp.front = temp.front->next;
        }
    }

    return 0;
}

//************************************************************************************************************
//display the group size of the list
void displayGroupSizeAhead(struct Queue *q, char *name)
{
    //debug info
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   DISPLAYING NUMBER OF GROUPS AHEAD...  (displayGroupSizeAhead)\n");
    }
    //variable to iterate through the list
    struct Queue temp = *q;
    while (temp.front != NULL) {

        if(debug == TRUE)
            printf("-->     Group: %s \t|\t Size: %d \t|\t Status: %s\n", temp.front->Name, temp.front->group_size, temp.front->status);

        if(strcmp(temp.front->Name, name) == 0)
            return;

        else{
            printf("Group: %s\tSize: %d\n", temp.front->Name, temp.front->group_size);
            temp.front = temp.front->next;
        }
    }

    return;
}

//************************************************************************************************************
//function to display the entire list
void displayListInformation(struct Queue *q)
{   //variable to account for the front of the list
  struct Node *front = q->front;

    //debug information
    if(debug == TRUE){
        printf("   ------------DEBUGGING INFO-----------\n");
        printf("   DISPLAYING ALL OF GROUPS...  (displayListInformation)\n");
    }

    int number_of_groups = 0;

    struct Queue temp = *q;
    if(temp.front == NULL)
        printf("List is Empty\n");

   //runs only when debug is true
    if(debug == TRUE){
        while (temp.front != NULL) {
            printf("-->     Group: %s \t|\t Size: %d  \t|\t Status: %s\n", temp.front->Name, temp.front->group_size, temp.front->status);
            temp.front = temp.front->next;
        }
    }

    temp.front = front;
    //printing the list
    while (temp.front != NULL) {
        number_of_groups++;
        printf("Group: %s  \t|\tSize: %d  \t|\tStatus: %s  \n", temp.front->Name, temp.front->group_size, temp.front->status);
        temp.front = temp.front->next;
    }
    printf("\nTotal Number of Groups: %d\n", number_of_groups);
    return;
}

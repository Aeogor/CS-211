//
//  main.c
//
//  Created by Srinivas Lingutla on 10/15/16.
//  Copyright © 2016 Srinivas Lingutla. All rights reserved.
//

#include "header.h"

extern bool debug;

//************************************************************************************************************

void doAdd (struct Queue *q)
{
    /* get group size from input */
    int size = getPosInt();
    if (size < 1)
    {
        printf ("Error: Add command requires an integer value of at least 1\n");
        printf ("Add command is of form: a <size> <name>\n");
        printf ("  where: <size> is the size of the group making the reservation\n");
        printf ("         <name> is the name of the group making the reservation\n");
        return;
    }

    /* get group name from input */
    char *name = getName();
    if (NULL == name)
    {
        printf ("Error: Add command requires a name to be given\n");
        printf ("Add command is of form: a <size> <name>\n");
        printf ("  where: <size> is the size of the group making the reservation\n");
        printf ("         <name> is the name of the group making the reservation\n");
        return;
    }

    printf ("Adding group \"%s\" of size %d\n", name, size);

    // add code to perform this operation here
    if(doesNameExist(q, name) == TRUE){
        printf("**Group <%s> already exists, use a different group name and try again!", name);
        return;
    }
    addToList(&q, size, name, "Waiting");
}

//************************************************************************************************************

void doCallAhead (struct Queue *q)
{
    /* get group size from input */
    int size = getPosInt();
    if (size < 1)
    {
        printf ("Error: Call-ahead command requires an integer value of at least 1\n");
        printf ("Call-ahead command is of form: c <size> <name>\n");
        printf ("  where: <size> is the size of the group making the reservation\n");
        printf ("         <name> is the name of the group making the reservation\n");
        return;
    }

    /* get group name from input */
    char *name = getName();
    if (NULL == name)
    {
        printf ("Error: Call-ahead command requires a name to be given\n");
        printf ("Call-ahead command is of form: c <size> <name>\n");
        printf ("  where: <size> is the size of the group making the reservation\n");
        printf ("         <name> is the name of the group making the reservation\n");
        return;
    }

    printf ("Call-ahead group \"%s\" of size %d\n", name, size);

    // add code to perform this operation here
    if(doesNameExist(q, name) == TRUE){
        printf("**Group <%s> already exists, use a different group name and try again!", name);
        return;
    }
    addToList(&q, size, name, "Call-Ahead");
}

//************************************************************************************************************

void doWaiting (struct Queue *q)
{
    /* get group name from input */
    char *name = getName();
    if (NULL == name)
    {
        printf ("Error: Waiting command requires a name to be given\n");
        printf ("Waiting command is of form: w <name>\n");
        printf ("  where: <name> is the name of the group that is now waiting\n");
        return;
    }

    printf ("Waiting group \"%s\" is now in the restaurant\n", name);

    // add code to perform this operation here
    if(doesNameExist(q, name) == FALSE){
        printf("Group <%s> doesnot exist", name);
        return;
    }

    updateStatus(q, name);
}

//************************************************************************************************************

void doRetrieve (struct Queue *q)
{
    /* get table size from input */
    int size = getPosInt();
    if (size < 1)
    {
        printf ("Error: Retrieve command requires an integer value of at least 1\n");
        printf ("Retrieve command is of form: r <size>\n");
        printf ("  where: <size> is the size of the group making the reservation\n");
        return;
    }

    clearToEoln();
    printf ("Retrieve (and remove) the first group that can fit at a table of size %d\n", size);

    // add code to perform this operation here
    char *name = retrieveAndRemove(q, size);

    if(strcmp(name, "") == 0)
        printf("No groups were found for the table size %d!", size);
    else
    {
        printf("Group <%s> is available for this table of size %d", name, size);
    }
}

//************************************************************************************************************

void doList (struct Queue *q)
{
    /* get group name from input */
    char *name = getName();
    if (NULL == name)
    {
        printf ("Error: List command requires a name to be given\n");
        printf ("List command is of form: l <name>\n");
        printf ("  where: <name> is the name of the group to inquire about\n");
        return;
    }

    printf ("Group \"%s\" is behind the following groups\n", name);

    // add code to perform this operation here
    if(doesNameExist(q, name) == FALSE){
        printf("Group <%s> doesnot exist", name);
        return;
    }

    int number_of_groups  = countGroupsAhead(q, name);
    printf("Number of Groups ahead: %d\n", number_of_groups);

    displayGroupSizeAhead(q, name);
}

//************************************************************************************************************

void doDisplay (struct Queue *q)
{
    clearToEoln();
    printf ("Display information about all groups\n");

    // add code to perform this operation here
    displayListInformation(q);
}

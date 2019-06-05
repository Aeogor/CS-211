// <<Srinivas Lingutla>>
// << Mac OS X 10.11.5 , XCODE>> // U. of Illinois, Chicago
// CS 111, Fall 2016
// Project 1 - Basic Functions
//
//  main.c
//  Homework 4 - 251
//
//  Created by Srinivas Lingutla on 9/2/16.
//  Copyright © 2016 Srinivas Lingutla. All rights reserved.

#include <stdio.h>
#include <stdlib.h>

//********************************************************************************************

//Copy the array into the new one
void arrayCopy (int fromArray[], int toArray[], int size){
    int c;
    for(c=0; c<size; c++)
        toArray[c] = fromArray[c];

}

//********************************************************************************************

//Sort the array by in ascending order
void sort (int arr[],int size){
    int i, j, temp;
    for (i = 0 ; i < (size - 1) ; i++)
    {
        for (j = 0 ; j < size - 1 - i ; ++j )
        {
            if (arr[j] > arr[j+1])
            {
                temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
            }
        }
    }

}

//********************************************************************************************

//search the array through linear search
int linSearch (int arr[], int size, int target, long int *numComparisons){
    *numComparisons = 0;
    int counter;
    printf("\nSIZE of array: %d\n" ,size );

    for ( counter = 0 ; counter < size ; counter++ )
    {
        *numComparisons = counter + 1;
        if ( arr[counter] == target )
            return counter;
    }
    return -1;
}

//********************************************************************************************

//perform the search through binary search
int binSearch (int arr[], int size, int target, long int *numComparisons){
    *numComparisons = 0;
    int lower,upper,middle = 0;
    lower = 0 , upper = size - 1;
    int found = -1;
    while(lower <= upper)
    {
        middle = (lower + upper) / 2;
        *numComparisons = *numComparisons + 1;
        if(target == arr[middle])
        {
            found = 0;
            break;
        }
        else if(target < arr[middle])
        {
            upper = middle - 1;

        }
        else{
            lower = middle + 1;
        }
    }
    if(found == 0)
        return middle;
    else
        return -1;
}

//********************************************************************************************

//Main function
int main(){

    printf("\t**Srinivas Lingutla**\n");

    //initialising array 1 and allocating the memory
    int *Array_1;
    int size = 100;
    Array_1 = (int *) malloc (size * sizeof(int) );

    int i=0;

    while (1) {

        //getting the numbers
        scanf("%d", &Array_1[i]);

        //break if -9999
        if (Array_1[i] == -999) {
            break;
        }
        //allocating the memory
        if(i >= size-1){

            int *temp;
            temp = (int *) malloc ( size * 2 * sizeof(int) );
            int i;
            for ( i = 0 ; i < size ; i++)
                temp[i] = Array_1[i];
            free (Array_1);
            Array_1 = temp;
            size = size * 2;

        }
        i++;
    }



    //copying from one array to another
    int *Array2;
    Array2 = (int *) malloc (i * sizeof(int) );
    arrayCopy(Array_1, Array2, i);

    //sorting the array 2
    sort(Array2, i);

    //getting the searching numbers
    int target;
    long int numComparisons_linear, numComparisons_binary;
    while (1) {


        printf("\n-------------------------------------------------------------------------------");
        printf("\n\nPlease enter the number to search: ");
        scanf("%d", &target);
        if (target==-999) {
            break;
        }
        printf("%d\n", target);




        //linear search
        int linear_search = linSearch(Array_1, i, target, &numComparisons_linear);
        printf("\nLINEAR SEARCH for : %d\n", target);

        if(linear_search != -1){
            printf("\n>> The number was successfully found!");
            printf("\n>> Location of %d in unsorted array is %d", target , linear_search);
        }
        else
            printf("\n>> The number was not found!");

        printf("\n>> Total Number of comparisons: %ld", numComparisons_linear);


        //binary search
        int binary_search = binSearch(Array2, i, target, &numComparisons_binary);
        printf("\n\nBINARY SEARCH for : %d\n", target);

        if(binary_search != -1){
            printf("\n>> The number was successfully found!");
            printf("\n>> Location of %d in sorted array is %d", target , binary_search);
        }
        else
            printf("\n>> The number was not found!");

        printf("\n>> Total Number of comparisons: %ld", numComparisons_binary);

    }

    return 0;
}

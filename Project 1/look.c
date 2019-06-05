#include <stdio.h>
#include <stdlib.h>

void arrayCopy(int fromArray[], int toArray[], int size)
{
	int i;
	//copy content from array 1 to array 2
	for (i = 0; i < size; i++) {
		toArray[i] = fromArray[i];
	}

}

void sortArray(int array[], int i) {
	int j, k;
	int temp;

	 for (j = 0; j < i - 1; j++){
        for (k = 0; k < i - 1 - j; k++){
            if (array[k] > array[k+1]){
                temp =  array[k+1];
                array[k+1] = array[k];
                array[k] = temp;
            }
        }
    }
}

int linearSearch(int array[], int size, int input, int *compar){
	*compar = 0;
	int counter;
	printf("\n The size of the array is %d\n", size);

	for (counter = 0; counter < size; counter++){
		*compar = counter+1;
		if (array[counter]==input)
			return counter;
	}
	return -1;
}

int binarySearch(int array[], int size, int input, int *compar) {

	*compar = 0;
	int low = 0, mid = 0, found = 0; //For bert you need to individually initialise it otherwise it assumes them as uninitialised and give them a random negative number
	int top = size-1;
	while (low <= top){
				mid = (low + top)/2;
				*compar = *compar + 1;
				if (input == array[mid])
				{
						found = 1;
						break;
				}
				else if (input < array[mid])
				{
						top = mid -1;
				}
				else
				{
						low = mid + 1;
				}
	}

	if (found == 1)
	{
			return mid;
	}
	else
	{
			return -1;
	}
}

int main()
{
	//declare variables
	int *fromArray;
	int *toArray;
	int size = 100;
	int i = 0;
	int *darr;

	//allocate memory for first array
	fromArray = (int *) malloc (size * sizeof(int));


	printf("Enter integers into the array \n");

	while (1)
	{
		//loop and collect input
		scanf("%d", &fromArray[i]);

		//break when you get -999
		if (fromArray[i] == -999) {
			break;
		}
		//if the array runs out of elements, double the size
		if (i>= size-1){
			int *temp;
			temp = (int*) malloc (size *2* sizeof(int));
			int j;
			for (j = 0; j < size; j++)
			{
				temp[j] = fromArray[j];
			}
			free(fromArray);
			fromArray = temp;
			size = size * 2;
		}
		i++;
	}
	//allocate memory for second array and pass for copying
	toArray = (int *) malloc (i * sizeof(int));
	arrayCopy(fromArray, toArray, i);
	//sort the array
	sortArray(toArray, i);

	int input;
	int binaryComp, linearComp;
	//Search:
	while (1) {
		printf("enter the number you would like to search for\n");
		scanf("%d", &input);

		if (input == -999) {
			break;
		}

		//linear search the unsorted array
        int linear_search = linearSearch(toArray, i, input, &linearComp);
        printf("\n linear search for : %d\n", input);

        if(linear_search != 0){
            printf("Your number was found\n");
            printf("The location of %d in the unsorted array is %d\n", input , linear_search);
        }
        else
            printf("\n> Your number was not found\n");

        printf("\n> Total number of comparisons: %d\n", linearComp);


        //binary search the sorted array
        int binary_search = binarySearch(toArray, i, input, &binaryComp);
        printf("Binary Search for : %d:\n\n", input);

        if(binary_search != 0){
            printf("Your number was found\n");
            printf("The location of %d in the sorted array is %d\n", input , binary_search);
        }
        else
            printf("Your number was not found\n");

        printf("Total Number of comparisons: %d\n", binaryComp);

    }

    return 0;


}

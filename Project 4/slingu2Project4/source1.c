/*  Code for the user interface for Lab 4 for CS 211 Fall 2015
 *
 *  Author: Srinivas Lingutla
 *  Date: 10/6/2013
 */



#include "header.h"

bool debug= FALSE;   //FOR DEBUG MODE//


//typedef enum {FALSE = 0, TRUE, NO = 0, YES} boolean;


//************************************************************************************************************


/* forward definition of functions */
void clearToEoln();

//************************************************************************************************************

/* Read in until the first Non-White-Space character is Read */
/* The white space characters are:
 *      space, tab \t, newline \n, vertical tab \v,
 *      form feed \f, and carriage return \r
 */
int getNextNWSChar ()
{
 int ch;

 ch = getc(stdin);
 if (ch == EOF)
   return ch;
 while (isspace (ch))
   {
    ch = getc(stdin);
    if (ch == EOF)
      return ch;
   }
 return ch;
}

//************************************************************************************************************

/* read in the next Positive Integer or error:    */
/* This is based on the Mathematical definition of a Postive Integer */
/*    zero is not counted as a positive number */
int getPosInt ()
{
 int value = 0;

 /* clear white space characters */
 int ch;
 ch = getc(stdin);
 while (!isdigit(ch))
   {
    if ('\n' == ch)  /* error \n ==> no integer given */
       return 0;
    if (!isspace(ch)) /* error non white space ==> integer not given next */
      {
       clearToEoln();
       return 0;
      }
    ch = getc(stdin);
   }

 value = ch - '0';
 ch = getc(stdin);
 while (isdigit(ch))
   {
    value = value * 10 + ch - '0';
    ch = getc(stdin);
   }

 ungetc (ch, stdin);  /* put the last read character back in input stream */

 /* Integer value of 0 is an error in this program */
 if (0 == value)
    clearToEoln();

 return value;
}

//************************************************************************************************************

/* read in the name until the end of the input */
char *getName()
{
 /* skip over the white space characters */
 int ch;
 ch = getc(stdin);
 while (isspace(ch))
   {
    if ('\n' == ch)  /* error \n ==> no integer given */
       return NULL;
    ch = getc(stdin);
   }

 char *word;
 int size;
 size = 10;
 word = (char *) malloc (sizeof(char) * size);

 // read in character-by-character until the newline is encountered
 int count = 0;

 while (ch != '\n')
   {
    if (count+1 >= size)
      {
       // to grow an array to make it "dynamically sized" using malloc
       char* temp;
       int i;
       size = size * 2;
       temp = (char *) malloc (sizeof(char) * size);

       // printf ("Growing array from size %d to size %d\n", count, size);
       // copy the characters to the new array
       for (i = 0 ; i < count ; i++)
           temp[i] = word[i];

       free (word);
       word = temp;
      }

    word[count] = ch;
    count++;
    word[count] = '\0';

    // read next character
    ch = getc(stdin);
   }

 if (count > 30)
   {
    count = 30;
    word[count] = '\0';
   }

 /* clear ending white space characters */
 while (isspace (word[count-1]))
   {
    count--;
    word[count] = '\0';
   }

 return word;
}

//************************************************************************************************************

/* Clear input until next End of Line Character - \n */
void clearToEoln()
{
 int ch;

 do {
     ch = getc(stdin);
    }
 while ((ch != '\n') && (ch != EOF));
}


//************************************************************************************************************


/* Print out a list of the commands for this program */
void printCommands()
{
 printf ("The commands for this program are:\n\n");
 printf ("q - to quit the program\n");
 printf ("? - to list the accepted commands\n");
 printf ("a <size> <name> - to add a group to the wait list\n");
 printf ("c <size> <name> - to add a call-ahead group to the wait list\n");
 printf ("w <name> - to specify a call-ahead group is now waiting in the restaurant\n");
 printf ("r <table-size> - to retrieve the first waiting group that can fit at the available table size\n");
 printf ("l <name> - list how many groups are ahead of the named group\n");
 printf ("d - display the wait list information\n");

 /* clear input to End of Line */
 clearToEoln();
}


//************************************************************************************************************

int main (int argc, char **argv)
{
    int i;
    for(i=0; i<argc ; i++){
        if(strcmp(argv[i], "-d") == 0){
            debug = TRUE;
        }
    }
    //FOR DEBUG MODE
    if(debug == TRUE)
        printf("\nDebugging Information \n");



    struct Queue *q = (struct Queue*)malloc(sizeof(struct Queue));
    q->front = q->back = NULL;

    int ch;

 printf ("Starting Restaurant Wait List Program\n\n");
 printf ("\nEnter command: ");

 while ((ch = getNextNWSChar ()) != EOF)
   {
    /* check for the various commands */
    if ('q' == ch)
      {
       printf ("Quitting Program\n");
       return (0);
      }
    else if ('?' == ch)
      {
       printCommands();
      }
    else if('a' == ch)
      {
       doAdd(q);
      }
    else if('c' == ch)
      {
       doCallAhead(q);
      }
    else if('w' == ch)
      {
       doWaiting(q);
      }
    else if('r' == ch)
      {
       doRetrieve(q);
      }
    else if('l' == ch)
      {
       doList(q);
      }
    else if('d' == ch)
      {
       doDisplay(q);
      }
    else
      {
       printf ("%c - in not a valid command\n", ch);
       printf ("For a list of valid commands, type ?\n");
       clearToEoln();
      }

    printf ("\nEnter command: ");
   }

 printf ("Quiting Program - EOF reached\n");
 return 1;
}

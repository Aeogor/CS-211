//
//  slingu2Proj2.cpp
//  Project 2
//
//  Created by Srinivas Lingutla on 9/16/16.
//  Copyright © 2016 Srinivas Lingutla. All rights reserved.
//
//
//
// Program to check balanced paranthesis
// <<Srinivas Lingutla>>
// << Mac OS X 10.11.5 , XCODE>> // U. of Illinois, Chicago
// CS 211, Fall 2016
//
//


#include<stdio.h>
#include<stdlib.h>
#include <string.h>

//Struct stack
typedef struct stack{
    int top;
    int size;
    char *items;
    int spaces;
}STACK;

//-------------------------------------------------------------------------------------------------------------

//Function to Print stack
void print_stack(STACK stack){
    
    printf("STACK:\t");
    for(int i = stack.top; i > -1 ; i--)
    {
        printf("%c", stack.items[i]);
    }
    printf("\n");
    
}

//-------------------------------------------------------------------------------------------------------------

//Function to push the element onto the stack
void push(STACK *stack, char element, int debug){
    
    //increasing the spaces to follow the correct element
    stack->spaces++;
    
    //Growing the array
    if(stack->top == (stack->size - 1))
    {
        //FOR DEBUG MODE ONLY
        if(debug)
            printf("\nOld Size: %d\n", stack->size);
        
        //allocation a new array and copying elements over
        STACK *temp = (STACK *) malloc (sizeof(STACK) );
        temp->items = (char *)malloc((stack->size + 2) + sizeof *stack->items);
        int i;
        for ( i = -1 ; i <= stack->size ; i++){
            temp->items[i] = stack->items[i];
        }
        
        //FOR DEBUG MODE ONLY
        if(debug)
            printf("Number of items copied over: %d\n", stack->size);
        
        //reinitialising stack to the new array and increaing the size
        stack->items = temp->items;
        stack->size = stack->size + 2;
        
        //FOR DEBUG MODE ONLY
        if(debug)
            printf("New size: %d\n\n", stack->size);
    }
    
    
    //Changing the position of top
    stack->top =  stack->top + 1;
    stack->items[stack->top] = element;
    
    //FOR DEBUG MODE ONLY
    if(debug){
        printf(">>Pushing: %c\t", stack->items[stack->top]);
        print_stack(*stack);
    }
    
}


//-------------------------------------------------------------------------------------------------------------

//Function to pop the element from the stack
void pop (STACK *stack, int debug){
    
    //Checking if stack is empty
    if(stack->top >= 0){
        stack->spaces++;
        
        //FOR DEBUG MODE ONLY
        if(debug == 1)
            printf("<<Popping: %c\t", stack->items[stack->top]);
        
        //Erasing the value of top in items and decrementing the value of top
        stack->top =  stack->top - 1;
        
        //FOR DEBUG MODE ONLY
        if(debug == 1)
            print_stack(*stack);
        
    }
    else{
        printf("Stack is empty\n");
    }
}

//-------------------------------------------------------------------------------------------------------------

//Function to get the opposite letter of the paranthesis
char pairing_paranthesis( char letter){
    
    switch (letter) {
        case '{':  return '}';
        case '(':  return ')';
        case '[':  return ']';
        case '<':  return '>';
        case '}':  return '{';
        case ')':  return '(';
        case ']':  return '[';
        case '>':  return '<';
        default:
            return letter;
    }
}

//-------------------------------------------------------------------------------------------------------------

//Function to get the top of the function
char get_top(STACK *stack){
    return (stack->items[stack->top]);
}

//-------------------------------------------------------------------------------------------------------------

//Function to check the next letter in the top to verify before popping
int peek(STACK stack , char letter, char *input_line){
    if(stack.top<0){
        char top = pairing_paranthesis(letter);
        printf("\n%s", input_line);
        for(int i = 0; i<stack.spaces ; i++) printf(" ");
        printf("^ missing %c\n", top);
        return 0;
    }
    return stack.items[stack.top];
}

//-------------------------------------------------------------------------------------------------------------

//Checking if the stack is empty or not
int is_empty(STACK stack){
    if(stack.top <0) return 1;
    else return 0;
}




//-------------------------------------------------------------------------------------------------------------

//Declaring and initialising a new array
STACK init(STACK *stack){
    
    stack = (STACK *) malloc (sizeof(STACK *) );
    stack->items = (char *)malloc((2) + sizeof *stack->items);
    stack->size = 2;
    stack->top = -1;
    stack->spaces = 0;
    return *stack;
}

//-------------------------------------------------------------------------------------------------------------

//Function to delete an array and reset all the variables to NULL
STACK delete_stack(STACK *stack){
    
    free(stack->items);
    stack->size = 0;
    stack->top = -1;
    stack->spaces = 0;
    
    return *stack;
    
}
//-------------------------------------------------------------------------------------------------------------

//FUnction to print the error message if there are poping elements compared to pushing elements
void check_error(char temp, STACK stack , char top , char *input_line){
    
    if(temp != 0){
        top = get_top(&stack);
        top = pairing_paranthesis(top);
        printf("\n%s", input_line);
        for(int i = 0; i<stack.spaces ; i++) printf(" ");
        printf("^ expecting  %c\n", top);
        
    }
    
}

//-------------------------------------------------------------------------------------------------------------


//Function to parse through the string input and call the appropriate function whether to push or pull
int parsing(char *s, int debug){
    STACK stack; //declare the stack
    stack.items = NULL; //declaring the items
    stack = init(&stack);  //calling the function to intialise the stack
    char temp;
    char *input_line = s; //A pointer to the original string input
    
    if(s == NULL) return 0;
    
    if(*s == '\0') return 1;
    
    //run until the string is empty
    while(*s != '\0'){
        char top;
        
        if(*s == ' ' || (*s >64 && *s < 91) || (*s >96 && *s < 123)){
            stack.spaces++;
            s++;
            
            continue;
        }
        
        //Switch case to push the elements
        switch (*s) {
            case '(':   push(&stack, *s, debug); break;
            case '{':   push(&stack, *s, debug); break;
            case '[':   push(&stack, *s, debug); break;
            case '<':   push(&stack, *s, debug); break;
            default:
                break;
        }
        
        //Popping for this letter
        if(*s == ')'){
            temp  = peek(stack, *s, input_line);
            if(temp == '(')
                pop(&stack, debug);
            else{
                check_error(temp, stack, top, input_line);
                return 0;
            }
        }
        
        //Popping for this letter
        else if(*s == '}'){
            temp  = peek(stack, *s, input_line);
            if(temp == '{')
                pop(&stack, debug);
            else{
                check_error(temp, stack, top, input_line);
                return 0;
            }
        }
        
        //Popping for this letter
        else if(*s == ']'){
            temp  = peek(stack, *s, input_line);
            if(temp == '[')
                pop(&stack, debug);
            else{
                check_error(temp, stack, top, input_line);
                return 0;
            }
        }
        
        //Popping for this letter
        else if(*s == '>'){
            temp  = peek(stack, *s, input_line);
            if(temp == '<')
                pop(&stack, debug);
            else{
                check_error(temp, stack, top, input_line);
                return 0;
            }
        }
        s++; //increment the array pointer
    }
    
    //if stack is empty
    if(is_empty(stack)){
        printf("\n%s\n", input_line);
        stack = delete_stack(&stack);
        return 1;
    }
    //if stack isnot empty
    else{
        //printing the error
        char top = get_top(&stack);
        top = pairing_paranthesis(top);
        printf("\n%s", input_line);
        for(int i = 0; i<stack.spaces ; i++) printf(" ");
        printf("  ^ missing  %c\n", top);
        
        //resetting the stack
        stack = delete_stack(&stack);
        return 0;
    }
}

//-------------------------------------------------------------------------------------------------------------

//Main function with command line arguments
int main(int argc, char** argv){
    char str[300]; //input string
    
    int debug = 0;  //dugging flag
    
    for(int i=0; i<argc ; i++){
        if(strcmp(argv[i], "-d") == 0){
            debug = 1;
        }
    }
    //FOR DEBUG MODE
    if(debug == 1)
        printf("\nDebugging Information \n");
    
    //Getting user input
    printf("\n\nEnter an Expression:\n");
    fgets(str, 300, stdin);
    
    //WHile loop to check if user wants to quit
    while(*str != 'q' || *str != 'Q' ){
        
        if(*str == 'q' || *str == 'Q')
            exit(0);
        
        //Calling the function parsing and displaying the correct info
        if(parsing(str, debug)){
            printf("Expression is balanced!\n");
        }
        else
            printf("NOT BALANCED!\n");
        
        //Getting the user input again
        printf("\n--------------------------------------------------------------");
        printf("\n\nEnter an Expression:\n");
        fgets(str, 300, stdin);
        
        
    }
    
    return 0;
}

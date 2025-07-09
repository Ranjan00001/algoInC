#include<stdio.h>

int show();  
int showadd(int b);  
int (*arr[3])();  
int (*(*ptr)[3])();  // To understand this declaration, start from variable name ptr and look into surrounding parentheses.
// To undersrand it fully, you may need to refer the precedence order of * (pointer operator) and [] (array subscript operator).

int main ()  {  
    int result1;  
    arr[0] = show;  
    arr[1] = showadd;  
    ptr = &arr;  
    result1 = (**ptr)();  
    printf("printing the value returned by show : %d",result1);  
    (*(*ptr+1))(result1);  
}  
int show()  
{  
    int a = 65;  
    return a++;  
}  
int showadd(int b)  
{  
    printf("\nAdding 90 to the value returned by show: %d",b+90);  
}  
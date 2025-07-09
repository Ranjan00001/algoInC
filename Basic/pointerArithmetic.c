#include<stdio.h>  
void main ()  
{  
    int a[10] = {100, 206, 300, 409, 509, 601};
    int *p[] = {a, a+1, a+2, a+3, a+4, a+5}; 
    int **pp = p;
    pp++; 
    printf("%d %d %d\n",pp-p,*pp - a,**pp); // pp points to p[1], p points to p[0] and *pp is the value of p[1] that is a + 1
    *pp++; // Due to operator precedence, this is equivalent to *(pp++);
    // pp is incremented to point to p[2], but p is still a
    printf("%d %d %d\n",pp-p,*pp - a,**pp);
    ++*pp;
    printf("%d %d %d\n",pp-p,*pp - a,**pp);
    ++**pp;
    printf("%d %d %d\n",pp-p,*pp - a,**pp);
}  
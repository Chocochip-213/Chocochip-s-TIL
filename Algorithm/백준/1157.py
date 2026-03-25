/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>


int main()
{
    char word[1000001] = {0, };
    int i=0, j;
    int alpha[123] = {0, }; // a=97, z=122 / A=65(a-32), Z=90(z-32)
    int Bignum=0;
    int same=0;
    int cash;
    int remem;


        scanf("%s", word);


        do
        {
            cash = word[i];

            if(word[i] >= 97 || word[i] <= 122)
            alpha[cash-32] = alpha[cash-32] + 1;

            alpha[cash] = alpha[cash] + 1;
            i++;
        }while(word[i]!=NULL);



        Bignum = alpha[65];
        remem = 65;
            for(j=65; j<90; j++)
            {

                if(Bignum>alpha[j+1])
                  {
                     Bignum = Bignum;
                     remem = remem;

                  }
                else if(Bignum==alpha[j+1])
                {
                    same = 1;
                }
                else
                {
                    Bignum = alpha[j+1];
                    remem = j+1;
                    same = 0;
                }
            }


    if(same>0)
        printf("?");
    else
        printf("%c", remem);




}



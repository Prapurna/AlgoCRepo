
package sri;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Variables
{
StringTokenizer ve,vs;
 String intvar[]=new String[10];
 String charvar[]=new String[10];
 String floatvar[]=new String[10];
  int fcount=0,fnc=0,icount=0,ccount=0,fl,j,m,flag=0;
 char b[]=new char[50];
 String str,var;
 int iparc=0,fparc=0,cparc=0;
public  Variables(){
  fcount=0;fnc=0;icount=0;ccount=0;flag=0;
 iparc=0;fparc=0;cparc=0;
    }
public void checkvariables()
{
 ve=new StringTokenizer(NewClass.s,"\n");
 while(ve.hasMoreTokens()) //while 1
   {
     String w1=ve.nextToken();
     vs=new StringTokenizer(w1);
     str=vs.nextToken();
     while(vs.hasMoreTokens()) //while 2
      {
        str=vs.nextToken();
       // System.out.println("--------------"+str);
        if(str.equals("end"))
          return;
          if(str.equals("read")||(str.equals("array")))
           {
            String temp=vs.nextToken();
            str=vs.nextToken();
            if(temp.equals("int"))
            icount=readvar(icount,intvar);
            else if(temp.equals("float"))
             fcount=readvar(fcount,floatvar);
            else if(temp.equals("char"))
             ccount=readvar(ccount,charvar);
            }
           else if(str.equals("begin")||str.equals("write")||str.equals("if")||str.equals("goto"))

           break;//from while 2
        else if(str.equals("Algorithm"))
          {
            str=vs.nextToken();
            b=w1.toCharArray();
            j=0;
            while(b[j]!='('){j++;}
            String w2=w1.substring(j+1, w1.length()-2);//start index is index of'('+1 and end index is (indexof')')-1 i.e (w1.length-1)-1
            System.out.println("+"+w2);
            String par[]=new String[10];
            iparc=0;cparc=0;fparc=0;
            if(!w2.equals(""))
            {
            par=w2.split(",");
           for(String x:par)
            {
             System.out.println("split="+x);
             String typepar[]=x.split(" ");
             String temp=typepar[0];
             str=typepar[1];
             if(temp.equals("int"))
             {
             
             icount=readvar(icount,intvar);
             iparc=icount;
             }
            else if(temp.equals("float"))
             {
               fparc++;
                fcount = readvar(fcount, floatvar);
             }
                else if(temp.equals("char"))
             {  cparc++;
                 ccount = readvar(ccount, charvar);
             }
                }

            }
            break;
          }
        else if(str.equals("return"))
          {
            if(vs.hasMoreTokens())
            {
                str = vs.nextToken();
            int i=checktype(str);
            if(i==1)
               NewClass.returntype[NewClass.beginc]="int ";
            else if(i==2)
                NewClass.returntype[NewClass.beginc]="float ";
            else if(i==3)
                 NewClass.returntype[NewClass.beginc]="char ";


            System.out.print("ret="+NewClass.returntype[NewClass.beginc]);
          }
          else NewClass.returntype[NewClass.beginc]="void ";
          }

           else
           {
            b=str.toCharArray();
            String temp="";
            j=0;
            // System.out.println("b[j] is"+b[j]);
            while(j<b.length)
            {
              if(b[j]=='='||b[j]=='[') break;
              temp+=b[j];
               j++;
            }
            if(j<b.length&&b[j]=='[')
             {break;}
            if(j==b.length) break;
          fl=checktype(temp);
          var=temp;
          System.out.println("var"+var+"checktype="+fl);
          if(fl==0)
           {
             m=gettype();
         if((m==1)||(m==0))
          {
            intvar[icount] = temp;
            icount++;
           }
        else if(m==2)
          {
                    floatvar[fcount] = temp;
                    fcount++;
                   }
                else if(m==3)
                   {
                    charvar[ccount] = temp;
                    ccount++;
                   }
               }
               }
            }//end of while2
           }//end of whle 1
}
public void printvar(int i,int c,String s[])
    {
     if(c!=0)
     {
     for(;i<c-1;){
     flag=0;
     b=s[i].toCharArray();
    String temp="";
     j=0;
     while(j<b.length)
      {
        if(b[j]=='['){ flag=1; break;}
         temp+=b[j];
         j++;
       }
     NewClass.out+=" "+s[i]+",";
    if(flag==1){i++;}
     i++;
   }
  if(i==(c-1))
     NewClass.out+=s[i]+";\n";
  else
     NewClass.out+=";\n";
    }
      //for(;i<c-1;i++){
      //NewClass.out+=s[i]+",";
       //}
     //NewClass.out+=s[c-1]+";\n";
    }

 public int readvar(int s,String ar[])
 {  flag=0;
    b=str.toCharArray();
    String temp1="";
     j=0;
     while(j<b.length)
      {
        if(b[j]=='['){ flag=1; break;}
         temp1+=b[j];
         j++;
       }
    for(int i=0;i<s;i++)
     {
        if(temp1.equals(ar[i]))
            return s;
      }
    ar[s]=str;
    s++;
    if(flag==1)
    {
      ar[s]=temp1;
      s++;
    }
     return s;
 }
 public int checktype(String temp)
 {
    int i;
     flag=0;
 for(i=0;i<icount;i++)
    {
     if(intvar[i].equals(temp))
                 {

                  flag=1;
                  return flag;
                 }
                 else flag=0;
    }
    for(i=0;i<fcount;i++)
    {
     if(floatvar[i].equals(temp))
                 {
    	          flag=2;
                  return flag;
                 }
                 else flag=0;
    }
    for(i=0;i<ccount;i++)
    {
     if(charvar[i].equals(temp))
                 {
                  flag=3;
                  return flag;
                 }
                 else flag=0;
    }
System.out.println("vvvvvvvvvvvvvv"+temp);
   if(Pattern.compile("([0-9])" ).matcher(temp).matches())
    {
        flag=1;
        return flag;
      }
   if(Pattern.compile("([0-9]*).([0-9]+)" ).matcher(temp).matches())
     {
        flag=2;
        return flag;
      }
     if(Pattern.compile("('[a-z]')" ).matcher(temp).matches())
    {
        flag=3;
        return flag;
      }
    else
  return 0; 
 }
 public int gettype()
 {
  String t;
  int type[]=new int[10],k=0,flag=0;
    j++;//ignore =
    for(;j<b.length;j++)
    {
        t="";
       while(j<b.length)
        {
         if (b[j] == '+' || b[j] == '*' || b[j] == '%' || b[j] == '-' || b[j] == '/'||b[j]=='['||b[j]=='%')
          break;
         else if((b[j] == '(') && (!t.equals("")))
           {
             NewClass.unvar[NewClass.ucount].current=NewClass.beginc;
             NewClass.unvar[NewClass.ucount].fun=""+t;
             NewClass.unvar[NewClass.ucount].vname=var;
             NewClass.ucount++;
             //System.out.println("*"+ unvar[ucount-1].current+","+unvar[ucount-1].fun+","+unvar[ucount-1].vname);
             //return -1;
             while(b[j]!=')')j++;
             flag=1;
             //type[k]=-1;
             //k++;
             j++;j++;
             t="";
            }
         else
         {
           t += b[j];
         j++;
         }
        }
        if(!t.equals(""))
        {
         type[k] = checktype(t);

         k++;
        }


   }

  for(int m=0;m<k;m++)
  { if(type[m]==2)
     {
      if(flag==1){NewClass.unvar[NewClass.ucount-1].type=2 ; return -1;}
      return 2;
      }
 else if(type[m]==3){return 3;}
  }
   // if(flag==1){ NewClass.unvar[NewClass.ucount-1].type=1;return -1;}
   if(k!=0&&flag==1)
    {
     if(type[k-1]==1) NewClass.unvar[NewClass.ucount-1].type=1 ;
     else if(type[k-1]==3) NewClass.unvar[NewClass.ucount-1].type=3;
     return -1;
    }
  if(k==0 &&flag==1) return -1;
return 1;
}
}



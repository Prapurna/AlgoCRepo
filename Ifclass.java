package sri;

import java.util.StringTokenizer;
import java.awt.Event.*;
public class Ifclass
{
String condition="";
int op,label1,lno,label2;
char rel[]=new char[20];
public void checkif() {
       NewClass.co=NewClass.ss.nextToken();
       conditioncheck(NewClass.co);
       NewClass.co=NewClass.ss.nextToken();
       NewClass.co=NewClass.ss.nextToken();
       gotofun();
       return;
    }
public void conditioncheck(String str){
      rel=str.toCharArray();
       int lo=0;
       condition="";
       for(op=0;op<rel.length;op++)
       {
           if(rel[op]=='('&&rel[op+1]=='!'){condition=str.substring(op+2,str.length()-1);break;}
            if(rel[op]=='<'||rel[op]=='>'||rel[op]=='='||rel[op]=='!')
            {
                reverse();

            }
          else
            condition+=""+rel[op];
       }
       //if(lo==1){condition+=str;}
}
public void reverse(){
     if(rel[op]=='<'&&rel[op+1]=='=')
     {
        op++;
        condition+=">";
     }
    if(rel[op]=='>'&&rel[op+1]=='=')
     {
        op++;
        condition+="<";
     }
      if(rel[op]=='='&&rel[op+1]=='=')
     {
        op++;
        condition+="!=";
     }
      if(rel[op]=='!'&&rel[op+1]=='=')
     {
        op++;
        condition+="==";
     }
      if(rel[op]=='<')
     {

        condition+=">=";
     }
      if(rel[op]=='>')
     {

        condition+="<=";
     }
}
public void simpleif(){
    NewClass.out+="if("+condition+")\n{\n";
    NewClass.linecount[NewClass.line-1]=3;
    NewClass.se=new StringTokenizer(NewClass.s,"\n");
   for(int i=1;i<=NewClass.line;i++){
       NewClass.se.nextToken();
     }
  while(NewClass.line!=label1-1)
    {
      NewClass.ss=new StringTokenizer(NewClass.se.nextToken());
      NewClass.co=NewClass.ss.nextToken();
      NewClass.line=Integer.parseInt(NewClass.co);
      while(NewClass.ss.hasMoreTokens())
       {
           NewClass.co=NewClass.ss.nextToken();
           NewClass.compare();
       }
     }
   NewClass.out+="}\n";

}
public void ifelse()
{
    NewClass.out+="if("+condition+")\n{\n";
    NewClass.linecount[NewClass.line-1]=6;
      NewClass.se=new StringTokenizer(NewClass.s,"\n");
     for(int i=1;i<=NewClass.line;i++){
       NewClass.se.nextToken();
       }
     while(NewClass.line!=label1-2)
      {
      NewClass.ss=new StringTokenizer(NewClass.se.nextToken());
      NewClass.co=NewClass.ss.nextToken();
      NewClass.line=Integer.parseInt(NewClass.co);
      while(NewClass.ss.hasMoreTokens())
       {
           NewClass.co=NewClass.ss.nextToken();
           NewClass.compare();
       }
      }
    NewClass.out+="}\n";
    NewClass.se.nextToken();//ignoring to process line with goto
    NewClass.out+="else\n{\n";
    while(NewClass.line!=label2-1)
      {
      NewClass.ss=new StringTokenizer(NewClass.se.nextToken());
      NewClass.co=NewClass.ss.nextToken();
      NewClass.line=Integer.parseInt(NewClass.co);
      while(NewClass.ss.hasMoreTokens())
       {
           NewClass.co=NewClass.ss.nextToken();
           NewClass.compare();
       }
      }
    NewClass.out+="}\n";
}
public void loopwhile(){
    NewClass.out+="while("+condition+")\n{\n";
    NewClass.linecount[NewClass.line-1]=3;
         NewClass.se=new StringTokenizer(NewClass.s,"\n");
        for(int i=1;i<=NewClass.line;i++){
          NewClass.se.nextToken();
           }
  while(NewClass.line!=label1-2)
    {
      NewClass.ss=new StringTokenizer(NewClass.se.nextToken());
      NewClass.co=NewClass.ss.nextToken();
      NewClass.line=Integer.parseInt(NewClass.co);
      while(NewClass.ss.hasMoreTokens())
       {
           NewClass.co=NewClass.ss.nextToken();
           NewClass.compare();
       }
     }
   NewClass.out+="}\n";
     NewClass.se.nextToken();}
public void gotofun()
{

  label1=Integer.parseInt(NewClass.co);
  lno=NewClass.line;//lno is at if() line
  if(lno<label1)
  {//checking for not do-while
    lno++;//first line of the body of the loop
    while(lno<label1-1)
    {
     NewClass.se.nextToken();
     lno++;
    }
    NewClass.ss=new StringTokenizer(NewClass.se.nextToken());
    NewClass.co=NewClass.ss.nextToken();//ignore lineno.
    NewClass.co=NewClass.ss.nextToken(); //last line's first word (either goto or not)
     if(NewClass.co.equals("goto"))
      {
       NewClass.co=NewClass.ss.nextToken();
       label2=Integer.parseInt(NewClass.co);
       if(lno<label2)
         ifelse();
       else
         loopwhile();
       }
     else//its simple if
       simpleif();
  }
 else{
   dowhile();
  }
}
public void dowhile(){
	int sum=0,i;
	String out1="";
	   NewClass.linecount[NewClass.line-1]=3;
	StringTokenizer dw=new StringTokenizer(NewClass.out,"\n");
	for(i=0;i<label1-1;i++){

	 sum+=NewClass.linecount[i];
	}
	for(i=0;i<sum;i++){
		out1+=dw.nextToken()+"\n";
	}
	out1+="do{\n";
	while(dw.hasMoreElements()){
		out1+=dw.nextToken()+"\n";
	}
	conditioncheck(condition);
	out1+="}\nwhile("+condition+");\n";
	NewClass.out=out1;
    }
 }


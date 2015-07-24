package sri;
    import java.io.*;
    import java.awt.Color;
    import java.applet.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.awt.AWTException.*;
    import java.util.StringTokenizer;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.imageio.ImageIO;
    import javax.swing.*;

    class unknown{
    public String fun="";
    public int current=0;
    public String vname="";
    public int type=-1;
   }
    public class NewClass extends Applet implements ActionListener {
       NewClass nc;
       JFrame frame=new JFrame();
       static String s=new String(),s1,fname[]=new String[10];
       static String co,out="";
       static StringTokenizer se,ss,ds;
      static int line=0,ucount=0,linecount[]=new int[50];
      static Variables v;
      static int endc=0,beginc=0;
      static String returntype[]=new String[10];
        TextArea ta;
        TextArea to;
         Button b1,b2,b3,b4;
         Label l;
         Image img;
         static unknown unvar[]=new unknown[10];
       public NewClass()
    {
    for(int i=0;i<10;i++)
        unvar[i]=new unknown();
}

   public void init()
    {
      try {
            img = ImageIO.read(new File("9.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       resize(1000,1000);
       Font f1= new Font("SANS_SERIF",Font.BOLD,14);
       setForeground(Color.BLACK);
       frame.setSize(400, 400);
       frame.setVisible(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       b3=new Button("Help");
       
       ta=new TextArea("",30,25);
       to=new TextArea("",30,25);
       setFont(f1);
        b1=new Button("Compute");
        b2=new Button("Refresh");
        b4=new Button("Execute");
        setForeground(Color.black);
        add(b3);
        setForeground(Color.blue);
        add(ta);
        setForeground(Color.black);
        add(b1);add(b2);add(b4);
        setForeground(Color.blue);
        add(to);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        repaint();
    }
    public void start(){ }
    public void actionPerformed(ActionEvent ae){
     String comp = ae.getActionCommand();
    try{
     if(comp.equals("Compute"))
        {
          s1=ta.getText();
          ds=new StringTokenizer(s1,"$");
        while(ds.hasMoreElements()){
           out+="$";
           s=ds.nextToken();
           System.out.println(""+s);
            v=new Variables();
          se=new StringTokenizer(s,"\n");
       while(se.hasMoreTokens())
         {
          ss=new StringTokenizer(se.nextToken());
          co=ss.nextToken();
          System.out.println(co);
          line=Integer.parseInt(co);
         while(ss.hasMoreTokens()){
           co=ss.nextToken();
           System.out.println(co);
           compare();
          }
            }
       }
          endfun();
         to.setText(out);
        }
           Writer x = null;
            //place the file where turboc++ is located(inside bin)
            File file = new File("C:\\TCWIN45\\BIN\\input.c");
            x = new BufferedWriter(new FileWriter(file));
            x.write(out);
            x.close();

        if(comp.equals("Refresh")){
                nc=new NewClass();
                out="";s1="";
                beginc=0;endc=0;

                ta.setText("");
                to.setText("");
                v=new Variables();
                line=0;ucount=0;
                
         }
        if(comp.equals("Help")){
            String msg="1.Start the main algorithm with begin \n";
            msg+="2.Start every line with a line number followed by space\n";
            msg+="3.Your algorithm must be within \"begin\" and \"end\"\n";
            msg+="4.Take user input for variables using \"read data_type var\"\n";
            msg+="5.Declare arrays before using them as \"array data_type arr_name[size]\"\n";
            msg+="6.Use only if and goto keywords(even for loops)\n";
            msg+="7.While starting an algorithm for functions, start with";
            msg+=" \"$1 Algorithm fun_name(parameters)\"\n";
           JOptionPane.showMessageDialog(frame,msg);
           setVisible(true);
        }
       if(comp.equals("Execute")){
           String[] cmd = {"cmd","/c","start", "algo.bat"} ;
           Process proc = Runtime.getRuntime().exec( cmd);
           //batch file is present in the dir where our project is
           //Runtime.getRuntime().exec("cmd /c start algo.bat");
       }
      } catch (IOException ex) {
            l=new Label("Exception at line: "+line);
            add(l);
        }

    }
 public static void compare()
  {
    if(co.equals("begin"))
      { if(beginc>0){
          System.out.println(";;;;;;;;;;");
            returntype[beginc]="void ";
            v.checkvariables();
            out+="\n";
            out+=returntype[beginc];
            out+=fname[beginc]+"\n{\n";
            linecount[line-1]=3;
        }
       else
         {
            fname[0]="main()";
            returntype[0]="void";
           out += "#include<stdio.h>\n#include<conio.h>\nvoid main()\n{\n";
        linecount[line-1]=4;
        v.checkvariables();
          }
       beginc++;
        int x=0;
           if(v.icount!=0){
	      NewClass.out+="int ";
	      v.printvar(v.iparc,v.icount,v.intvar);
	       x++;
              }
          if(v.fcount!=0)
             {
               NewClass.out+="float ";
               v.printvar(v.fparc,v.fcount,v.floatvar);
               x++;
             }
          if(v.ccount!=0)
            {
              NewClass.out+="char ";
              v.printvar(v.cparc,v.ccount,v.charvar);
              x++;
            }
                linecount[0]+=x;
     }
 else if(co.equals("Algorithm")) {
     fname[beginc]="";
     while(ss.hasMoreTokens())
   fname[beginc]+=ss.nextToken()+" ";
 }
  else if(co.equals("array"))
   {
    ss.nextToken();
    ss.nextToken();
   }

   else if (co.equals("end")) {
         if(endc>0){
            out+="}\n";

        }
        else{
             out += "getch();\n}";
        }
         endc++;
         linecount[line-1]=1;
      }

   else if(co.equals("if")){
        Ifclass ifo=new Ifclass();
        ifo.checkif();
   }
   else if(co.equals("write")){
        String a=new String();
        a=ss.nextToken();
       char b[]=new char[50];
       b=a.toCharArray();
     if(b[0]=='\"'){
         out+="printf("+a;
         while(b[b.length-1]!='\"'){
             a=ss.nextToken();
             b=a.toCharArray();
             out+=" "+a;
         }
         out+=");\n";
     }
     else{
         b=a.toCharArray();
    String temp1="";
     int j=0;
     while(j<b.length&&b[j]!='[')
      {
        temp1+=b[j];
         j++;
       }
        int f1=v.checktype(temp1);
        if (f1==0)
        {
          int i=0;
          while(i<ucount&&!a.equals(unvar[i].vname))
            i++;
          if(i<ucount)
          { f1=unvar[i].type;
          if(f1==-1) f1=1;
          }
        }
        System.out.print(""+f1);
         if(f1 == 1)
         out+="printf(\"%d\","+a+");\n";
          if(f1 == 2)
         out+="printf(\"%f\","+a+");\n";
          if(f1 == 3)
         out+="printf(\"%c\","+a+");\n";

     }
     linecount[line-1]=1;
 }
 else if(co.equals("read")){

     String a=new String();
     char b[]=new char[10];
     a=ss.nextToken();//see the data type;
    
     a=ss.nextToken();
      
      b=a.toCharArray();
    String t="";
     int j=0;
      while(j<b.length)
      {
         if(b[j]=='['){break;}
        t+=b[j];
        
         j++;
       }

        int f1=v.checktype(t);

     if(f1==1)
     {

     out+="printf(\"Enter "+a+":\");\n";
     out+="scanf(\"%d\",&"+a+");\n";
     }
     if(f1==3)
     {

     out+="printf(\"Enter "+a+":\");\n";
     out+="scanf(\"%c\",&"+a+");\n";
     }
     if(f1==2)
     {

     out+="printf(\"Enter "+a+":\");\n";
     out+="scanf(\"%f\",&"+a+");\n";
     }
     linecount[line-1]=2;
  }
 else if(co.equals("return"))
    {out+="return";
        while (ss.hasMoreTokens())
         out+=" "+ss.nextToken();
    out+=";\n";
    linecount[line-1]=1;

    }
else {
	out+=""+co+";\n";
	linecount[line-1]=1;
}
 }
 public void endfun()
{
 for(int j=0;j<ucount;j++)
 {
  int i=1,x=0;
  int n=unvar[j].fun.length();
  System.out.println("fname"+fname[i]+"fun="+unvar[j].fun+"ret"+returntype[i]);
  System.out.println("     "+fname[i].substring(0,n));
  while(i<beginc&&!(unvar[j].fun.equals(fname[i].substring(0,n))))
  {System.out.print("!!!!!");
      i++;
  }
  if(i>=beginc) {System.out.println("000000000");break;}
  if(returntype[i].equals("int "))
   x=1;
  else if(returntype[i].equals("float "))
   x=2;
  else if(returntype[i].equals("char "))
   x=3;
  else x=1;
  System.out.println("$$$="+x+"$$$="+unvar[j].type);
  if(x==2) unvar[j].type=2;
  else if(x==1)
  {
  if(unvar[j].type==1 || unvar[j].type==2) {}
  else {unvar[j].type=x;}
 }
  else unvar[j].type=x;
 System.out.print(""+unvar[j].vname+" -- "+unvar[j].type);
}
         String out1="";
         se=new StringTokenizer(out,"\n");
         for(int i=0;i<2;i++)
         {
             out1+=se.nextToken()+"\n";
         }
         int i=1;
         while(i<beginc)
         {
           out1+=returntype[i]+" "+fname[i]+";\n";
           i++;
          }
         while(se.hasMoreTokens())
           out1+=se.nextToken()+"\n";
           out=out1;
     ds=new StringTokenizer(out,"$");
      int j=0,n,k;
      i=0;out1="";
        while(ds.hasMoreElements()){
           s=ds.nextToken();
           if(i==0) {n=4+beginc;}
           else n=2;
           se=new StringTokenizer(s,"\n");
       for(k=0;k<n;k++)
         out1+=se.nextToken()+"\n";
       while(j<ucount&&unvar[j].current==i)
        {
         if(unvar[j].type==1)
          out1+="int ";
         else if(unvar[j].type==2)
          out1+="float ";
         else if(unvar[j].type==3)
          out1+="char ";
         out1+=unvar[j].vname+";\n";
         j++;
        }
       while(se.hasMoreTokens())
        out1+=se.nextToken()+"\n";
       i++;
       }
      out=out1;
        // linecount[0]+=beginc;

        }

public void paint(Graphics g){
     g.drawImage(img, 0, 0, null);
}
    }
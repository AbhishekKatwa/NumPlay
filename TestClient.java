import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;


class Node
{  int token;
    Node left;
   Node right;
   String str;
   public Node(String s,int t)
   {  token=t;
      str=s;
      left=null;
      right=null;
   }
}

class LIST extends JFrame implements ActionListener
{  JFrame f;
   JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,img,back;
   String[] imgs;
   Node list,shufList;
   
   public LIST()
   {  list=null;
      shufList=null; 
      f=new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().setBackground(Color.ORANGE);
      f.setLocation(300,170);     
       back = new JButton("BACK");
       back.setFont(new Font("ARIAL",1,20));
       back.setBounds(220,450,140,70);
       back.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       f.setVisible(false);
       StartPage f=new StartPage(); 
       }
       });
      f.add(back);
      
      img=new JButton(new ImageIcon("comp.jpg"));
                  img.setBounds(400,50,220,220);  
                  f.add(img);

      
      
      imgs=new String[]{"img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg","img6.jpg","img7.jpg","img8.jpg","img9.jpg","null.jpg"};
      createList();
   }
   
   //create list creates original picture list 
   public void createList()
   {  Node temp=null;  
      for(int i=1;i<=9;i++)
      {  Node newnode=new Node(imgs[i-1],i);
         if(list==null)
         {   list=newnode;
             temp=list;
         }
         else{
            temp.right=newnode;
            newnode.left=temp;
            temp=temp.right;
         }
      }
      createPuzzle();   //calls createPuzzle to create puzzled picture list
   }
   
   public void createPuzzle()
   {  //copy contents of original list to shufList
      Node temp1=list;
      Node temp2=null;
      while(temp1!=null)
      {  
         Node newnode=new Node(temp1.str,temp1.token);     
         if(shufList==null)
         {   shufList=newnode;
             temp2=shufList;
         }
         else{
            temp2.right=newnode;
            newnode.left=temp2;
            temp2=temp2.right;
         }
         temp1=temp1.right;
      }
      
      listTraverse(list);
      listTraverse(shufList);
      //now shuffle copied shufList
      shuffle();
      listTraverse(shufList);
      
      /*insert images of shufList to all buttons 
         and display it in frame   
      */
      
       // goto Node's token number 9 and set that to null
      temp1=shufList;
      while(temp1.token!=9 && temp1!=null)
         temp1=temp1.right;
      if(temp1.token==9)
         temp1.str="null.jpg";
      
      
      //set tokens in increasing order (because token represents position of node/image in list)
      int count=0;   
      temp1=shufList;
      while(temp1!=null)
      {  count++; 
         temp1.token=count;
         temp1=temp1.right;
      }


      //first button(base case)
      b1=new JButton(new ImageIcon(shufList.str));
      b1.setBounds(60,80,100,100);
      f.add(b1);
   
      count=1;
      temp1=shufList;
      
      while(temp1!=null)
      {
            temp1=temp1.right;
            
            count++;
            if(count==2)
                  {
                  b2=new JButton(new ImageIcon(temp1.str));
                  b2.setBounds(160,80,100,100);  
                  f.add(b2);
                  }
            if(count==3)
                  {
                  b3=new JButton(new ImageIcon(temp1.str));
                  b3.setBounds(260,80,100,100);  
                  f.add(b3);
                  }
            if(count==4)
                  {
                  b4=new JButton(new ImageIcon(temp1.str));
                  b4.setBounds(60,180,100,100);
                  f.add(b4);
                  }
            if(count==5)
                  {
                  b5=new JButton(new ImageIcon(temp1.str));
                   b5.setBounds(160,180,100,100);  
                  f.add(b5);
                  }
            if(count==6)
                  {
                  b6=new JButton(new ImageIcon(temp1.str));
                  b6.setBounds(260,180,100,100);  
                  f.add(b6);
                  }
            if(count==7)
                  {
                  b7=new JButton(new ImageIcon(temp1.str));
                  b7.setBounds(60,280,100,100);
                  f.add(b7);
                  }
            if(count==8)
                  {
                  b8=new JButton(new ImageIcon(temp1.str));
                   b8.setBounds(160,280,100,100);  
                  f.add(b8);
                  }
            if(count==9)
                  {
                  b9=new JButton(new ImageIcon(temp1.str));
                  b9.setBounds(260,280,100,100);  
                  f.add(b9);
                  }
            
       }
       
                  
      
      //add to frame and set actionListener
      b1.addActionListener(this);  
      b2.addActionListener(this);  
      b3.addActionListener(this);
      b4.addActionListener(this);
      b5.addActionListener(this);  
      b6.addActionListener(this);  
      b7.addActionListener(this);
      b8.addActionListener(this);
      b9.addActionListener(this);
      
      f.setSize(700,700);
      f.setLayout(null);
      f.setVisible(true);
      f.setResizable(false);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
   
   // event handlers
   /* whenever the button is clicked swap method to be called
   */
   public void actionPerformed(ActionEvent e)
   {  String ix;
      int count;
      if(e.getSource()==b1)
      {
         ix=( (ImageIcon)b1.getIcon()).getDescription();
         count=1;
      }
      else if(e.getSource()==b2)
      {
         ix=( (ImageIcon)b2.getIcon()).getDescription();
         count=2;
      }
      else if(e.getSource()==b3)
      {
         ix=( (ImageIcon)b3.getIcon()).getDescription();
         count=3;
      }
      else if(e.getSource()==b4)
      {
         ix=( (ImageIcon)b4.getIcon()).getDescription();
         count=4;
      }
      else if(e.getSource()==b5)
      {
         ix=( (ImageIcon)b5.getIcon()).getDescription();
         count=5;
      }
      else if(e.getSource()==b6)
      {
         ix=( (ImageIcon)b6.getIcon()).getDescription();
         count=6;
      }
      else if(e.getSource()==b7)
      {
         ix=( (ImageIcon)b7.getIcon()).getDescription();
         count=7;
      }  
      else if(e.getSource()==b8)
      {
         ix=( (ImageIcon)b8.getIcon()).getDescription();
         count=8;
      }
      else
      {
         ix=( (ImageIcon)b9.getIcon()).getDescription();
         count=9;
      }
      Listswap(ix,count);
      //after swapping and adjusting list and displaying check whether game is complete
      if(isComplete())
      {  Node temp=shufList;
         while(temp.right!=null)
            temp=temp.right;
         temp.str="img9.jpg";
         adjustpuz();  
         System.out.println("YOU WON");
         JOptionPane.showMessageDialog(this,"You won"); 
      }
   }
   
   //swaping after the action performed
   public void Listswap(String y,int count)
   {  
      Node temp1=shufList;
      while(temp1.str!=y)
         temp1=temp1.right;
      Node temp2=shufList;
      while(temp2.str!="null.jpg")
         temp2=temp2.right;
      if((count%3)!=0)
      {
         if(Math.abs(temp1.token-temp2.token)==3 || Math.abs(temp1.token-temp2.token)==1)
         {  String s=temp1.str;
            //int t=temp1.token;
            
            temp1.str=temp2.str;
            //temp1.token=temp2.token;
            
            temp2.str=s;
            //temp2.token=t;
         }
      }
      else   
      {
          if((Math.abs(temp1.token-temp2.token)==3) || temp1.token-temp2.token==1)
         {  String s=temp1.str;
            //int t=temp1.token;
            
            temp1.str=temp2.str;
            //temp1.token=temp2.token;
            
            temp2.str=s;
            //temp2.token=t;
         }

      }
      //adjust frame to redraw buttons after swap
      adjustpuz();
   }
   public void adjustpuz()
   {  Node temp1=shufList;
      b1.setIcon(new ImageIcon(temp1.str));
      int num=1;
      while(temp1!=null)
      {
         num++;
         temp1=temp1.right;
         if(num==2)
            b2.setIcon(new ImageIcon(temp1.str));
         else if(num==3)
            b3.setIcon(new ImageIcon(temp1.str));
         else if(num==4)
            b4.setIcon(new ImageIcon(temp1.str));
         else if(num==5)
            b5.setIcon(new ImageIcon(temp1.str));
         else if(num==6)
            b6.setIcon(new ImageIcon(temp1.str));
         else if(num==7)
            b7.setIcon(new ImageIcon(temp1.str));
         else if(num==8)
            b8.setIcon(new ImageIcon(temp1.str));
         else if(num==9)
            b9.setIcon(new ImageIcon(temp1.str));
       }
   }

   public void listTraverse(Node x)
   {  Node temp=x;
      for(int i=1;i<=9;i++)
      {  if(temp!=null)
            System.out.print(temp.token+"+"+temp.str+"\n");
         if(i%3==0)
            System.out.println();
         if(temp!=null)
            temp=temp.right;
      }
   }
   
   public boolean isComplete()
   {  Node temp1=shufList;
      Node temp2=list;
      while(temp1.right!=null)
      {  if(temp1.str!=temp2.str)
            return false;
         temp1=temp1.right;
         temp2=temp2.right;
      }
      return true;
   }
   
   public void shuffle()
   {  Node temp=shufList;
      Node t=shufList;
      for(int i=8;i>=0;i--)
      {  int j=0+(int)(Math.random()*i+1);
         temp=shufList;
         while(temp.token!=i+1)
            temp=temp.right;
         int r=temp.token;
         String s=temp.str;
         
         t=shufList;
         while(t.token!=j)
            t=t.right;
         
         temp.token=t.token;
         temp.str=t.str;
         
         t.token=r;
         t.str=s;
      }
      //System.out.println("IS GAME COMPLETE:"+isComplete());
   }
   
}






class FLIST extends JFrame implements ActionListener
{  JFrame f;
   JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,back,img;
   String[] imgs;
   Node list,shufList;
   
   public FLIST()
   {  list=null;
      shufList=null; 
      f=new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      f.getContentPane().setBackground(Color.ORANGE);
      f.setLocation(300,170);     
      back = new JButton("BACK");
      back.setFont(new Font("ARIAL",1,20));
      back.setBounds(220,550,140,70);
       back.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
       f.setVisible(false);
       StartPage f=new StartPage(); 
       }
       });

      f.add(back);
    
      imgs=new String[]{"img1.jpg","img2.jpg","img3.jpg","img4.jpg","img5.jpg","img6.jpg","img7.jpg","img8.jpg","img9.jpg","img10.jpg","img11.jpg","img12.jpg","img13.jpg","img14.jpg","img15.jpg","img16.jpg","null.jpg"};
      createList();
   }
   
   //create list creates original picture list 
   public void createList()
   {  Node temp=null;  
      for(int i=1;i<=16;i++)
      {  Node newnode=new Node(imgs[i-1],i);
         if(list==null)
         {   list=newnode;
             temp=list;
         }
         else{
            temp.right=newnode;
            newnode.left=temp;
            temp=temp.right;
         }
      }
      createPuzzle();   //calls createPuzzle to create puzzled picture list
   }
   
   public void createPuzzle()
   {  //copy contents of original list to shufList
      Node temp1=list;
      Node temp2=null;
      while(temp1!=null)
      {  Node newnode=new Node(temp1.str,temp1.token);     
         if(shufList==null)
         {   shufList=newnode;
             temp2=shufList;
         }
         else{
            temp2.right=newnode;
            newnode.left=temp2;
            temp2=temp2.right;
         }
         temp1=temp1.right;
      }
      
      listTraverse(list);
      listTraverse(shufList);
      //now shuffle copied shufList
      shuffle();
      listTraverse(shufList);
      
      /*insert images of shufList to all buttons 
         and display it in frame   
      */
      
       // goto Node's token number 16 and set that to null

            //set tokens in increasing order (because token represents position of node/image in list)
      int count=0;   
      temp1=shufList;
      while(temp1!=null)
      {  count++; 
         temp1.token=count;
         temp1=temp1.right;
      }


      //first button(base case)
      b1=new JButton(new ImageIcon(shufList.str));
      b1.setBounds(60,80,100,100);
      f.add(b1);
   
      count=1;
      temp1=shufList;
      
      while(temp1.right!=null)
      {
            temp1=temp1.right;
            
            count++;
            System.out.println(count);
            if(count==2)
                  {
                  b2=new JButton(new ImageIcon(temp1.str));
                  b2.setBounds(160,80,100,100);  
                  f.add(b2);
                  }
            if(count==3)
                  {
                  b3=new JButton(new ImageIcon(temp1.str));
                  b3.setBounds(260,80,100,100);  
                  f.add(b3);
                  }
            if(count==4)
                  {
                  b4=new JButton(new ImageIcon(temp1.str));
                  b4.setBounds(360,80,100,100);
                  f.add(b4);
                  }
            if(count==5)
                  {
                  b5=new JButton(new ImageIcon(temp1.str));
                   b5.setBounds(60,180,100,100);  
                  f.add(b5);
                  }
            if(count==6)
                  {
                  b6=new JButton(new ImageIcon(temp1.str));
                  b6.setBounds(160,180,100,100);  
                  f.add(b6);
                  }
            if(count==7)
                  {
                  b7=new JButton(new ImageIcon(temp1.str));
                  b7.setBounds(260,180,100,100);
                  f.add(b7);
                  }
            if(count==8)
                  {
                  b8=new JButton(new ImageIcon(temp1.str));
                   b8.setBounds(360,180,100,100);  
                  f.add(b8);
                  }
            if(count==9)
                  {
                  b9=new JButton(new ImageIcon(temp1.str));
                  b9.setBounds(60,280,100,100);  
                  f.add(b9);
                  }
            if(count==10)
                  {
                  b10=new JButton(new ImageIcon(temp1.str));
                  b10.setBounds(160,280,100,100);  
                  f.add(b10);
                  }
            if(count==11)
                  {
                  b11=new JButton(new ImageIcon(temp1.str));
                  b11.setBounds(260,280,100,100);  
                  f.add(b11);
                  }
            if(count==12)
                  {
                  b12=new JButton(new ImageIcon(temp1.str));
                  b12.setBounds(360,280,100,100);  
                  f.add(b12);
                  }

            if(count==13)
                  {
                  b13=new JButton(new ImageIcon(temp1.str));
                  b13.setBounds(60,380,100,100);  
                  f.add(b13);
                  }
            if(count==14)
                  {
                  b14=new JButton(new ImageIcon(temp1.str));
                  b14.setBounds(160,380,100,100);  
                  f.add(b14);
                  }
            if(count==15)
                  {
                  b15=new JButton(new ImageIcon(temp1.str));
                  b15.setBounds(260,380,100,100);  
                  f.add(b15);
                  }
            if(count==16)
                  {
                  b16=new JButton(new ImageIcon(temp1.str));
                  b16.setBounds(360,380,100,100);  
                  f.add(b16);
                  }

       }
       
                  
      
      //add to frame and set actionListener
      b1.addActionListener(this);  
      b2.addActionListener(this);  
      b3.addActionListener(this);
      b4.addActionListener(this);
      b5.addActionListener(this);  
      b6.addActionListener(this);  
      b7.addActionListener(this);
      b8.addActionListener(this);
      b9.addActionListener(this);
      b10.addActionListener(this);
      b11.addActionListener(this);
      b12.addActionListener(this);
      b13.addActionListener(this);
      b14.addActionListener(this);
      b15.addActionListener(this);
      b16.addActionListener(this);
      
      f.setSize(700,700);
      f.setLayout(null);
      f.setVisible(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
   
   // event handlers
   /* whenever the button is clicked swap method to be called
   */
   public void actionPerformed(ActionEvent e)
   { 
      String ix;
      int count;
      if(e.getSource()==b1)
      {
         ix=( (ImageIcon)b1.getIcon()).getDescription();
         count=1;
      }  
      else if(e.getSource()==b2)
      {
         ix=( (ImageIcon)b2.getIcon()).getDescription();
         count=2;
      }
      else if(e.getSource()==b3)
      {
         ix=( (ImageIcon)b3.getIcon()).getDescription();
         count=3;
      }
      else if(e.getSource()==b4)
      {
         ix=( (ImageIcon)b4.getIcon()).getDescription();
         count=4;
      } 
      else if(e.getSource()==b5)
      {
         ix=( (ImageIcon)b5.getIcon()).getDescription();
         count=5;
      }
      else if(e.getSource()==b6)
      {
         ix=( (ImageIcon)b6.getIcon()).getDescription();
         count=6;
      }
      else if(e.getSource()==b7)
      {
         ix=( (ImageIcon)b7.getIcon()).getDescription();
         count=7;
      }  
      else if(e.getSource()==b8)
      {
         ix=( (ImageIcon)b8.getIcon()).getDescription();
         count=8;
      }
      else if(e.getSource()==b9)
      {
         ix=( (ImageIcon)b9.getIcon()).getDescription();
         count=9;
      } 
      else if(e.getSource()==b10)
      {
         ix=( (ImageIcon)b10.getIcon()).getDescription();
         count=10;
      }
      else if(e.getSource()==b11)
      {
         ix=( (ImageIcon)b11.getIcon()).getDescription();
         count=11;
      }
      else if(e.getSource()==b12)
      {
         ix=( (ImageIcon)b12.getIcon()).getDescription();
         count=12;
      }
      else if(e.getSource()==b13)
      {
         ix=( (ImageIcon)b13.getIcon()).getDescription();
         count=13;
      }
      else if(e.getSource()==b14)
      {
         ix=( (ImageIcon)b14.getIcon()).getDescription();
         count=14;
      }
      else if(e.getSource()==b15)
      {
         ix=( (ImageIcon)b15.getIcon()).getDescription();
         count=15;
      }
      else
      {
         ix=( (ImageIcon)b16.getIcon()).getDescription();
         count=16;
      }






      Listswap(ix,count);
      //after swapping and adjusting list and displaying check whether game is complete
      if(isComplete())
      {  Node temp=shufList;
         while(temp.right!=null)
            temp=temp.right;
         temp.str="img16.jpg";
         adjustpuz();  
         System.out.println("YOU WON");
         JOptionPane.showMessageDialog(this,"You won"); 
      }
   }
   
   //swaping after the action performed
   public void Listswap(String y,int count)
   {  Node temp1=shufList;
      while(temp1.str!=y)
         temp1=temp1.right;
      Node temp2=shufList;
      while(temp2.str!="null.jpg")
         temp2=temp2.right;
      if(count%3!=0)
      {
         if(Math.abs(temp1.token-temp2.token)==4 || Math.abs(temp1.token-temp2.token)==1)
         {  String s=temp1.str;
            //int t=temp1.token;
            
            temp1.str=temp2.str;
            //temp1.token=temp2.token;
            
            temp2.str=s;
            //temp2.token=t;
         }
      }
      else
      {
         if(Math.abs(temp1.token-temp2.token)==4 || temp1.token-temp2.token==1)
         {  String s=temp1.str;
            //int t=temp1.token;
            
            temp1.str=temp2.str;
            //temp1.token=temp2.token;
            
            temp2.str=s;
            //temp2.token=t;
         }
      }
      //adjust frame to redraw buttons after swap
      adjustpuz();
   }
   public void adjustpuz()
   {  Node temp1=shufList;
      b1.setIcon(new ImageIcon(temp1.str));
      int num=1;
      while(temp1!=null)
      {
         num++;
         temp1=temp1.right;
         if(num==2)
            b2.setIcon(new ImageIcon(temp1.str));
         else if(num==3)
            b3.setIcon(new ImageIcon(temp1.str));
         else if(num==4)
            b4.setIcon(new ImageIcon(temp1.str));
         else if(num==5)
            b5.setIcon(new ImageIcon(temp1.str));
         else if(num==6)
            b6.setIcon(new ImageIcon(temp1.str));
         else if(num==7)
            b7.setIcon(new ImageIcon(temp1.str));
         else if(num==8)
            b8.setIcon(new ImageIcon(temp1.str));
         else if(num==9)
            b9.setIcon(new ImageIcon(temp1.str));
         else if(num==10)
            b10.setIcon(new ImageIcon(temp1.str));
         else if(num==11)
            b11.setIcon(new ImageIcon(temp1.str));
         else if(num==12)
            b12.setIcon(new ImageIcon(temp1.str));
         else if(num==13)
            b13.setIcon(new ImageIcon(temp1.str));
         else if(num==14)
            b14.setIcon(new ImageIcon(temp1.str));
         else if(num==15)
            b15.setIcon(new ImageIcon(temp1.str));
         else if(num==16)
            b16.setIcon(new ImageIcon(temp1.str));
         
       }
   }

   public void listTraverse(Node x)
   {  Node temp=x;
      for(int i=1;i<=16;i++)
      {  if(temp!=null)
            System.out.print(temp.token+"+"+temp.str+"\n");
         if(i%4==0)
            System.out.println();
         if(temp!=null)
            temp=temp.right;
      }
   }
   
   public boolean isComplete()
   {  Node temp1=shufList;
      Node temp2=list;
      while(temp1.right!=null)
      {  if(temp1.str!=temp2.str)
            return false;
         temp1=temp1.right;
         temp2=temp2.right;
      }
      return true;
   }
   
   public void shuffle()
   {  Node temp=shufList;
      Node t=shufList;
      for(int i=15;i>=0;i--)
      {  int j=0+(int)(Math.random()*i+1);
         temp=shufList;
         while(temp.token!=i+1)
            temp=temp.right;
         int r=temp.token;
         String s=temp.str;
         
         t=shufList;
         while(t.token!=j)
            t=t.right;
         
         temp.token=t.token;
         temp.str=t.str;
         
         t.token=r;
         t.str=s;
      }
      //System.out.println("IS GAME COMPLETE:"+isComplete());
   }
   
}





class StartPage
{
   LIST l;
   FLIST tf;
   //Options o;
   JFrame f;
   JLabel l1,l2,l3;
   JButton start,Rules,Easy,difficult;
   JPanel p;
   StartPage()
   {
   
      f=new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocation(300,170);
      f.getContentPane().setBackground(Color.BLUE);
      
      l1=new JLabel("PUZZLE GAME");
      l2=new JLabel("START GAME");
      l3=new JLabel("RULES");
      l1.setBounds(220,50,260,50);
      l1.setFont(new Font("ARIAL",3,30));
      l2.setBounds(0,180,200,50);
      l2.setFont(new Font("ARIAL",2,30));
      l3.setBounds(150,220,200,50);
      l3.setFont(new Font("ARIAL",2,30));
    
      Easy = new JButton("EASY");
      Easy.setFont(new Font("ARIAL",1,20));
      Easy.setBounds(230,180,140,70);
      difficult = new JButton("DIFFICULT");
      difficult.setFont(new Font("ARIAL",1,20));
      difficult.setBounds(430,180,140,70);
      Rules = new JButton("RULES");
      Rules.setFont(new Font("ARIAL",1,20));
      Rules.setBounds(230,260,140,70);
      
      f.add(Easy);
      f.add(difficult);
      f.add(Rules);
      f.add(l1);
      f.add(l2);
      f.add(l3);
      
      f.setSize(750,600);
      f.setVisible(true);
      f.setResizable(false);
      
      
      
      
      
      Easy.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         f.dispose();
         l=new LIST();
                 }
      });
      difficult.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
         f.dispose();
         tf=new FLIST();
         }
      });      
   }
}

//Game Client
class TestClient
{     public static void main(String args[])
      { 
         StartPage f=new StartPage();    
      }
   
}

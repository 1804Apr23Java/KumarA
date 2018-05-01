package com.revature.CodeChallengeOne;
import java.util.*;
public class ProducerConsumerThread {
	 
	
	
        LinkedList<Integer> list = new LinkedList<>();
        int capacity = 1;
 
        // Function called by producer thread
        public void letsProduce() throws InterruptedException
        {
        	
            int value = 0;
           // while (true)
            {
            	synchronized (this)
                {
                 
                    while (list.size()==capacity)
                        wait();
 
                    System.out.println("Producer is producing and puting in basket-"
                                                  + value);
 
                    
                    list.add(value++);
 
                    
                    notify();
 
                    
                    Thread.sleep(500);
                }
            }
        }
 
       //function called by the consumer
        public void letsConsume() throws InterruptedException
        {
           // while (true)
            {
               synchronized (this)
                {
                    
                    while (list.size()==0)
                        wait();
 
                   
                    int val = list.removeFirst();
 
                    System.out.println("Consumer taking out of basket and consuming-"
                                                    + val);
 
                    
                    notify();
 
                   
                    Thread.sleep(500);
                }
            }
        }	
//}
	public static void main(String[] args) throws InterruptedException{
			
      
		ProducerConsumerThread pct = new ProducerConsumerThread(); 
        
		//producer thread
        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pct.letsProduce();
                }
                catch(InterruptedException e)
                {
                  
                	
                	System.out.println("Something went wrong");
                }
                
            }
        });
	
        // consumer thread below
        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pct.letsConsume();
                }
                catch(InterruptedException e)
                {
                	System.out.println("Something went wrong");
                }
            }
        });
 
        // both threads start at the same time
        th1.start();
        th2.start();
 
      try { 
        th1.join();
        th2.join();
      } catch(Exception e) {
    	  System.out.println("Did you just interupted me");
      }
      }
    }


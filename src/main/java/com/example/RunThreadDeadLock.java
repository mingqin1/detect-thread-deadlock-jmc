/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author Ming Qin  ( mingqin@live.com)
 */
public class RunThreadDeadLock {
    
    private static class LeftRightDeadLock implements Runnable{
        private static final Object left = new Object();
        private static final Object right = new Object();
        private int number =1;  // change value of numbe to 4000,  to find 
                                // thread starvation issue.
        
        
        public void doSomething( int number){
            for ( int i=0; i< number; i++){
                            char[] tmp = new char [1024*1024];
                            tmp [1]='a';

                        }
            
        }
        public void leftRight() {
                synchronized (left) {
                    synchronized (right) {
                       doSomething (number); 
                        
                    }
                }
       }

       public void rightLeft() {
            synchronized (right) {
                synchronized (left) {
                    doSomething(number); 
                }
            }
        }
       
       public void run() {
		try {
			while (true) {
				leftRight();
                                rightLeft();
			}
		} catch (Exception e) {
	              System.out.println ( e.getMessage());
                      System.out.flush();
		}
	}

    }
    
    public static void main ( String[] args ){
        LeftRightDeadLock first = new LeftRightDeadLock( );
        LeftRightDeadLock second = new LeftRightDeadLock( );
        LeftRightDeadLock third = new LeftRightDeadLock( );
       
        new Thread( first).start();
        new Thread( second).start();
        new Thread( third).start();
           
           
  
    
    }
    
}

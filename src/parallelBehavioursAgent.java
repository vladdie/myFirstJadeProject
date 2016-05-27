import jade.core.Agent;
import jade.core.behaviours.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fan
 */
public class parallelBehavioursAgent extends Agent{
    
     private long t0 = System.currentTimeMillis();
     
     protected void setup() 
        {
            System.out.println("Agent "+ getLocalName() + " is initializing..."); 
            //agent creates two instances of a Looper behaviour
            //the first Looper should print a line every 0.3s and the second every 0.5s.
            //addBehaviour( new Looper( this, 300 ) );
            //addBehaviour( new Looper( this, 500 ) );
            
            //addBehaviour( new TwoStep());
            
            //addBehaviour( new BlockTwice());
            
//            addBehaviour( new sleepTest(this, t0) );
//            addBehaviour( new Looper( this, 300 ) );

            addBehaviour( new Step1(this, t0) );
            addBehaviour( new Looper( this, 300 ) );
            System.out.println("Set up finished. Agent "+ getLocalName() + " is ready."); 
        }
     
     
    /*****************************************************************

        Agent2.java:  Combining sequential actions with a cyclic Looper
            -----------     behaviour
    *****************************************************************/
    
}

class TwoStep extends SimpleBehaviour
{   
    public void action() 
    {
        block(250);
        System.out.println( "--- Message 1 --- " );
        block(500);
        System.out.println( "    - message 2 " );
        finished = true;// behaviour only execute once.
    }
    
    private boolean finished = false;
    public  boolean done() {  
        return finished;  
    }
}
/********************************************************************
	Looper.java:  
        - Simple behaviour which loops printing out the name
	            of its agent every DT msec, where DT is a parameter.
	            
	- Looper Behaviour to print its output in different columns
*********************************************************************/
class Looper extends SimpleBehaviour
{
	static String offset = "";
	static long   t0     = System.currentTimeMillis();
	
	String tab = "" ;
	int    n   = 1;
	long   dt;
	
	public Looper( Agent a, long dt) {
		super(a);
		this.dt = dt;
		offset += "    " ;
   		tab = new String(offset) ;
	}
	
	public void action() 
	{
            System.out.println( "--- Looper start: " );
            System.out.println( tab + 
                       (System.currentTimeMillis()-t0)/10*10 + ": " +
			myAgent.getLocalName() + " works every " + dt + " ms");
            // block() takes the behaviour out of the active queue and starts a timer to make it active again after the prescribed delay 
            // block(dt) doesn't block; it just sets a delay for the next execution of the behaviour
            block( dt );
            System.out.println( tab + "Loop block started... " );
            n++;
	}
	
	public  boolean done() { 
            return n>6; 
           //return false;
        }

}

/*********************************************************************

    BlockTwice.java:   Experiment to see the effect of calling block() twice.
   
***********************************************************************/
class BlockTwice extends SimpleBehaviour
{   
    static long t0 = System.currentTimeMillis();
    
    public void action() 
    {
        System.out.println( "Start: " 
                   + (System.currentTimeMillis()-t0) );
        block(250);
        System.out.println( "   after block(250): " 
                   + (System.currentTimeMillis()-t0) );
        block(1000);
        System.out.println( "   after block(1000): " 
                   + (System.currentTimeMillis()-t0) );
        System.out.println();
    }
    
    private int n = 0;
    public  boolean done() {  return ++n > 3;  }
}

/*****************************************************************

    sleepTest.java:  Using SLEEP to pace sequential actions 
    ---------     
    * Result: no intersections of sleepTest and Loop.
*****************************************************************/
class sleepTest extends SimpleBehaviour
{   
        long t0;
        public sleepTest(Agent a, long t0) { 
          super(a);  
          this.t0 = t0;
        }


        public void action() 
        {
           try
           {  System.out.println( "--- sleepTest start: " 
                       + (System.currentTimeMillis()- t0)/10*10 );

              Thread.sleep(200);
              System.out.println( " -- Message 1 ---: "
                       + (System.currentTimeMillis()-t0)/10*10 );

              Thread.sleep(500);
              System.out.println( "  - message 2    : "
                       + (System.currentTimeMillis()-t0)/10*10 );
           }
           catch (Exception e) {}
        }

        private int n = 0;
        public  boolean done() {  return ++n > 2;  }
}


class Step1 extends SimpleBehaviour
    {	
            int state = 0;
            long t0;
            public Step1(Agent a, long t0) 
            { 
            super(a);  
            this.t0 = t0;
            }
            public void action() 
            {
                    if (state==0){  
                            block( 200 );
                            System.out.println( "step 1, state 0, block 200ms starts... " );
                    }
                    else {
                            System.out.println( "--- Message 1 --- "  + (System.currentTimeMillis()-t0)/10*10 );
                            myAgent.addBehaviour( new Step2(myAgent, t0) );
                    }
                    state++;
            }

            public  boolean done() {  return state>1;  }
    }

    class Step2 extends SimpleBehaviour
    {	
            int state = 0;
            long t0;
            public Step2(Agent a, long t0) 
            { 
                super(a);  
                this.t0 = t0;
            }
            public void action() 
            {
                    if (state==0){  
                            block( 600 );
                            System.out.println( "step 2, state 0, block 600ms starts... " );
                    }
                    else {
                            System.out.println( "    - message 2 " + (System.currentTimeMillis()-t0)/10*10 );
                            myAgent.doDelete();   // applies to the Agent
                    }
                    state++;
            }

            public  boolean done() {  return state>1;  }
    }
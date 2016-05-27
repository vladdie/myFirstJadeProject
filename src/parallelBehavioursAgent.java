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
     protected void setup() 
        {
            System.out.println("Agent "+ getLocalName() + " is initializing..."); 
            //agent creates two instances of a Looper behaviour
            //the first Looper should print a line every 0.3s and the second every 0.5s.
            addBehaviour( new Looper( this, 300 ) );
            addBehaviour( new Looper( this, 500 ) );
        }
}

/********************************************************************

	Looper.java:  Simple behaviour which loops printing out the name
	            of its agent every DT msec, where DT is a parameter.
	            
	- We also arrange for each Looper Behaviour to print its output
	  in different columns

 Author:  Jean Vaucher
 Date:    Aug 2 2003 
	
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
		System.out.println( tab + 
		   (System.currentTimeMillis()-t0)/10*10 + ": " +
			myAgent.getLocalName() + " working every " + dt + " ms");
               // block() takes the behaviour out of the active queue and starts a timer to make it active again after the prescribed delay 
		block( dt );
		n++;
	}
	
	public  boolean done() { 
            return n>6; 
           //return false;
        }

}

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
public class helloAgent extends Agent{
      protected void setup() 
      { 
          //the arguments are obtained by calling the method getArguments which returns an array of Objects 
          //the objects must be cast to Strings
          //GUI input: -gui -agents "helloAgent:helloAgent(3,Feya,Yu )"
          Object[] args = getArguments();
          if(args != null){
              for(int i = 0; i<args.length; i++){
                  System.out.println("Parameter " + i + " is: " + (String)args[i]);
              }
              
              //extracting the integer
              int i = Integer.parseInt((String)args[0]);
              System.out.println("i * i = " + i * i);
          }
          System.out.println("Hello World. ");
          //System.out.println("Agent "+ getLocalName() + " is ready."); 
          
          //pass a reference to the owner agent (this) when creating a Behaviour.
          addBehaviour(// -------- Anonymous SimpleBehaviour 
                  new myBehaviour(this));
      }     
}

class myBehaviour extends SimpleBehaviour 
        {   
            public myBehaviour(Agent a) { 
                super(a);  
            }
            
            int n = 0;
            
            public void action() 
            {
               //...this is where the real programming goes !!
                //myAgent: a local variable of all Behaviour objects which stores the agent reference passed as a parameter when create the behaviour.
                //myBehaviour is outside the context of Agent, use myAgent to access its owner's attributes and methods
                System.out.println("Agent "+ myAgent.getLocalName() + " is ready."); 
                n++;
            }
            
            //
            private boolean finished = false;
            
            public boolean done() {  
                // a FINISHED flag which is tested in the "done" method
                // FINISHED is set in action
               //return finished;  
               return n >= 3;  
            }
            
        } // ----------- End myBehaviour
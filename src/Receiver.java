
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;
import jade.core.AID;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Receiver.java:  Agent which receives and prints messages
 * @author Fan
 */
public class Receiver extends Agent {
    protected void setup(){
        System.out.println("Agent "+ getLocalName() + " is initializing..."); 
        //CyclicBehaviour: stays active as long as its agent is alive. 
        //usually used to handle message reception
        //by using this behaviour, we don't need to specify done() method with false
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg = receive();
                if(msg != null){
                    System.out.println(myAgent.getLocalName() + " has a new message! " );
                    System.out.println(" - " + myAgent.getLocalName() + " <- " + msg.getContent());
                    //use block to put the behaviour on hold until the next message is received, at which
                    //all behaviours whether waiting for messages or the passage of time(with block(dt)) will be 
                    //activated and their action methods called one after the other
                    //without block(), agent behaviour will stay ative and cause a loop
                    
                    
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Respond to sender: Information received! ");
                    send(reply);
                }
                
                block();
            }
            
        });
        System.out.println("Agent "+ getLocalName() + " is ready."); 
    }
}


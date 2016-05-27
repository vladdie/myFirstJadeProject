
import jade.core.Agent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fan
 */
public class JadeProj extends Agent{
    @Override
    protected void setup(){
        System.out.println("Agent is created");
    }
    
    @Override
    protected void takeDown(){
        System.out.println("Agent is destoryed");
    }
}

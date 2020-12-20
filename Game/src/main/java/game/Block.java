/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author migue
 */
public class Block extends AbstractGameObject{
    
    private String path_icon;
    
    public Block(String path_icon, Position position) {
        super(position);
        this.path_icon = path_icon;
    }
    public Block(Position position) {
        super(position);
    }
}

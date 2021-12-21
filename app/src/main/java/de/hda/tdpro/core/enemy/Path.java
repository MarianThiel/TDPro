package de.hda.tdpro.core.enemy;

import java.util.ArrayList;
import java.util.List;

import de.hda.tdpro.core.Position;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Class representing the path of a map or an Enemy
 * works like a linked list
 */
public class Path {
    AscPoint start;

    public Path() {

    }


    public void addPoint(int x, int y){
        if(start == null){
            start = new AscPoint(x,y);

        }else
        for(AscPoint p = start; p != null; p = p.getNextPoint()){
            if(p.getNextPoint()==null){
                p.setNextPoint(new AscPoint(x,y));
                return;
            }
        }
    }

    public List<Position> generateAllPositions(){
        List<Position> list = new ArrayList<>();
        for (AscPoint p = start; p!= null; p = p.getNextPoint()){
            list.addAll(p.calculatePositions());
        }
        List<Position> noDupList = new ArrayList<>();
        for(Position p : list){
            if(!noDupList.contains(p)){
                noDupList.add(p);
            }
        }
        return noDupList;
    }
}

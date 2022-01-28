package de.hda.tdpro.core.factories;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import de.hda.tdpro.core.persistence.ConfigWriter;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.path.Position;
import de.hda.tdpro.core.enemy.EnemyType;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.path.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.misc.MiscObject;
import de.hda.tdpro.core.misc.MiscType;

public class GameFactory {

    private static GameFactory instance;

    private final DocumentBuilderFactory documentBuilderFactory;

    public static final String CONFIG_PATH = "config/";

    private GameFactory(){
        documentBuilderFactory = DocumentBuilderFactory.newInstance();

    }

    public static GameFactory getInstance() {
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }

    public Game createLevelOne(){
        Path p = PathFactory.getInstance().createRelativePathTest();
        try {
            Game g = parseGameConfig("level_one_config.xml", p);
            return g;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Game createDemoLevel(){
        Path p = PathFactory.getInstance().createRelativePathTest();
        try {
            List<Position> l = p.generateAllPositions();
            Game g = parseGameConfig("demo_level.xml", p);
            Position p1 = l.get(50);
            Position p2 = new Position(p1.getxVal(),p1.getyVal()+100);
            Position p3 = new Position(p2.getxVal()+550,p2.getyVal()-160);
            MiscObject m = new MiscObject(new Position(p1.getxVal(),p1.getyVal()+100), MiscType.TREE_LARGE);
            g.insertMisc(m);
            m = new MiscObject(new Position(m.getPosition().getxVal()+40,m.getPosition().getyVal()+80), MiscType.TREE_LARGE);
            g.insertMisc(m);

            ///
            MiscObject m2 = new MiscObject(new Position(p3.getxVal(),p3.getyVal()), MiscType.ROCK_TALL);
            g.insertMisc(m2);
            p3 = new Position(p3.getxVal()+1400,p3.getyVal()+640);
            m2 = new MiscObject(new Position(p3.getxVal(),p3.getyVal() ), MiscType.ROCK_TALL);
            g.insertMisc(m2);

            p3 = l.get(1);
            p3 = new Position(p3.getxVal(),p3.getyVal()+640);
            m2 = new MiscObject(new Position(p3.getxVal(),p3.getyVal() ), MiscType.ROCK_TALL);
            g.insertMisc(m2);
            int x = 0;
            int y = -80;
            boolean b = true;
            boolean b2 = true;
            p3 = new Position(p3.getxVal()-80,p3.getyVal()+150);
            for ( int i = 0 ; i < 100; i++) {
                if (b) {
                    p3 = new Position(p3.getxVal()+80,p3.getyVal());
                }
                else{
                    if (b2) {
                        p3 = new Position(p3.getxVal() + 40, p3.getyVal() - 40);
                        b2 = false;
                        if (b) {
                            b = false;
                        }
                        else {
                            b = true;
                        }
                    }
                    else {
                        p3 = new Position(p3.getxVal() + 40, p3.getyVal() + 40);
                        b2 = true;
                        if (b) {
                            b = false;
                        }
                        else {
                            b = true;
                        }
                    }
                }

                m2 = new MiscObject(new Position(p3.getxVal(),p3.getyVal() ), MiscType.TREE_LARGE);
                g.insertMisc(m2);

                if (b) {
                    b = false;
                }
                else {
                    b = true;
                }

                /////
            }

            p3 = l.get(1);
            p3 = new Position(p3.getxVal() + 670, p3.getyVal() + 40);
            m2 = new MiscObject(new Position(p3.getxVal()+400,p3.getyVal() ), MiscType.TREE_LARGE);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+430,p3.getyVal() ), MiscType.TREE_LARGE);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+460,p3.getyVal() ), MiscType.TREE_LARGE);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+400,p3.getyVal()-25 ), MiscType.TREE_LARGE);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+430,p3.getyVal()-45 ), MiscType.TREE_LARGE);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+460,p3.getyVal()- 74 ), MiscType.TREE_LARGE);
            g.insertMisc(m2);

            p3 = l.get(1);
            p3 = new Position(p3.getxVal() + 490, p3.getyVal() + 120);
            m2 = new MiscObject(new Position(p3.getxVal()+1260,p3.getyVal()-60 ), MiscType.ROCK_SMALL);
            g.insertMisc(m2);
            /*
            m2 = new MiscObject(new Position(p3.getxVal()+970,p3.getyVal() ), MiscType.ROCK_SMALL);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+980,p3.getyVal() ), MiscType.ROCK_SMALL);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+400,p3.getyVal()-25 ), MiscType.ROCK_SMALL);
            g.insertMisc(m2);
            m2 = new MiscObject(new Position(p3.getxVal()+430,p3.getyVal()-45 ), MiscType.ROCK_SMALL);
            g.insertMisc(m2);
               */


            return g;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Game parseGameConfig(String filename,Path path) throws ParserConfigurationException, IOException, SAXException {


        int NUMBER_OF_WAVES;


        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream is = StaticContext.getContext().getAssets().open(CONFIG_PATH + filename);
        Document doc = documentBuilder.parse(is);
        Element element = doc.getDocumentElement();
        element.normalize();

        NodeList nodes = element.getElementsByTagName("game");

        NUMBER_OF_WAVES = getNumberOfWaves(nodes);
        Log.println(Log.ASSERT,"parse", Integer.toString(NUMBER_OF_WAVES));

        nodes =  element.getElementsByTagName("wave");

        List<EnemyWave> waves = getWaves(nodes,path);

        WaveManager waveManager = new WaveManager(NUMBER_OF_WAVES,path);
        waveManager.addAll(waves);
        Game g = new Game(waveManager,path, ConfigWriter.getInstance().readHealth(),ConfigWriter.getInstance().readGold(),ConfigWriter.getInstance().readDiamonds(),0);
        return g;
    }

    private int getNumberOfWaves(NodeList lst){
        for (int i=0; i<lst.getLength(); i++) {

            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element2 = (Element) node;
                return Integer.parseInt(element2.getAttribute("num_waves"));
            }
        }
        return 0;
    }

    private List<EnemyWave> getWaves(NodeList lst, Path path){
        List<EnemyWave> l = new LinkedList<>();
        for(int i = 0; i < lst.getLength(); i++){

            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element2 = (Element) node;


                EnemyWave wave = new EnemyWave(Integer.parseInt(element2.getAttribute("num_enemies")),path, Integer.parseInt(element2.getAttribute("diamonds")));
                NodeList sub = element2.getElementsByTagName("enemy");

                for (int j = 0; j < sub.getLength(); j++){
                    Node enemyNode = sub.item(j);
                    if (enemyNode.getNodeType() == Node.ELEMENT_NODE){
                        Element enem = (Element) enemyNode;
                        int enemyCount = Integer.parseInt(enem.getAttribute("count"));
                        for(int k = 0; k < enemyCount; k++){
                            wave.addEnemy(EnemyFactory.getInstance().createEnemyByType(EnemyType.valueOf(enem.getAttribute("type"))));
                        }


                    }
                }
                l.add(wave);
            }
        }
        return l;
    }
}

package de.hda.tdpro.core.factories;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.enemy.EnemyType;
import de.hda.tdpro.core.enemy.EnemyWave;
import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.WaveManager;
import de.hda.tdpro.core.tower.TowerType;

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

    private Game parseGameConfig(String filename,Path path) throws ParserConfigurationException, IOException, SAXException {
        //documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,false);

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
        Game g = new Game(waveManager,path);
        return g;
    }
    private String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
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
            //EnemyWave wave = new EnemyWave();
            Node node = lst.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element2 = (Element) node;


                EnemyWave wave = new EnemyWave(Integer.parseInt(element2.getAttribute("num_enemies")),path);
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

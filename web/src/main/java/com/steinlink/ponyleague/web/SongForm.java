package com.steinlink.ponyleague.web;

import com.steinlink.service.UserManager;
import com.steinlink.webapp.action.BasePage;
import com.steinlink.ponyleague.core.service.InstrumentManager;
import com.steinlink.ponyleague.core.service.GeneManager;
import com.steinlink.ponyleague.core.service.SongJobManager;
import com.steinlink.ponyleague.core.service.ConfigManager;
import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.song.SongModel;
import com.steinlink.ponyleague.core.daemon.SongQueueDaemon;

import javax.faces.model.SelectItem;
import java.util.*;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 3:12:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongForm extends BasePage {

    private UserManager userManager;
    private InstrumentManager instrumentManager;
    private GeneManager geneManager;
    private SongJobManager songJobManager;
    private SongModel songModel;
    private ConfigManager configManager;
    private String style;
    private String appletHeight = "5";
    private boolean drawPlan = true;
    private boolean drawApplet = false;
    private long songID = 0;
    private String rating="nostar";
    private int ratingsHeight = 0;

    public void setConfigManager(ConfigManager manager) { this.configManager = manager; }
    public void setUserManager(UserManager manager) { this.userManager = manager; }
    public void setInstrumentManager(InstrumentManager manager) {this.instrumentManager = manager; }
    public void setGeneManager(GeneManager manager) {this.geneManager = manager; }
    public void setSongJobManager(SongJobManager manager) {this.songJobManager = manager; }

    public SongForm() {
        songModel = new SongModel();
    }

    public String init() {
        
        return "Success";
    }

    public List<SongModel.GrammarNode> getThreads() {

        if (songModel.getNodes() == null) {
            songModel.setNodes(new LinkedList<SongModel.GrammarNode>());
            songModel.getNodes().add(SongModel.createGrammarNode(songModel.getNodes()));
        }
        
        return songModel.getNodes();
    }

    public void setThreads(List<SongModel.GrammarNode> l) {
        songModel.setNodes(l);
    }

    public String getStyle() {
        return songModel.getStyle();
    }

    public void setStyle(String s) {
        songModel.setStyle(s);
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String s) {
        rating = s;
    }

    public List<SelectItem> getAllStyles() {
        List<SelectItem> list = new LinkedList<SelectItem>();
        list.add(new SelectItem("Classical"));
        list.add(new SelectItem("Country"));
        list.add(new SelectItem("Rock"));

        return list;
    }

    public List<SelectItem> getAllComplexities() {
        List<SelectItem> list = new LinkedList<SelectItem>();
        list.add(new SelectItem("Complex"));
        list.add(new SelectItem("Normal"));
        list.add(new SelectItem("Simple"));
        return list;
    }

    public List<SelectItem> getAllInstruments() {

        List<SelectItem> list = new LinkedList<SelectItem>();
        list.add(new SelectItem(" - null - "));

        List<Instrument> instruments = instrumentManager.getAll();
        for(Iterator i = instruments.iterator(); i.hasNext();) {

            Instrument ins = (Instrument)i.next();
            SelectItem item = new SelectItem(ins.getName());

            item.setValue(String.valueOf(ins.getId()));
            list.add(item);
        }

        return list;
    }

    public String getAppletHeight() {
        return appletHeight;
    }

    public boolean getDrawPlan() {
        return drawPlan;
    }

    public boolean getDrawApplet() {
        return drawApplet;
    }

    public String compose() {

        // now create a songJob
        SongJob job = new SongJob();
        job.setStatus(SongJob.STATUS_NEW);
        job.setSong(songModel);
        appletHeight="250";
        ratingsHeight=40;
        drawApplet = true;
        drawPlan = false;

        job.setUser(userManager.getUserByUsername(getRequest().getRemoteUser()));

        job = songJobManager.save(job);
        songID = job.getId();

        return "success";
    }

    public String getRatingsHeight() {
        return (ratingsHeight > 0 ? "true" : "false");
    }

    public String rate() {
        rating = "onestar";
        drawApplet = true;
        return "success";
    }

    public String ratetwo() {
        rating = "twostar";
        drawApplet = true;
        return "success";
    }

    public String ratethree() {
        rating = "threestar";
        drawApplet = true;
                return "success";
    }

    public String getSongID() {
        return Long.toString(songID);
    }

    public String ratefour() {
        rating = "fourstar";
        drawApplet = true;
                return "success";
    }

    public String ratefive() {
        rating = "fivestar";
        drawApplet = true;
                return "success";
    }
}

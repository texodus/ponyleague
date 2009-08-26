package com.steinlink.ponyleague.web;

import com.steinlink.webapp.action.BasePage;
import com.steinlink.ponyleague.core.model.*;
import com.steinlink.ponyleague.core.service.*;
import com.steinlink.ponyleague.core.song.Note;

import java.util.*;

/**
 * This is the configuration menu;  it is only accessible to an admin and allows generating default
 * database entries, checking server status & statistics, adding news items and announcements, setting
 * server wide options, and generally anything that only an admin should be able to do.
 * User: steinlink
 * Date: Apr 21, 2007
 * Time: 5:19:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongPanelForm extends BasePage {

    private int SEED_SIZE = 4;

    private String newsContent = "";
    private String subjectContent = "";
    private String complexities[] = {"Complex", "Normal", "Simple"};

    private InstrumentManager instrumentManager;
    private GeneManager geneManager;
    private JobManager jobManager;
    private StyleManager styleManager;
    private NewsManager newsManager;
    private ConfigManager configManager;

    public void setInstrumentManager(InstrumentManager manager) { instrumentManager = manager; }
    public void setGeneManager(GeneManager manager) { geneManager = manager; }
    public void setJobManager(JobManager manager) { jobManager = manager; }
    public void setStyleManager(StyleManager manager) { styleManager = manager; }
    public void setNewsManager(NewsManager manager) { newsManager = manager; }
    public void setConfigManager(ConfigManager manager) { configManager = manager; }

    public String getNewsContent() { return newsContent; }
    public void setNewsContent(String newsContent) { this.newsContent = newsContent; }

    public String getSubjectContent() { return subjectContent; }
    public void setSubjectContent(String subjectContent) { this.subjectContent = subjectContent; }

    public String addNews() {

        News news = new News();
        news.setText(newsContent.replaceAll("\n", "<br/><br/>"));
        news.setCreateDate(Calendar.getInstance().getTime());
        news.setSubject(subjectContent);

        newsManager.save(news);
        addMessage(" Successfully added news item! ");
        newsContent = "";


        return "success";
    }

    /**
     * Generate instrument records
     * @return
     */
    public String generateInstruments() {

        try {
            instrumentManager.removeAll();
        } catch (Exception e) {
            // Do nothing, the table is empty
        }

        Instrument piano = new Instrument();
        piano.setMidiValue(0);
        piano.setLowerbound(40);
        piano.setUpperbound(70);
        piano.setVelocityOffset(80);
        piano.setName("Piano");
        instrumentManager.save(piano);

        Instrument violin = new Instrument();
        violin.setMidiValue(7);
        violin.setLowerbound(40);
        violin.setUpperbound(70);
        violin.setVelocityOffset(80);
       violin.setName("Violin");
        instrumentManager.save(violin);

        Instrument bass = new Instrument();
        bass.setMidiValue(14);
        bass.setLowerbound(40);
        bass.setUpperbound(70);
        bass.setVelocityOffset(80);
        bass.setName("Bass");
        instrumentManager.save(bass);

        Instrument cello = new Instrument();
        cello.setMidiValue(9);
        cello.setLowerbound(40);
        cello.setUpperbound(70);
        cello.setVelocityOffset(80);
        cello.setName("Cello");
        instrumentManager.save(cello);

        Instrument guitar = new Instrument();
        guitar.setMidiValue(13);
        guitar.setLowerbound(40);
        guitar.setUpperbound(70);
        guitar.setVelocityOffset(80);
        guitar.setName("Guitar");
        instrumentManager.save(guitar);



        this.addMessage(" Successfully Generated Instrument Records! ");

        return "success";
    }

    /**
     *
     * @return
     */
    public String generateGenes() {

        try {
            geneManager.removeAll();
        } catch(Exception e) {
            // do nothing
        }

        // Iterate over all Instruments and null
        List<Instrument> instruments = instrumentManager.getAll();
        List<Style> styles = styleManager.getAll();

        for (int c = 0; c < 3; c ++) {
            String complexity = complexities[c];

            for (Iterator i = styles.iterator(); i.hasNext();) {
                Style style = (Style)i.next();

                double numberOfGenes = Math.pow(2, instruments.size());

                for (int j = 1; j < numberOfGenes; j ++) {

                    // get the instruments
                    Set<Instrument> currentGeneInstruments = new HashSet<Instrument>();
                    for (int k = 0; k < instruments.size(); k ++) {

                        int index = j;
                        for (int l = 0; l < k; l ++) index = index / 2;


                        if (index % 2 == 1) {
                            currentGeneInstruments.add(instruments.get(k));
                        }
                    }

                    // Generate a sequence of notes
                    for (int q = 0; q < SEED_SIZE; q ++) {

                        Random random = new Random();
                        int numberOfNotes = random.nextInt(5) + 2;

                        Note[] notes = new Note[numberOfNotes];

                        for (int k = 0; k < numberOfNotes; k ++) {

                            Note note = new Note();
                            note.setPitch(random.nextInt(5) + 1);
                            note.setRhythm(random.nextInt(4) + 1);
                            note.setVelocity(random.nextInt(4) + 1);

                            notes[k] = note;
                        }

                        Gene gene = new Gene();
                        gene.setInstruments(currentGeneInstruments);
                        gene.setStyle(style);
                        gene.setPoints(0);
                        gene.setVotes(0);
                        gene.setComplexity(complexity);
                        gene.setNotes(notes);

                        geneManager.save(gene);
                    }
                }
            }
        }
        this.addMessage(" Successfully Generated Genes! ");
        return "success";
    }

    /**
     * Generate the default styles
     * @return
     */
    public String generateStyles() {

        try {
            styleManager.removeAll();
        } catch (Exception e) {
            // Do Nothing!
        }

        Style style = new Style();
        style.setTitle("Classical");
        styleManager.save(style);

        style = new Style();
        style.setTitle("Pop");
        styleManager.save(style);

        this.addMessage(" Successfully Generated Styles! ");
        return "success";
    }

    /**
     * Generate the default Configs
     * @return
     */
    public String generateConfigs() {

        Config config = new Config();
        config.setId(Config.USER_STORAGE);
        config.setValue("TMP");
        configManager.save(config);

        config = new Config();
        config.setId(Config.IS_DEBUG);
        config.setValue("TRUE");
        configManager.save(config);


        this.addMessage(" Successfully Generated Default Config Values! ");
        return "success";
    }
}

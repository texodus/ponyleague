package com.steinlink.ponyleague.core.daemon;

import com.steinlink.ponyleague.core.composition.AudubonEngineAdapter;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.service.SongJobManager;
import com.steinlink.ponyleague.core.service.ConfigManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.spi.MidiFileWriter;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 2:14:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class SongQueueDaemon extends Daemon {

    public static final String SONG_FILE = "song.mid";

    private final Log log = LogFactory.getLog(getClass());

    public static final String NAME = "SongQueueDaemon";
    private boolean _running = true;
    private int numThreads = 1;
    private long current = 0;
    private File userStorage;
    private ServletContext servletContext;
    private ApplicationContext ctx;

    private List<CompositionEngineThread> threadPool;

    /**
     * Shutdown the daemon
     */
    public void shutdown() {

        for (int i = 0; i < threadPool.size(); i ++) threadPool.get(i).shutdown();
        _running = false;

    }

    /**
     * Initializes teh SongQueueDaemon
     * @param c
     */
    public void init(ServletContext c) {

        threadPool = new LinkedList<CompositionEngineThread>();
        servletContext = c;
        ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        // get the user storage directory
        ConfigManager manager = (ConfigManager)ctx.getBean("configManager");

        // create worker threads
        for (int i = 0; i < numThreads; i ++) {
            threadPool.add(new CompositionEngineThread());
        }
    }

    /**
     * Run method
     */
    public void run() {

        // start the compositionEngine threads
        for (int i = 0; i < numThreads; i ++) {
            threadPool.get(i).start();
        }

        while(_running) {

            // check to see if any threads are empty
            if (true) {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    _running = false;
                }
            }
        }
    }

    /**
     * Get the next song in the database for processing
     * @return
     */
    protected synchronized SongJob getNextSongJob() {

        SongJobManager manager = (SongJobManager)ctx.getBean("songJobManager");
        return manager.findTopByStatusAndIncrement(SongJob.STATUS_NEW);
    }

    /**
     * class for a worker thread
     */
    private class CompositionEngineThread extends Thread {

        protected final Log log = LogFactory.getLog(getClass());

        private boolean _innerRunning = true;
        private AudubonEngineAdapter adapter;

        /**
         * shutdown the worker thread
         */
        public void shutdown() {
            _innerRunning = false;
        }

        /**
         * getter for the genes of a songjob
         * @param job
         * @return
         */
        private List<Gene> getGenes(SongJob job) {
            return null;
        }

        /**
         * Worer thread run method; reponsible for polling for new songJobs and using the selected
         * adapter to process them into a sequence, then writing them to disk under the
         * $USER_STORAGE/{username} directory.
         */
        public void run() {

            // TODO make the adapter class configurable from the config table
            adapter = new AudubonEngineAdapter();
            while (_innerRunning) {

                SongJob job = null;

                try {

                    // get top new songjob
                    SongJobManager manager = (SongJobManager)ctx.getBean("songJobManager");
                    job = manager.findTopByStatusAndIncrement(SongJob.STATUS_NEW);

                    if (job != null) {

                        // mark this thread by job ID
                        NDC.push("[SongJob" + job.getId() + "]");
                        log.debug("Starting composition.");

                        // process the job
                        job.setMidi(adapter.compose(job.getSong(), ctx));


                        log.debug("Composition successfully completed.");
                        job.setStatus(SongJob.STATUS_DONE);
                        job = manager.save(job);

                    } else {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            _innerRunning = false;
                        }
                    }
                } catch (Exception e) {

                    SongJobManager manager = (SongJobManager)ctx.getBean("songJobManager");
                    if (job != null) {
                        job.setStatus(SongJob.STATUS_ERROR);
                        manager.save(job);
                        log.error("Processing songJob "+ job.getId() + " failed;", e);
                    } else {
                        log.error("Processing failed for unknown reason", e);
                    }
                } finally {

                    // Reset the NDC stack if the job was finished
                    if (job != null) {
                        NDC.remove();
                    }
                }
            }

        }
    }
}

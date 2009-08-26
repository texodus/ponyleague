package com.steinlink.ponyleague.core;

import com.steinlink.ponyleague.core.daemon.Daemon;
import com.steinlink.ponyleague.core.daemon.SongQueueDaemon;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 10, 2007
 * Time: 1:02:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class DaemonManager implements ServletContextListener {

    private final Log log = LogFactory.getLog(getClass());

    private static final String NAME = "DaemonManger";
    private DaemonManagerControllerThread thread;
    private ServletContext servletContext;

    /**
     * When the ServletContext is started, we need to start the threadpool
     * @param event
     */
    public void contextInitialized(ServletContextEvent event) {

        try {
            servletContext = event.getServletContext();
            servletContext.setAttribute(DaemonManager.NAME, this);

            thread = new DaemonManagerControllerThread();
            thread.start();
        } catch (Exception e) {
            log.error("Daemon Manager failed to initialize.", e);
        }

        log.info("Daemon Manager started successfully.");
    }

    public void contextDestroyed(ServletContextEvent event) {

        try {
            thread.shutdown();
        } catch (Exception e) {
            log.error("Daemon Manager shutdown failed.", e);
        }
       log.info("Daemon Manager shutdown successfully.");
    }

    /**
     * Main DaemonManager Controller thread, from which all other daemon threads are created
     */
    private class DaemonManagerControllerThread extends Thread {

        private boolean _running = true;
        private List<Daemon> threadPool;

        public void shutdown() {
            _running = false;
        }

        public void run() {

            //initialize the threadPool
            threadPool = new ArrayList<Daemon>();

            // TODO get a list of Daemons to start and add them to the ThreadPool
            threadPool.add(new SongQueueDaemon());
            // TODO start them
            threadPool.get(0).init(servletContext);
            threadPool.get(0).start();


            // application loop
            while(_running) {
                // just sit here until the thread shuts down
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    _running = false;
                }
            }

            // shutdown/stop all of the Daemons
            for (Iterator i = threadPool.iterator(); i.hasNext();) {
                try {
                    ((Daemon)i.next()).shutdown();
                } catch (Exception e) {
                    
                }
            }
        }
    }
}

package com.steinlink.ponyleague.core.filter;

import org.springframework.web.context.support.WebApplicationContextUtils;
import com.steinlink.service.UserManager;
import com.steinlink.model.User;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.*;

import com.steinlink.ponyleague.core.service.ConfigManager;
import com.steinlink.ponyleague.core.service.SongJobManager;
import com.steinlink.ponyleague.core.util.Database;
import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.ponyleague.core.composition.AudubonEngineAdapter;
import com.steinlink.ponyleague.core.daemon.SongQueueDaemon;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Aug 19, 2007
 * Time: 6:03:09 PM
 * To change this template use File | Settings | File Templates.
 *
 * Filter to server midi files to the client
 */
public class SongServerFilter implements Filter {

    private String tempDirectory;
    protected final Log log = LogFactory.getLog(getClass());

    private UserManager userManager;
    private SongJobManager songJobManager;
    private boolean isDebug = true;

    /**
     * Initialize the SongServerFilter
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {

        userManager = (UserManager)Database.getManager("userManager", filterConfig.getServletContext());
        songJobManager = (SongJobManager)Database.getManager("songJobManager", filterConfig.getServletContext());

        // Set up the temp directory
        ConfigManager manager = (ConfigManager)Database.getManager("configManager", filterConfig.getServletContext());
        tempDirectory = manager.getConfig(Config.USER_STORAGE);
        
        String debug = manager.getConfig(Config.IS_DEBUG);
        isDebug = debug.equals("TRUE");
    }

    /**
     * If the midi file has been written, serve it raw & juicy by injecting it into the response's output
     * stream.  If not, return a null so the applet knows that the file isn't ready.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        User user = null;

        // Because the filter needs the user to find a song, you cannot debug requests from
        // the applet running in debug mode;  to solve this, the program automatically returns the
        // file from the default admin when debug mode is on.  This can cause problems if the default
        // admin has no file!
        if (isDebug) {
            user = userManager.getUserByUsername("admin");
        } else {
            user = userManager.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        String songID = request.getParameter("songID");

        log.debug("User " + user.getUsername() + " polled for song " + songID);

        SongJob job = songJobManager.get(Long.parseLong(songID));

        if (job.getMidi() != null) {

            // bingo!  read the file from disk and inject dat shit, yo!
            Sequence sequence;
            try {
                MidiSystem.write(MidiSystem.getSequence(new ByteArrayInputStream(job.getMidi())), 1, response.getOutputStream());
                response.getOutputStream().close();
            } catch (Exception e) {
                log.error("Error reading file from disk");
            }
        }
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

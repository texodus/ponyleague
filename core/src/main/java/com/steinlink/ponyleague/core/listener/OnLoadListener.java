package com.steinlink.ponyleague.core.listener;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Aug 28, 2007
 * Time: 10:24:46 PM
 * To change this template use File | Settings | File Templates.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

/**
 * @author andrew
 *
 */
public class OnLoadListener
        implements PhaseListener
{
        private static Logger logger = Logger.getLogger(OnLoadListener.class);
        private Map<String, NavigationRule> config;

        /**
         * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
         */
        public void afterPhase(PhaseEvent evt)
        {
                FacesContext ctx = evt.getFacesContext();
                NavigationRule rule;

                initialize();

                // if this is an ajax4jsf call, ignore it
                if (ctx.getViewRoot() == null) return;
                rule = this.config.get(ctx.getViewRoot().getViewId());


                if (rule == null)
                {
                        logger.debug("No rule found for view " + ctx.getViewRoot().getViewId());
                        return;
                }

                MethodBinding method = ctx.getApplication().createMethodBinding(rule.getAction(), new Class[0]);
                logger.debug("Invoking action: " + rule.getAction());
                Object result = method.invoke(ctx, new Object[0]);

                if (result != null && (result instanceof String))
                {
                        if (result.equals(rule.successResult) == false)
                        {
                                logger.debug("Calling navigation handler with result: " + result);
                                ctx.getApplication().getNavigationHandler().handleNavigation(ctx, rule.getAction(), "Failed");
                        }
                }
        }

        /**
         * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
         */
        public void beforePhase(PhaseEvent evt) {}

        /**
         * @see javax.faces.event.PhaseListener#getPhaseId()
         */
        public PhaseId getPhaseId()
        {
                return PhaseId.RESTORE_VIEW;
        }

        private void initialize()
        {
                if (this.config != null) return;

                config = new HashMap<String, NavigationRule>();

                try
                {
                        logger.info("On Load configuration is being loaded...");
                        InputStream configStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(
                                "/WEB-INF/onload-config.xml");
                        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
                        docBuilder.setEntityResolver(new DtdEntityResolver());
                        Document doc = docBuilder.parse(configStream);
                        XPath xpath = XPathFactory.newInstance().newXPath();

                        NodeList rules = doc.getDocumentElement().getElementsByTagName("navigation-rule");
                        logger.info(rules.getLength() + " rule(s) have been found");

                        for (int i = 0; i < rules.getLength(); i++)
                        {
                                NavigationRule rule;
                                Element node = (Element)rules.item(i);

                                rule = new NavigationRule(
                                        (String)xpath.evaluate("view-id", node, XPathConstants.STRING),
                                        (String)xpath.evaluate("action", node, XPathConstants.STRING),
                                        (String)xpath.evaluate("success-result", node, XPathConstants.STRING));

                                config.put(rule.getVewId(), rule);
                                logger.debug("Rule: " + rule);
                        }
                }
                catch (Exception ex)
                {
                        logger.error("Failed to parse onload configuration", ex);
                }
        }

        private class NavigationRule
        {
                private String action;
                private String vewId;
                private String successResult;

                NavigationRule(String viewId, String action, String successResult)
                {
                        this.vewId = viewId;
                        this.action = action;
                        this.successResult = successResult;
                }

                /**
                 * @return Returns the successResult.
                 */
                String getSuccessResult()
                {
                        return this.successResult;
                }

                /**
                 * @return Returns the action.
                 */
                String getAction()
                {
                        return this.action;
                }

                /**
                 * @return Returns the fromViewId.
                 */
                String getVewId()
                {
                        return this.vewId;
                }

                @Override
                public String toString()
                {
                        return String.format("view-id: %s, action: %s, success-result: %s",
                                new Object[] { this.vewId, this.action, this.successResult });
                }
        }

        private class DtdEntityResolver
                implements EntityResolver
        {
                private static final String ONLOAD_PUBLIC_ID = "-//mysite//onload 1.0//EN";

                /**
                 * @see org.xml.sax.EntityResolver#resolveEntity(java.lang.String, java.lang.String)
                 */
                public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
                {
                        if (publicId.equals(ONLOAD_PUBLIC_ID))
                        {
                                FacesContext ctx = FacesContext.getCurrentInstance();
                                return new InputSource(ctx.getExternalContext().getResourceAsStream("/WEB-INF/onload-config.dtd"));
                        }
                        return null;
                }

        }
}

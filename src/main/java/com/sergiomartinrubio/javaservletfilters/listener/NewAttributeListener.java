package com.sergiomartinrubio.javaservletfilters.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener("Checks for new attributes during request")
public class NewAttributeListener implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
        log.info("The attribute \"" + servletRequestAttributeEvent.getName() + "\" with value \""
                + servletRequestAttributeEvent.getValue() + "\" was added.");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }
}

package com.soundhive.gui;

import com.soundhive.core.authentication.SessionHandler;
import com.soundhive.core.authentication.UserProfileConsumer;
import com.soundhive.core.conf.ConfHandler;
import com.soundhive.core.conf.ConfigFileException;
import com.soundhive.core.conf.MissingParamException;
import com.soundhive.gui.plugin.PluginUIContainer;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private boolean verbose;
    private SessionHandler session;
    private final Router router;
    private final ConfHandler conf;

    private List<PluginUIContainer> plugins;

    private final UserProfileConsumer profileLoader;

    public Context(final Router router, final UserProfileConsumer profileLoader)  throws ConfigFileException, MissingParamException{
        this.plugins = new ArrayList<>();
        this.conf = new ConfHandler();
        this.profileLoader = profileLoader;

        this.router = router;
        initSession();

        initVerbose();

    }

    private void initSession() throws MissingParamException{
        String tokenDirectory = conf.getParam("token_directory");


        this.session = new SessionHandler(tokenDirectory, this.profileLoader);
    }

    private void initVerbose() {
        // verbose is an optional parameter
        try {
            this.verbose = conf.getParam("verbose").equals("true");
        } catch (MissingParamException e){
            this.verbose = false;
        }
    }

    public SessionHandler getSession() {
        return session;
    }

    public Router getRouter() {
        return router;
    }

    public ConfHandler getConf() {
        return conf;
    }

    public boolean Verbose() {
        return verbose;
    }

    public List<PluginUIContainer> getPlugins() {
        return plugins;
    }

    public void setPlugin(final PluginUIContainer plugin) {
        this.plugins.add(plugin);
    }

    public void deletePlugin(final PluginUIContainer plugin) {
        plugin.delete();
        this.plugins.remove(plugin);
    }

    public void log(final String message) {
        if (this.Verbose()) {
            System.out.println(message);
        }
    }

    public void logException(final Throwable e) {
        if (this.Verbose()) {
            e.printStackTrace();
        }
    }
}

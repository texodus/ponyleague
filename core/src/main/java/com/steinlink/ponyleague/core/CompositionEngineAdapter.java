package com.steinlink.ponyleague.core;

import com.steinlink.ponyleague.core.song.SongModel;

import javax.sound.midi.Sequence;

import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 15, 2007
 * Time: 3:42:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CompositionEngineAdapter {

    public Sequence compose(SongModel songModel, ApplicationContext ctx) throws Exception ;
}

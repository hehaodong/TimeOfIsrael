package com.andy.timeofisrael.event;

import com.andy.timeofisrael.bean.Langs;

/**
 * Created by lancelot on 2016/12/17.
 */

public class LangEditedEvent {
    private Langs langs;

    public LangEditedEvent(Langs langs) {
        this.langs = langs;
    }

    public Langs getLangs() {
        return langs;
    }
}

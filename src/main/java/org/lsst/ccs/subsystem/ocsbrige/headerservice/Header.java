package org.lsst.ccs.subsystem.ocsbrige.headerservice;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author tonyj
 */
public class Header {
    private final String keyword;
    private final String value;
    private final String comment;

    @JsonCreator
    public Header(@JsonProperty("keyword") String keyword, @JsonProperty("value")  String value, @JsonProperty("comment") String comment) {
        this.keyword = keyword;
        this.value = value;
        this.comment = comment;
    }
    
    public String getKeyword() {
        return keyword;
    }

    public String getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Header{" + "keyword=" + keyword + ", value=" + value + ", comment=" + comment + '}';
    }
}

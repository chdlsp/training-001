package com.rasol.training001.model.dto.querybook.naver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss {
    @XmlElement(name = "channel")
    private NaverBooks naverBooks;

    public NaverBooks getNaverBooks() {
        return naverBooks;
    }

    public Rss setNaverBooks(NaverBooks naverBooks) {
        this.naverBooks = naverBooks;
        return this;
    }
}

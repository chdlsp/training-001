package com.rasol.training001.model.dto.querybook.naver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class NaverBooks {
    @XmlElement(name="item")
    private List<NaverBook> books = new ArrayList<>();

    public List<NaverBook> getBooks() {
        return books;
    }

    public NaverBooks setBooks(List<NaverBook> naverBooksList) {
        this.books = naverBooksList;
        return this;
    }
}

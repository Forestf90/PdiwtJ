package com.gallery;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ImagesView {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            images.add("nature" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}

package com.micro.jfxexe.domain;

import javafx.scene.control.Label;

/**
 * @author XiongJiaMin
 * @apiNote 标签
 * @since 2022-12-19 17:21
 **/
public class LabelF extends Label {

    /**
     * @since 2023/4/18 16:44
     * @description <p>
     *  标签ID
     * </p>
     */
    private String labelId;

    /**
     * @since 2023/4/18 16:44
     * @description <p>
     *  标签名
     * </p>
     */
    private String labelName;

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public LabelF() {}

    public LabelF(String text) {
        super(text);
    }

    public boolean equals (LabelF var1) {
        return this.getLabelId().equals(var1.getLabelId()) || this.getLabelName().equals(var1.getLabelName());
    }
}

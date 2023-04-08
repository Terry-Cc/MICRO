package com.micro.jfxexe.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author XiongJiaMin
 * @apiNote 根
 * @since 2022-11-21 14:33
 **/
@SuppressWarnings("unused")
public class NoteBase implements Serializable {

    private static final long serialVersionUID = 5005060712083821412L;

    private LocalDateTime createdDate;
    
    private LocalDateTime updatedDate;

    public NoteBase() {}

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public NoteBase(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}

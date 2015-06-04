package com.soowoowon.viewModel;

import com.soowoowon.model.Enum.ReservationState;

/**
 * Created by jazzbach on 15. 3. 29.
 */
public class ReservationResponseForUser implements ReservationResponse {
    private int id;
    private String title;
    private String content;
    private String date;
    private String published;
    private ReservationState state;
    private byte part;
    private int adult;
    private int child;
    private int userId;
    private String displayName;
    private byte role;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getPublished() {
        return published;
    }

    @Override
    public void setPublished(String published) {
        this.published = published;
    }

    @Override
    public ReservationState getState() {
        return state;
    }

    @Override
    public void setState(ReservationState state) {
        this.state = state;
    }

    @Override
    public byte getPart() {
        return part;
    }

    @Override
    public void setPart(byte part) {
        this.part = part;
    }

    @Override
    public int getAdult() {
        return adult;
    }

    @Override
    public void setAdult(int adult) {
        this.adult = adult;
    }

    @Override
    public int getChild() {
        return child;
    }

    @Override
    public void setChild(int child) {
        this.child = child;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public byte getRole() {
        return role;
    }

    @Override
    public void setRole(byte role) {
        this.role = role;
    }
}

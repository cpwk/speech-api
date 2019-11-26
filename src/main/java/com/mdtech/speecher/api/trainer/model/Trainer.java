package com.mdtech.speecher.api.trainer.model;

import javax.persistence.*;

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pc_img")
    private String pcImg;

    @Column(name = "mob_img")
    private String mobImg;

    @Column(name = "intro")
    private String intro;

    @Column(name = "type")
    private Byte type;

    @Column(name = "status")
    private Byte status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "master_piece")
    private String masterPiece;

    @Transient
    private String newpassword;

    public Trainer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcImg() {
        return pcImg;
    }

    public void setPcImg(String pcImg) {
        this.pcImg = pcImg;
    }

    public String getMobImg() {
        return mobImg;
    }

    public void setMobImg(String mobImg) {
        this.mobImg = mobImg;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getMasterPiece() {
        return masterPiece;
    }

    public void setMasterPiece(String masterPiece) {
        this.masterPiece = masterPiece;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}

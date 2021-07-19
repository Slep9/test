package com.example.marvel.data.marvel;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.marvel.bd.ConverterComics;
import com.example.marvel.bd.ConverterEvents;
import com.example.marvel.bd.ConverterSeries;
import com.example.marvel.bd.ConverterStories;
import com.example.marvel.bd.ConverterThumbnail;
import com.example.marvel.bd.ConverterUrl;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;
@Entity(tableName = "result", indices = @Index(value = {"name"}, unique = true))
public class Result {
    @PrimaryKey (autoGenerate = true)
    @NotNull
    private Long idPrimaryKey;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    @TypeConverters(ConverterUrl.class)
    private List<Url> urls = null;
    @SerializedName("thumbnail")
    @Expose
    @TypeConverters(ConverterThumbnail.class)
    private Thumbnail thumbnail;
    @SerializedName("comics")
    @Expose
    @TypeConverters(ConverterComics.class)
    private Comics comics;
    @SerializedName("stories")
    @Expose
    @TypeConverters(ConverterStories.class)
    private Stories stories;
    @SerializedName("events")
    @Expose
    @TypeConverters(ConverterEvents.class)
    private Events events;
    @SerializedName("series")
    @Expose
    @TypeConverters(ConverterSeries.class)
    private Series series;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public Stories getStories() {
        return stories;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @NotNull
    public Long getIdPrimaryKey() {
        return idPrimaryKey;
    }

    public void setIdPrimaryKey(@NotNull Long idPrimaryKey) {
        this.idPrimaryKey = idPrimaryKey;
    }

}
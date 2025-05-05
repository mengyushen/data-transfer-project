package org.datatransferproject.types.common.models.motions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Objects;
import org.datatransferproject.types.transfer.models.ItemResource;
import org.datatransferproject.types.common.models.photos.PhotoModel;
import org.datatransferproject.types.common.models.videos.VideoModel;

@JsonTypeName("MotionContainerResource")
public class MotionItemResource extends ItemResource {
  private final PhotoModel photo;
  private final VideoModel paired_video;

  @JsonCreator
  public MotionItemResource(
      @JsonProperty("photo") PhotoModel photo,
      @JsonProperty("paired_video") VideoModel video) {
    if (photo == null || video == null) {
      throw new IllegalArgumentException("photo and paired video must be set.");
    }
    this.photo = photo;
    this.paired_video = video;
  }

  public PhotoModel getPhoto() {
    return photo;
  }

  public VideoModel getPairedVideo() {
    return paired_video;
  }

  public Map<String, Integer> getCounts() {
  return new ImmutableMap.Builder<String, Integer>()
    .put("photo", getPhoto() == null ? 0 : 1)
    .put("video", getPairedVideo() == null ? 0 : 1)
    .build();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("photo", getPhoto())
        .add("video", getPairedVideo())
        .toString();
  }

  @Override
  public boolean equals(Object o) {
  if (this == o) {
    return true;
  }
  if (o == null || getClass() != o.getClass()) {
    return false;
  }

  MotionItemResource that = (MotionItemResource) o;
  return Objects.equals(getPhoto(), that.getPhoto()) &&
    Objects.equals(getPairedVideo(), that.getPairedVideo());
  }

}

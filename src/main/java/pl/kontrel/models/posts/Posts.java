package pl.kontrel.models.posts;

import java.util.List;

public class Posts {
  private List<Post> posts;
  private int total;
  private int skip;
  private int limit;

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getSkip() {
    return skip;
  }

  public void setSkip(int skip) {
    this.skip = skip;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }
}

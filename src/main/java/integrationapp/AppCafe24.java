package integrationapp;

import java.util.Objects;

public class AppCafe24 {

  private String id;

  public AppCafe24() {}

  public AppCafe24(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppCafe24 appCafe24 = (AppCafe24) o;
    return Objects.equals(id, appCafe24.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

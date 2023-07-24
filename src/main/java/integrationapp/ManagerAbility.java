package integrationapp;

import java.util.Objects;

public class ManagerAbility {

  private String id;

  public ManagerAbility() {}

  public ManagerAbility(String id) {
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
    ManagerAbility that = (ManagerAbility) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

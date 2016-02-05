package co.ninetynine.android.exercise.model;

import com.google.gson.JsonPrimitive;
import java.util.ArrayList;

/**
 * Created by suresh on 27/11/15.
 */
public class RowCheckbox extends Row<Boolean> {

  @Override public String getValueForDisplay() {
    return null;
  }

  @Override public void saveChosenValue(Boolean valueToSave) throws ValidationException {
    value = new JsonPrimitive(valueToSave);
  }
}